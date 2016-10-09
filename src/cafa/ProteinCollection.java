/*
 * The MIT License
 *
 * Copyright 2016 Miguel Amezola <amezolma@plu.edu>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package cafa;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Represents a collection of proteins. Protein instances originate from a 
 * common file in FASTA format.
 *
 * @author Miguel Amezola <amezolma@plu.edu>
 */
public class ProteinCollection {

    // FASTA codes
    private final char[] CODES = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'Y', 'Z', 'X', '*', '-'};
    // loadFile method requires exactly two states for file reading
    private final boolean INSIDE_SEQUENCE = true;
    private final boolean OUTSIDE_SEQUENCE = false;
    // protein sequence list
    private ArrayList<Protein> proteins;
    // protein with the longest sequence
    private Protein longest;
    // protein with the shortest sequence
    private Protein shortest;
    // frequency of each amino acid
    private int[] firstOrderFreqs;
    
    /**
     * Creates a new collection of proteins.
     * 
     * @param filePath file path to data file in FASTA format
     */
    public ProteinCollection(String filePath) {

        if (!filePath.endsWith(".fasta")) {
            throw new IllegalArgumentException(filePath + " is not a .fasta file");
        }
        File f = new File(filePath);
        this.longest = null;
        this.shortest = null;
        this.firstOrderFreqs = new int[this.CODES.length];
        this.proteins = this.loadFile(f);
                
        // for(int i = 0; i < this.CODES.length; i++)
        //    this.firstOrderFreqs[i] = 0;
    }

    /**
     * Return an ArrayList of the Protein instances in this collection.
     *
     * @return an ArrayList of the Protein instances in this collection
     */
    public ArrayList<Protein> getProteins() {
        return proteins;
    }

    /**
     * Return the Protein with the longest sequence in this collection.
     *
     * @return Protein with the longest sequence in this collection
     */
    public Protein getLongest() {
        return longest;
    }
    
    /**
     * Return the Protein with the shortest sequence in this collection.
     *
     * @return Protein with the shortest sequence in this collection
     */
    public Protein getShortest() {
        return shortest;
    }
    
    public double [] firstOrderProbabilites() {
        
        double [] probabilities = new double[this.firstOrderFreqs.length];

        long totalFreqs = 0;
        
        for(int i = 0; i < this.firstOrderFreqs.length; i++)
            totalFreqs += this.firstOrderFreqs[i];
        
        for(int i = 0; i < this.firstOrderFreqs.length; i++)
            probabilities[i] = (double) this.firstOrderFreqs[i] / totalFreqs;
        
        return probabilities;
    }
    
    /**
     * Convert a FASTA formatted file to a collections of Protein instances.
     *
     * @param f - FASTA formatted file
     * @return - ArrayList<String> containing protein sequences
     */
    private ArrayList<Protein> loadFile(File f) {

        // protein sequences container
        ArrayList<Protein> proteinList;
        // new protein
        Protein newProtein;
        // file reader
        Scanner fileReader;
        // stores new line read
        String newline;
        // stores a protein accession
        String accession;
        // stores a protein sequence
        String sequence;
        // stores current state of reader
        boolean state;

        // protein list is an arraylist
        proteinList = new ArrayList<>();
        // instantiate accession to empty string to facilitate file reading
        accession = "";
        // instantiate sequence to empty string to facilitate file reading
        sequence = "";
        // reader is initial not in a sequence
        state = OUTSIDE_SEQUENCE;

        // try to read file
        try {
            // open scanner as file reader
            fileReader = new Scanner(f);
            // read entire file line by line
            while (fileReader.hasNextLine()) {
                // read a new line
                newline = fileReader.nextLine();
                // if new line is empty
                if (newline.length() == 0) {
                    // change state to outside sequence
                    state = OUTSIDE_SEQUENCE;
                    // create new protein
                    newProtein = new Protein(accession, sequence);
                    // update extrema
                    this.updateExtrema(newProtein);
                    // add current sequence to protein list
                    proteinList.add(newProtein);
                    // update amino acid freqs
                    this.computeFirstOrderFreq(newProtein);
                // if new line starts with greater-than symbol
                } else if (newline.charAt(0) == '>') {
                    // change state to inside sequence
                    state = INSIDE_SEQUENCE;
                    // let accession be the new line
                    accession = newline.substring(1);
                    // reset sequence to empty string
                    sequence = "";
                // if fileReader is currently inside a sequence
                } else if (state) {
                    // add new line to current sequence
                    sequence += newline;
                } else {
                    throw new Exception(f.getName() + " is not formatted appropriately");
                }
            }
            
            System.out.println(Arrays.toString(this.firstOrderFreqs));
            
            int sum = 0;
            
            for(int i = 0; i < this.firstOrderFreqs.length; i++)
                sum += this.firstOrderFreqs[i];
            
            System.out.println(sum);

            // close file reader
            fileReader.close();
            // return protein list
            return proteinList;

        } catch (Exception e) {
            // if error reading file
            System.out.println(e.getMessage());
        }
        // return null by default
        return null;
    }

    /**
     * This method find the protein with the shortest sequence and the protein
     * with the longest sequence in this collection.
     *
     * @param p new protein
     */
    private void updateExtrema(Protein p) {
        // if there is no shortest protein yet 
        if (this.shortest == null) {
            // let p be the shortest protein
            this.shortest = p;
            // if p sequence is shorter than shortest sequence
        } else if (p.getSequence().length() < this.shortest.getSequence().length()) {
            // let p be the shortest protein
            this.shortest = p;
        }
        // if there is no longest protein yet
        if (this.longest == null) {
            // let p be the longest protein
            this.longest = p;
            // if p sequence is longer than longer sequence            
        } else if (p.getSequence().length() > this.longest.getSequence().length()) {
            // let p be the longest protein
            this.longest = p;
        }

    }

    private void computeFirstOrderFreq(Protein p) {

        String s;
        char c;

        s = p.getSequence();

        for (int i = 0; i < s.length(); i++) {
            
            c = s.charAt(i);
            
            for(int j = 0; j < this.CODES.length; j++) {
                if(c == this.CODES[j]) {
                    this.firstOrderFreqs[j] += 1;
                }
            }
        }

    }

}
 