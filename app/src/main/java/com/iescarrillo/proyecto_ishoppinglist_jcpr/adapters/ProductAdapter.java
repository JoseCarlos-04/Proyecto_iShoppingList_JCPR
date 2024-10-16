package com.iescarrillo.proyecto_ishoppinglist_jcpr.adapters;

import static com.iescarrillo.proyecto_ishoppinglist_jcpr.R.*;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.iescarrillo.proyecto_ishoppinglist_jcpr.R;
import com.iescarrillo.proyecto_ishoppinglist_jcpr.models.Product;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    private List<Product> productList;

    public ProductAdapter(@NonNull Context context, int resource, List<Product> productList) {
        super(context, resource, productList);

        this.productList = productList;
    }

    public View getView(int position, View convertView, @NonNull android.view.ViewGroup parent) {
        Product p = this.productList.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        LinearLayout layoutListView = convertView.findViewById(id.layoutListView);

        TextView textProductName = convertView.findViewById(R.id.textProductName);
        TextView textDescription = convertView.findViewById(R.id.textDescription);
        TextView textLactosa = convertView.findViewById(R.id.textLactosa);
        TextView textGluten = convertView.findViewById(R.id.textGluten);

        textProductName.setText(p.getProductName());
        textDescription.setText(p.getInfoNote());

        if(p.isLactosa()){
            textLactosa.setText("Tiene lactosa");
        }else{
            textLactosa.setText("No tiene lactosa");
        }

        if(p.isGluten()){
            textGluten.setText("Tiene gluten");
        }else{
            textGluten.setText("No tiene gluten");
        }

        if(p.isLactosa() && p.isGluten()){
        }else if(p.isLactosa()){
        }else if(p.isGluten()){
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product p = this.productList.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(layout.spinner_product, parent, false);
        }

        TextView textProductName = convertView.findViewById(id.textSpinner);

        textProductName.setText(p.getProductName());

        return convertView;
    }
}
