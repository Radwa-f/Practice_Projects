package ma.tp2_pizza_mobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ma.tp2_pizza_mobile.adapter.PizzaAdapter;
import ma.tp2_pizza_mobile.classes.Produit;
import ma.tp2_pizza_mobile.service.ProduitService;

public class ListPizzaActivity extends AppCompatActivity {

    private ProduitService prod = null;
    private ListView list;
    private PizzaAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pizza);

        prod = new ProduitService();
        prod.create(new Produit("Barbequed Pizaa", 3, R.drawable.pizza1, 35, "- 2 boneless skinless chicken breast halves\n" +
                "(6 ounces each)\n" +
                "- 1/4 teaspoon pepper\n" +
                "- 1 cup barbecue sauce, divided\n" +
                "- 1 tube (13.8 ounces) refrigerated pizza crust\n" +
                "- 2 teaspoons olive oil\n" +
                "-2 cups shredded Gouda cheese\n" +
                "-1 small red onion, halved and thinly sliced\n" +
                "-1/4 cup minced fresh cilantro", "So fast and so easy with refrigerated pizza crust, these saucy, smoky pizzas make quick fans with their hot-off-the-grill, rustic flavor. They're perfect for spur-of-the-moment cookouts and summer dinners on the patio. —Alicia Trevithick, Temecula, California", "Preparation: \n \n STEP 1:\n" +
                "prinkle chicken with pepper; place on an oiled grill rack over medium heat. Grill, covered, until a thermometer reads 165°,\n" +
                "5-7 minutes per side, basting frequently with 1/2 cup barbecue sauce during the last 4 minutes. Cool slightly. Cut into cubes.\n" +
                "STEP 2:\n" +
                "ivide dough in half. On a well-greased large sheet of heavy-duty foil, press each portion of dough into a 10x8-in. rectangle; brush lightly with oil. Invert dough onto grill rack; peel off foil. Grill, covered, over medium heat until bottom is lightly browned, 1-2 minutes. \nSTEP 3:\n" +
                "emove from grill. Spread grilled sides with remaining barbecue sauce. Top with cheese, chicken and onion. Grill, covered, until bottom is lightly browned and cheese is melted, 2-3 minutes. Sprinkle with cilantro. Yield: 2 pizzas (4 pieces each)."));
        prod.create(new Produit("Bruschetta Pizza", 5, R.drawable.pizza2, 35, "pizza zwina", "3 ingredients", "pizza"));
        prod.create(new Produit("Spinach Pizza", 2, R.drawable.pizza3, 25, "pizza zwina", "6 ingredients", "pizza"));
        prod.create(new Produit("Deep-Dish Sausage Pizza", 20, R.drawable.pizza4, 25, "pizza zwina", "6 ingredients", "pizza"));
        prod.create(new Produit("Homemade Pizza", 20, R.drawable.pizza5, 50, "pizza zwina", "6 ingredients", "pizza"));
        prod.create(new Produit("Pesto Chicken Pizza", 20, R.drawable.pizza6, 50, "pizza zwina", "6 ingredients", "pizza"));
        prod.create(new Produit("Loaded Mexican Pizza", 20, R.drawable.pizza7, 30, "pizza zwina", "6 ingredients", "pizza"));
        prod.create(new Produit("Bacon Cheeseburger Pizza", 20, R.drawable.pizza8, 20, "pizza zwina", "6 ingredients", "pizza"));
        prod.create(new Produit("Pizza Margarita", 20, R.drawable.pizza9, 30, "pizza zwina", "6 ingredients", "pizza"));
        prod.create(new Produit("Pepperoni-Sausage Stuffed Pizza", 20, R.drawable.pizza10, 45, "pizza zwina", "6 ingredients", "pizza"));

        list = findViewById(R.id.liste);
        list.setAdapter(new PizzaAdapter(prod.findAll(), this));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Produit selectedPizza = (Produit) prod.findAll().get(position);

                Intent intent = new Intent(ListPizzaActivity.this, DetailsActivity.class);
                intent.putExtra("name", selectedPizza.getNom());
                intent.putExtra("description", selectedPizza.getDescription());
                intent.putExtra("image", selectedPizza.getPhoto());
                intent.putExtra("preparation", selectedPizza.getPreparation());

                startActivity(intent);
            }
        });



    }
}