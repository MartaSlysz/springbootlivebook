package com.slyszmarta.springbootlivebook.computer.controller;

import com.slyszmarta.springbootlivebook.computer.dto.ComputerDto;
import com.slyszmarta.springbootlivebook.computer.exception.ComputerNotFoundException;
import com.slyszmarta.springbootlivebook.computer.model.Computer;
import com.slyszmarta.springbootlivebook.computer.service.ComputerService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/computers/")
@RequiredArgsConstructor
public class ComputerController {

    @Autowired
    private ComputerService computerService;

    @GetMapping
    @Operation(summary = "GET ALL COMPUTERS", description = "Returns list of all computers")
    public List<ComputerDto> getAllComputers(){
        return computerService.getAllComputers().stream().map(ComputerDto::new).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    @Operation(summary = "GET COMPUTER BY ID",
            description = "Returns computer details by its id",
            responses = {
                    @ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
                    @ApiResponse(responseCode = "404", description = "Computer not found")}
    )
    public ResponseEntity<ComputerDto> getComputerById(@ApiParam(value = "id of computer") @PathVariable(name = "id") Long id) throws ComputerNotFoundException {
        return ResponseEntity.ok(new ComputerDto(computerService.getComputerById(id)));
    }

    @PostMapping
    @Operation(summary = "CREATE NEW COMPUTER",
    description = "Creates new computer using passed data",
    responses = {
            @ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "201"),
            @ApiResponse(responseCode = "422", description = "Invalid data supplied")
    })
    public ResponseEntity<ComputerDto> createComputer(@RequestBody ComputerDto computerDto){
        Computer computerToCreate = new Computer(computerDto);
        return new ResponseEntity<>(new ComputerDto(computerService.createComputer(computerToCreate)), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Operation(summary = "UPDATE COMPUTER",
    description = "Updates computer of id given with passed data",
    responses = {
            @ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Computer not found"),
            @ApiResponse(responseCode = "422", description = "Invalid data supplied")
    })
    public ResponseEntity<ComputerDto> updateComputer(@Parameter(description = "Id of computer to update", required = true) @PathVariable(name = "id") Long id,
                                                      @RequestBody ComputerDto computerDto)
            throws ComputerNotFoundException {
        Computer computerToUpdate = new Computer(computerDto);
        return ResponseEntity.ok(new ComputerDto(computerService.updateComputer(id, computerToUpdate)));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "DELETE COMPUTER",
    description = "Deletes computer of id given",
    responses = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Computer not found")
    })
    public ResponseEntity<Void> deleteComputer(@Parameter(description = "Id of computer to delete", required = true) @PathVariable(name = "id") Long id){
        computerService.deleteComputer(id);
        return ResponseEntity.noContent().build();
    }


}
