package com.loopme.ironsource_75;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ironsource.adapters.custom.loopme.LoopmeCustomAdapter;
import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.adunit.adapter.utility.AdInfo;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.LevelPlayBannerListener;
import com.loopme.LoopMeSdk;

public class BannerActivity extends Activity {
    private static final String appKey = "124e1d38d";
    private static final String loopmeAppKey = "f5826542ae"; // banner 300x250

    IronSourceBannerLayout banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        View loadButton = findViewById(R.id.load_button);
        loadButton.setEnabled(false);

        LoopMeSdk.initialize(this, new LoopMeSdk.Configuration(), new LoopMeSdk.LoopMeSdkListener() {
            @Override
            public void onSdkInitializationSuccess() {
                Toast.makeText(BannerActivity.this, "Loopme has been initialized", Toast.LENGTH_LONG).show();
                loadButton.setEnabled(true);
            }

            @Override
            public void onSdkInitializationFail(int error, String message) {
                Toast.makeText(BannerActivity.this, "Loopme failed initialization", Toast.LENGTH_LONG).show();
            }
        });

        IronSource.init(this, appKey, IronSource.AD_UNIT.BANNER);
        IronSource.setAdaptersDebug(true);

        Toast.makeText(this, "IronSource has been initialized", Toast.LENGTH_SHORT).show();

        banner = IronSource.createBanner(this, ISBannerSize.RECTANGLE);

        banner.setLevelPlayBannerListener(new LevelPlayBannerListener() {
            // Invoked each time a banner was loaded. Either on refresh, or manual load.
            //  AdInfo parameter includes information about the loaded ad
            @Override
            public void onAdLoaded(AdInfo adInfo) {
                Log.i("BannerActivity", "onAdLoaded");
            }

            // Invoked when the banner loading process has failed.
            //  This callback will be sent both for manual load and refreshed banner failures.
            @Override
            public void onAdLoadFailed(IronSourceError error) {
                Log.i("BannerActivity", "onAdLoadFailed");
            }

            // Invoked when end user clicks on the banner ad
            @Override
            public void onAdClicked(AdInfo adInfo) {
                Log.i("BannerActivity", "onAdClicked");
            }

            // Notifies the presentation of a full screen content following user click
            @Override
            public void onAdScreenPresented(AdInfo adInfo) {
                Log.i("BannerActivity", "onAdScreenPresented");
            }

            // Notifies the presented screen has been dismissed
            @Override
            public void onAdScreenDismissed(AdInfo adInfo) {
                Log.i("BannerActivity", "onAdScreenDismissed");
            }

            //Invoked when the user left the app
            @Override
            public void onAdLeftApplication(AdInfo adInfo) {
                Log.i("BannerActivity", "onAdLeftApplication");
            }
        });

        LinearLayout parent = findViewById(R.id.parent_layout);
        parent.addView(banner, 2);
    }

    public void onLoadClicked(View view) {
        LoopmeCustomAdapter.setWeakActivity(this);
        LoopmeCustomAdapter.setLoopmeAppkey(loopmeAppKey);
    }

    public void onShowClicked(View view) {
        IronSource.loadBanner(banner);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IronSource.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        IronSource.onPause(this);
    }
}