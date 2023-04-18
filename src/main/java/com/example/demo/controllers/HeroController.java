package com.example.demo.controllers;

import com.example.demo.domain.dtos.CreateHeroDTO;
import com.example.demo.domain.dtos.UpdateHeroDTO;
import com.example.demo.domain.entities.Hero;
import com.example.demo.services.HeroService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<Hero> createHero(@RequestBody @Valid CreateHeroDTO createHeroDTO) {
        return new ResponseEntity<>(heroService.createHero(createHeroDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{heroId}")
    public ResponseEntity<Hero> updateHero(@PathVariable Long heroId, @RequestBody @Valid UpdateHeroDTO updateHeroDTO) {
        return new ResponseEntity<>(heroService.updateHero(heroId, updateHeroDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{heroId}")
    public ResponseEntity<Hero> deleteHero(@PathVariable Long heroId) {
        return new ResponseEntity<>(heroService.deleteHero(heroId), HttpStatus.OK);
    }

    @GetMapping("/{heroId}")
    public ResponseEntity<Hero> getHero(@PathVariable Long heroId) {
        return new ResponseEntity<>(heroService.getHero(heroId), HttpStatus.OK);
    }
}
