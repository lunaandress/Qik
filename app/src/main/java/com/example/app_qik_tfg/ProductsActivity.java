package com.example.app_qik_tfg;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    private static final String TAG = "ProductsActivity";

    ListView lvProducts;
    TextView tvCategoryName;
    ArrayList<Product> products;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        Log.d(TAG, "onCreate iniciado");

        tvCategoryName = findViewById(R.id.tvCategoryName);
        lvProducts = findViewById(R.id.lvProducts);
        products = new ArrayList<>();

        db = FirebaseFirestore.getInstance();
        Log.d(TAG, "Firestore inicializado");

        String category = getIntent().getStringExtra("category");
        Log.d(TAG, "Categoría recibida por Intent: [" + category + "]");

        if (category == null || category.trim().isEmpty()) {
            Log.e(TAG, "Categoría null o vacía — cerrando actividad");
            Toast.makeText(this, "Categoría inválida", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        tvCategoryName.setText(category);

        cargarProductosDesdeFirestore(category);
    }

    private void cargarProductosDesdeFirestore(String category) {

        Log.d(TAG, "Inicio carga de productos para categoría: [" + category + "]");

        db.collection("restaurantes")
                .document("restaurante_1")
                .collection("producto")
                .whereEqualTo("categoría", category)
                .get()
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful() && task.getResult() != null) {

                        products.clear();

                        Log.d(TAG, "Consulta Firestore OK. Documentos encontrados: "
                                + task.getResult().size());

                        for (QueryDocumentSnapshot doc : task.getResult()) {

                            try {
                                String docId = doc.getId();
                                String nombre = doc.getString("nombre");
                                Double precio = doc.getDouble("precio");
                                String imgName = doc.getString("img");

                                Log.d(TAG, "Documento ID: " + docId);
                                Log.d(TAG, " → nombre: " + nombre);
                                Log.d(TAG, " → precio: " + precio);
                                Log.d(TAG, " → img: " + imgName);

                                int imgResId = R.drawable.ic_launcher_background;

                                if (imgName != null && !imgName.trim().isEmpty()) {
                                    String drawableName = imgName
                                            .replace(".jpg", "")
                                            .replace(".png", "")
                                            .trim()
                                            .toLowerCase();

                                    imgResId = getResources().getIdentifier(
                                            drawableName,
                                            "drawable",
                                            getPackageName()
                                    );

                                    if (imgResId == 0) {
                                        Log.w(TAG, "Imagen no encontrada en drawable: " + drawableName);
                                        imgResId = R.drawable.ic_launcher_background;
                                    }
                                }

                                Product product = new Product(
                                        nombre != null ? nombre : "Sin nombre",
                                        precio != null ? precio : 0,
                                        imgResId
                                );

                                product.setCantidad(0);
                                products.add(product);

                            } catch (Exception e) {
                                Log.e(TAG, "Error procesando documento: " + doc.getId(), e);
                            }
                        }

                        Log.d(TAG, "Total productos cargados en lista: " + products.size());

                        ProductAdapter adapter = new ProductAdapter(this, products);
                         lvProducts.setAdapter(adapter);

                        if (products.isEmpty()) {
                            Log.w(TAG, "No se encontraron productos para esta categoría");
                            Toast.makeText(
                                    this,
                                    "No se encontraron productos en la categoría seleccionada",
                                    Toast.LENGTH_LONG
                            ).show();
                        }

                    } else {
                        Log.e(TAG, "Error al obtener productos de Firestore", task.getException());
                        Toast.makeText(
                                this,
                                "Error al cargar productos",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
    }
}
