package ma.tp2_pizza_mobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import ma.tp2_pizza_mobile.R;
import ma.tp2_pizza_mobile.classes.Produit;

public class PizzaAdapter extends BaseAdapter {

    private List<Produit> produits;
    private LayoutInflater inflater;

    public PizzaAdapter(List<Produit> produits, Activity activity) {
        this.produits = produits;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return produits.size();
    }

    @Override
    public Object getItem(int position) {
        return produits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = inflater.inflate(R.layout.item,null);

        TextView id = convertView.findViewById(R.id.id);
        TextView nom = convertView.findViewById(R.id.nom);
        TextView duree = convertView.findViewById(R.id.duree);
        TextView ingredient = convertView.findViewById(R.id.ingredients);
        ImageView image = convertView.findViewById(R.id.photo);

        id.setText(produits.get(position).getId()+"");
        nom.setText(produits.get(position).getNom()+"");
        duree.setText(String.format("%s min", (int)produits.get(position).getDuree()));
        ingredient.setText("Ingredient: " + produits.get(position).getNbrIngredients() );
        image.setImageResource(produits.get(position).getPhoto());
        return convertView;
    }
    public void removeFruit(int position) {
        produits.remove(position);
        notifyDataSetChanged();
    }
    public List<Produit> findAll() {
        return produits;
    }

    public void create(Produit fruit) {
        produits.add(fruit);
    }
}
