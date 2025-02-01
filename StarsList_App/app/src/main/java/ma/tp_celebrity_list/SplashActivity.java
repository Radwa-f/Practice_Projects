package ma.tp_celebrity_list;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.ImageView;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_splash);

      ImageView logo = findViewById(R.id.logo);

      // Faire tourner le logo de 360 degrés en 2000 millisecondes
      logo.animate().rotation(360f).setDuration(2000);

      // Réduire le logo à 50% de sa taille initiale (échelle X et Y) en 3000 millisecondes
      logo.animate().scaleX(0.5f).scaleY(0.5f).setDuration(3000);

      // Rendre le logo complètement transparent (alpha 0) en 6000 millisecondes
      logo.animate().alpha(0f).setDuration(6000);

      Thread t = new Thread() {
         @Override
         public void run() {
            try {
               sleep(6000);
               Intent intent = new Intent(SplashActivity.this, ListActivity.class);
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
