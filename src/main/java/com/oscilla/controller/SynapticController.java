package com.oscilla.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oscilla.service.SynapticService;

@RestController
public class SynapticController {

    private final SynapticService synapticService;

    public SynapticController(SynapticService synapticService) {
        this.synapticService = synapticService;
    }

    @GetMapping("/grid")
    public int[][] getGrid() {
        return synapticService.getGrid();
    }

    @PostMapping("/step")
    public int[][] step() {
        synapticService.step();
        return synapticService.getGrid();
    }
}
