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

import cern.colt.matrix.DoubleMatrix2D;

/**
 *
 * @author Miguel Amezola <amezolma@plu.edu>
 */
public class FeaturesGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String pathToFastaFile = args[0];
        int pcaType = Integer.parseInt(args[1]);
        
        Sequences sequences = new Sequences(pathToFastaFile);
        
        DoubleMatrix2D matrix = sequences.getSequenceMatrix();
        int nrows = matrix.rows();
        int ncols = matrix.columns();
        
        System.out.println("FASTA file with filepath " 
                + pathToFastaFile 
                + " has been converted into a Sequence matrix with "
                + nrows + " rows and "
                + ncols + " columns."
        );
        
        PrincipalComponents pca = new PrincipalComponents(matrix, pcaType);
        
        DoubleMatrix2D components = pca.getPrincipalComponents();
        
        System.out.println("Principle components:\n" + components);
        
    }
    
}
