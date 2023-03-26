package com.slyszmarta.springbootlivebook.computer.repository;

import com.slyszmarta.springbootlivebook.computer.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {
}
