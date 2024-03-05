package dev.kertz.dto;

import dev.kertz.model.Runway;

public class RunwayMapper {
    public static RunwayDTO toDTO(Runway runway){
        return new RunwayDTO(runway.getDesignator(), runway.getLength(), runway.getWidth(), runway.getSurface());
    }
}
