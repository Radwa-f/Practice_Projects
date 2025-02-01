package ma.ensaj.apollokotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ma.ensaj.AllComptesQuery
//import ma.ensaj.apollokotlin.R
import ma.ensaj.appllokotlin.R

class ComptesAdapter(
    private var comptes: MutableList<AllComptesQuery.AllCompte?>,
    private val onDeleteClick: (String) -> Unit
) : RecyclerView.Adapter<ComptesAdapter.CompteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_compte, parent, false)
        return CompteViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompteViewHolder, position: Int) {
        val compte = comptes[position]
        holder.bind(compte, onDeleteClick)
    }

    override fun getItemCount(): Int = comptes.size

    fun removeItem(id: String) {
        val position = comptes.indexOfFirst { it?.id == id }
        if (position != -1) {
            comptes.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun updateData(newComptes: List<AllComptesQuery.AllCompte?>) {
        comptes.clear()
        comptes.addAll(newComptes)
        notifyDataSetChanged()
    }

    fun addItem(compte: AllComptesQuery.AllCompte) {
        comptes.add(compte)
        notifyItemInserted(comptes.size - 1)
    }

    class CompteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idTextView: TextView = itemView.findViewById(R.id.compte_id)
        private val soldeTextView: TextView = itemView.findViewById(R.id.compte_solde)
        private val dateCreationTextView: TextView = itemView.findViewById(R.id.compte_date_creation)
        private val typeTextView: TextView = itemView.findViewById(R.id.compte_type)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.btn_delete)

        fun bind(compte: AllComptesQuery.AllCompte?, onDeleteClick: (String) -> Unit) {
            idTextView.text = " ${compte?.id}"
            soldeTextView.text = " ${compte?.solde}"
            dateCreationTextView.text = " ${compte?.dateCreation}"
            typeTextView.text = " ${compte?.type}"

            deleteButton.setOnClickListener {
                compte?.id?.let { id ->
                    onDeleteClick(id)
                }
            }
        }
    }
}
