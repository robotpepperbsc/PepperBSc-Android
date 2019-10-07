package com.example.pepperpilot.singletons;


public class VolleySingleton {
    private static VolleySingleton instance;


    public static VolleySingleton getInstance() {
        if(instance == null) {
            instance = new VolleySingleton();
        }
        return instance;
    }

}
