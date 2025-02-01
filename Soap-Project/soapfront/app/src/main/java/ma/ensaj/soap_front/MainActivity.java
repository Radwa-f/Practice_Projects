package ma.ensaj.soap_front;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.ensaj.soap_front.adapters.CompteAdapter;
import ma.ensaj.soap_front.models.Compte;
import ma.ensaj.soap_front.soap.SoapClient;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        fetchComptes();
    }

    private void fetchComptes() {
        new Thread(() -> {
            List<Compte> comptes = new SoapClient().getAllComptes();
            runOnUiThread(() -> {
                recyclerView.setAdapter(new CompteAdapter(comptes, MainActivity.this));
            });
        }).start();
    }


    public void updateCompteInDatabase(Long id, Compte compte) {
        new Thread(() -> {
            boolean success = new SoapClient().updateCompte(compte);
            runOnUiThread(() -> {
                if (success) {
                    Toast.makeText(MainActivity.this, "Compte mis à jour avec succès", Toast.LENGTH_SHORT).show();
                    fetchComptes();
                } else {
                    Toast.makeText(MainActivity.this, "Erreur lors de la mise à jour", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    public void updateCompteDialog(Long id, Compte currentCompte) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Mettre à jour le compte");
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_update_compte, null);
        EditText editTextSolde = dialogView.findViewById(R.id.editTextSolde);
        EditText editTextDateCreation = dialogView.findViewById(R.id.editTextDateCreation);
        EditText editTextType = dialogView.findViewById(R.id.editTextType);

        editTextSolde.setText(String.valueOf(currentCompte.getSolde()));
        editTextDateCreation.setText(currentCompte.getDateCreation());
        editTextType.setText(currentCompte.getType());

        builder.setView(dialogView)
                .setPositiveButton("Mettre à jour", (dialog, which) -> {

                    double newSolde = Double.parseDouble(editTextSolde.getText().toString());
                    String newDateCreation = editTextDateCreation.getText().toString();
                    String newType = editTextType.getText().toString();

                    Compte updatedCompte = new Compte(id, newSolde, newDateCreation, newType);

                    updateCompteInDatabase(id, updatedCompte);
                })
                .setNegativeButton("Annuler", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    public void deleteCompte(Long id) {
        new Thread(() -> {
            boolean success = new SoapClient().deleteCompte(id);
            runOnUiThread(() -> {
                if (success) {
                    Toast.makeText(MainActivity.this, "Compte supprimé avec succès", Toast.LENGTH_SHORT).show();
                    fetchComptes();
                } else {
                    Toast.makeText(MainActivity.this, "Erreur lors de la suppression", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

}
