package dev.kertz.decode;

public class EndOfReportDecoder extends Decoder {

    EndOfReportDecoder(){
        super("=");
    }

    @Override
    public String decode(String section) {
        return super.getMatcher(section).find() ? "Fin del reporte." : null;
    }
}
