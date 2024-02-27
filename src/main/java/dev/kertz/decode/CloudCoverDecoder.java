package dev.kertz.decode;

import dev.kertz.enums.CloudCoverType;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Stream;
import static java.lang.Integer.parseInt;

public class CloudCoverDecoder extends SingleSectionDecoder {

    public CloudCoverDecoder(){
        super("(?<cover>FEW|BKN|SCT|OVC)(?<altitude>\\d{3})(?<type>CB|TCU)?");
    }

    @Override
    public boolean decode(String[] rawSections) {
        Matcher matcher = super.getMatcher(rawSections[0]);

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

            // TODO caso TSRA tormenta con lluvia

            setDecoding( new Decoding(List.of(rawSections[0]), wrapper.cover + (type != null ? " de tipo " + type : "") + " con base a " + altitude + " ft sobre el aeropuerto."));
            return true;
        }
        return false;
    }
}