package ma.geolocationapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView logo = findViewById(R.id.logo);


        // Scale down the logo to 40% of its original size (X and Y scale) over 2000 milliseconds
        logo.animate()
                .scaleX(0.4f)
                .scaleY(0.4f)
                .setDuration(2000)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        // Rotate the logo 360 degrees over 1000 milliseconds
                        logo.animate()
                                .rotation(360f)
                                .setDuration(1000)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Translate the logo upward by 100 pixels over 1000 milliseconds
                                        logo.animate()
                                                .translationY(-100f)
                                                .setDuration(1000)
                                                .withEndAction(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        // Make the logo completely transparent (alpha 0) over 4000 milliseconds
                                                        logo.animate()
                                                                .alpha(0f)
                                                                .setDuration(4000)
                                                                .start();
                                                    }
                                                })
                                                .start();
                                    }
                                })
                                .start();
                    }
                })
                .start();


        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}