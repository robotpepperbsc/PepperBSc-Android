package com.example.pepperpilot.models;

import java.util.ArrayList;
import java.util.List;

public class Scenario{
    private String name;
    private String lastDateTimeEdited;
    private String description;
    private List<Task> tasks;

    public Scenario(String name, String description,String lastDateTimeEdited) {
        this.name = name;
        this.description = description;
        this.lastDateTimeEdited = lastDateTimeEdited;
        this.tasks = new ArrayList<>();
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

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
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
