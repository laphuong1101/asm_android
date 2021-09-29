package com.example.assignment;

import android.app.Application;

public class App extends Application {
    private static App instance;
    private Database database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = new Database(this, "ToDoList.sqlite", null, 1);
    }

    public static App getInstance() {
        return instance;
    }

    public Database getDatabase() {
        return database;
    }
}
