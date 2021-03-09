import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * File-backed implementation of GameStats
 *
 * Returns the number of games *within the last 30 days* where the person took a given number of guesses
 */
public class StatsFile extends GameStats {
    public static final String FILENAME = "guess-the-number-stats.csv";


    // maps the number of guesses required to the number of games within
    // the past 30 days where the person took that many guesses
    private SortedMap<Integer, Integer> statsMap;

    //Tough to say if we need anymore smaller functions, might be worth to seperate the statsMap.put line, but I feel
    // like you are just sending all params to it anyway.
    public StatsFile(){
        statsMap = new TreeMap<>();
        LocalDateTime limit = generateLimit();

        try (CSVReader csvReader = new CSVReader(new FileReader(FILENAME))) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                // values should have the date and the number of guesses as the two fields
                try {
                    LocalDateTime timestamp = LocalDateTime.parse(values[0]);
                    int numGuesses = Integer.parseInt(values[1]);

                    limitTest(timestamp, limit, numGuesses);
                }
                catch(NumberFormatException nfe){
                    // NOTE: In a full implementation, we would log this error and possibly alert the user
                    throw nfe;
                }
                catch(DateTimeParseException dtpe){
                    // NOTE: In a full implementation, we would log this error and possibly alert the user
                    throw dtpe;
                }
            }
        } catch (CsvValidationException e) {
            // NOTE: In a full implementation, we would log this error and alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
        } catch (IOException e) {
            // NOTE: In a full implementation, we would log this error and alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
        }
    }

    public boolean limitTest(LocalDateTime timestamp, LocalDateTime limit, int numGuesses){
        if (timestamp.isBefore(limit)) {
            treeMapPut(numGuesses);
            return true;
        }
        else{
            return false;
        }
    }

    public LocalDateTime generateLimit()
    {
        return  LocalDateTime.now().minusDays(30);
    }

    public LocalDateTime generateLimit(LocalDateTime timeTest)
    {
        return  timeTest.minusDays(30);
    }

    @Override
    public int numGames(int numGuesses) {
        return statsMap.getOrDefault(numGuesses, 0);
    }

    @Override
    public int maxNumGuesses(){
        return statsMap.lastKey();
    }

    @Override
    public void recordGame(GameResult result) {
        // write stats to file
        try (CSVWriter writer = new CSVWriter(new FileWriter(StatsFile.FILENAME, true))) {

            String[] record = new String[2];
            record[0] = LocalDateTime.now().toString();
            record[1] = Integer.toString(result.numGuesses);

            writer.writeNext(record);
        } catch (IOException e) {
            // NOTE: In a full implementation, we would log this error and possibly alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
        }
    }

    public void treeMapPut(int numGuesses){
        statsMap.put(numGuesses, 1 + statsMap.getOrDefault(numGuesses, 0));
    }

    @Override
    public int findTotalGamesInBin(int[] BIN_EDGES,int binIndex)
    {
        final int lowerBound = BIN_EDGES[binIndex];

        int numGames = 0;

        if(binIndex == BIN_EDGES.length-1) {
            // last bin
            // Sum all the results from lowerBound on up
            numGames += findLastBinNum(lowerBound);
        }
        else{
            int upperBound = BIN_EDGES[binIndex+1];
            numGames+=findNonLastBinNum(upperBound,lowerBound);
        }

        return numGames;
    }

    public int findLastBinNum(int lowerBound) {
        int total = 0;
        for (int numGuesses = lowerBound; numGuesses < maxNumGuesses(); numGuesses++) {
            total += numGames(numGuesses);
        }
        return total;
    }

    public int findNonLastBinNum(int upperBound,int lowerBound) {
        int total = 0;
        for (int numGuesses = lowerBound; numGuesses <= upperBound; numGuesses++) {
            total += numGames(numGuesses);
        }
        return total;
    }

}
