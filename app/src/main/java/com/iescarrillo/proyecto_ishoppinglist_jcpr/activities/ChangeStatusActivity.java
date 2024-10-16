package com.iescarrillo.proyecto_ishoppinglist_jcpr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.iescarrillo.proyecto_ishoppinglist_jcpr.R;
import com.iescarrillo.proyecto_ishoppinglist_jcpr.adapters.ProductAdapter;
import com.iescarrillo.proyecto_ishoppinglist_jcpr.database.Database;
import com.iescarrillo.proyecto_ishoppinglist_jcpr.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ChangeStatusActivity extends AppCompatActivity {

    private Spinner spinnerProducts;
    private Button btnSaveChange;
    private Button btnChangeCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_status);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinnerProducts = findViewById(R.id.spinnerProducts);
        btnSaveChange = findViewById(R.id.btnSaveChange);

        List<Product> noPendProducts = new ArrayList<>();

        for (Product product : Database.productList) {
            if (!product.isPendStatus()) {
                noPendProducts.add(product);
            }
        }

        ProductAdapter adapter = new ProductAdapter(this, R.layout.spinner_product, noPendProducts);

        adapter.setDropDownViewResource(R.layout.spinner_product);

        spinnerProducts.setAdapter(adapter);

        btnSaveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProductStatus();
            }
        });

        btnChangeCancel = findViewById(R.id.btnCancelChange);

        btnChangeCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMainActivity = new Intent(ChangeStatusActivity.this, MainActivity.class);

                startActivity(intentMainActivity);
            }
        });
    }

    private void saveProductStatus(){
        Product selectedProduct = (Product) spinnerProducts.getSelectedItem();

        if(selectedProduct != null){
            selectedProduct.setPendStatus(true);

            Intent intentMainActivity = new Intent(ChangeStatusActivity.this, MainActivity.class);

            startActivity(intentMainActivity);
        }else{
            Toast.makeText(this, "No hay un producto seleccionado", Toast.LENGTH_SHORT).show();
        }
    }

}