import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    @Test
    public void nullNameExceptionTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                new Horse(null, 0.1, 0.1);
            });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @Test
    public void blankNameExceptionTest() {
        String exceptionText = "Name cannot be blank.";
        Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(" ", 0.1, 0.1);
        });
        assertEquals(exceptionText, exception1.getMessage());

        Throwable exception2 = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("", 0.1, 0.1);
        });
        assertEquals(exceptionText, exception2.getMessage());

        Throwable exception3 = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("\n", 0.1, 0.1);
        });
        assertEquals(exceptionText, exception3.getMessage());

        Throwable exception4 = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("\t", 0.1, 0.1);
        });
        assertEquals(exceptionText, exception4.getMessage());
    }

    @Test
    public void negativeSpeedExceptionTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", -0.1, 0.1);
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void negativeDistanceExceptionTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("name", 0.1, -0.1);
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getNameTest() {
        assertEquals("name", new Horse("name", 0.1, 0.2).getName());
    }

    @Test
    public void getSpeedTest() {
        assertEquals(0.1, new Horse("name", 0.1, 0.2).getSpeed());
    }

    @Test
    public void getDistanceTest() {
        assertEquals(0.5, new Horse("name", 0.1, 0.5).getDistance());
        assertEquals(0, new Horse("name", 0.1).getDistance());
    }
}
