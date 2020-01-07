package com.example.pepperpilot.models;

import com.example.pepperpilot.enums.MediaType;
import com.example.pepperpilot.enums.TaskType;

public class Recording {
    private String name;
    private MediaType type;
    private int duration;

    public Recording(String name, MediaType type, int duration) {
        this.name = name;
        this.type = type;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
