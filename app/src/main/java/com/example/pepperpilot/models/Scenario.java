package com.example.pepperpilot.models;

import com.example.pepperpilot.enums.TaskType;

import java.util.List;

public class Scenario extends Task {
    private String name;
    private String lastDateTimeEdited;
    private List<TaskType> tasks;

    public Scenario(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastDateTimeEdited() {
        return lastDateTimeEdited;
    }

    public void setLastDateTimeEdited(String lastDateTimeEdited) {
        this.lastDateTimeEdited = lastDateTimeEdited;
    }

    public List<TaskType> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskType> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Scenario: " + this.name;
    }
}
