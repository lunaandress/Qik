package com.example.app_qik_tfg;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class OrderSummaryActivity extends AppCompatActivity {

    // Elementos de la interfaz
    private ListView lvResumen;
    private TextView tvTotal;
    private Button btnEnviarPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Infla el layout de la actividad (asegúrate de que exista activity_order_summary.xml)
        setContentView(R.layout.activity_order_summary);

        // Asignación de vistas mediante IDs del layout
        lvResumen = findViewById(R.id.lvResumen);
        tvTotal = findViewById(R.id.tvTotal);
        btnEnviarPedido = findViewById(R.id.btnEnviarPedido);

        // Obtener la lista de productos del pedido desde el Singleton
        List<Product> pedido = PedidoSingleton.getInstance().getPedido();

        // Crear y asignar el adaptador personalizado que muestra el resumen del pedido
        ResumenAdapter adapter = new ResumenAdapter(this, pedido);
        lvResumen.setAdapter(adapter);

        // Calcular el total del pedido
        double total = 0;
        for (Product p : pedido) {
            total += p.getPrice() * p.getCantidad(); // precio x cantidad
        }

        // Mostrar el total en el TextView formateado a 2 decimales
        tvTotal.setText("Total: €" + String.format("%.2f", total));

        // Acción del botón "Enviar Pedido"
        btnEnviarPedido.setOnClickListener(v -> {
            // Aquí solo es una simulación, solo se muestra un mensaje
            Toast.makeText(this, "Pedido enviado (simulado)", Toast.LENGTH_SHORT).show();

            // Limpiar el pedido almacenado en el Singleton
            PedidoSingleton.getInstance().limpiarPedido();

            // Cerrar la actividad y volver a la anterior
            finish();
        });
    }
}





