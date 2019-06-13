package com.example.pepperpilot.models;

import com.example.pepperpilot.enums.TaskType;

public class Video extends Task{
    public Video(TaskType taskType, String taskName, String taskDescription) {
        super(taskType, taskName, taskDescription);
    }
}
