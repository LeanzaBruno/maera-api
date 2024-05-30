package dev.kertz.mapper;

import dev.kertz.dto.Decoding;
import dev.kertz.dto.ReportDTO;
import dev.kertz.dto.SIGMET;
import dev.kertz.model.Report;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReportMapper {
    public static ReportDTO makeDTO(Report report, List<Decoding> decodings){
        return new ReportDTO(report.getRaw(), decodings);
    }

    public static SIGMET makeSigmet(Report report, List<Decoding> decodings){
        return null;
    }

}
