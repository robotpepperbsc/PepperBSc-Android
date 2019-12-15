package com.example.pepperpilot.models;

import com.example.pepperpilot.enums.TaskType;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class Speech extends Task {
    private String text;
    private float volume;
    private int speed;
    private String language;


    public Speech(TaskType taskType, String taskName, String taskDescription, String text, float volume, int speed, String language) {
        super(taskType, taskName, taskDescription);
        this.text = text;
        this.volume = volume;
        this.speed = speed;
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public JSONObject getAsJsonObject() throws JSONException {

        JsonObject json = new JsonObject();
        json.addProperty("type","speech");
        json.addProperty("text",text);
        json.addProperty("volume",volume);
        json.addProperty("speech_speed",speed);
        json.addProperty("language",language);

        return new JSONObject(json.toString());
    }
}
