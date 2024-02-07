package kertz.maera;

import dev.kertz.core.MetarDecoder;
import dev.kertz.model.Metar;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class DecoderTest {
    private final Metar metar = new Metar("METAR SAAV 071600Z 02008KT 9999 FEW030 SCT040CB OVC100TCU 36/23 Q1009 RMK PP/// =" );
    private final Metar metar2 = new Metar("METAR SAAV 071600Z 02008KT 36/23 Q1009 RMK PP/// =" );

    DecoderTest(){
        MetarDecoder.decode(metar);
        MetarDecoder.decode(metar2);
    }

    @Test
    void visibilityIsCorrect(){
        assertEquals("9999", metar.getVisibility() );
    }

    @Test
    void cloudsAreCorrect(){
        assertEquals(Arrays.asList("FEW030","SCT040CB","OVC100TCU"), metar.getClouds());
    }

    @Test
    void airportCodeIsCorrect() {
        assertEquals("SAAV",  metar.getAirportCode() );
    }

    @Test
    void dateIsCorrect() {
        assertEquals("07",  metar.getDate() );
        assertEquals("1600", metar.getTime() );
    }

    @Test
    void timeIsCorrect() {
        assertEquals("1600", metar.getTime() );
    }

    @Test
    void windIsCorrect(){
        assertEquals("020", metar.getWindDirection());
    }

    // Tests for second metar
    @Test
    void thereAreNoClouds(){
        assertEquals( new ArrayList<>(), metar2.getClouds());
    }

    @Test
    void visibilityIsNull(){
        assertNull( metar2.getVisibility() );
    }
}
