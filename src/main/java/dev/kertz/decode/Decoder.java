package dev.kertz.decode;

import lombok.Getter;

@Getter
public abstract class Decoder {

    /**
     * When a decoder finds a correct match for the section(s), its decoding is saved in this reference
     */
    private Decoding decoding = null;

    /**
     * Decodes section(s) belonging to the report
     * @param rawSections the still undecoded sections.
     * @return true if there was a successful decoding, false otherwise.
     */
    public abstract boolean decode(String[] rawSections);

    /**
     * Method for subclasses to set the decoding, if any was found.
     * @param decoding the decoding
     */
    protected final void setDecoding(Decoding decoding){
        this.decoding = decoding;
    }
}
