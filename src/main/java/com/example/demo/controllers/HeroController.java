package com.example.demo.controllers;

import com.example.demo.domain.entities.Hero;
import com.example.demo.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;
    
    @GetMapping
    public ResponseEntity<List<Hero>> getHeroes() {
        List<Hero> heroes = heroService.getAllHeroes();
        return new ResponseEntity<>(heroes, HttpStatus.OK);
    }
}
