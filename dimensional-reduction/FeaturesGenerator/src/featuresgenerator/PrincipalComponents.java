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

import cern.colt.matrix.DoubleFactory1D;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.linalg.SingularValueDecomposition;
import cern.jet.math.Functions;

/**
 *
 * @author Miguel Amezola <amezolma@plu.edu>
 */
public class PrincipalComponents {

    public static final int TYPE_CENTER = 1;
    public static final int TYPE_CENTER_AND_SCALE = 2;
    public static final int TYPE_NONE = 3;
    
    private DoubleMatrix2D sequences;
    private SingularValueDecomposition svd;
    private boolean transposed;

    public PrincipalComponents(DoubleMatrix2D sequences, int type) {
        this.sequences = sequences;
        this.transposed = false;
        
        if(type == TYPE_CENTER) {
            this.centerByPosition();
        } else if(type == TYPE_CENTER_AND_SCALE) {
            this.centerAndScaleSequences();
        }
        
        int nrows = sequences.rows();
        int ncols = sequences.columns();
        
        if(nrows < ncols) {
            this.transposed = true;
            this.svd = new SingularValueDecomposition(sequences.viewDice());
        } else {
            this.svd = new SingularValueDecomposition(sequences);
        }
    }
    
    private void centerByPosition() {
        int nrows = this.sequences.rows();
        int ncols = this.sequences.columns();
        
        for(int c = 0; c < ncols; c++) {
            DoubleMatrix1D position = this.sequences.viewColumn(c);
            // divide the sum of all cells by nrows
            double mean = position.zSum() / nrows;
            position.assign(Functions.minus(mean));
        }
    }
    
    private void centerAndScaleSequences() {
        int nrows = this.sequences.rows();
        int ncols = this.sequences.columns();
        
        for(int c = 0; c < ncols; c++) {
            DoubleMatrix1D position = this.sequences.viewColumn(c);
            double mean = position.aggregate(Functions.plus, Functions.identity) / nrows;
            position.assign(Functions.minus(mean));
            double sd = position.aggregate(Functions.plus, Functions.square);
            sd /= position.size() - 1;
            sd = Math.sqrt(sd);
            position.assign(Functions.div(sd));
        }    
    }
    
    public DoubleMatrix2D getUS(int n) {
        if (this.transposed) {
            int ncol = this.svd.getV().columns();
            return this.svd.getV().viewPart(0, 0, ncol, n).zMult(this.svd.getS().viewPart(0, 0, n, n), null);
        }
        else {
            int ncol = this.svd.getU().columns();
            return this.svd.getU().viewPart(0, 0, ncol, n).zMult(this.svd.getS().viewPart(0, 0, n, n), null);
        }
    }

    public DoubleMatrix2D getSV(int n) {
        if (this.transposed) {
            int ncol = this.svd.getU().columns();
            return this.svd.getU().viewPart(0, 0, ncol, n).zMult(this.svd.getS().viewPart(0, 0, n, n), null).viewDice().copy();
        }
        else {
            int ncol = this.svd.getV().columns();
            return this.svd.getV().viewPart(0, 0, ncol, n).zMult(this.svd.getS().viewPart(0, 0, n, n), null).viewDice().copy();
        }
    }
    
    public DoubleMatrix2D getU() {
    	if (this.transposed) 
            return this.svd.getV();
    	else 
            return this.svd.getU();
    }
    
    public DoubleMatrix2D getV() {
    	if (this.transposed) 
            return this.svd.getU();
    	else 
            return this.svd.getV();
    }
    
    public DoubleMatrix1D getSingularValues() {
    	return DoubleFactory1D.dense.make(this.svd.getSingularValues());
    }
    
    public DoubleMatrix2D getEigenVectors() {
    	if (this.transposed) 
            return this.svd.getU();
 
        return this.svd.getV();
    }

    public DoubleMatrix2D getPrincipalComponents() {
    	if (this.transposed) 
            return this.svd.getV().zMult(this.svd.getS(), null).viewDice().copy();
    	else 
            return this.svd.getU().zMult(this.svd.getS(), null).viewDice().copy();
    }
    
    public DoubleMatrix1D getEigenValues() {
    	double[] s = this.svd.getSingularValues();
        int n = s.length;
        int size = this.sequences.rows();

    	double[] eigenvalues = new double[n];
    	for (int i = 0; i < n; i++) {
            eigenvalues[i] = s[i] * s[i]/(size - 1);
        }
        
    	return DoubleFactory1D.dense.make(eigenvalues);
    }    
}
