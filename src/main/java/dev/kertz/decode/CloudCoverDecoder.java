package dev.kertz.decode;

import java.util.regex.Matcher;

import static java.lang.Integer.parseInt;

public class CloudCoverDecoder extends Decoder{

    CloudCoverDecoder(){
        super("(?<cover>FEW|BKN|SCT|OVC)(?<altitude>\\d{3})(?<type>CB|TCU)?");
    }

    @Override
    public String decode(String section) {
        Matcher matcher = super.getMatcher(section);

        if (matcher.find()) {
            String cover = switch( matcher.group("cover") ){
               case "FEW" -> "Nubes escasas (de 1/8 a 2/8)";
               case "SCT" -> "Nubes dispersas (de 3/8 a 4/8)";
               case "BKN" -> "Nubes fragmentadas (de 5/8 a 7/8)";
               default -> "Cielo cubierto (8/8)";
            };
            int altitude = parseInt( matcher.group("altitude") ) * 100;
            String type = matcher.group("type");

            return cover + (type != null ? " de tipo " + type : "") + " con base a " + altitude + " ft sobre el aeropuerto.";
        }
        return null;
    }
}
