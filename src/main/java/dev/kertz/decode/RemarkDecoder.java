package dev.kertz.decode;

import java.util.regex.Matcher;

public class RemarkDecoder extends Decoder{

    RemarkDecoder(){
        super("RMK");
    }

    @Override
    public String decode(String section) {
        return super.getMatcher(section).find() ? "El reporte tiene observaciones adicionales." : null;
    }
}
