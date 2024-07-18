package com.java.bookingbistro.controller;

import com.java.bookingbistro.entity.Expert;
import com.java.bookingbistro.model.ExpertDTO;
import com.java.bookingbistro.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expert")
@CrossOrigin(origins = "*")
public class ExpertController {

    @Autowired
    private ExpertService expertService;

    @PostMapping("/add")
    public void addExpert(@RequestBody Expert expert) {
        expertService.addNewExpert(expert);
    }

    @GetMapping("/all-doctors")
    public List<ExpertDTO> getAllDoctors() {
        return expertService.getAllDoctors();
    }

    @GetMapping("/all-nurses")
    public List<ExpertDTO> getAllNurses() {
        return expertService.getAllNurses();
    }
}
