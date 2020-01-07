package com.example.pepperpilot.models;

import com.example.pepperpilot.enums.TaskType;

import java.io.Serializable;

public class Task{
    protected TaskType taskType;
    protected String taskName;
    protected String taskDescription;

    public Task() {

    }

    public Task(TaskType taskType, String taskName, String taskDescription) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public String getStringTaskType() {
        if(taskType.equals(TaskType.SPEECH)) {
            return "MOWA";
        } else if(taskType.equals(TaskType.MOVEMENT)) {
            return "RUCH";
        } else if(taskType.equals(TaskType.SHOW_MULTIMEDIA)) {
            return "MULTIMEDIA";
        } else if(taskType.equals(TaskType.BEHAVIOR)) {
            return "ZACHOWANIE";
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
