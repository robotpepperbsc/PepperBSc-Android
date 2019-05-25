package com.example.pepperpilot.models;

import com.example.pepperpilot.enums.TaskType;

public class Movement extends Task {

    public Movement(TaskType taskType, String taskName, String taskDescription) {
        super(taskType, taskName, taskDescription);
    }
}
