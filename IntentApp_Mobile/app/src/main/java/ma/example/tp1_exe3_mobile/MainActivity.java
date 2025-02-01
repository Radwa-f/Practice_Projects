package ma.example.tp1_exe3_mobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    private RadioButton radioOui, radioNon;
    private Button buttonSuivant, buttonQuitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        radioOui = findViewById(R.id.radio_oui);
        radioNon = findViewById(R.id.radio_non);

        buttonSuivant = findViewById(R.id.button_suivant);
        buttonQuitter = findViewById(R.id.button_quitter);

        buttonSuivant.setOnClickListener(view -> {

            StringBuilder resultat = new StringBuilder("Selected Options:\n");

            if (checkBox1.isChecked()) resultat.append("Multiple Versions Combined\n");
            if (checkBox2.isChecked()) resultat.append("Model View Controller\n");
            if (checkBox3.isChecked()) resultat.append("Main Value Compiled\n");
            if (checkBox4.isChecked()) resultat.append("Mandatory Validated Controls\n");

            resultat.append("Syntax in JSP: ");

            if (radioOui.isChecked()) {
                resultat.append("OUI");
            } else {
                resultat.append("NON");
            }

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("resultat", resultat.toString());
            startActivity(intent);
        });

        buttonQuitter.setOnClickListener(view -> finish());
    }
}