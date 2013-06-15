package com.idp.grandprix;

import com.idp.grandprix.controller.Controller;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class LaserGrandPrixApp extends Application {
	private static LaserGrandPrixApp instance;
	private static final String TAG = LaserGrandPrixApp.class.getSimpleName();
	
	private static Controller controller;
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "LaserGrandPrixApp.onCreate called");
		instance = this;
		controller = Controller.createController(this.getApplicationContext());
	}
	
	public static Context getContext(){
		return instance.getApplicationContext();
	}
		
	public static Controller getController(){
		return controller;
	}

}
