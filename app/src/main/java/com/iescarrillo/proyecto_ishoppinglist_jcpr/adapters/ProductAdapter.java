package com.iescarrillo.proyecto_ishoppinglist_jcpr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

        TextView textProductName = convertView.findViewById(R.id.textProductName);
        TextView textDescription = convertView.findViewById(R.id.textDescription);

        textProductName.setText(p.getProductName());
        textDescription.setText(p.getInfoNote());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product p = this.productList.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        TextView textProductName = convertView.findViewById(R.id.textProductName);
        TextView textDescription = convertView.findViewById(R.id.textDescription);

        textProductName.setText(p.getProductName());
        textDescription.setText(p.getInfoNote());

        return convertView;
    }
}
