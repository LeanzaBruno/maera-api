package dev.kertz.service.decoders;

import dev.kertz.enums.CloudCoverType;
import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.stream.Stream;
import static java.lang.Integer.parseInt;

@Service
public class CloudCoverDecoder extends SingleDecoder {

    public CloudCoverDecoder(){
        super("(?<cover>FEW|BKN|SCT|OVC)(?<altitude>\\d{2,3})(?<type>CB|TCU)?");
    }

    @Override
    public Optional<Decoding> decode(String section) {
        Matcher matcher = super.getMatcher(section);

        if (matcher.find()) {
            var wrapper = new Object(){ String cover; };
            Stream.of(CloudCoverType.values())
                    .filter( cloud -> cloud.name().equals(matcher.group("cover")))
                    .findFirst()
                    .ifPresent( cloud -> wrapper.cover = cloud.getMeaning() );

            int altitude = parseInt( matcher.group("altitude") ) * 100;
            String type = switch( matcher.group("type") ){
                case "CB" -> "cúmulonimbus";
                case "TCU" -> "torrecúmulus";
                case null, default -> null;
            };
            return Optional.of( new Decoding(wrapper.cover + (type != null ? " de tipo " + type : "") + " con base a " + altitude + " ft sobre el aeropuerto.", section));
        }
        return Optional.empty();
    }
}