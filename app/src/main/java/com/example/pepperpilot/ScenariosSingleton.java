package com.example.pepperpilot;

import android.app.Activity;

import com.example.pepperpilot.models.Scenario;

import java.util.ArrayList;
import java.util.List;

public class ScenariosSingleton {
    private static ScenariosSingleton instance;
    private static List<Scenario> scenarios;

    public static ScenariosSingleton getInstance() {
        if (instance == null) {
            instance = new ScenariosSingleton();
            scenarios = new ArrayList<>();
        }
        return instance;
    }

    public List<Scenario> getScenarios() {
        return scenarios;
    }

}
