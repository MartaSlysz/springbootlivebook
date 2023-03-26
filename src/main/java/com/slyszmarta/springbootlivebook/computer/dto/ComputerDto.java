package com.slyszmarta.springbootlivebook.computer.dto;

import com.slyszmarta.springbootlivebook.computer.model.Computer;
import com.slyszmarta.springbootlivebook.computer.model.DDRType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComputerDto {

    private Long id;
    private DDRType ddrType;
    private Long MHz;
    private Long memorySize;

    public ComputerDto (Computer computer){
        this.id = computer.getId();
        this.ddrType = computer.getDdrType();
        this.MHz = computer.getMHz();
        this.memorySize = computer.getMemorySize();
    }
}
