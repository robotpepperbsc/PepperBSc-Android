package com.example.pepperpilot.models;

import com.example.pepperpilot.enums.TaskType;

public class Speech extends Task {
    private float volume;
    private int speed;

    public Speech(TaskType taskType, String taskName, String taskDescription) {
        super(taskType, taskName, taskDescription);
    }
}
