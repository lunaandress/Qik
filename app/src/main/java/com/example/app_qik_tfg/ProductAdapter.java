package com.example.app_qik_tfg;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    public ProductAdapter(@NonNull Context context, @NonNull List<Product> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        Product product = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.product_item, parent, false);
        }

        ImageView imgProduct = convertView.findViewById(R.id.imgProduct);
        TextView tvName = convertView.findViewById(R.id.tvProductName);
        TextView tvPrice = convertView.findViewById(R.id.tvProductPrice);
        Button btnAdd = convertView.findViewById(R.id.btnAdd);

        if (product != null) {
            imgProduct.setImageResource(product.getImgProduct());
            tvName.setText(product.getName());
            tvPrice.setText(String.format("€ %.2f", product.getPrice()));

            btnAdd.setOnClickListener(v -> {
                Toast.makeText(getContext(),
                        product.getName() + " añadido al pedido",
                        Toast.LENGTH_SHORT).show();

                // Añade 1 unidad por defecto
                product.setCantidad(product.getCantidad() + 1);

                PedidoSingleton.getInstance().agregarProducto(product);
            });
        }

        return convertView;
    }

}
