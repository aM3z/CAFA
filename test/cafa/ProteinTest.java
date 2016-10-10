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

import java.util.Arrays;
import junit.framework.TestCase;

/**
 *
 * @author Miguel Amezola <amezolma@plu.edu>
 */
public class ProteinTest extends TestCase {
    
    private Protein instance1; // large
    private Protein instance2; // small
    private Protein instance3; // 1 char sequence

    public ProteinTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.instance1 = new Protein("GQPKAAPSVTLFPPSSEELQANKATLVCLVSDFYPGAVTVAWKADGSPVKVGVETTKPSKQSNNKYAASSYLSLTPEQWKSHRSYSCRVTHEGSTVEKTVAPAECS");
        this.instance2 = new Protein("ABC");
        this.instance3 = new Protein("M");
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of constructor, of class Protein.
     */
    public void testProtein() {
        System.out.println("constructor");
        String expResult = "GQPKAAPSVTLFPPSSEELQANKATLVCLVSDFYPGAVTVAWKADGSPVKVGVETTKPSKQSNNKYAASSYLSLTPEQWKSHRSYSCRVTHEGSTVEKTVAPAECS";
        String result = this.instance1.getSequence();
        assertEquals(expResult, result);
    }


    /**
     * Test of countMonograms method, of class Protein.
     */
    public void testCountMonograms() {
        System.out.println("countMonograms");
        
        Protein instance;
        int[] result;
        
        // large sequence
        instance = this.instance1;
        // {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'Y', 'Z', 'X', '*', '-'}
        int [] expResult1 = {11, 0, 3, 2, 7, 2, 5, 2, 0, 9, 6, 0, 3, 9, 4, 2, 15, 9, 0, 11, 2, 4, 0, 0, 0, 0};
        result = instance.countMonograms();
        assertTrue(Arrays.equals(expResult1, result));
        
        // small sequence
        instance = this.instance2;
        // {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'Y', 'Z', 'X', '*', '-'}
        int [] expResult2 = {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        result = instance.countMonograms();
        assertTrue(Arrays.equals(expResult2, result));
        
        // 1 char sequence
        instance = this.instance3;
        // {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'Y', 'Z', 'X', '*', '-'}
        int [] expResult3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        result = instance.countMonograms();
        assertTrue(Arrays.equals(expResult3, result));
    }

  /**
     * Test of monogramDensity method, of class Protein.
     */
    public void testMonogramDensity() {

        System.out.println("monogramDensity");
        
        Protein instance;
        double [] dens;
        double totalDens;
        
        // large sequence
        instance= this.instance1;
        dens = instance.monogramDensity();        
        totalDens = 0.0;
        
        for(int i = 0; i < dens.length; i++)
            totalDens += dens[i];

        assertEquals(totalDens, 1.0, 0.00000001);
    }
    
    /**
     * Test of countBigrams method, of class Protein.
     */
    public void testCountBigrams() {
        System.out.println("countBigrams");
        
        Protein instance;
        
        // large sequence
        instance= this.instance1;
        
        int[] result = instance.countBigrams();
        assertEquals(result.length, 26 * 26);
        
        int countPairs = 0;
        for(int i = 0; i < result.length; i++)
            countPairs += result[i];
        
        assertEquals(instance.getSequence().length() - 1, countPairs);

        // small sequence
        instance= this.instance2;
        
        result = instance.countBigrams();
        assertEquals(result.length, 26 * 26);
        
        countPairs = 0;
        for(int i = 0; i < result.length; i++)
            countPairs += result[i];
        
        assertEquals(instance.getSequence().length() - 1, countPairs);
        
        // 1 char sequence
        instance= this.instance3;
        
        result = instance.countBigrams();
        assertEquals(result.length, 26 * 26);
        
        countPairs = 0;
        for(int i = 0; i < result.length; i++)
            countPairs += result[i];
        
        assertEquals(instance.getSequence().length() - 1, countPairs);        
        
    }
    /**
     * Test of toString method, of class Protein.
     */
    public void testToString() {
        System.out.println("toString");
        Protein instance = this.instance1;
        String expResult = ">\nGQPKAAPSVTLFPPSSEELQANKATLVCLVSDFYPGAVTVAWKADGSPVKVGVETTKPSKQSNNKYAASSYLSLTPEQWKSHRSYSCRVTHEGSTVEKTVAPAECS\n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
