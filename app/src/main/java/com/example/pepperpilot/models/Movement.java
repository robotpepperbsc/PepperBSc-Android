package com.example.pepperpilot.models;

import com.example.pepperpilot.enums.MovementDirection;
import com.example.pepperpilot.enums.TaskType;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class Movement extends Task {
    private MovementDirection direction;
    private float distance;

    public Movement(TaskType taskType, String taskName, String taskDescription, MovementDirection direction, float distance) {
        super(taskType, taskName, taskDescription);
        this.direction = direction;
        this.distance = distance;
    }

    public MovementDirection getDirection() {
        return direction;
    }

    public void setDirection(MovementDirection direction) {
        this.direction = direction;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public JSONObject getAsJsonObject() throws JSONException {

        JsonObject json = new JsonObject();
        json.addProperty("type", "movement");

        if (direction.equals(MovementDirection.FORWARD)) {
            json.addProperty("command", "move_forward");
            json.addProperty("distance", distance);
        } else if (direction.equals(MovementDirection.BACK)) {
            json.addProperty("command", "move_backward");
            json.addProperty("distance", distance);
        } else if (direction.equals(MovementDirection.LEFT)) {
            json.addProperty("command", "turn_left");
            json.addProperty("angle", distance);
        } else if (direction.equals(MovementDirection.RIGHT)) {
            json.addProperty("command", "turn_right");
            json.addProperty("angle", distance);
        }

        json.addProperty("name",taskName);
        json.addProperty("description",taskDescription);

        return new JSONObject(json.toString());
    }
}
