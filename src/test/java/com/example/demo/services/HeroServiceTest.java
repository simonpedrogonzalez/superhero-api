package com.example.demo.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.demo.domain.dtos.CreateHeroDTO;
import com.example.demo.domain.dtos.UpdateHeroDTO;
import com.example.demo.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.demo.domain.entities.Hero;
import com.example.demo.domain.repositories.HeroRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void shouldDeleteHero() {
        // Given
        Hero heroToDelete = new Hero(1L, "Spiderman");
        Mockito.when(heroRepository.findById(1L)).thenReturn(Optional.of(heroToDelete));

        // When
        Hero deletedHero = heroService.deleteHero(1L);

        // Then
        assertThat(deletedHero).isEqualTo(heroToDelete);
        Mockito.verify(heroRepository).delete(heroToDelete);
    }

    @Test
    void shouldThrowHeroNotFoundExceptionOnUpdate() {
        // Given
        Long heroId = 1L;
        UpdateHeroDTO updateHeroDTO = new UpdateHeroDTO("Spiderman");
        Mockito.when(heroRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        // When/Then
        assertThrows(NotFoundException.class, () -> heroService.updateHero(heroId, updateHeroDTO));
        Mockito.verify(heroRepository).findById(Mockito.anyLong());
    }

    @Test
    void shouldThrowHeroNotFoundExceptionOnDelete() {
        // Given
        Mockito.when(heroRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        // When/Then
        assertThrows(NotFoundException.class, () -> heroService.deleteHero(1L));
        Mockito.verify(heroRepository).findById(Mockito.anyLong());
    }

    @Test
    void shouldReturnOneHero() {
        // Given
        Long heroId = 1L;
        Hero hero = new Hero(heroId, "Ironman");
        Mockito.when(heroRepository.findById(heroId)).thenReturn(Optional.of(hero));

        // When
        Hero result = heroService.getHero(heroId);

        // Then
        assertThat(result).isEqualTo(hero);
        Mockito.verify(heroRepository).findById(heroId);
    }

    @Test
    void shouldThrowHeroNotFoundExceptionOnGetOne() {
        // Given
        Long heroId = 1L;
        Mockito.when(heroRepository.findById(heroId)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(NotFoundException.class, () -> heroService.getHero(heroId));
        Mockito.verify(heroRepository).findById(heroId);
    }
}