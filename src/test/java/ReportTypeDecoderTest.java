import dev.kertz.decode.ReportTypeDecoder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReportTypeDecoderTest {

    @Test
    void assertIsNull(){
        ReportTypeDecoder decoder = new ReportTypeDecoder();
        assertFalse( decoder.decode("SAAV 221900Z".split(" ")) );
        assertFalse( decoder.decode("18010KT 030V090".split(" ")) );
        assertFalse( decoder.decode("Q1010 23/15".split(" ")) );
        assertFalse( decoder.decode("BECMG 09005KT".split(" ")) );
    }

    @Test
    void assertIsPresent(){
        ReportTypeDecoder decoder = new ReportTypeDecoder();
        assertTrue( decoder.decode("METAR SAAV".split(" ")) );
        assertTrue( decoder.decode("TAF SABE".split(" ")) );
        assertTrue( decoder.decode("SPECI SAZM".split(" ")) );
        assertTrue( decoder.decode("METAR NIL".split(" ")) );
    }

    @Test
    void groupCountIsOne(){
        ReportTypeDecoder decoder = new ReportTypeDecoder();
        assertTrue( decoder.decode("METAR SAAV".split(" ")) );
        assertEquals(1, decoder.getDecoding().getDecodedSections() );
    }

    @Test
    void groupCountIsTwo(){
        ReportTypeDecoder decoder = new ReportTypeDecoder();
        assertTrue( decoder.decode( "METAR NIL".split(" ")) );
        assertEquals( 2, decoder.getDecoding().getDecodedSections() );
    }
}
