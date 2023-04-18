package com.example.demo.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
                new Hero(1L, "Superman"),
                new Hero(2L, "Batman"),
                new Hero(3L, "Spiderman")
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
                new Hero(1L, "Superman"),
                new Hero(2L, "Spiderman"),
                new Hero(3L, "Manolito el fuerte")
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
        Hero hero = new Hero(1L, "Ironman");
        CreateHeroDTO createHeroDTO = new CreateHeroDTO("Ironman");
        Mockito.when(heroRepository.save(hero)).thenReturn(hero);

        // When
        Hero result = heroService.createHero(createHeroDTO);

        // Then
        assertThat(result).isEqualTo(hero);
        Mockito.verify(heroRepository).save(hero);
    }
}