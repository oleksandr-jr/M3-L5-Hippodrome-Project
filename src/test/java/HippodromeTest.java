import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HippodromeTest {
    @Test
    public void constructor_NullParamPassed_ThrowsIllegalArgumentException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void constructor_EmptyListParamPassed_ThrowsIllegalArgumentException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<>());
        });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getHorses_ReturnsListWithAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse(String.valueOf(i), (double) i/10, (double) i/5));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals("1", hippodrome.getHorses().get(0).getName());
        assertEquals(1.5, hippodrome.getHorses().get(14).getSpeed());
        assertEquals(6.0, hippodrome.getHorses().get(29).getDistance());
    }


    @Test
    @ExtendWith(MockitoExtension.class)
    public void move_MovesAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }



    @Test
    public void getWinner_ReturnsTheCorrectWinner() {
        Hippodrome hippodrome = new Hippodrome(List.of(
            new Horse("10", 1, 10),
            new Horse("20", 1, 20),
            new Horse("10.1", 1, 10.1)));
        assertEquals("20", hippodrome.getWinner().getName());
    }
}
