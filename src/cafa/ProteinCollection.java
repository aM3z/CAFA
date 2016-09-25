/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafa;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a collection of Protein instances from a common FASTA formatted 
 * file.
 *
 * @author Miguel Amezola <amezolma@plu.edu>
 */
public class ProteinCollection {

    // loadFile method requires exactly two states for file reading
    private final boolean INSIDE_SEQUENCE = true;
    private final boolean OUTSIDE_SEQUENCE = false;
    // protein sequence list
    private ArrayList<Protein> proteins;
    // length of longest sequence
    private Protein longest;
    // length of shortest sequence
    private Protein shortest;

    public ProteinCollection(String filePath) {

        if (!filePath.endsWith(".fasta")) {
            throw new IllegalArgumentException(filePath + " is not a .fasta file");
        }
        File f = new File(filePath);
        this.longest = null;
        this.shortest = null;
        this.proteins = this.loadFile(f);
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

}
