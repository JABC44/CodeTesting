import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestComputerGuessesGame {

    @Test
    public void testConstructor()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        assertEquals(0,testGame.getNumGuesses());
        assertEquals(501,testGame.getLastGuess());
        assertEquals(1,testGame.getLowerBound());
        assertEquals(1000,testGame.getUpperBound());
    }

    @Test
    public void testSetUpperBoundNormal()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        int val = testGame.calcNewUpperBound(100,50);
        assertEquals(50,val);
    }

    @Test
    public void testSetUpperBoundGuessZero()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        int val = testGame.calcNewUpperBound(10,0);
        assertEquals(0,val);
    }

    @Test
    public void testSetUpperBoundGuessNegative()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        int val = testGame.calcNewUpperBound(10,-10);
        assertEquals(-10,val);
    }


    @Test
    public void testSetLowerBoundNormal()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        int val = testGame.calcNewLowerBound(100,150);
        assertEquals(151,val);
    }

    @Test
    public void testSetLowerBoundSameValue()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        int val = testGame.calcNewLowerBound(5,5);
        assertEquals(6,val);
    }

    @Test
    public void testSetLowerBoundLowerGuess()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        int val = testGame.calcNewLowerBound(5,-5);
        assertEquals(5,val);
    }

    @Test
    public void testIncrementNumGuesses()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        testGame.incrementNumGuesses();
        assertEquals(1,testGame.getNumGuesses());
    }

    @Test
    public void testFindMiddleNormalClean()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        int val = testGame.findMiddle(10,20);
        assertEquals(15,val);
    }

    @Test
    public void testFindMiddleNormalRoundOff()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        int val = testGame.findMiddle(10,29);
        assertEquals(20,val);
    }

    @Test
    public void testFindMiddleNormalSameVal()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        int val = testGame.findMiddle(5,5);
        assertEquals(5,val);
    }

    @Test
    public void testFindMiddleNormalOffset()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        int val = testGame.findMiddle(10,0);
        assertEquals(5,val);
    }

    @Test
    public void testFindMiddleNormalNegative()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        int val = testGame.findMiddle(-20,20);
        assertEquals(0,val);
    }

    @Test
    public void testFindMiddleNormalBig()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        int val = testGame.findMiddle(0,1000);
        assertEquals(500,val);
    }

    @Test
    public void testHandleGuessEntiretyHigh()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        testGame.handleNonEqualGuess(GuessResult.HIGH);

        assertEquals(1,testGame.getNumGuesses());
        assertEquals(1,testGame.getLowerBound());
        assertEquals(501,testGame.getUpperBound());
        assertEquals(251,testGame.getLastGuess());
    }

    @Test
    public void testHandleGuessEntiretyLow()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        testGame.handleNonEqualGuess(GuessResult.LOW);

        assertEquals(1,testGame.getNumGuesses());
        assertEquals(502,testGame.getLowerBound());
        assertEquals(1000,testGame.getUpperBound());
        assertEquals(751,testGame.getLastGuess());
    }

    @Test
    public void testHandleGuessEntiretyEqual()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        assertThrows(IllegalArgumentException.class,()->{testGame.handleNonEqualGuess(GuessResult.CORRECT);});
    }

    @Test
    public void testGrabResultFromStart()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        GameResult res = testGame.grabResult();
        assertEquals(501,res.correctValue);
        assertEquals(1,res.numGuesses);
        assertEquals(false,res.humanWasPlaying);
    }

    @Test
    public void testGrabResultAfterTwoGuesses()
    {
        ComputerGuessesGame testGame = new ComputerGuessesGame();
        testGame.handleNonEqualGuess(GuessResult.HIGH);
        testGame.handleNonEqualGuess(GuessResult.HIGH);
        GameResult res = testGame.grabResult();
        assertEquals(126,res.correctValue);
        assertEquals(3,res.numGuesses);
        assertEquals(false,res.humanWasPlaying);
    }

}
