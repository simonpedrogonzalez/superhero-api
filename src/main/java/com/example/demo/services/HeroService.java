package com.example.demo.services;

import com.example.demo.domain.dtos.CreateHeroDTO;
import com.example.demo.domain.dtos.UpdateHeroDTO;
import com.example.demo.domain.entities.Hero;
import com.example.demo.domain.repositories.HeroRepository;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.models.Problems;
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

    public Hero createHero(CreateHeroDTO createHeroDTO) {
        return heroRepository.save(new Hero(createHeroDTO.getName()));
    }

    public Hero updateHero(Long heroId, UpdateHeroDTO updateHeroDTO) {
        return heroRepository.findById(heroId)
                .map(hero -> {
                    hero.setName(updateHeroDTO.getName());
                    return heroRepository.save(hero);
                })
                .orElseThrow(() -> new NotFoundException(Problems.HERO_NOT_FOUND));
    }

    public Hero deleteHero(Long heroId) {
        return heroRepository.findById(heroId)
                .map(hero -> {
                    heroRepository.delete(hero);
                    return hero;
                })
                .orElseThrow(() -> new NotFoundException(Problems.HERO_NOT_FOUND));
    }
}