package com.example.app_qik_tfg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    // Declaras un ListView donde mostrarás las categorías
    ListView lvCategories;

    // Arreglo con las categorías que se mostrarán en el ListView
    String[] categories = {"Bebidassss", "Comidas", "Postres"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        // Conectas el ListView del layout con la variable en la clase
        lvCategories = findViewById(R.id.lvCategories);

        // Creas un adaptador que convierte el arreglo en elementos visuales de lista
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, categories);

        // Asignas el adaptador al ListView
        lvCategories.setAdapter(adapter);

        // Evento al hacer clic en un ítem de la lista
        lvCategories.setOnItemClickListener((parent, view, position, id) -> {

            // Obtienes la categoría seleccionada
            String category = categories[position];

            // Lanzar ProductsActivity con el nombre de la categoría
            Intent intent = new Intent(MenuActivity.this, ProductsActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        });
    }
}
