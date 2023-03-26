package com.slyszmarta.springbootlivebook.computer.controller;

import com.slyszmarta.springbootlivebook.computer.dto.ComputerDto;
import com.slyszmarta.springbootlivebook.computer.exception.ComputerNotFoundException;
import com.slyszmarta.springbootlivebook.computer.model.Computer;
import com.slyszmarta.springbootlivebook.computer.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/computers")
@RequiredArgsConstructor
public class ComputerController {

    @Autowired
    private ComputerService computerService;

    @GetMapping("/")
    public List<ComputerDto> getAllComputers(){
        return computerService.getAllComputers().stream().map(ComputerDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComputerDto> getComputerById(@PathVariable(name = "id") Long id) throws ComputerNotFoundException {
        return ResponseEntity.ok(new ComputerDto(computerService.getComputerById(id)));
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<ComputerDto> createComputer(@RequestBody ComputerDto computerDto){
        Computer computerToCreate = new Computer(computerDto);
        return new ResponseEntity<>(new ComputerDto(computerService.createComputer(computerToCreate)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComputerDto> updateComputer(@PathVariable(name = "id") Long id, @RequestBody ComputerDto computerDto) throws ComputerNotFoundException {
        Computer computerToUpdate = new Computer(computerDto);
        return ResponseEntity.ok(new ComputerDto(computerService.updateComputer(id, computerToUpdate)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComputer(@PathVariable(name = "id") Long id){
        computerService.deleteComputer(id);
        return ResponseEntity.noContent().build();
    }


}
