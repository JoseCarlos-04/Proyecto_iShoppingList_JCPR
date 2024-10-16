package com.iescarrillo.proyecto_ishoppinglist_jcpr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.iescarrillo.proyecto_ishoppinglist_jcpr.R;
import com.iescarrillo.proyecto_ishoppinglist_jcpr.database.Database;
import com.iescarrillo.proyecto_ishoppinglist_jcpr.models.Product;

public class EditProductActivity extends AppCompatActivity {

    private EditText editEditProductName;
    private EditText editEditDescription;
    private Switch switchEditPendProduct;
    private Switch switchEditLactosa;
    private Switch switchEditGluten;
    private Button btnEditProduct;
    private Button btnCancelEditProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editEditProductName = findViewById(R.id.editEditProductName);
        editEditDescription = findViewById(R.id.editEditDescription);
        switchEditPendProduct = findViewById(R.id.switchEditPendProduct);
        switchEditLactosa = findViewById(R.id.switchEditLactosa);
        switchEditGluten = findViewById(R.id.switchEditGluten);

        editEditProductName.setText(getIntent().getStringExtra("productName"));
        editEditDescription.setText(getIntent().getStringExtra("productDescription"));
        switchEditPendProduct.setChecked(getIntent().getBooleanExtra("productPend", false));
        switchEditLactosa.setChecked(getIntent().getBooleanExtra("lactosa", false));
        switchEditGluten.setChecked(getIntent().getBooleanExtra("gluten", false));

        btnEditProduct = findViewById(R.id.btnEditProduct);

        btnEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct();
            }
        });

        btnCancelEditProduct = findViewById(R.id.btnCancelEditProduct);

        btnCancelEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentShowProductInfoActivity = new Intent(EditProductActivity.this, ShowProductInfoActivity.class);

                intentShowProductInfoActivity.putExtra("productId", getIntent().getIntExtra("id", 0));
                intentShowProductInfoActivity.putExtra("productName", getIntent().getStringExtra("productName"));
                intentShowProductInfoActivity.putExtra("productDescription", getIntent().getStringExtra("productDescription"));
                intentShowProductInfoActivity.putExtra("productPend", getIntent().getBooleanExtra("productPend", false));

                startActivity(intentShowProductInfoActivity);
            }
        });

    }

    private void saveProduct(){
        String productName = editEditProductName.getText().toString().trim();
        String description = editEditDescription.getText().toString().trim();
        boolean pendStatus = switchEditPendProduct.isChecked();
        boolean lactosa = switchEditLactosa.isChecked();
        boolean gluten = switchEditGluten.isChecked();


        if (productName.isEmpty()) {
            Toast.makeText(this, "El nombre del producto no puede estar vacío", Toast.LENGTH_SHORT).show();

            return;
        }

        for (Product p : Database.productList) {
            if (p.getProductName().equalsIgnoreCase(productName) && p.getId() != getIntent().getIntExtra("id", 0)) {
                Toast.makeText(this, "El nombre del producto ya existe y no puede repetirse", Toast.LENGTH_SHORT).show();

                return;
            }
        }

        for (Product p : Database.productList) {
            if (p.getId() == getIntent().getIntExtra("id", -1)) {
                p.setProductName(productName);
                p.setInfoNote(description);
                p.setPendStatus(pendStatus);
                p.setLactosa(lactosa);
                p.setGluten(gluten);

                break;
            }
        }

        Intent intentMainActivity = new Intent(EditProductActivity.this, MainActivity.class);

        startActivity(intentMainActivity);
    }

}