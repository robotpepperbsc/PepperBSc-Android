package com.example.pepperpilot.requests;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pepperpilot.enums.MovementDirection;
import com.example.pepperpilot.interfaces.ServerCallback;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestMaker {

    public final static String IP = "192.168.1.101";
    public final static String PORT = "5000";

    public static String ipPort = IP + ":" + PORT;

    public static void connect(final ServerCallback serverCallback, Context context) {

        String url = "http://" + ipPort + "/add_move?id=nazwa_ruchu";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                serverCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                serverCallback.onError(error.toString());
            }
        });

        VolleySingleton.getInstance(context).getRequestQueue().add(request);

    }

    public static void sendMovement(final ServerCallback serverCallback, final MovementDirection direction, Context context) {

        String url = "http://" + ipPort + "/add_action";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                serverCallback.onSuccess(response);
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
                params.put("type", "generic");

                switch(direction) {
                    case FORWARD:
                        params.put("command", "move_forward");
                        break;
                    case BACK:
                        params.put("command", "move_back");
                        break;
                    case RIGHT:
                        params.put("command", "turn_right");
                        break;
                    case LEFT:
                        params.put("command", "turn_left");
                        break;
                }



                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };


        VolleySingleton.getInstance(context).getRequestQueue().add(request);

    }

    public static void sendSpeech(final ServerCallback serverCallback, final String text, Context context) {

        String url = "http://" + ipPort + "/add_action";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                serverCallback.onSuccess(response);
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
                params.put("type", "speech");
                params.put("command", "say");
                params.put("text", text);
                params.put("volume", 0.8);
                params.put("speech_speed", 100);
                params.put("language", "Polish");

                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };


        VolleySingleton.getInstance(context).getRequestQueue().add(request);


    }

    public static void sendAnimation(final ServerCallback serverCallback, String type, String command, Context context) {

        //String url =
    }


}
