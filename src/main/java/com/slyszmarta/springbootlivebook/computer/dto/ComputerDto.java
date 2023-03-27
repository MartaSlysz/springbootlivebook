package com.slyszmarta.springbootlivebook.computer.dto;

import com.slyszmarta.springbootlivebook.computer.model.Computer;
import com.slyszmarta.springbootlivebook.computer.model.DDRType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("Computer DTO")
public class ComputerDto {

    @ApiModelProperty("id of computer")
    private Long id;
    @NotEmpty
    private DDRType ddrType;
    @NotEmpty
    private Long MHz;
    @NotEmpty
    private Long memorySize;

    public ComputerDto (Computer computer){
        this.id = computer.getId();
        this.ddrType = computer.getDdrType();
        this.MHz = computer.getMHz();
        this.memorySize = computer.getMemorySize();
    }
}
