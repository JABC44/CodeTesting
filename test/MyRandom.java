import java.util.Random;

public class MyRandom extends Random {

    private int value;

    MyRandom(int setVal)
    {
        value= setVal;
    }

    @Override
    public int nextInt()
    {
        return value;
    }

    @Override
    public int nextInt(int bound)
    {
        return value;
    }
}


