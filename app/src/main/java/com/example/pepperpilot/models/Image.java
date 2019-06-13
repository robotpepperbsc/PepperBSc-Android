package com.example.pepperpilot.models;

import com.example.pepperpilot.enums.TaskType;

public class Image extends Task {

    public Image(TaskType taskType, String taskName, String taskDescription) {
        super(taskType, taskName, taskDescription);
    }
}
