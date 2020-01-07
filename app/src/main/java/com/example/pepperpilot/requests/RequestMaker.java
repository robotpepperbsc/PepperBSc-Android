package com.example.pepperpilot.requests;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.pepperpilot.enums.MediaType;
import com.example.pepperpilot.enums.MovementDirection;
import com.example.pepperpilot.enums.TaskType;
import com.example.pepperpilot.interfaces.JsonObjectCallback;
import com.example.pepperpilot.interfaces.StringCallback;
import com.example.pepperpilot.models.Behavior;
import com.example.pepperpilot.models.Media;
import com.example.pepperpilot.models.Movement;
import com.example.pepperpilot.models.Scenario;
import com.example.pepperpilot.models.Speech;
import com.example.pepperpilot.models.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestMaker {

    public static String IP = "192.168.1.115";
    public static int PORT = 5000;


    //TESTED - OK
    public static void connect(final StringCallback serverCallback, Context context, final String ip, final int port, final String password) {

        String url = "http://" + getIpPort(IP, PORT) + "/connect";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                serverCallback.onSuccess(response);
                IP = ip;
                PORT = port;
                Log.d("Connect", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                serverCallback.onError(error.toString());
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("password", password);
                params.put("IP", ip);
                params.put("port", port);

                Log.d("Connect", new JSONObject(params).toString());

                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        VolleySingleton.getInstance(context).getRequestQueue().add(request);
    }

    // TESTED - OK
    public static void sendSpeech(final StringCallback serverCallback,
                                  final String text,
                                  final double volume,
                                  final int speed,
                                  final String language,
                                  Context context) {

        String url = "http://" + getIpPort(IP, PORT) + "/add_action";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                serverCallback.onSuccess(response);
                Log.d("SendSpeech", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                serverCallback.onError(error.toString());
                Log.e("SendSpeech", error.toString());
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("type", "speech");
                params.put("text", text);
                params.put("volume", volume);
                params.put("speech_speed", speed);
                params.put("language", language);

                Log.d("SendSpeech", new JSONObject(params).toString());

                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        VolleySingleton.getInstance(context).getRequestQueue().add(request);
    }


    // TESTED - OK
    public static void sendMovement(final StringCallback serverCallback,
                                    final MovementDirection direction,
                                    final double value,
                                    Context context) {

        String url = "http://" + getIpPort(IP, PORT) + "/add_action";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                serverCallback.onSuccess(response);
                Log.d("SendMovement", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                serverCallback.onError(error.toString());
                Log.e("SendMovement", error.toString());
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                Map<String, Object> params = new HashMap<String, Object>();

                params.put("type", "movement");

                switch (direction) {
                    case FORWARD:
                        params.put("command", "move_forward");
                        params.put("distance", value);
                        break;
                    case BACK:
                        params.put("command", "move_backward");
                        params.put("distance", value);
                        break;
                    case RIGHT:
                        params.put("command", "turn_right");
                        params.put("angle", value);
                        break;
                    case LEFT:
                        params.put("command", "turn_left");
                        params.put("angle", value);
                        break;
                }

                Log.d("SendMovement", new JSONObject(params).toString());

                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };


        VolleySingleton.getInstance(context).getRequestQueue().add(request);

    }

    // TESTED - OK
    public static void getLogger(final JsonObjectCallback serverCallback, Context context) {
        String url = "http://" + getIpPort(IP, PORT) + "/logger";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                serverCallback.onSuccess(response);
                Log.d("Logger", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Logger", error.toString());
            }
        });

        VolleySingleton.getInstance(context).getRequestQueue().add(request);
    }

    // TESTED - OK
    public static void getMedia(final JsonObjectCallback serverCallback, Context context) {
        String url = "http://" + getIpPort(IP, PORT) + "/media";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("GetMedia", response.toString());
                serverCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("GetMedia", error.toString());
                serverCallback.onError(null);
            }
        });

        VolleySingleton.getInstance(context).getRequestQueue().add(request);
    }

    // TESTED - OK
    public static void displayOnScreen(final StringCallback serverCallback,
                                       final Media media,
                                       Context context) {

        String url = "http://" + getIpPort(IP, PORT) + "/add_action";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                serverCallback.onSuccess(response);
                Log.d("DisplayOnScreen", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                serverCallback.onError(error.toString());
                Log.e("DisplayOnScreen", error.toString());
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("type", "media");
                params.put("name", media.getName());
                if (media.getType().equals(MediaType.MOVIE)) {
                    params.put("file_type", "mp4");
                } else if (media.getType().equals(MediaType.IMAGE)) {
                    params.put("file_type", "jpg");
                }

                Log.d("DisplayOnScreen", new JSONObject(params).toString());

                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        VolleySingleton.getInstance(context).getRequestQueue().add(request);
    }


    // TESTED - OK
    public static void getBehaviors(final JsonObjectCallback serverCallback, Context context) {
        String url = "http://" + getIpPort(IP, PORT) + "/sequences";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("GetBehaviors", response.toString());
                serverCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("GetBehaviors", error.toString());
                serverCallback.onError(null);
            }
        });
        VolleySingleton.getInstance(context).getRequestQueue().add(request);
    }

    // TESTED - OK
    public static void sendBehavior(final StringCallback serverCallback,
                                    final String name, Context context) {
        String url = "http://" + getIpPort(IP, PORT) + "/add_action";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                serverCallback.onSuccess(response);
                Log.d("SendBehavior", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                serverCallback.onError(error.toString());
                Log.e("SendBehavior", error.toString());
            }
        }) {
            @Override
            public byte[] getBody() {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("type", "sequence");
                params.put("name", name);

                Log.d("SendBehavior", new JSONObject(params).toString());
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        VolleySingleton.getInstance(context).getRequestQueue().add(request);
    }

    // TESTED - OK
    public static void getScenarios(final JsonObjectCallback serverCallback, Context context) {

        String url = "http://" + getIpPort(IP, PORT) + "/scenarios";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("GetScenarios", response.toString());
                serverCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("GetScenarios", error.toString());
            }
        });

        VolleySingleton.getInstance(context).getRequestQueue().add(request);
    }


    // TESTED - OK
    public static void getScenarioDetails(final JsonObjectCallback serverCallback, Context
            context, String name) {

        String url = "http://" + getIpPort(IP, PORT) + "/scenario?name=" + name + "&run=false&start=0&end=0";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("GetScenarioDetails", response.toString());
                serverCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("GetScenarioDetails", error.toString());
            }
        });

        VolleySingleton.getInstance(context).getRequestQueue().add(request);
    }


    // TESTED - OK
    public static void createScenario(final StringCallback serverCallback, Context context, final Scenario scenario) {
        String url = "http://" + getIpPort(IP, PORT) + "/scenarios";


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("AddScenario", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("AddScenario", error.toString());
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {


                JSONArray actionsArray = new JSONArray();

                try {
                    for (Task task : scenario.getTasksList()) {
                        if (task.getTaskType().equals(TaskType.BEHAVIOR)) {
                            Behavior behavior = (Behavior) task;
                            actionsArray.put(behavior.getAsJsonObject());
                        } else if (task.getTaskType().equals(TaskType.SHOW_MULTIMEDIA)) {
                            Media media = (Media) task;
                            actionsArray.put(media.getAsJsonObject());
                        } else if (task.getTaskType().equals(TaskType.MOVEMENT)) {
                            Movement movement = (Movement) task;
                            actionsArray.put(movement.getAsJsonObject());
                        } else if (task.getTaskType().equals(TaskType.SPEECH)) {
                            Speech speech = (Speech) task;
                            actionsArray.put(speech.getAsJsonObject());
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Map<String, Object> params = new HashMap<String, Object>();
                params.put("name", scenario.getName());
                params.put("description", scenario.getDescription());
                params.put("actions", actionsArray);

                Log.d("AddScenario", new JSONObject(params).toString());


                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        VolleySingleton.getInstance(context).getRequestQueue().add(request);
    }


    // TESTED - OK
    public static void runScenarioInRange(final StringCallback serverCallback, Context context, String name, int from, int to) {

        String url = "http://" + getIpPort(IP, PORT) + "/scenario?name=" + name + "&run=true&start=" + from + "&end=" + to;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RunScenario", response);
                serverCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RunScenario", error.toString());
                serverCallback.onError(error.toString());
            }
        });

        VolleySingleton.getInstance(context).getRequestQueue().add(request);

    }

    // TESTED - OK
    public static void clearQueue(final StringCallback serverCallback, Context context) {
        String url = "http://" + getIpPort(IP, PORT) + "/clear_queue";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ClearQueue", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ClearQueue", error.toString());
            }
        });

        VolleySingleton.getInstance(context).getRequestQueue().add(request);
    }


    // TESTED - OK
    public static void removeScenario(final StringCallback serverCallback, Context
            context, String name) {

        String url = "http://" + getIpPort(IP, PORT) + "/scenarios/remove?name=" + name;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                serverCallback.onSuccess(response);
                Log.d("RemoveScenario", response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RemoveScenario", error.toString());
            }
        });

        VolleySingleton.getInstance(context).getRequestQueue().add(request);
    }

    // TESTED - OK
    public static void modifyScenario(final StringCallback server, Context context, final Scenario scenario) {

        String url = "http://" + getIpPort(IP, PORT) + "/scenarios?name=" + scenario.getName();

        StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ModifyScenario", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ModifyScenario", error.toString());
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                JSONArray actionsArray = new JSONArray();

                try {
                    for (Task task : scenario.getTasksList()) {
                        if (task.getTaskType().equals(TaskType.BEHAVIOR)) {
                            Behavior behavior = (Behavior) task;
                            actionsArray.put(behavior.getAsJsonObject());
                        } else if (task.getTaskType().equals(TaskType.SHOW_MULTIMEDIA)) {
                            Media media = (Media) task;
                            actionsArray.put(media.getAsJsonObject());
                        } else if (task.getTaskType().equals(TaskType.MOVEMENT)) {
                            Movement movement = (Movement) task;
                            actionsArray.put(movement.getAsJsonObject());
                        } else if (task.getTaskType().equals(TaskType.SPEECH)) {
                            Speech speech = (Speech) task;
                            actionsArray.put(speech.getAsJsonObject());
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Map<String, Object> params = new HashMap<String, Object>();
                params.put("name", scenario.getName());
                params.put("description", scenario.getDescription());
                params.put("actions", actionsArray);

                Log.d("AddScenario", new JSONObject(params).toString());
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        VolleySingleton.getInstance(context).getRequestQueue().add(request);
    }

    private static String getIpPort(String IP, int port) {
        return IP + ":" + port;
    }


}
