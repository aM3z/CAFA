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
    
    private Protein instance1;
    
    public ProteinTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.instance1 = new Protein("GQPKAAPSVTLFPPSSEELQANKATLVCLVSDFYPGAVTVAWKADGSPVKVGVETTKPSKQSNNKYAASSYLSLTPEQWKSHRSYSCRVTHEGSTVEKTVAPAECS");
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
     * Test of firstOrderFreqs method, of class Protein.
     */
    public void testFirstOrderFreqs() {
        System.out.println("firstOrderFreqs");
        Protein instance = this.instance1;
        // {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'Y', 'Z', 'X', '*', '-'};
        int[] expResult = {11, 0, 3, 2, 7, 2, 5, 2, 0, 9, 6, 0, 3, 9, 4, 2, 15, 9, 0, 11, 2, 4, 0, 0, 0, 0};
        int[] result = instance.firstOrderFreqs();
        System.out.println("exp: " + Arrays.toString(expResult));
        System.out.println("res: " + Arrays.toString(result));
        assertTrue(Arrays.equals(expResult, result));
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
