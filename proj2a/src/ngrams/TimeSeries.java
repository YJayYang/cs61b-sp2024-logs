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

    private List<Integer> sort(List<Integer> list) {
        if (this.isEmpty()) {
            return null;
        }
        list = this.years();
        Collections.sort(list);
        return list;
    }

    private List<Integer> compareSize(List<Integer> a, List<Integer> b) {
        if (a.size() <= b.size()) {
            return a;
        }
        return b;
    }


    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        // TODO: Fill in this method.
        if (this.isEmpty()) {
            return new ArrayList<>();
        }
        Set<Integer> yearsSet = this.keySet();
        return new ArrayList<>(yearsSet);

    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        if (this.isEmpty()) {
            return new ArrayList<>();
        }
        List<Integer> year = this.years();
        List<Double> data = new ArrayList<>();
        for (Integer y : year) {
            data.add(this.get(y));
        }
        return data;
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
        if (checkEmpty(ts) && checkEmpty(this)) {
            return ts;
        }
        TimeSeries timeSeriesPlus = new TimeSeries();
        List<Integer> tsYears = ts.years();
        List<Integer> thisYears = this.years();

        for (Integer year : thisYears) {
            if (tsYears.contains(year)) {
                timeSeriesPlus.put(year, this.get(year) + ts.get(year));
            } else {
                timeSeriesPlus.put(year, this.get(year));
            }
        }

        for (Integer year : tsYears) {
            if (!thisYears.contains(year)) {
                timeSeriesPlus.put(year, ts.get(year));
            }
        }

        return timeSeriesPlus;
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
        List<Integer> tsYears = ts.years();
        List<Integer> thisYears = this.years();

        for (Integer year : thisYears) {
            if (!tsYears.contains(year)) {
                throw new IllegalArgumentException("The given TimeSeries is missing year: " + year);
            }
            if (ts.get(year) == 0.0) {
                throw new IllegalArgumentException("the dividedBy operation never divides by zero");
            }
            result.put(year, this.get(year) / ts.get(year));
        }

        return result;

    }
}
    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.

