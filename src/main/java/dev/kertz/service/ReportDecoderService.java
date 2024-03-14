package dev.kertz.service;

import dev.kertz.dto.Decoding;
import dev.kertz.model.Report;
import dev.kertz.service.decoders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * This service is in charge of decoding reports
 */
@Service
public class ReportDecoderService {

    @Autowired
    private List<SingleDecoder> singleDecoders;

    @Autowired
    private List<MultiDecoder> multiDecoders;

    public List<Decoding> decode(Report report){
        List<Decoding> decodings = new LinkedList<>();
        final String[] sections = report.getRaw().split(" ");

        int index = 0;
        while( index < sections.length ){
            Optional<Decoding> decoding = Optional.empty();

            for(SingleDecoder decoder : singleDecoders){
                decoding = decoder.decode(sections[index]);
                if(decoding.isPresent())
                    break;
            }

            if(decoding.isPresent()){
                decodings.add(decoding.get());
                ++index;
                continue;
            }

            String [] nextSections = Arrays.copyOfRange(sections, index, sections.length);
            for(MultiDecoder decoder : multiDecoders){
                decoding = decoder.decode(nextSections);
                if(decoding.isPresent())
                    break;
            }

            if(decoding.isPresent()){
                decodings.add(decoding.get());
                index += decoding.get().getDecodedSections();
            }
            else{
                decodings.add( new Decoding("-", sections[index]));
                ++index;
            }
        }
        return decodings;
    }
}
