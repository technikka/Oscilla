package com.oscilla.simulation;

import java.util.Random;

public class Synaptic implements CellularAutomaton {

    private int[][] grid;

    public Synaptic() {
        Random rand = new Random();
        initializeGrid(rand);
    }

    private void initializeGrid(Random rand) {
        grid = new int[50][50];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int state = rand.nextDouble() < 0.15 ? 1 : 0;
                grid[row][col] = state;
            }
        }
    }

    @Override
    public int[][] getGrid() {
        return grid;
    }

    @Override
    public void step() {
        int[][] nextGrid = new int[grid.length][grid[0].length];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int state = grid[row][col];

                // Off cells become firing if exactly two neighbors are firing.
                int neighborsFiring = 0;

                if (state == 0) {
                    // Check neighbors
                    for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
                        for (int colOffset = -1; colOffset <= 1; colOffset++) {

                            // Skip self
                            if (rowOffset == 0 && colOffset == 0) {
                                continue;
                            }

                            int neighborRow = row + rowOffset;
                            int neighborCol = col + colOffset;

                            // Skip neighbors outside grid boundaries
                            if (neighborRow < 0 || neighborRow >= grid.length ||
                                    neighborCol < 0 || neighborCol >= grid[neighborRow].length) {
                                continue;
                            }

                            // Count firing neighbors
                            if (grid[neighborRow][neighborCol] == 1) {
                                neighborsFiring++;
                            }
                        }
                    }

                    // Apply Brian's Brain rule
                    if (neighborsFiring == 2) {
                        nextGrid[row][col] = 1;
                    }
                    // Firing Cells become refactory
                } else if (state == 1) {
                    nextGrid[row][col] = 2;
                    // Refactory cells become off
                } else {
                    nextGrid[row][col] = 0;
                }
            }
        }

        grid = nextGrid;
    }

    @Override
    public void reset() {
        // some code
    }

}