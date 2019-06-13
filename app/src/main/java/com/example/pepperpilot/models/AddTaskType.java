package com.example.pepperpilot.models;

import com.example.pepperpilot.enums.TaskType;

public class AddTaskType {
    private TaskType type;
    private String name;
    private int icon;

    public AddTaskType(TaskType type, String name, int icon) {
        this.type = type;
        this.name = name;
        this.icon = icon;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
