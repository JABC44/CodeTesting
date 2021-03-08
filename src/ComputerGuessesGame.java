/**
 * UI screen for when the computer is guessing a number
 *
 * Tracks Computer's Progress to finding number
 *
 */
public class ComputerGuessesGame{

    private int numGuesses;
    private int lastGuess;

    // upperBound and lowerBound track the computer's knowledge about the correct number
    // They are updated after each guess is made
    private int upperBound; // correct number is <= upperBound
    private int lowerBound; // correct number is >= lowerBound

    public ComputerGuessesGame()
    {
        numGuesses = 0;
        upperBound = 1000;
        lowerBound = 1;
        lastGuess = findMiddle(lowerBound,upperBound);
    }

    int calcNewUpperBound(int upBound, int guess)
    {
        return Math.min(upBound, guess);
    }

    int calcNewLowerBound(int lowBound, int guess)
    {
        return Math.max(lowBound, guess + 1);
    }

    void incrementNumGuesses()
    {
        numGuesses += 1;
    }

    int findMiddle(int lowBound,int upBound)
    {
        int middle = (lowBound + upBound + 1) / 2;
        return middle;
    }

    void handleNonEqualGuess(GuessResult result)
    {
        if(result == GuessResult.HIGH)
        {
            upperBound = calcNewUpperBound(upperBound,lastGuess);
        }
        else if(result == GuessResult.LOW)
        {
            lowerBound = calcNewLowerBound(lowerBound,lastGuess);
        }
        else
        {
            throw new IllegalArgumentException("Guess should not be equal.");
        }

        lastGuess = findMiddle(lowerBound,upperBound);
        incrementNumGuesses();
    }

    GameResult grabResult()
    {
        GameResult result = new GameResult(false, lastGuess, numGuesses);
        return result;
    }

    public int getNumGuesses()
    {
        return numGuesses;
    }

    public int getUpperBound()
    {
        return upperBound;
    }

    public int getLowerBound()
    {
        return lowerBound;
    }
    public int getLastGuess()
    {
        return lastGuess;
    }

}
