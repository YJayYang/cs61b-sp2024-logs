package ngrams;

import java.util.*;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /**
     * If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here.
     */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        // TODO: Fill in this constructor.
        validateYearRange(startYear, endYear);
        copyTimeSeries(ts, startYear, endYear);
    }


    private void validateYearRange(int startYear, int endYear) {
        if (startYear < MIN_YEAR || endYear > MAX_YEAR) {
            throw new IllegalArgumentException("year out of range");
        }
    }

    private void copyTimeSeries(TimeSeries ts, int startYear, int endYear) {
        for (int year = startYear; year <= endYear; year++) {
            if (ts.containsKey(year)) {
                this.put(year, ts.get(year));
            }
        }
    }

    private boolean checkEmpty(TimeSeries ts) {
        return ts.isEmpty();
    }



    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        // TODO: Fill in this method.
        if (this.isEmpty()) {
            return new ArrayList<>();
        }

        return new ArrayList<>(this.keySet());

    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        List<Double> dataList = new ArrayList<>();
        for (Integer year : this.years()) {
            dataList.add(this.get(year));
        }
        return dataList;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     * <p>
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries result = new TimeSeries();
        for (Map.Entry<Integer, Double> entry : this.entrySet()) {
            int year = entry.getKey();
            double value = entry.getValue() + ts.getOrDefault(year, 0.0);
            result.put(year, value);
        }
        for (Map.Entry<Integer, Double> entry : ts.entrySet()) {
            int year = entry.getKey();
            if (!this.containsKey(year)) {
                result.put(year, entry.getValue());
            }
        }
        return result;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     * <p>
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries result = new TimeSeries();
        for (Map.Entry<Integer, Double> entry : this.entrySet()) {
            int year = entry.getKey();
            if (!ts.containsKey(year)) {
                throw new IllegalArgumentException("给定的时间序列缺少年份：" + year);
            }
            double divisor = ts.get(year);
            if (divisor == 0.0) {
                throw new IllegalArgumentException("除数不能为零。");
            }
            result.put(year, entry.getValue() / divisor);
        }
        return result;

    }
}
    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.

