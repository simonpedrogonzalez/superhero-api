package com.example.demo.controllers;

import com.example.demo.domain.dtos.CreateHeroDTO;
import com.example.demo.domain.dtos.UpdateHeroDTO;
import com.example.demo.domain.entities.Hero;
import com.example.demo.services.HeroService;
import com.example.demo.utils.ContentResponse;
import com.example.demo.utils.TimedAPICall;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@Validated
@RequestMapping("/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @TimedAPICall
    @GetMapping
    public ResponseEntity<ContentResponse<Hero>> getHeroes(
            @RequestParam(value = "name", required = false) Optional<String> name) {
        return new ResponseEntity<>(heroService.getHeroes(name), HttpStatus.OK);
    }

    @TimedAPICall
    @PostMapping
    public ResponseEntity<Hero> createHero(@Valid @RequestBody CreateHeroDTO createHeroDTO) {
        return new ResponseEntity<>(heroService.createHero(createHeroDTO), HttpStatus.CREATED);
    }

    @TimedAPICall
    @PutMapping("/{heroId}")
    public ResponseEntity<Hero> updateHero(@PathVariable Long heroId, @RequestBody @Valid UpdateHeroDTO updateHeroDTO) {
        return new ResponseEntity<>(heroService.updateHero(heroId, updateHeroDTO), HttpStatus.OK);
    }

    @TimedAPICall
    @DeleteMapping("/{heroId}")
    public ResponseEntity<Hero> deleteHero(@PathVariable Long heroId) {
        return new ResponseEntity<>(heroService.deleteHero(heroId), HttpStatus.OK);
    }

    @TimedAPICall
    @GetMapping("/{heroId}")
    public ResponseEntity<Hero> getHero(@PathVariable Long heroId) {
        return new ResponseEntity<>(heroService.getHero(heroId), HttpStatus.OK);
    }
}
