package com.oscilla.simulation;

public interface CellularAutomaton {
    void step();

    void reset();

    int[][] getGrid();
}
