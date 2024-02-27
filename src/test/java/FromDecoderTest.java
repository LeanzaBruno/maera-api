import dev.kertz.decode.Decoding;
import dev.kertz.decode.FromDecoder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FromDecoderTest {

    @Test
    void assertIsPresent(){
        FromDecoder decoder = new FromDecoder();
        assertTrue(decoder.decode("FM221930 18010KT".split(" ")));
    }

    @Test
    void assertIsNotPresent(){
        FromDecoder decoder = new FromDecoder();
        assertFalse(decoder.decode("METAR SAAV".split(" ")));
    }

    @Test
    void assertDecodingsAreCorrect(){
        FromDecoder decoder = new FromDecoder();
        decoder.decode("FM051230 TSRA".split(" "));
        Decoding decoding = decoder.getDecoding();
        assertEquals("FM051230", decoding.getSection() );
        System.out.println(decoding);

        decoder.decode("FM221700 TSRA".split(" "));
        decoding = decoder.getDecoding();
        assertEquals("FM221700", decoding.getSection() );
        System.out.println(decoding);

        decoder.decode("FM010000 TSRA".split(" "));
        decoding = decoder.getDecoding();
        assertEquals("FM010000", decoding.getSection() );
        System.out.println(decoding);
    }
}