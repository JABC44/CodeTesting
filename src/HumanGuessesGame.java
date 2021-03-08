import java.util.Random;

/**
 * A game where a human guesses a number between 1 and UPPER_BOUND
 * Tracks the target, the number of guesses made, and if the number has been guessed
 *
 * NOTE: You can refactor and edit this file if needed
 */
public class HumanGuessesGame {
    public final static int UPPER_BOUND = 1000;

    private final int target;
    private int numGuesses;
    private boolean gameIsDone; // true iff makeGuess has been called with the target value

    //JOSH: Will probs want to create stub for random here

    HumanGuessesGame()
    {
        this(new Random());
    }

    HumanGuessesGame(Random rand){
        this.target = rand.nextInt(UPPER_BOUND) + 1;

        numGuesses = 0;
        gameIsDone = false;
    }

    //JOSH: Perhaps seperate increment and checks, and return.
    GuessResult makeGuess(int value){
        incrementNumGuesses();

        if(value < target){
            return GuessResult.LOW;
        }
        if(value > target){
            return GuessResult.HIGH;
        }

        return GuessResult.CORRECT;
    }

    int getNumGuesses(){
        return numGuesses;
    }

    boolean isDone(){
        return gameIsDone;
    }

    void incrementNumGuesses()
    {
        numGuesses += 1;
    }

    int getTarget()
    {
        return target;
    }
}
