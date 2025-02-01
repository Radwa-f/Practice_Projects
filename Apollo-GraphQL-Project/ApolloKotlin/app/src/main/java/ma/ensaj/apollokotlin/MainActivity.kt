package ma.ensaj.apollokotlin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.exception.ApolloException
import kotlinx.coroutines.launch
import ma.ensaj.AllComptesQuery
import ma.ensaj.DeleteCompteMutation
import ma.ensaj.SaveCompteMutation
import ma.ensaj.apollokotlin.adapter.ComptesAdapter
import ma.ensaj.type.CompteRequest
import ma.ensaj.type.TypeCompte
import com.apollographql.apollo.api.Optional
import ma.ensaj.appllokotlin.R

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ComptesAdapter
    private lateinit var apolloClient: ApolloClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apolloClient = ApolloClient.Builder()
            .serverUrl("http://10.0.2.2:8082/graphql")
            .build()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val addButton: ImageView = findViewById(R.id.btn_add_compte)
        addButton.setOnClickListener {
            showAddDialog()
        }

        fetchComptes()
    }

    private fun showAddDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_compte, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(true)
            .create()

        val soldeInput: EditText = dialogView.findViewById(R.id.dialog_input_solde)
        val dateCreationInput: EditText = dialogView.findViewById(R.id.dialog_input_date_creation)
        val typeSpinner: Spinner = dialogView.findViewById(R.id.dialog_input_type)
        val addButton: Button = dialogView.findViewById(R.id.dialog_btn_add)

        addButton.setOnClickListener {
            val solde = soldeInput.text.toString().toFloatOrNull()
            val dateCreation = dateCreationInput.text.toString()
            val type = typeSpinner.selectedItem.toString()

            if (solde != null && dateCreation.isNotBlank() && type.isNotBlank()) {
                createCompte(solde, dateCreation, type)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs correctement", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }

    private fun fetchComptes() {
        val query = AllComptesQuery()

        lifecycleScope.launch {
            try {
                val response = apolloClient.query(query).execute()
                val fetchedComptes = response.data?.allComptes?.toMutableList() ?: mutableListOf()

                if (::adapter.isInitialized) {
                    adapter.updateData(fetchedComptes)
                } else {
                    adapter = ComptesAdapter(fetchedComptes) { id ->
                        deleteCompte(id)
                    }
                    recyclerView.adapter = adapter
                }
            } catch (e: ApolloException) {
                e.printStackTrace()
            }
        }
    }

    private fun createCompte(solde: Float, dateCreation: String, type: String) {
        lifecycleScope.launch {
            try {
                val compteRequest = CompteRequest(
                    solde = Optional.Present(solde.toDouble()),
                    dateCreation = Optional.Present(dateCreation),
                    type = Optional.Present(TypeCompte.valueOf(type.uppercase()))
                )

                val response = apolloClient.mutation(SaveCompteMutation(compteRequest)).execute()

                if (response.data?.saveCompte != null) {
                    Toast.makeText(this@MainActivity, "Compte créé avec succès!", Toast.LENGTH_SHORT).show()
                    fetchComptes()
                } else {
                    Toast.makeText(this@MainActivity, "Échec de la création du compte", Toast.LENGTH_SHORT).show()
                }
            } catch (e: ApolloException) {
                e.printStackTrace()
                Toast.makeText(this@MainActivity, "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteCompte(id: String) {

        val confirmationDialog = AlertDialog.Builder(this)
            .setTitle("Supprimer le compte")
            .setMessage("Êtes-vous sûr de vouloir supprimer ce compte ?")
            .setCancelable(true)
            .setPositiveButton("Oui") { dialog, _ ->

                confirmDelete(id)
                dialog.dismiss()
            }
            .setNegativeButton("Non") { dialog, _ ->

                dialog.dismiss()
            }
            .create()


        confirmationDialog.show()
    }

    private fun confirmDelete(id: String) {
        lifecycleScope.launch {
            try {
                val response = apolloClient.mutation(DeleteCompteMutation(id)).execute()

                if (response.data?.deleteCompte?.contains("deleted successfully") == true) {
                    runOnUiThread {
                        adapter.removeItem(id)
                        Toast.makeText(this@MainActivity, "Compte supprimé avec succès!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "Échec de la suppression du compte", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: ApolloException) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

