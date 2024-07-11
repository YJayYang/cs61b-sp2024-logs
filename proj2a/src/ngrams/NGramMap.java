package ngrams;

import edu.princeton.cs.algs4.In;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {
    // TODO: Add any necessary static/instance variables.
    private final Map<String, TimeSeries> wordDataMap = new HashMap<>();
    private final TimeSeries countDataMap = new TimeSeries();

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        loadWordData(wordsFilename);
        loadCountsData(countsFilename);
    }

    private void loadWordData(String wordsFilename) {
        In wordFile = new In(wordsFilename);
        while (wordFile.hasNextLine()) {
            String line = wordFile.readLine();
            String[] splitLine = line.split("\t");
            String word = splitLine[0];
            int year = Integer.parseInt(splitLine[1]);
            double count = Double.parseDouble(splitLine[2]);
            TimeSeries ts = wordDataMap.getOrDefault(word, new TimeSeries());
            ts.put(year, count);
            wordDataMap.put(word, ts);
        }
    }
    private void loadCountsData(String countsFilename) {
        In countsFile = new In(countsFilename);
        while (countsFile.hasNextLine()) {
            String line = countsFile.readLine();
            String[] splitLine = line.split(",");
            int year = Integer.parseInt(splitLine[0]);
            double count = Double.parseDouble(splitLine[1]);
            countDataMap.put(year, count);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        if (wordDataMap.containsKey(word)) {
            TimeSeries original = wordDataMap.get(word);
            return new TimeSeries(original, startYear, endYear);
        }
        return new TimeSeries();
    }


    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        if (wordDataMap.containsKey(word)) {
            TimeSeries original = wordDataMap.get(word);
            return original;
        }
        return new TimeSeries();
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        TimeSeries defensiveCopy = new TimeSeries();
        defensiveCopy = countDataMap;

        return defensiveCopy;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        if (wordDataMap.containsKey(word)) {
            TimeSeries original = new TimeSeries(wordDataMap.get(word), startYear,endYear);
            return original.dividedBy(countDataMap);
        }
        return null;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        if (wordDataMap.containsKey(word)) {
            TimeSeries original = wordDataMap.get(word);
            return original.dividedBy(countDataMap);
        }
        return null;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries summedHistory = new TimeSeries();
        for (String word : words) {
            if (wordDataMap.containsKey(word)) {
                TimeSeries wordHistory = new TimeSeries(wordDataMap.get(word), startYear, endYear);
                summedHistory = summedHistory.plus(wordHistory.dividedBy(countDataMap));
            }
        }
        return summedHistory;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        TimeSeries summedHistory = new TimeSeries();
        for (String word : words) {
            if (wordDataMap.containsKey(word)) {
                TimeSeries wordHistory = wordDataMap.get(word);
                summedHistory = summedHistory.plus(wordHistory.dividedBy(countDataMap));
            }
        }
        return summedHistory;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
