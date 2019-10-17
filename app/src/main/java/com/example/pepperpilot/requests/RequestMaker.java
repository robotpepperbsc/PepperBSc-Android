package com.example.pepperpilot.requests;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pepperpilot.interfaces.ServerCallback;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestMaker {

    public final static String IP = "192.168.1.106";
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

    public static void sendMovement(final ServerCallback serverCallback, String type, String command, Context context) {

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
                Map<String, String> params = new HashMap<String,String>();
                params.put("type", "generic");
                params.put("command", "turn_right");

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
                Map<String, String> params = new HashMap<String,String>();
                params.put("type", "speech");
                params.put("command", "say");
                params.put("text",text);

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
