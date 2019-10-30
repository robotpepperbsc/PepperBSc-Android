package com.example.pepperpilot.models;

import com.example.pepperpilot.enums.MultimediaFileType;

public class MultimediaFile {
    private String fileName;
    private MultimediaFileType type;

    public MultimediaFile(String fileName, MultimediaFileType type) {
        this.fileName = fileName;
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MultimediaFileType getType() {
        return type;
    }

    public void setType(MultimediaFileType type) {
        this.type = type;
    }
}
