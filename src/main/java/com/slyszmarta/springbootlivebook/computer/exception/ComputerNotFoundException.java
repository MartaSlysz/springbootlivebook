package com.slyszmarta.springbootlivebook.computer.exception;

public class ComputerNotFoundException extends Exception{

    public ComputerNotFoundException(Long id) {
        super("Computer with id: " + id + " is not present in database.");
    }
}
