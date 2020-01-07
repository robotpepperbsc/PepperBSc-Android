package com.example.pepperpilot.models;

import com.example.pepperpilot.enums.TaskType;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class Behavior extends Task{
    private String behaviorName;

    public Behavior(String behaviorName) {
        this.behaviorName = behaviorName;
    }

    public Behavior(TaskType taskType, String taskName, String taskDescription, String behaviorName) {
        super(taskType, taskName, taskDescription);
        this.behaviorName = behaviorName;
    }

    public String getBehaviorName() {
        return behaviorName;
    }

    public void setBehaviorName(String behaviorName) {
        this.behaviorName = behaviorName;
    }

    public JSONObject getAsJsonObject() throws JSONException {
        JsonObject json = new JsonObject();
        json.addProperty("type","sequence");
        json.addProperty("name",taskName);
        json.addProperty("description",taskDescription);

        return new JSONObject(json.toString());
    }
}
