package com.example.pepperpilot.models;

import com.example.pepperpilot.enums.TaskType;

public class Recording {
    private String title;
    private String recordDateTime;


    public Recording(String title, String recordDateTime) {
        this.title = title;
        this.recordDateTime = recordDateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecordDateTime() {
        return recordDateTime;
    }

    public void setRecordDateTime(String recordDateTime) {
        this.recordDateTime = recordDateTime;
    }
}
