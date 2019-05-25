package com.example.pepperpilot.models;

import com.example.pepperpilot.enums.TaskType;

public class Task {
    private TaskType taskType;
    private String taskName;
    private String taskDescription;

    public Task(TaskType taskType, String taskName, String taskDescription) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public String getStringTaskType() {
        if(taskType.equals(TaskType.TELL)) {
            return "MOWA";
        } else if(taskType.equals(TaskType.MOVE)) {
            return "RUCH";
        } else if(taskType.equals(TaskType.SHOW_IMAGE_ON_THE_SCREEN)) {
            return "ZDJECIE";
        } else if(taskType.equals(TaskType.SHOW_VIDEO_ON_THE_SCREEN)) {
            return "FILM";
        }
        return "";
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
