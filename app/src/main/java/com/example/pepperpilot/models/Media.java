package com.example.pepperpilot.models;

import com.example.pepperpilot.enums.MediaType;
import com.example.pepperpilot.enums.TaskType;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class Media extends Task {
    private String name;
    private MediaType type;

    public Media(String name, MediaType type) {
        this.name = name;
        this.type = type;
    }


    public Media(TaskType taskType, String taskName, String taskDescription, String name, MediaType type) {
        super(taskType, taskName, taskDescription);
        this.name = name;
        this.type = type;
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

    public JSONObject getAsJsonObject() throws JSONException {

        JsonObject json = new JsonObject();
        json.addProperty("type", "media");
        json.addProperty("name", name);

        if (type.equals(MediaType.MOVIE)) {
            json.addProperty("file_type", "mp4");
        } else if (type.equals(MediaType.IMAGE)) {
            json.addProperty("file_type", "jpg");
        }

        json.addProperty("description", taskDescription);

        return new JSONObject(json.toString());
    }
}
