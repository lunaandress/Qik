package com.example.app_qik_tfg;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    ListView lvProducts;
    TextView tvCategoryName;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        tvCategoryName = findViewById(R.id.tvCategoryName);
        lvProducts = findViewById(R.id.lvProducts);

        products = new ArrayList<>();

        // Obtener categoría desde el intent
        String category = getIntent().getStringExtra("category");
        tvCategoryName.setText(category);

        // Cargar productos locales (versión intermedia)
        cargarProductosLocalmente(category);

        // Adaptador
        ProductAdapter adapter = new ProductAdapter(this, products);
        lvProducts.setAdapter(adapter);
    }

    private void cargarProductosLocalmente(String category) {

        // Limpia la lista por si acaso
        products.clear();

        switch (category) {

            case "Bebidas":
            case "Bebidassss":  // por si mantienes el nombre anterior
                products.add(new Product("Coca-Cola", 1.50, R.drawable.ic_launcher_background));
                products.add(new Product("Agua", 1.00, R.drawable.ic_launcher_background));
                products.add(new Product("Fanta Naranja", 1.50, R.drawable.ic_launcher_background));
                break;

            case "Comidas":
                products.add(new Product("Hamburguesa", 6.50, R.drawable.ic_launcher_background));
                products.add(new Product("Pizza", 7.99, R.drawable.ic_launcher_background));
                products.add(new Product("Perrito Caliente", 3.50, R.drawable.ic_launcher_background));
                break;

            case "Postres":
                products.add(new Product("Tarta de Queso", 3.20, R.drawable.ic_launcher_background));
                products.add(new Product("Helado Vainilla", 2.00, R.drawable.ic_launcher_background));
                products.add(new Product("Brownie", 2.80, R.drawable.ic_launcher_background));
                break;
        }

        // Inicializamos cantidad en 0
        for (Product p : products) {
            p.setCantidad(0);
        }
    }
}
