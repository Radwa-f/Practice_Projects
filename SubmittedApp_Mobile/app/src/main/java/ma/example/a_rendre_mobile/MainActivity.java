package ma.example.a_rendre_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText champNomPrenom, champEmail, champTelephone, champAdresse;
    private Spinner spinnerVille;
    private Button boutonEnvoyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        champNomPrenom = findViewById(R.id.nom_prenom);
        champEmail = findViewById(R.id.email);
        champTelephone = findViewById(R.id.telephone);
        champAdresse = findViewById(R.id.adresse);
        spinnerVille = findViewById(R.id.villes);
        boutonEnvoyer = findViewById(R.id.envoyer);

        boutonEnvoyer.setOnClickListener(view -> {

            String nomPrenom = champNomPrenom.getText().toString();
            String email = champEmail.getText().toString();
            String telephone = champTelephone.getText().toString();
            String adresse = champAdresse.getText().toString();
            String ville = spinnerVille.getSelectedItem().toString();

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("nom_prenom", nomPrenom);
            intent.putExtra("email", email);
            intent.putExtra("telephone", telephone);
            intent.putExtra("adresse", adresse);
            intent.putExtra("ville", ville);

            startActivity(intent);
        });
    }
}

