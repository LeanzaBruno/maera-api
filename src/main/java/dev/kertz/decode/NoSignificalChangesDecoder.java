package dev.kertz.decode;

public class NoSignificalChangesDecoder extends Decoder{

    NoSignificalChangesDecoder(){
        super("NOSIG");
    }

    @Override
    public String decode(String section) {
        return super.getMatcher(section).find() ? "No se esperan cambios significativos en las próximas 2 horas." : null;
    }
}
