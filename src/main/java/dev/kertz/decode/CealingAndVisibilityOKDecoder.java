package dev.kertz.decode;

public class CealingAndVisibilityOKDecoder extends Decoder {

    CealingAndVisibilityOKDecoder(){
        super("CAVOK");
    }

    @Override
    public String decode(String section) {
        return super.getMatcher(section).find() ? "Visibilidad mayor a 10 km, sin nubes por debajo de 5000 ft sin CB ni TCU, y no hay fenómenos meteorólogicos significativos." : null;
    }
}
