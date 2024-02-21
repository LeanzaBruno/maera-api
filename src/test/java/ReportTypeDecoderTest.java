import dev.kertz.decode.ReportTypeDecoder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReportTypeDecoderTest {

    @Test
    void assertIsNull(){
        ReportTypeDecoder decoder = new ReportTypeDecoder();
        assertNull(decoder.decode("18010KT", "23/15"));
    }

    @Test
    void groupCountIsOne(){
        ReportTypeDecoder decoder = new ReportTypeDecoder();
        assertEquals(1, decoder.decode("METAR", "SAAV").get().getDecodedSections());
        assertEquals(1, decoder.decode("TAF", "SAAV").get().getDecodedSections());
        assertEquals(1, decoder.decode("SPECI", "SAAV").get().getDecodedSections());
    }

    @Test
    void groupCountIsTwo(){
        ReportTypeDecoder decoder = new ReportTypeDecoder();
        assertEquals(2, decoder.decode("METAR", "NIL").get().getDecodedSections() );
        assertEquals(2, decoder.decode("TAF", "NIL").get().getDecodedSections());
    }
}
