import dev.kertz.decode.BecomingDecoder;
import dev.kertz.decode.Decoding;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BecomingDecoderTest {

    @Test
    void assertIsPresent(){
        BecomingDecoder decoder = new BecomingDecoder();
        assertTrue(decoder.decode("BECMG 18010KT".split(" ")));
    }

    @Test
    void assertIsNotPresent(){
        BecomingDecoder decoder = new BecomingDecoder();
        assertFalse(decoder.decode("METAR SAAV".split(" ")));
    }

    @Test
    void assertDecodedSectionsAreOne(){
        BecomingDecoder decoder = new BecomingDecoder();
        assertTrue( decoder.decode("BECMG 18010KT".split(" ")));
        Decoding decoding = decoder.getDecoding();
        assertEquals(1, decoding.getDecodedSections() );
        System.out.println(decoding);
    }

    @Test
    void assertDecodedSectionsAreTwo(){
        BecomingDecoder decoder = new BecomingDecoder();
        assertTrue( decoder.decode("BECMG 2117/2120".split(" ")));
        Decoding decoding = decoder.getDecoding();
        assertEquals(2, decoding.getDecodedSections() );
        System.out.println(decoding);
    }
}
