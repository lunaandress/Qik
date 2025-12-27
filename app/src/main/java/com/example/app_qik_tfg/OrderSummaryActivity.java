package com.example.app_qik_tfg;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class OrderSummaryActivity extends AppCompatActivity {

    private static final String TAG = "OrderSummaryActivity";

    // UI
    private ListView lvResumen;
    private TextView tvTotal;
    private Button btnEnviarPedido;

    // Datos
    private List<Product> pedido;

    // Firestore
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        Log.d(TAG, "onCreate iniciado");

        // Referencias UI
        lvResumen = findViewById(R.id.lvResumen);
        tvTotal = findViewById(R.id.tvTotal);
        btnEnviarPedido = findViewById(R.id.btnEnviarPedido);

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance();

        // Obtener el pedido desde el Singleton
        pedido = PedidoSingleton.getInstance().getPedido();

        // Asignar adaptador al ListView
        ResumenAdapter adapter = new ResumenAdapter(this, pedido);
        lvResumen.setAdapter(adapter);

        // Calcular total y mostrarlo
        mostrarTotal();

        // Acción botón enviar
        btnEnviarPedido.setOnClickListener(v -> enviarPedido());
    }

    /**
     * Calcula el total del pedido y lo muestra en pantalla
     */
    private void mostrarTotal() {
        double total = 0;

        for (Product p : pedido) {
            total += p.getPrice() * p.getCantidad();
        }

        tvTotal.setText("Total: €" + String.format(Locale.getDefault(), "%.2f", total));
    }

    /**
     * Envía el pedido a Firestore
     */
    private void enviarPedido() {

        if (pedido == null || pedido.isEmpty()) {
            Toast.makeText(this, "El pedido está vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calcular total
        double total = 0;
        for (Product p : pedido) {
            total += p.getPrice() * p.getCantidad();
        }

        // Convertir productos a formato Firestore
        ArrayList<Map<String, Object>> listaProductos = new ArrayList<>();

        for (Product p : pedido) {
            if (p.getCantidad() > 0) {
                Map<String, Object> prod = new HashMap<>();
                prod.put("nombre", p.getName());
                prod.put("precio", p.getPrice());
                prod.put("cantidad", p.getCantidad());
                listaProductos.add(prod);
            }
        }

        // Mapa del pedido completo
        Map<String, Object> pedidoMap = new HashMap<>();
        pedidoMap.put("fecha", new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date()));
        pedidoMap.put("total", total);
        pedidoMap.put("productos", listaProductos);
        pedidoMap.put("estado", "pendiente");

        Log.d(TAG, "Enviando pedido: " + pedidoMap);

        // Enviar a Firestore
        db.collection("restaurantes")
                .document("restaurante_1")
                .collection("pedidos")
                .add(pedidoMap)
                .addOnSuccessListener(ref -> {
                    Log.d(TAG, "Pedido guardado con ID: " + ref.getId());
                    Toast.makeText(this, "Pedido enviado correctamente", Toast.LENGTH_LONG).show();

                    // Limpiar pedido y cerrar
                    PedidoSingleton.getInstance().limpiarPedido();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error al guardar pedido", e);
                    Toast.makeText(this, "Error al enviar el pedido", Toast.LENGTH_LONG).show();
                });
    }
}
