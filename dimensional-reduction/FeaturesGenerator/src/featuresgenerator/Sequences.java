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
package featuresgenerator;

import cern.colt.matrix.impl.SparseDoubleMatrix2D;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a collection of protein sequences.
 * 
 * @author Miguel Amezola <amezolma@plu.edu>
 */
public class Sequences {
    
    @SuppressWarnings("FieldMayBeFinal")    
    // loadFile method requires two states for file reading
    private final boolean INSIDE_SEQUENCE = true;
    private final boolean OUTSIDE_SEQUENCE = false;
    
    private SparseDoubleMatrix2D sequenceMatrix;

    /**
     * Creates an instance of the Sequences class.
     * 
     * @param pathToFastaFile relative path to FASTA file containing sequences 
     */
    public Sequences(String pathToFastaFile) {
        int rows, cols;
        File fastaFile;
        int [] dims;
        // instantiate fasta file
        fastaFile = new File(pathToFastaFile);
        
        dims = this.getDimensions(fastaFile);
        
        rows = dims[0];
        
        if(dims[1] % 2 == 0)
            cols = dims[1] / 2;
        else
            cols = dims[1] / 2 + 1;
        
        if(rows * cols > Integer.MAX_VALUE)
            throw new IllegalArgumentException("FASTA file too large");
                
        this.sequenceMatrix = new SparseDoubleMatrix2D(rows, cols);
        
        try {
            this.loadSequences(fastaFile);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public SparseDoubleMatrix2D getSequenceMatrix() {
        return sequenceMatrix;
    }

    private int[] getDimensions(File f) {
        int [] result;
        int numberOfSequences;
        int maxSequenceLength;
        Scanner fileReader;
        String sequence;
        String newline;
        boolean state;
        
        result = new int[2];
        numberOfSequences = 0;
        maxSequenceLength = 0;
        sequence = "";
        // instantiate state of reader
        state = OUTSIDE_SEQUENCE;

        // try to read file
        try {
            // open file reader
            fileReader = new Scanner(f);
            // read entire file line by line
            while (fileReader.hasNextLine()) {
                // read a new line
                newline = fileReader.nextLine();
                // if new line is empty
                if (newline.length() == 0) {
                    // change state of reader
                    state = OUTSIDE_SEQUENCE;
                    // update max
                    if(sequence.length() > maxSequenceLength)
                        maxSequenceLength = sequence.length();
                    
                    numberOfSequences++;
                } else if (newline.charAt(0) == '>') {
                    // change state of reader
                    state = INSIDE_SEQUENCE;                    
                    // reset sequence
                    sequence = "";
                    // if fileReader is currently inside a sequence
                } else if (state) {
                    // append new line to current sequence
                    sequence += newline;
                } else {
                    throw new Exception(f.getName() + " is not formatted appropriately");
                }
            }
            
            // close file reader
            fileReader.close();
            // return protein stack
            result[0] = numberOfSequences;
            result[1] = maxSequenceLength;
            
            return result;

        } catch (Exception e) {
            // if error reading file
            System.out.println("holla " + e.getMessage());
        }
        
        return null;
    }

    private void loadSequences(File f) throws Exception {
        
        if(this.sequenceMatrix == null)
            throw new Exception("sequenceMatrix not initialized");

        Scanner fileReader;
        String sequence;
        String newline;
        boolean state;
        int row;
        double leftCode;
        double rightCode;
        
        // FASTA codes
        Map<Character, Integer> codes;
        
        sequence = "";
        // instantiate state of reader
        state = OUTSIDE_SEQUENCE;
        row = 0;

        codes = new HashMap<>();
        codes.put('A', 2);
        codes.put('B', 3);
        codes.put('C', 5);
        codes.put('D', 7);
        codes.put('E', 11);
        codes.put('F', 13);
        codes.put('G', 17);
        codes.put('H', 19);
        codes.put('I', 23);
        codes.put('J', 29);
        codes.put('K', 31);
        codes.put('L', 37);
        codes.put('M', 41);
        codes.put('N', 43);
        codes.put('O', 47);
        codes.put('P', 53);
        codes.put('Q', 59);
        codes.put('R', 61);
        codes.put('S', 67);
        codes.put('T', 71);
        codes.put('U', 73);
        codes.put('V', 79);
        codes.put('W', 83);
        codes.put('Y', 89);        
        codes.put('Z', 97);
        codes.put('X', 101);        
        codes.put('*', 103); 
        codes.put('-', 107);        
        // try to read file
        try {
            // open file reader
            fileReader = new Scanner(f);
            // read entire file line by line
            while (fileReader.hasNextLine()) {
                // read a new line
                newline = fileReader.nextLine();

                // if new line is empty
                if (newline.length() == 0) {
                    // change state of reader
                    state = OUTSIDE_SEQUENCE;
                    
                    // System.out.println(row + "\t" + sequence + "\n");
                    for(int i = 0; i < sequence.length(); i += 2) {
                        leftCode = (double) codes.get(sequence.charAt(i));
                        if(i + 1 < sequence.length()) {
                            rightCode = (double) codes.get(sequence.charAt(i + 1));
                            rightCode = rightCode * rightCode;
                        } else {
                            rightCode = 1;
                        }
                        // System.out.println(i / 2 + ". " + leftCode + "\t" + rightCode);
                        this.sequenceMatrix.set(row, i / 2, leftCode * rightCode);
                    }

                    row++;
                // if new line starts with greater-than symbol
                } else if (newline.charAt(0) == '>') {
                    // change state of reader
                    state = INSIDE_SEQUENCE;                    
                    // reset sequence
                    sequence = "";
                // if fileReader is currently inside a sequence
                } else if (state) {
                    // append new line to current sequence
                    sequence += newline;
                } else {
                    throw new Exception(f.getName() + " is not formatted appropriately");
                }
            }
            
            // close file reader
            fileReader.close();

        } catch (Exception e) {
            // if error reading file
            System.out.println(e.getMessage());
        }
        
    }
        
}
