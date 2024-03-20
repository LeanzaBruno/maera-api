import dev.kertz.service.decoders.WeatherPhenomDecoder;
import org.junit.jupiter.api.Test;

public class WeatherPhenomDecoderTest {

    private final WeatherPhenomDecoder decoder = new WeatherPhenomDecoder();

    @Test
    void assertIsEmpty(){
        String target = "TEMPO";
        decoder.decode(target).ifPresent(System.out::println);
    }
}
