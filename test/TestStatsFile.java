import org.junit.Test;
import java.time.DateTimeException;
import java.time.LocalDateTime;

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

    @Test
    public void TreeMapInsert1(){
        StatsFile testFile = new StatsFile();
        testFile.treeMapPut(25);
        assertEquals(25, testFile.maxNumGuesses());
    }

    @Test
    public void TreeMapValueTest(){
        StatsFile testFile = new StatsFile();
        testFile.treeMapPut(26);
        assertEquals(1, testFile.numGames(26));
    }

    @Test
    public void TreeMapValueExistingDataTest(){
        StatsFile testFile = new StatsFile();
        assertEquals(1, testFile.numGames(2));
    }

    @Test
    public void TreeMapMultipleNewValueTest(){
        StatsFile testFile = new StatsFile();
        testFile.treeMapPut(26);
        testFile.treeMapPut(26);
        assertEquals(2, testFile.numGames(26));
    }

    @Test
    public void TimeEqualTest(){
        StatsFile testFile = new StatsFile();
        LocalDateTime testDate = LocalDateTime.now().minusDays(30);
        assertEquals(testDate.minusDays(30), testFile.generateLimit(testDate));
    }

    @Test
    public void TimeBeforeTest(){
        StatsFile testFile = new StatsFile();
        LocalDateTime testDate = LocalDateTime.now().minusDays(30);
        assertEquals(testDate.minusDays(35), testFile.generateLimit(testDate));
    }

    @Test
    public void TimeAfterTest(){
        StatsFile testFile = new StatsFile();
        LocalDateTime testDate = LocalDateTime.now().plusDays(30);
        assertEquals(testDate, testFile.generateLimit(testDate));
    }

    @Test
    public void TreeAddBeforeTimeTest(){
        StatsFile testFile = new StatsFile();
        LocalDateTime testDate = LocalDateTime.now().minusDays(15);
        testFile.limitTest(testDate, LocalDateTime.now(), 28);
        assertEquals(1, testFile.numGames(28));
    }

    @Test
    public void TreeAddAfterTimeTest(){
        StatsFile testFile = new StatsFile();
        LocalDateTime testDate = LocalDateTime.now().plusDays(15);
        testFile.limitTest(testDate, LocalDateTime.now(), 28);
        assertEquals(1, testFile.numGames(28));
    }

    @Test
    public void findLastBinTest(){
        StatsFile testFile = new StatsFile();
        assertEquals(0, testFile.findLastBinNum(11));
    }

    @Test
    public void findNonLastBinTest(){
        StatsFile testFile = new StatsFile();
        assertEquals(5, testFile.findNonLastBinNum(15, 5));
    }
}
