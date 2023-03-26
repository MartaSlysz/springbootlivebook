package com.slyszmarta.springbootlivebook.computer.service;

import com.slyszmarta.springbootlivebook.computer.exception.ComputerNotFoundException;
import com.slyszmarta.springbootlivebook.computer.model.Computer;
import com.slyszmarta.springbootlivebook.computer.repository.ComputerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComputerService {

    @Autowired
    private final ComputerRepository computerRepository;

    public List<Computer> getAllComputers(){
        return computerRepository.findAll();
    }

    public Computer getComputerById(Long id) throws ComputerNotFoundException {
        Optional<Computer> result = computerRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        } else {
            throw new ComputerNotFoundException(id);
        }
    }

    public Computer createComputer(Computer computer){
        return computerRepository.save(computer);
    }

    public Computer updateComputer(Long idToUpdate, Computer newComputer) throws ComputerNotFoundException {
        Computer computer = computerRepository.findById(idToUpdate).orElseThrow(() ->new ComputerNotFoundException(idToUpdate));
        computer.setDdrType(newComputer.getDdrType());
        computer.setMHz(newComputer.getMHz());
        computer.setMemorySize(newComputer.getMemorySize());
        return computerRepository.save(computer);
    }

    public void deleteComputer(Long id){
        computerRepository.deleteById(id);
    }
}
