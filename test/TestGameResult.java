import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestGameResult {

    @Test
    public void testNumGuesses(){
        GameResult testResult = new GameResult(true, 0, 5);
        assertEquals(5, testResult.numGuesses);
    }

    @Test
    public void testHumanWasPlaying(){
        GameResult testResult = new GameResult(true, 0, 5);
        assertTrue(testResult.humanWasPlaying);
    }

    @Test
    public void testCorrectValue(){
        GameResult testResult = new GameResult(true, 0, 5);
        assertEquals(0, testResult.correctValue);
    }
}
