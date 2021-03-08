import org.junit.Test;
import java.time.DateTimeException;
import static org.junit.jupiter.api.Assertions.*;

public class TestStatsFile {

    @Test
    public void testNumGuesses(){
        StatsFile testFile = new StatsFile();
        assertEquals(2, testFile.numGames(10));
    }

    @Test
    public void testMaxTestGuesses(){
        StatsFile testFile = new StatsFile();
        assertEquals(18,testFile.maxNumGuesses());
    }

    @Test
    public void testLastBin(){
        int[] intArray = new int[3];
        intArray[0] = 3;
        intArray[1] = 4;
        intArray[2] = 5;
        StatsFile testFile = new StatsFile();
        assertEquals(9, testFile.findTotalGamesInBin(intArray, 2));
    }

    @Test
    public void testLastBinEmptyArray(){
        int[] intArray = new int[3];
        StatsFile testFile = new StatsFile();
        assertEquals(0, testFile.findTotalGamesInBin(intArray, 0));
    }

    @Test
    public void NumberException() {
        StatsFile testFile = new StatsFile();
        assertNotEquals(NumberFormatException.class, testFile);
    }

    @Test
    public void DateException(){
        StatsFile testFile = new StatsFile();
        assertNotEquals(DateTimeException.class, testFile);
    }

}
