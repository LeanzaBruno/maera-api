import dev.kertz.decode.VisibilityDecoder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VisibilityDecoderTest {

    @Test
    void assertIsNotPresent(){
        VisibilityDecoder decoder = new VisibilityDecoder();
        assertFalse(decoder.decode("METAR", "SAAV").isPresent());
        assertFalse(decoder.decode("SAAV", "SAAV").isPresent());
        assertFalse(decoder.decode("18010KT", "300V090").isPresent());
        assertFalse(decoder.decode("36005G20KT", "TSRA").isPresent());
        assertFalse(decoder.decode("Q1020", "23/20").isPresent());
        assertFalse(decoder.decode("19/10", "Q1020").isPresent());
    }

    @Test
    void assertIsPresent(){
        VisibilityDecoder decoder = new VisibilityDecoder();
        assertTrue(decoder.decode("1000", "SAAV").isPresent());
        assertTrue(decoder.decode("9999", "SAAV").isPresent());
        assertTrue(decoder.decode("5000", "SAAV").isPresent());
        assertTrue(decoder.decode("0500", "SAAV").isPresent());
        assertTrue(decoder.decode("0200", "SAAV").isPresent());
        assertTrue(decoder.decode("8000", "SAAV").isPresent());
    }

}
