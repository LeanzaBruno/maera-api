import dev.kertz.decode.BecomingDecoder;
import dev.kertz.decode.Decodification;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BecomingDecoderTest {

    @Test
    void assertIsPresent(){
        BecomingDecoder decoder = new BecomingDecoder();
        Optional<Decodification> decodification =  decoder.decode("BECMG", "18010KT");
        assertTrue(decodification.isPresent());
    }

    @Test
    void assertIsNotPresent(){
        BecomingDecoder decoder = new BecomingDecoder();
        Optional<Decodification> decodification =  decoder.decode("METAR", "SAAV");
        assertFalse(decodification.isPresent());
    }

    @Test
    void assertDecodedSectionsAreOne(){
        BecomingDecoder decoder = new BecomingDecoder();
        Decodification decodification =  decoder.decode("BECMG", "18010KT").get();
        assertEquals(1, decodification.getDecodedSections() );
        System.out.println(decodification);
    }

    @Test
    void assertDecodedSectionsAreTwo(){
        BecomingDecoder decoder = new BecomingDecoder();
        Decodification decodification =  decoder.decode("BECMG", "2117/2120").get();
        assertEquals(2, decodification.getDecodedSections() );
        System.out.println(decodification);
    }
}
