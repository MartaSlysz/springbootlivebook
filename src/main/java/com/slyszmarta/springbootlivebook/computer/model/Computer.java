package com.slyszmarta.springbootlivebook.computer.model;

import com.slyszmarta.springbootlivebook.computer.dto.ComputerDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "computers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ddrType")
    private DDRType ddrType;
    @Column(name = "mhz")
    private Long MHz;
    @Column(name = "memorySize")
    private Long memorySize;

    public Computer(ComputerDto dto){
        this.id = dto.getId();
        this.ddrType = dto.getDdrType();
        this.MHz = dto.getMHz();
        this.memorySize = dto.getMemorySize();
    }
}
