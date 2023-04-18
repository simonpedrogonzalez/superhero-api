package com.example.demo.controllers;

import com.example.demo.domain.entities.Hero;
import com.example.demo.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @GetMapping
    public ResponseEntity<List<Hero>> getHeroes(
            @RequestParam(value = "searchTerm", required = false) Optional<String> searchTerm) {
        return new ResponseEntity<>(heroService.getHeroes(searchTerm), HttpStatus.OK);
    }
}
