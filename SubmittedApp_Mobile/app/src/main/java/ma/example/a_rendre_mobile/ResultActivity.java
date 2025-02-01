package ma.example.a_rendre_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView afficherNom, afficherEmail, afficherTelephone, afficherAdresse, afficherVille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        afficherNom = findViewById(R.id.afficher_nom);
        afficherEmail = findViewById(R.id.afficher_email);
        afficherTelephone = findViewById(R.id.afficher_telephone);
        afficherAdresse = findViewById(R.id.afficher_adresse);
        afficherVille = findViewById(R.id.afficher_ville);

        Intent intent = getIntent();
        String nomPrenom = intent.getStringExtra("nom_prenom");
        String email = intent.getStringExtra("email");
        String telephone = intent.getStringExtra("telephone");
        String adresse = intent.getStringExtra("adresse");
        String ville = intent.getStringExtra("ville");

        afficherNom.setText("Nom : " + nomPrenom);
        afficherEmail.setText("Email : " + email);
        afficherTelephone.setText("Téléphone : " + telephone);
        afficherAdresse.setText("Adresse : " + adresse);
        afficherVille.setText("Ville : " + ville);
    }
}
