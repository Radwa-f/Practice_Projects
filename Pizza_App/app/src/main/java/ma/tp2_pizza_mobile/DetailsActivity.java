package ma.tp2_pizza_mobile;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");
        int imageRes = getIntent().getIntExtra("image", 0);
        String preparation = getIntent().getStringExtra("preparation");

        ImageView pizzaImage = findViewById(R.id.pizza_image);
        TextView pizzaName = findViewById(R.id.pizza_name);
        TextView pizzaDescription = findViewById(R.id.pizza_description);
        TextView pizzaPreparation = findViewById(R.id.pizza_preparation);

        pizzaImage.setImageResource(imageRes);
        pizzaName.setText(name);
        pizzaDescription.setText("Description:\n \n " + description);
        pizzaPreparation.setText(preparation);
    }
}
