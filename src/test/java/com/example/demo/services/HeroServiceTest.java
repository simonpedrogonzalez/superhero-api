package com.example.demo.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.demo.domain.dtos.CreateHeroDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.demo.domain.entities.Hero;
import com.example.demo.domain.repositories.HeroRepository;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
class HeroServiceTest {

    @Mock
    private HeroRepository heroRepository;

    @InjectMocks
    private HeroService heroService;

    @Test
    void shouldReturnAllHeroes() {
        // Given
        List<Hero> heroes = Arrays.asList(
                new Hero("Superman"),
                new Hero("Batman"),
                new Hero("Spiderman")
        );
        Mockito.when(heroRepository.findAll()).thenReturn(heroes);

        // When
        List<Hero> result = heroService.getHeroes(Optional.empty());

        // Then
        assertThat(result).containsExactlyElementsOf(heroes);
        Mockito.verify(heroRepository).findAll();
    }

    @Test
    void shouldReturnAllHeroesContaining() {
        // Given
        String searchTerm = "man";
        List<Hero> heroes = Arrays.asList(
                new Hero("Superman"),
                new Hero("Spiderman"),
                new Hero("Manolito el fuerte")
        );

        Mockito.when(heroRepository.findByNameContainingIgnoreCase(searchTerm)).thenReturn(heroes);

        // When
        List<Hero> result = heroService.getHeroes(Optional.of(searchTerm));

        // Then
        assertThat(result).containsExactlyElementsOf(heroes);
        Mockito.verify(heroRepository).findByNameContainingIgnoreCase(searchTerm);
    }

    @Test
    void shouldCreateHero() {
        // Given
        Hero hero = new Hero("Ironman");
        CreateHeroDTO createHeroDTO = new CreateHeroDTO("Ironman");
        Mockito.when(heroRepository.save(Mockito.any(Hero.class))).thenReturn(hero);

        // When
        Hero result = heroService.createHero(createHeroDTO);

        // Then
        assertThat(result).isEqualTo(hero);
        Mockito.verify(heroRepository).save(Mockito.any(Hero.class));
    }

    @Test
    void shouldUpdateHero() {
        // Given
        Long heroId = 1L;
        Hero existingHero = new Hero(heroId, "Ironman");
        UpdateHeroDTO updateHeroDTO = new UpdateHeroDTO("Tony Stark");
        Mockito.when(heroRepository.findById(heroId)).thenReturn(Optional.of(existingHero));
        Mockito.when(heroRepository.save(Mockito.any(Hero.class))).thenReturn(existingHero);

        // When
        Hero result = heroService.updateHero(heroId, updateHeroDTO);

        // Then
        assertThat(result).isEqualTo(existingHero);
        assertThat(result.getName()).isEqualTo("Tony Stark");
        Mockito.verify(heroRepository).findById(heroId);
        Mockito.verify(heroRepository).save(Mockito.any(Hero.class));
    }
}