package com.loopme.ironsource;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button interstitialButton = findViewById(R.id.interstitial);
        Button rewardedButton = findViewById(R.id.rewarded);
        Button bannerButton = findViewById(R.id.banner);
        interstitialButton.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), InterstitialActivity.class)));
        rewardedButton.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RewardedVideoActivity.class)));
        bannerButton.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), BannerActivity.class)));
    }
}