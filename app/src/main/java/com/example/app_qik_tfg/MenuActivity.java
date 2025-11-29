package com.example.app_qik_tfg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MenuActivity extends AppCompatActivity {

    ListView lvCategories;
    MaterialButton btnResumenPedido;

    String[] categories = {"Bebidas", "Comidas", "Postres"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        lvCategories = findViewById(R.id.lvCategories);
        btnResumenPedido = findViewById(R.id.ResumenPedido);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                categories
        );
        lvCategories.setAdapter(adapter);

        // Al pulsar una categoría → abre ProductsActivity
        lvCategories.setOnItemClickListener((parent, view, position, id) -> {
            String category = categories[position];
            Intent intent = new Intent(MenuActivity.this, ProductsActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        });

        // Botón "Resumen de pedido"
        btnResumenPedido.setOnClickListener(v -> {
            // Miramos si hay algo en el pedido
            if (PedidoSingleton.getInstance().getPedido().isEmpty()) {
                Toast.makeText(this,
                        "No hay productos en el pedido",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                Intent intent = new Intent(MenuActivity.this, OrderSummaryActivity.class);
                startActivity(intent);
            }
        });
    }
}
