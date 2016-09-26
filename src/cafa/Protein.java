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

/**
 * Represents a protein.
 * 
 * @author Miguel Amezola <amezolma@plu.edu>
 */
public class Protein {
    
    // UniProtKB Accession
    private String accession;
    // Sequence
    private String sequence;
    
    /**
     * Creates a new protein.
     * 
     * @param accession UniProtKB Accession
     * @param sequence Sequence of amino acids in this protein
     */
    public Protein(String accession, String sequence) {
        this.accession = accession;
        this.sequence = sequence;
    }
    
    /**
     * Return accession.
     * 
     * @return accession
     */
    public String getAccession() {
        return accession;
    }
    
    /**
     * Set accession.
     * 
     * @param accession new accession
     */
    public void setAccession(String accession) {
        this.accession = accession;
    }
    
    /**
     * Return sequence.
     * 
     * @return sequemce
     */
    public String getSequence() {
        return sequence;
    }
    
    /**
     * Set sequence.
     * 
     * @param sequence new sequence
     */
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
    
    /**
     * Represents this protein as a String in FASTA format.
     * 
     * @return String in FASTA format
     */
    @Override
    public String toString() {
        return ">" + accession + "\n" + sequence + "\n";
    }    
}
