package com.example.app_qik_tfg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    private static final String TAG = "ProductAdapter";

    public ProductAdapter(@NonNull Context context, @NonNull List<Product> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        try {
            Product product = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.product_item, parent, false);
            }

            ImageView imgProduct = convertView.findViewById(R.id.imgProduct);
            TextView tvName = convertView.findViewById(R.id.tvProductName);
            TextView tvPrice = convertView.findViewById(R.id.tvProductPrice);
            TextView tvCantidadAñadida = convertView.findViewById(R.id.tvCantidadAñadida);
            Button btnAdd = convertView.findViewById(R.id.btnAdd);

            if (product == null) {
                Log.w(TAG, "Producto null en posición " + position);
                return convertView;
            }

            if (imgProduct != null) imgProduct.setImageResource(product.getImgProduct());
            if (tvName != null) tvName.setText(product.getName());
            if (tvPrice != null) tvPrice.setText(String.format("€ %.2f", product.getPrice()));
            if (tvCantidadAñadida != null)
                tvCantidadAñadida.setText("Añadido: " + product.getCantidad());

            if (btnAdd != null && tvCantidadAñadida != null) {
                btnAdd.setOnClickListener(v -> {
                    PedidoSingleton.getInstance().agregarProducto(product);
                    tvCantidadAñadida.setText("Añadido: " + product.getCantidad());
                });

                tvCantidadAñadida.setOnClickListener(v -> {
                    if (product.getCantidad() > 0) {
                        PedidoSingleton.getInstance().quitarUnidad(product);
                        tvCantidadAñadida.setText("Añadido: " + product.getCantidad());
                    }
                });
            }

            return convertView;

        } catch (Exception e) {
            Log.e(TAG, "Error en getView posición " + position, e);
            return convertView != null ? convertView : new View(getContext());
        }
    }
}
