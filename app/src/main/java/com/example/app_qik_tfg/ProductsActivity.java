package com.example.app_qik_tfg;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    // ListView donde se mostrarán los productos
    ListView lvProducts;
    // Texto que mostrará el nombre de la categoría seleccionada
    TextView tvCategoryName;
    // Lista donde se almacenarán los productos obtenidos de Firestore
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        // Referencias a los elementos del layout
        tvCategoryName = findViewById(R.id.tvCategoryName);
        lvProducts = findViewById(R.id.lvProducts);

        // Se inicializa la lista de productos
        products = new ArrayList<>();


        // Se obtiene la categoría enviada desde MenuActivity
        String category = getIntent().getStringExtra("category");

        // Se muestra la categoría en pantalla
        tvCategoryName.setText(category);

    }


}
