package dev.kertz.decode;

import dev.kertz.enums.CloudCoverType;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.stream.Stream;
import static java.lang.Integer.parseInt;

public class CloudCoverDecoder extends Decoder {

    CloudCoverDecoder(){
        super("(?<cover>FEW|BKN|SCT|OVC)(?<altitude>\\d{3})(?<type>CB|TCU)?");
    }

    @Override
    public Optional<Decodification> decode(String section, String nextSection) {
        Matcher matcher = super.getMatcher(section);

        if (matcher.find()) {
            var wrapper = new Object(){ String cover; };
            Stream.of(CloudCoverType.values())
                    .filter( cloud -> cloud.name().equals(matcher.group("cover")))
                    .findFirst()
                    .ifPresent( cloud -> wrapper.cover = cloud.getMeaning() );

            int altitude = parseInt( matcher.group("altitude") ) * 100;
            String type = matcher.group("type");

            return Optional.of( new Decodification(List.of(section), wrapper.cover + (type != null ? " de tipo " + type : "") + " con base a " + altitude + " ft sobre el aeropuerto."));
        }
        return Optional.empty();
    }
}