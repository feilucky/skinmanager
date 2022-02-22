package com.mxtech.skin.demo;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.mxtech.demo.loader.SkinManager;

public class SkinApplication extends Application {
	private Handler mHandler;
	public void onCreate() {
		super.onCreate();
		mHandler = new Handler(Looper.getMainLooper());
		initSkinLoader();
	}

	/**
	 * Must call init first
	 */
	private void initSkinLoader() {
		SkinManager.getInstance().init(this);
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				SkinManager.getInstance().load();
			}
		},100);
	}
}