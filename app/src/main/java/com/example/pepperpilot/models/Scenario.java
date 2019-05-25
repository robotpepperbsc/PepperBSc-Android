package com.example.pepperpilot.models;

import com.example.pepperpilot.enums.TaskType;

import java.util.List;

public class Scenario{
    private String name;
    private String lastDateTimeEdited;
    private String description;
    private List<TaskType> tasks;

    public Scenario(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Scenario: " + this.name;
    }
}
