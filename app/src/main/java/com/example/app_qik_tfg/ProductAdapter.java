package com.example.app_qik_tfg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
        TextView tvCantidadAñadida = convertView.findViewById(R.id.tvCantidadAñadida);
        Button btnAdd = convertView.findViewById(R.id.btnAdd);

        if (product != null) {
            imgProduct.setImageResource(product.getImgProduct());
            tvName.setText(product.getName());
            tvPrice.setText(String.format("€ %.2f", product.getPrice()));

            // Mostrar la cantidad actual
            tvCantidadAñadida.setText("Añadido: " + product.getCantidad());

            // ➕ SUMAR al pulsar el botón morado
            btnAdd.setOnClickListener(v -> {
                PedidoSingleton.getInstance().agregarProducto(product);
                tvCantidadAñadida.setText("Añadido: " + product.getCantidad());

                Toast.makeText(
                        getContext(),
                        product.getName() + " añadido al pedido",
                        Toast.LENGTH_SHORT
                ).show();
            });

            // ➖ RESTAR al pulsar el texto rojo "Añadido: X"
            tvCantidadAñadida.setOnClickListener(v -> {
                if (product.getCantidad() > 0) {
                    PedidoSingleton.getInstance().quitarUnidad(product);
                    tvCantidadAñadida.setText("Añadido: " + product.getCantidad());

                    Toast.makeText(
                            getContext(),
                            "Quitado 1 de " + product.getName(),
                            Toast.LENGTH_SHORT
                    ).show();
                }
            });
        }

        return convertView;
    }
}
