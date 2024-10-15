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

public class CreateProductActivity extends AppCompatActivity {

    private EditText editAddProductName;
    private EditText editAddDescription;
    private Switch switchPendProduct;
    private Button btnAddProduct;
    private Button btnCancelProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editAddProductName = findViewById(R.id.editAddProductName);
        editAddDescription = findViewById(R.id.editAddDescription);
        switchPendProduct = findViewById(R.id.switchPendProduct);
        btnAddProduct = findViewById(R.id.btnAddProduct);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct();
            }
        });

        btnCancelProduct = findViewById(R.id.btnCancelProduct);

        btnCancelProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMainActivity = new Intent(CreateProductActivity.this, MainActivity.class);

                startActivity(intentMainActivity);
            }
        });


    }

    private void saveProduct(){
        String productName = editAddProductName.getText().toString().trim();
        String description = editAddDescription.getText().toString().trim();
        boolean pendStatus = switchPendProduct.isChecked();

        if (productName.isEmpty()) {
            Toast.makeText(this, "El nombre del producto no puede estar vacío", Toast.LENGTH_SHORT).show();

            return;
        }

        for (Product p : Database.productList) {
            if (p.getProductName().equalsIgnoreCase(productName)) {
                Toast.makeText(this, "El nombre del producto ya existe y no puede repetirse", Toast.LENGTH_SHORT).show();

                return;
            }
        }

        Product newProduct = new Product();

        newProduct.setId(Database.productList.size() + 1);
        newProduct.setProductName(productName);
        newProduct.setInfoNote(description);
        newProduct.setPendStatus(pendStatus);

        Database.productList.add(newProduct);

        Intent intentMainActivity = new Intent(CreateProductActivity.this, MainActivity.class);

        startActivity(intentMainActivity);
    }
}