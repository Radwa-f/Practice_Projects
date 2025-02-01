package ma.example.tp1_exe3_mobile;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class ResultActivity extends AppCompatActivity {

    private TextView resultatTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String result = getIntent().getStringExtra("result");

        resultatTextView = findViewById(R.id.result_text_view);
        resultatTextView.setText(result);
    }
}