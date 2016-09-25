/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafa;

/**
 *
 * @author Miguel Amezola <amezolma@plu.edu>
 */
public class Protein {
    // UniProtKB Accession
    private String accession;
    // Sequence
    private String sequence;

    public Protein(String accession, String sequence) {
        this.accession = accession;
        this.sequence = sequence;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return ">" + accession + "\n" + sequence + "\n";
    }
    
}
