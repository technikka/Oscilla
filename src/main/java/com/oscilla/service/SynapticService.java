package com.oscilla.service;

import org.springframework.stereotype.Service;

import com.oscilla.simulation.Synaptic;

@Service
public class SynapticService {

    Synaptic synaptic = new Synaptic();

    public int[][] getGrid() {
        return synaptic.getGrid();
    }

    public void step() {
        synaptic.step();
    }
}
