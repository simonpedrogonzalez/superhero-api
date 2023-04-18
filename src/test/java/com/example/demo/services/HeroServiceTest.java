import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import com.example.demo.domain.entities.Hero;
import com.example.demo.domain.repositories.HeroRepository;
import com.example.demo.services.HeroService;
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
        List<Hero> result = heroService.getAllHeroes();

        // Then
        assertThat(result).containsExactlyElementsOf(heroes);
        Mockito.verify(heroRepository).findAll();
    }
}