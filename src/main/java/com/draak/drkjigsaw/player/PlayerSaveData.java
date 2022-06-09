package com.draak.drkjigsaw.player;

import com.draak.drkjigsaw.Main;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerSaveData {

    private ArrayList<Integer> ownedPlots = new ArrayList<>();

    public enum PlotSize {Small, Large, Massive, Huge}

    // Includes both claimed and unclaimed plots
    private HashMap<PlotSize, Integer> totalOwnedPlots = new HashMap<PlotSize, Integer>();
    private HashMap<Integer, PlotSize> claimedPlots = new HashMap<>();

    public boolean giveEmptyPlotToPlayer(PlotSize plotSize) {

        if (getAvailablePlots(plotSize) > 0) {
            ownedPlots.add(Main.allPlotIDs);
            claimedPlots.put(Main.allPlotIDs, plotSize);

            Main.allPlotIDs++;

            return true;
        }

        return false;
    }
    public void addPlot(PlotSize plotSize, int amount) {
        if (totalOwnedPlots.containsKey(plotSize)) {
            totalOwnedPlots.put(plotSize, totalOwnedPlots.get(plotSize) + amount);
        } else {
            totalOwnedPlots.put(plotSize, amount);
        }
    }
    public HashMap<PlotSize, Integer> getTotalOwnedPlots() {
        return totalOwnedPlots;
    }
    public int getAvailablePlots(PlotSize plotSize) {
        int r =totalOwnedPlots.get(plotSize);
        for (int i : claimedPlots.keySet()) {
            if (claimedPlots.get(i) == plotSize) r--;
        }
        return r;
    }
    public HashMap<Integer, PlotSize> getClaimedPlots() {
        return claimedPlots;
    }


}
