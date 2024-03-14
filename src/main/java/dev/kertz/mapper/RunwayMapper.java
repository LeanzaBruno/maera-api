package dev.kertz.mapper;

import dev.kertz.dto.RunwayDTO;
import dev.kertz.model.Runway;

public class RunwayMapper {
    public static RunwayDTO toDTO(Runway runway){
        return new RunwayDTO(runway.getDesignator(), runway.getLength(), runway.getWidth(), runway.getSurface());
    }
}
