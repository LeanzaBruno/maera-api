import dev.kertz.decode.VisibilityDecoder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VisibilityDecoderTest {

    @Test
    void assertIsNotPresent(){
        VisibilityDecoder decoder = new VisibilityDecoder();
        assertFalse(decoder.decode("METAR SAAV".split(" ")));
        assertFalse(decoder.decode("SAAV SAAV".split(" ")));
        assertFalse(decoder.decode("18010KT 300V090".split(" ")));
        assertFalse(decoder.decode("36005G20KT TSRA".split(" ")));
        assertFalse(decoder.decode("Q1020 23/20".split(" ")));
        assertFalse(decoder.decode("19/10 Q1020".split(" ")));
    }

    @Test
    void assertIsPresent(){
        VisibilityDecoder decoder = new VisibilityDecoder();
        assertTrue(decoder.decode("1000 SAAV".split(" ")));
        assertTrue(decoder.decode("9999 SAAV".split(" ")));
        assertTrue(decoder.decode("5000 SAAV".split(" ")));
        assertTrue(decoder.decode("0500 SAAV".split(" ")));
        assertTrue(decoder.decode("0200 SAAV".split(" ")));
        assertTrue(decoder.decode("8000 SAAV".split(" ")));
    }

    @Test
    void assertDecodingAreCorrect(){
        VisibilityDecoder decoder = new VisibilityDecoder();

        assertTrue( decoder.decode("1000".split(" ")) );
        System.out.println( decoder.getDecoding() );

        assertTrue(decoder.decode("9999".split(" ")));
        System.out.println(decoder.getDecoding());

        assertTrue(decoder.decode("5000 SAAV".split(" ")));
        System.out.println(decoder.getDecoding());

        assertTrue(decoder.decode("0500 SAAV".split(" ")));
        System.out.println();

        assertTrue(decoder.decode("0200 SAAV".split(" ")));
        System.out.println();

        assertTrue(decoder.decode("8000 SAAV".split(" ")));
        System.out.println();
    }
}
