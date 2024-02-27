import dev.kertz.decode.TemporaryDecoder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemporaryDecoderTest {

    @Test
    void assertIsPresent(){
        TemporaryDecoder decoder = new TemporaryDecoder();
        assertTrue(decoder.decode("TEMPO 1818/2323 18010KT".split(" ")));
        System.out.println(decoder.getDecoding().getDecoding());

        assertTrue(decoder.decode("PROB40 TEMPO 1818/1823 18010KT".split(" ")));
        System.out.println(decoder.getDecoding().getDecoding());

        assertTrue(decoder.decode("PROB40 TEMPO 1818/1823 18010KT".split(" ")));
        System.out.println(decoder.getDecoding().getDecoding());

        assertTrue(decoder.decode("PROB30 TEMPO 2615/2620 TSCU".split(" ")));
        System.out.println(decoder.getDecoding().getDecoding());
    }

    @Test
    void assertNoDecoding(){
        TemporaryDecoder decoder = new TemporaryDecoder();
        assertFalse(decoder.decode("METAR SAAV".split(" ")));
        assertFalse(decoder.decode("METAR SAAV 192000Z".split(" ")));
        assertFalse(decoder.decode("TSCU -RA DZ".split(" ")));
    }
}
