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

import cern.colt.matrix.impl.SparseDoubleMatrix1D;
import cern.colt.matrix.impl.SparseDoubleMatrix2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Amezola <amezolma@plu.edu>
 */
public class SequencesTest {
    
    Sequences instance;
    String pathToFastaFile;
    
    @Before
    public void setUp() {
        pathToFastaFile = "../CAFA/CAFATrainingData/GO/go_sequence.fasta";
        instance = new Sequences(pathToFastaFile);
    }
    
    @After
    public void tearDown() {
    }
    
//    @Test
//    public void testSequences() {
//        File f = new File(pathToFastaFile);
//
//        SparseDoubleMatrix2D sequenceMatrix = this.instance.getSequenceMatrix();
//        
//        int lastRow = sequenceMatrix.rows() - 1;
//        
//        System.out.println(sequenceMatrix.viewRow(0));
//        System.out.println(sequenceMatrix.viewRow(lastRow));
//        System.out.println(sequenceMatrix.viewRow(lastRow).cardinality());        
//    }
    
    
    @Test
    public void testGetSequenceMatrix() {
        
        System.out.println("getSequenceMatrix");

        SparseDoubleMatrix2D result = this.instance.getSequenceMatrix();
        
        int expRows, expCols;
        expRows = 118411;
        expCols = 17607;

        assertEquals(expRows, result.rows());
        assertEquals(expCols, result.columns());
        
        SparseDoubleMatrix1D expFirstRow, expLastRow;
        
        double [] arr1 = {164, 112627, 8, 15059, 171349, 3283, 4913, 300763, 13357, 56129, 31423, 50653, 68921, 23273, 300763, 4913, 106097, 4913, 4913, 300763, 264851, 31211, 1813, 268, 68, 242, 148, 2738, 50653, 184493, 85291, 2107, 72557, 26011, 184049, 20519, 28, 2738, 23273, 578, 89093, 8, 242, 6859, 21299, 53371, 6859, 21299, 85291, 221911, 10082, 578, 36517, 233227, 199439, 148877, 72557, 58357, 172, 284, 68, 5618, 10082, 380701, 244, 578, 1675, 76313, 4913, 316, 4913, 357911, 24187, 300763, 539, 106097, 4693, 268, 19663, 6859, 6859, 205379, 26011, 205379, 184493, 184493, 205379, 205379, 205379, 205379, 184493, 66139, 205379, 205379, 205379, 205379, 205379, 184493, 165731, 9971, 6859, 66139, 184493, 21299, 236, 205379, 12482, 8, 284, 212, 8, 98, 26011, 17797, 148877, 8, 578, 8, 59177, 205379, 578, 23987, 8, 20519, 722, 6253, 85291, 28, 97997, 171349, 12427, 50933, 199439, 131279, 112789, 134657, 3362, 87079, 148877, 212, 8, 8, 132799, 19363, 76, 164, 8978, 106097, 316, 63257, 4913, 4913, 4913, 245, 242, 539, 15059, 11999, 8, 8, 8, 68651, 300763, 343, 68651, 11849, 63257, 4913, 12482, 11849, 244, 12482, 4913, 48373, 199439, 5618, 5618, 8978, 578, 8, 146071, 316, 8, 5618, 8, 212, 85697, 518003, 50933, 16337, 212, 9559, 354631, 13778, 5618, 5618, 8, 330773, 8, 8, 8, 12482, 12482, 188203, 443111, 8, 52, 8, 330773, 9559, 12482, 2738, 293959, 233227, 219539, 7139, 7139, 66139, 230917, 368219, 8, 242, 205379, 24367, 205379, 1922, 171349, 293959, 300763, 226981, 244, 7139, 233227, 236, 8, 44, 205379, 8, 205379, 8, 8, 205379, 205379, 21299, 20519, 19363, 22747, 22831, 6727, 17051, 87079, 17051, 23273, 205379, 63257, 330773, 10082, 165731, 188203, 148877, 212, 316, 284, 8, 8, 8, 284, 330773, 10082, 5618, 316, 443111, 10082, 8, 284, 35287, 115169, 219539, 186517, 12482, 205379, 2299, 15842, 212, 493039, 17629, 97199, 43687, 58621, 50, 103933, 10309, 72557, 316, 7381, 236, 44, 15059, 264851, 316, 284, 2738, 205379, 8, 8, 15317, 8978, 8, 398239, 8, 8, 8, 8, 8, 284, 578, 3179, 124, 2057, 16337, 8, 284, 226981, 4913, 17629, 8, 44, 44, 8, 2738, 212, 22831, 8978, 31433, 15317, 1813, 44, 11849, 15842, 293959, 316, 2738, 25721, 847, 8, 8, 316, 275, 49379, 62197, 98, 637, 41323, 128797, 10693, 58867, 10082, 11999, 8, 8, 188203, 284, 8, 19363, 244, 17629, 68, 273829, 4913, 22831, 4913, 85697, 115351, 316, 19363, 3698, 8978, 68, 193027, 19363, 184049, 4913, 4913, 833, 268, 68, 44, 8, 833, 18491, 75779, 5618, 68, 318719, 3871, 284, 20519, 68, 343, 539, 2057, 1331, 3179, 205379, 15317, 68, 8, 8, 12482, 8, 578, 2057, 8978, 80771, 8, 35443, 8, 562391, 76313, 80771, 274999, 316, 8978, 148, 219539, 50933, 22831, 42439, 148877, 8, 247151, 50653, 8959, 571787, 343, 7267, 544231, 419813, 237917, 1331, 6727, 107911, 10693, 44, 8, 68413, 216763, 31211, 79507, 14027, 42527, 219539, 115351, 130891, 18259, 3211, 97997, 226981, 148877, 337747, 7381, 44, 40931, 50653, 244, 578, 1058};
        double [] arr2 = {8, 2738, 119351, 80063, 264191, 578, 19133, 112627, 483181, 1573, 284, 13351, 47753, 23273, 2057, 197213, 6877, 418147, 134657, 3871, 216763, 9971, 354631, 637, 3283, 242, 120787, 273829, 30899, 273829, 365117, 4961, 7139, 47753, 87131, 10043, 7381, 247151, 92, 1519, 5203, 264851, 3757, 372587, 137677, 58867, 8303, 704969, 149683, 64387, 4913, 4693, 11999, 219539, 166093, 425, 11767, 23273, 2023, 83509, 137677, 134657, 128797, 52, 4361, 63257, 55447, 92, 68413, 539, 35557, 489119, 164, 98, 230917, 2738, 115943, 226981, 213559, 38291, 578, 8, 15059, 85529, 102973, 4477, 2057, 31205, 75779, 137677, 483181, 4477, 10693, 5203, 97199, 137677, 3479, 148877, 124, 118579, 264191, 53371, 171349, 578, 43687, 97199, 1525, 332, 10693, 102973, 212, 3703, 97199, 489119, 80771, 12427, 1331, 186517, 2891, 4961, 230917, 55451, 171349, 578, 2023, 11999, 56699, 332, 12482, 493039, 72557, 16337, 38291, 340603, 1775, 380701, 6859, 3179, 103933, 30899, 186517, 137677, 10043, 148877, 237917, 20519, 123883, 255881, 22747, 12482, 230917, 108151, 68, 41791, 12167, 44, 255881, 338, 2738, 139159, 226981, 16399, 1058, 103933, 47753, 2};
        
        expFirstRow = new SparseDoubleMatrix1D(expCols);
        expLastRow = new SparseDoubleMatrix1D(expCols);
        
        for(int i = 0; i < arr1.length; i++)
            expFirstRow.set(i, arr1[i]);
        
        for(int i = 0; i < arr2.length; i++)
            expLastRow.set(i, arr2[i]);
        
//        System.out.println(result.viewRow(0) + "\n" + expFirstRow);
//        System.out.println(result.viewRow(expRows - 1) + "\n" + expLastRow);
        
        assertTrue(expFirstRow.equals(result.viewRow(0)));
        assertTrue(expLastRow.equals(result.viewRow(expRows - 1)));
    }
    
}
