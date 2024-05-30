package dev.kertz.service.decoders;

import dev.kertz.model.Airport;
import dev.kertz.model.FIR;
import dev.kertz.repository.AirportRepository;
import dev.kertz.dto.Decoding;
import dev.kertz.repository.FirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;

@Service
public final class StationDecoder extends SingleDecoder {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private FirRepository firRepository;

    public StationDecoder(){
        super("S[A-Z]{3}");
    }

    @Override
    public Optional<Decoding> decode(String section){
        Matcher matcher = super.getMatcher(section);
        if(matcher.find()){
            Optional<Airport> airportOpt = airportRepository.findByICAOIgnoreCase(section);
            if(airportOpt.isPresent())
                return Optional.of( new Decoding("El reporte pertenece al " + airportOpt.get().getName() + ".", section));

            Optional<FIR> firOpt = firRepository.findByIcaoIgnoreCase(section);
            if (firOpt.isPresent())
                return Optional.of( new Decoding("El reporte pertenece a la fir " + firOpt.get().getName() + ".", section));
        }
        return Optional.empty();
    }
}
