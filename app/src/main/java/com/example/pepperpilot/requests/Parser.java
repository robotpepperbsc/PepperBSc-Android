package com.example.pepperpilot.requests;

import com.example.pepperpilot.enums.MediaType;
import com.example.pepperpilot.enums.MovementDirection;
import com.example.pepperpilot.enums.TaskType;
import com.example.pepperpilot.models.Behavior;
import com.example.pepperpilot.models.Media;
import com.example.pepperpilot.models.Movement;
import com.example.pepperpilot.models.Recording;
import com.example.pepperpilot.models.Scenario;
import com.example.pepperpilot.models.Speech;
import com.example.pepperpilot.models.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;


public class Parser {


    public static LinkedList<Behavior> jsonObjectToBehaviorsList(JSONObject jsonObject) throws JSONException {
        LinkedList<Behavior> list = new LinkedList<>();

        JSONArray array = jsonObject.getJSONArray("sequences");

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            list.add(new Behavior(obj.getString("name")));
        }

        return list;
    }

    public static List<Scenario> jsonObjectToScenariosList(JSONObject object) throws JSONException {
        List<Scenario> scenarios = new LinkedList<>();

        JSONArray array = object.getJSONArray("scenarios");
        String lastModificationDate;

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            String name = obj.getString("name");
            String description = obj.getString("description");
            try {
                lastModificationDate = obj.getString("last_mod_date");
            } catch (JSONException e) {
                lastModificationDate = "";
            }


            scenarios.add(new Scenario(name, description, lastModificationDate));
        }

        return scenarios;
    }

    // JPG, MP4 are valid formats
    public static LinkedList<Media> jsonObjectToMediaList(JSONObject jsonObject) throws JSONException {
        LinkedList<Media> list = new LinkedList<>();

        JSONArray videosArr = jsonObject.getJSONArray("videos");
        JSONArray photosArr = jsonObject.getJSONArray("photos");


        for (int i = 0; i < videosArr.length(); i++) {
            JSONObject object = videosArr.getJSONObject(i);
            String name = object.getString("name");
            list.add(new Media(name, MediaType.MOVIE));
        }

        for (int i = 0; i < photosArr.length(); i++) {
            JSONObject object = photosArr.getJSONObject(i);
            String name = object.getString("name");
            list.add(new Media(name, MediaType.IMAGE));
        }

        for (Media obj : list) {
            System.out.println(obj.getName());
        }

        return list;
    }

    public static LinkedList<Task> jsonObjectToScenarioTasks(JSONObject jsonObject) throws JSONException {
        LinkedList<Task> list = new LinkedList<>();
        JSONArray actionsArray = jsonObject.getJSONArray("actions");

        for (int i = 0; i < actionsArray.length(); i++) {
            JSONObject action = actionsArray.getJSONObject(i);
            String actionType = action.getString("type");

            String description;

            try {
                description = action.getString("description");
            } catch (JSONException e) {
                description = "";
            }


            switch (actionType) {
                case "speech":
                    String text = action.getString("text");
                    float volume = (float) action.getDouble("volume");
                    int speed = action.getInt("speech_speed");
                    String language = action.getString("language");


                    Speech speech = new Speech(TaskType.SPEECH, "", description, text, volume, speed, language);
                    list.add(speech);

                    break;
                case "sequence":
                    String behaiorName = action.getString("name");

                    Behavior behavior = new Behavior(TaskType.BEHAVIOR, behaiorName, description, behaiorName);
                    list.add(behavior);

                    break;
                case "media":

                    String fileName = action.getString("name");
                    String fileType = action.getString("file_type");
                    MediaType type = null;


                    if (fileType.equals("jpg")) {
                        type = MediaType.IMAGE;
                    } else if (fileType.equals("mp4")) {
                        type = MediaType.MOVIE;
                    }

                    Media media = new Media(TaskType.SHOW_MULTIMEDIA, fileName, description, fileName, type);
                    list.add(media);

                    break;
                case "movement":

                    String command = action.getString("command");
                    float distance;
                    MovementDirection direction = null;

                    if (command.equals("move_forward") || command.equals("move_backward")) {
                        distance = (float) action.getDouble("distance");
                    } else {
                        distance = (float) action.getDouble("angle");
                    }

                    if (command.equals("move_forward")) {
                        direction = MovementDirection.FORWARD;
                    } else if (command.equals("move_backward")) {
                        direction = MovementDirection.BACK;
                    } else if (command.equals("turn_right")) {
                        direction = MovementDirection.RIGHT;
                    } else if (command.equals("turn_left")) {
                        direction = MovementDirection.LEFT;
                    }

                    Movement movement = new Movement(TaskType.MOVEMENT, "", description, direction, distance);
                    list.add(movement);
                    break;
            }
        }
        return list;
    }

    public static LinkedList<Recording> jsonObjectToRecordingList(JSONObject jsonObject) throws JSONException {
        LinkedList<Recording> list = new LinkedList<>();

        JSONArray array = jsonObject.getJSONArray("recordings");


        return list;
    }

}
