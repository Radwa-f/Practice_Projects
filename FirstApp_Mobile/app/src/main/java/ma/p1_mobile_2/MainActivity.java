package ma.p1_mobile_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nom, adresse, surface, nombrePieces;
    private CheckBox piscine;
    private TextView resultat;
    private Button boutonCalculer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom = findViewById(R.id.nom);
        adresse = findViewById(R.id.adresse);
        surface = findViewById(R.id.surface);
        nombrePieces = findViewById(R.id.nombre_pieces);
        piscine = findViewById(R.id.piscine);
        boutonCalculer = findViewById(R.id.bouton_calculer);
        resultat = findViewById(R.id.resultat);

        boutonCalculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculerImpot();
            }
        });
    }

    private void calculerImpot() {
        String nomText = nom.getText().toString();
        String adresseText = adresse.getText().toString();
        String surfaceText = surface.getText().toString();
        String nombrePiecesText = nombrePieces.getText().toString();

        if (surfaceText.isEmpty() || nombrePiecesText.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        double surfaceValue = Double.parseDouble(surfaceText);
        int nombrePiecesValue = Integer.parseInt(nombrePiecesText);
        double impotDeBase = surfaceValue * 2;
        double impotSupplementaire = nombrePiecesValue * 50;
        if (piscine.isChecked()) {
            impotSupplementaire += 100;
        }

        double impotTotal = impotDeBase + impotSupplementaire;

        resultat.setText("Impôt de base : " + impotDeBase + "\nImpôt supplémentaire : " + impotSupplementaire + "\nImpôt total : " + impotTotal);

    }
}
