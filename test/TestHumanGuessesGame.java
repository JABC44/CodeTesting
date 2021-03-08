import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestHumanGuessesGame {


    @Test
    public void testConstructorNoTestValue()
    {
        HumanGuessesGame testGame = new HumanGuessesGame();
        assertEquals(0,testGame.getNumGuesses());
        assertEquals(false,testGame.isDone());
    }

    @Test
    public void testConstructorStubRand()
    {
        HumanGuessesGame testGame = new HumanGuessesGame(new MyRandom(40));
        assertEquals(41,testGame.getTarget());
    }

    @Test
    public void testIncrementGuesses()
    {
        HumanGuessesGame testGame = new HumanGuessesGame();
        testGame.incrementNumGuesses();
        testGame.incrementNumGuesses();
        assertEquals(2,testGame.getNumGuesses());
    }

    //using dependency injection
    @Test
    public void testMakeGuessLOW()
    {
        HumanGuessesGame testGame = new HumanGuessesGame(new MyRandom(40));
        GuessResult res = testGame.makeGuess(20);
        assertEquals(GuessResult.LOW,res);
        assertEquals(1,testGame.getNumGuesses());
        assertEquals(false,testGame.isDone());
    }

    @Test
    public void testMakeGuessHIGH()
    {
        HumanGuessesGame testGame = new HumanGuessesGame(new MyRandom(40));
        GuessResult res = testGame.makeGuess(60);
        assertEquals(GuessResult.HIGH,res);
        assertEquals(1,testGame.getNumGuesses());
        assertEquals(false,testGame.isDone());
    }

    @Test
    public void testMakeGuessEQUALS()
    {
        HumanGuessesGame testGame = new HumanGuessesGame(new MyRandom(40));
        GuessResult res = testGame.makeGuess(41);
        assertEquals(GuessResult.CORRECT,res);
        assertEquals(1,testGame.getNumGuesses());
        assertEquals(true,testGame.isDone());
    }

    @Test
    public void testMakeGuessNegative()
    {
        HumanGuessesGame testGame = new HumanGuessesGame(new MyRandom(40));
        GuessResult res = testGame.makeGuess(-20);
        assertEquals(GuessResult.LOW,res);
        assertEquals(1,testGame.getNumGuesses());
        assertEquals(false,testGame.isDone());
    }

    @Test
    public void testMakeGuessZero()
    {
        HumanGuessesGame testGame = new HumanGuessesGame(new MyRandom(40));
        GuessResult res = testGame.makeGuess(0);
        assertEquals(GuessResult.LOW,res);
        assertEquals(1,testGame.getNumGuesses());
        assertEquals(false,testGame.isDone());
    }

    @Test
    public void testMakeGuessSuperHigh()
    {
        HumanGuessesGame testGame = new HumanGuessesGame(new MyRandom(40));
        GuessResult res = testGame.makeGuess(10000);
        assertEquals(GuessResult.HIGH,res);
        assertEquals(1,testGame.getNumGuesses());
        assertEquals(false,testGame.isDone());
    }

    @Test
    public void testMakeGuessSuperOneOff()
    {
        HumanGuessesGame testGame = new HumanGuessesGame(new MyRandom(40));
        GuessResult res = testGame.makeGuess(40);
        assertEquals(GuessResult.LOW,res);
        assertEquals(1,testGame.getNumGuesses());
        assertEquals(false,testGame.isDone());
    }
}
