/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafa;

import cafa.Protein;
import cafa.ProteinCollection;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Amezola <amezolma@plu.edu>
 */
public class ProteinCollectionTest {

    // instantiate
    private ProteinCollection instance;
    
    public ProteinCollectionTest() {
    }

    @BeforeClass
    public static void setUpClass() {    
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.instance = new ProteinCollection("CAFATrainingData/GO/go_sequence.fasta");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of constructor, of class ProteinCollection.
     */
    @Test
    public void testConstructor() {
        // print label
        System.out.println("ProteinCollection(String filePath)\n");

        ArrayList<Protein> result = instance.getProteins();

        // print total number of proteins
        System.out.println(result.size() + " proteins\n");
        
        // print extrema
        System.out.println("Shortest sequence: " + instance.getShortest().getSequence().length() + " residuals\n" + instance.getShortest());
        System.out.println("Longest sequence: " + instance.getLongest().getSequence().length() + " residuals\n" + instance.getLongest());
        // expResults
        String firstAccession = "A0A098";
        String firstSequence = "MASMAAELRPSDGGSSLHMLDSLLMMGLSSGGGVGGGGSSQSQILDSAGAAELAALLLPQHSNDPLHLMS"
                + "TGDAALGLAGPMAAAEHHQHHPHHQHHSVPATAGFPSQTPPPPLFSNATAGAAPATRVRAAGSCGSGGVA"
                + "GGTTSHSSEDGVFHSADPHHHHQQHLQQPQPQQQQQQQQQPQHQQQQQQQQQQQPQQPQFHHHQPQQHQA"
                + "QQAVAATAPAAAADHLFLPPAAAGAAGQQQAGWGAATGAHLFHSDAPNRPNGPKTPTNRNGYAMKPPPPA"
                + "AAAAVMSGHAMAASGVVAGRGGGGGGCDAEEDELTFAAAAAAEVSSDDEVMGGRGGAVMGRAAVGGFRTP"
                + "APAPASAGAAVNVAAAAPAAPAGTWVPKGKPAVEVSAWAPAPAAPVAAAAAAAVAVSPTVAAFAAAPVVE"
                + "AVALVRSQQRQEQEHQLVQVAAAEQQDQQQAKRPVRSSRRRAQESQQAAAEAQQAAQQAAAAQQQQQHTG"
                + "SGNIVGDKQGKPQGGLQQGRPVATQPSPPPPAVATAAAAAAATAPVATAPVATVATAATADTMPQRLTAV"
                + "QQHEAYPAVVRGTLDVRKACLPRFPLVAREQAEAELQSVATAALQQAAAAPGASAAVTAAAAAAAAAATA"
                + "AGEGKAGEGKAATARRGGRGAAEAEAAAALPAVGASGNPGLDEAMGAYVRVAALYGDEAAAAVAECESLM"
                + "ADFDNKLQLGNLATTFAAAASPTAAASGRARGGARSGGVGGGGTKRVASGANASGANSSGMSGGGGGDSA"
                + "GAEAAAGDEMEWAPGATSVDTATGGADDEDGEEEEGQQPGGAAAAAAVAAAGGEASQLAASIAATYGSQL"
                + "VQVAASLAQRPKVGKLPPAATQLLKGWWDDNFVWPYPSEEDKKQLGEAAALNNTQINNWFINQRKRHWHK"
                + "HFPNRRPPSTREEAERLLRAAGAI";

        String lastAccession = "S4S1W8";
        String lastSequence = "AAALTMIQTRAGPHSMRYFETAVFGPGLGEPRFISVGYVDNTQFVSFDSDAENPRSEPRSPWMEQEGPEY"
                + "WERETQIAKDNEQSFGWSLRNLIHYYNQSKGGFHTFQRLSGCDMGLDGRLLRGYLQFAYDGRDYIALNED"
                + "LKTWMAADLVALITRRKWEQAGAAELYKFYLEGECVEWLRRYLELGNETLLRTDPPKAHVTRHPRPAGDV"
                + "TLRCWALGFYPADITLTWQLNGEELTQDMELVETRPAGDGTFQKWAAVVVPLGKEQNYTCRVHHEGLPEP"
                + "LTLRWEPPPSTGSNMVNIAVLVVLGAVIIIEAMVAFALKSRRKIAILPGPA";

        assertEquals(firstAccession, result.get(0).getAccession());
        assertEquals(firstSequence, result.get(0).getSequence());        
        assertEquals(lastAccession, result.get(result.size() - 1).getAccession());
        assertEquals(lastSequence, result.get(result.size() - 1).getSequence());
    }

    /**
     * Test of setProteins method, of class ProteinGroup.
     */
    @Test
    public void testFirstOrderProbabilites() {
        
        double [] result = instance.firstOrderProbabilites();
        double total = 0;
        
        for(int i = 0; i < result.length; i++)
            total += result[i];
        
        assertEquals(1.0, total, 0.00000001);
        
    }
}
