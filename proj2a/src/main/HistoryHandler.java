package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jay
 */
public class HistoryHandler extends NgordnetQueryHandler {

    private final NGramMap map;
    public HistoryHandler(NGramMap map){
        this.map = map;

    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        ArrayList<TimeSeries> lts = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        for (String word : words) {
            TimeSeries ts = map.countHistory(word, startYear, endYear);
            lts.add(ts);
            labels.add(word);
        }
        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);

        return Plotter.encodeChartAsString(chart);
    }
}
