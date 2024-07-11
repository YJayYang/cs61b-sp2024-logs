package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import browser.NgordnetServer;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.ArrayList;
import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private final NGramMap map;
    public HistoryTextHandler(NGramMap map){
        this.map = map;

    }

    @Override
    public String handle(NgordnetQuery q){
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        StringBuilder response = new StringBuilder();

        for (String word : words) {
            TimeSeries wordHistory = map.weightHistory(word, startYear, endYear);
            response.append(word).append(": ").append(wordHistory.toString()).append("\n");
        }

        return response.toString();
    }

}
