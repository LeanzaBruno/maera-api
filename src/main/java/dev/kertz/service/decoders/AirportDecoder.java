package dev.kertz.service.decoders;

import dev.kertz.model.Airport;
import dev.kertz.repository.AirportRepository;
import dev.kertz.dto.Decoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;

@Service
public final class AirportDecoder extends SingleDecoder {

    @Autowired
    private AirportRepository airportRepository;

    public AirportDecoder(){
        super("S[A-Z]{3}");
    }

    @Override
    public Optional<Decoding> decode(String section){
        Matcher matcher = super.getMatcher(section);
        if(matcher.find()){
            Optional<Airport> airportOpt = airportRepository.findByICAOIgnoreCase( matcher.group() );
            if(airportOpt.isPresent())
                return Optional.of( new Decoding("El reporte pertenece al " + airportOpt.get().getName() + ".", matcher.group() ));
        }
        return Optional.empty();
    }
}
