import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void constructor_NullParamPassed_ThrowsIllegalArgumentException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                new Horse(null, 0.1, 0.1);
            });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\n", "\n\n\n", "\t", "\t\t\t\t", "\r"})
    public void constructor_EmptyListParamPassed_ThrowsIllegalArgumentException(String name) {
        String exceptionText = "Name cannot be blank.";
        Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 0.1, 0.1);
        });
        assertEquals(exceptionText, exception1.getMessage());
    }

    @Test
    public void constructor_NegativeSpeedParamPassed_ThrowsIllegalArgumentException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", -0.1, 0.1);
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void constructor_NegativeDistanceParamPassed_ThrowsIllegalArgumentException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", 0.1, -0.1);
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getNameTest() {
        // Arrange - вхідні дані
        String name = "name";
        double speed = 0.1;
        double distance = 0.2;

        // Act - виклик методу, який тестуємо
        Horse horse = new Horse(name, speed, distance);

        // Assert - перевірка результату
        assertEquals(name, horse.getName());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.3})
    public void getSpeed_ReturnsTheCorrectValue(double speed) {
        Horse horse = new Horse("name", speed, 0.2);

        double actualSpeed = horse.getSpeed();

        assertEquals(speed, actualSpeed);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.3})
    public void getDistance_ReturnsTheCorrectValue(double distance) {
        Horse horse = new Horse("name", 0.1, distance);

        double actualDistance = horse.getDistance();

        assertEquals(distance, actualDistance);
    }

    @Test
    public void move_CallsRandomMethodWithParams() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("name", 0.1, 0.2);

            horse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }

    }

    @Test
    public void move_ChangesDistance() {
        Horse horse = new Horse("name", 1, 250);
        double distanceBeforeMove = horse.getDistance();

        horse.move();

        double distanceAfterMove = horse.getDistance();
        assertTrue(distanceAfterMove > distanceBeforeMove);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9, 1.0, 99.0, 0.0})
    public void getRandomDouble_ReturnsTheCorrectValue(double fakeRandom) {
        double min = 0.2;
        double max = 0.9;
        double speed = 30;
        double distance = 250;

        Horse horse = new Horse("name", speed, distance);

        try (MockedStatic<Horse> mathMockedStatic = mockStatic(Horse.class)) {
            mathMockedStatic.when(() -> Horse.getRandomDouble(min, max)).thenReturn(fakeRandom);

            horse.move();

            assertEquals(distance + speed * fakeRandom, horse.getDistance());
        }
    }
}
