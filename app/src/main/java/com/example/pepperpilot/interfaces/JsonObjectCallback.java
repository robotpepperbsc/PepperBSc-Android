package com.example.pepperpilot.interfaces;

import org.json.JSONObject;

public interface JsonObjectCallback {
    void onSuccess(JSONObject result);
    void onError(JSONObject error);
}
