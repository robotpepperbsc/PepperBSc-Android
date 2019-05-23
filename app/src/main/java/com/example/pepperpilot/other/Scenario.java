package com.example.pepperpilot.other;

public class Scenario {
    private String name;

    public Scenario(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Scenario: " + this.name;
    }
}
