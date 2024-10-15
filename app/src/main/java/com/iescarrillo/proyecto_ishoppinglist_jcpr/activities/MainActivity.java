package com.iescarrillo.proyecto_ishoppinglist_jcpr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

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

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnAddProducts;
    private Button btnPendProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicializa la lista de productos desde la base de datos (solo si es null la lista es decir la primera vez)
        Database.initializeList();

        listView = findViewById(R.id.listView);

        List<Product> pendProducts = new ArrayList<>();

        // Filtra los productos pendientes de la lista de la base de datos
        for (Product product : Database.productList) {
            if (product.isPendStatus()) {
                pendProducts.add(product);
            }
        }

        // Crea un adaptador para mostrar los productos en el ListView
        ProductAdapter adapter = new ProductAdapter(this, R.layout.item_product, pendProducts);
        listView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAddProducts = findViewById(R.id.btnAddProducts);

        btnAddProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCreateProductActivity = new Intent(MainActivity.this, CreateProductActivity.class);

                startActivity(intentCreateProductActivity);
            }
        });

        btnPendProducts = findViewById(R.id.btnPendProducts);

        btnPendProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChangeStatusActivity = new Intent(MainActivity.this, ChangeStatusActivity.class);

                startActivity(intentChangeStatusActivity);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = pendProducts.get(position);

                Intent intentShowProductInfoActivity = new Intent(MainActivity.this, ShowProductInfoActivity.class);

                // Pasa datos del producto a la nueva actividad
                intentShowProductInfoActivity.putExtra("productId", selectedProduct.getId());
                intentShowProductInfoActivity.putExtra("productName", selectedProduct.getProductName());
                intentShowProductInfoActivity.putExtra("productDescription", selectedProduct.getInfoNote());
                intentShowProductInfoActivity.putExtra("productPend", selectedProduct.isPendStatus());

                startActivity(intentShowProductInfoActivity);
            }
        });

    }
}