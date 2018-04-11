package com.samsung.czolgi;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.surfaceview.RatioResolutionStrategy;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.samsung.czolgi.GraCzolgi;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.resolutionStrategy = new RatioResolutionStrategy(GraCzolgi.EKRAN_SZEROKOSC, GraCzolgi.EKRAN_WYSOKOSC);
		initialize(new GraCzolgi(), config);
	}
}
