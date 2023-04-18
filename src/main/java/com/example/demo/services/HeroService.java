package com.example.demo.services;

import com.example.demo.domain.entities.Hero;
import com.example.demo.domain.repositories.HeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService {

    private final HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public List<Hero> getHeroes(Optional<String> searchTerm) {
        return searchTerm
                .map((q) -> heroRepository.findByNameContainingIgnoreCase(q))
                .orElseGet(() -> heroRepository.findAll());
    }
}