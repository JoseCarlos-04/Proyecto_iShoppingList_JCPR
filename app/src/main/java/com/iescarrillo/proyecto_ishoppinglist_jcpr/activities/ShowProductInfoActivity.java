package com.iescarrillo.proyecto_ishoppinglist_jcpr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.iescarrillo.proyecto_ishoppinglist_jcpr.R;

public class ShowProductInfoActivity extends AppCompatActivity {

    private TextView textInfoProductName;
    private TextView textInfoDescription;
    private TextView textInfoPend;
    private Button btnInfoEdit;
    private Button btnInfoCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_product_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textInfoProductName = findViewById(R.id.textInfoProductName);
        textInfoDescription = findViewById(R.id.textInfoDescription);
        textInfoPend = findViewById(R.id.textInfoPend);

        textInfoProductName.setText(textInfoProductName.getText() + getIntent().getStringExtra("productName"));
        textInfoDescription.setText(textInfoDescription.getText() + getIntent().getStringExtra("productDescription"));
        if(getIntent().getBooleanExtra("productPend", true)){
            textInfoPend.setText(textInfoPend.getText() + "Sí");
        }else{
            textInfoPend.setText(textInfoPend.getText() + "No");
        }


        btnInfoEdit = findViewById(R.id.btnInfoEdit);

        btnInfoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEditProductActivity = new Intent(ShowProductInfoActivity.this, EditProductActivity.class);

                intentEditProductActivity.putExtra("productId", getIntent().getIntExtra("id", 0));
                intentEditProductActivity.putExtra("productName", getIntent().getStringExtra("productName"));
                intentEditProductActivity.putExtra("productDescription", getIntent().getStringExtra("productDescription"));
                intentEditProductActivity.putExtra("productPend", getIntent().getBooleanExtra("productPend", false));

                startActivity(intentEditProductActivity);
            }
        });

        btnInfoCancel = findViewById(R.id.btnInfoCancel);

        btnInfoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMainActivity = new Intent(ShowProductInfoActivity.this, MainActivity.class);

                startActivity(intentMainActivity);
            }
        });

    }
}