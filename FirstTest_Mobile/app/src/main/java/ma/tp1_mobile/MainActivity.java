package ma.tp1_mobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

public class MainActivity extends AppCompatActivity {

    private int compteur = 0;
    private TextView texteCompteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        texteCompteur = findViewById(R.id.counting);


        Button boutonToast = findViewById(R.id.toast);
        boutonToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Hello Toast!", Toast.LENGTH_SHORT).show();
            }
        });


        Button boutonCompteur = findViewById(R.id.count);
        boutonCompteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compteur++;
                texteCompteur.setText(String.valueOf(compteur));
            }
        });


    }
}
