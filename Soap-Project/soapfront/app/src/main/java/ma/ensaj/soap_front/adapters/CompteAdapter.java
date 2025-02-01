package ma.ensaj.soap_front.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import ma.ensaj.soap_front.MainActivity;
import ma.ensaj.soap_front.R;
import ma.ensaj.soap_front.models.Compte;
public class CompteAdapter extends RecyclerView.Adapter<CompteAdapter.ViewHolder> {

    private List<Compte> comptes;
    private Context context;

    public CompteAdapter(List<Compte> comptes, Context context) {
        this.comptes = comptes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_compte, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Compte compte = comptes.get(position);
        holder.tvId.setText(String.valueOf(compte.getId()));
        holder.tvSolde.setText(String.valueOf(compte.getSolde()));
        holder.tvDate.setText(compte.getDateCreation());
        holder.tvType.setText(compte.getType());


        holder.btnUpdate.setOnClickListener(v -> {

            compte.setSolde(compte.getSolde());
            ((MainActivity) context).updateCompteDialog(compte.getId(), compte);
        });

        holder.btnDelete.setOnClickListener(v -> {
            ((MainActivity) context).deleteCompte(compte.getId());
        });
    }

    @Override
    public int getItemCount() {
        return comptes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvId, tvSolde, tvDate, tvType;
        ImageButton btnUpdate, btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvSolde = itemView.findViewById(R.id.tvSolde);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvType = itemView.findViewById(R.id.tvType);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
