package com.example.app_qik_tfg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ResumenAdapter extends ArrayAdapter<Product> {

    // Constructor del adaptador
    // Recibe el contexto y la lista de productos que se va a mostrar en el ListView
    public ResumenAdapter(Context context, List<Product> productos) {
        // Llamada al constructor del ArrayAdapter
        // El "0" indica que no se usará un layout por defecto, sino uno personalizado
        super(context, 0, productos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtener el producto correspondiente a esta posición en la lista
        Product producto = getItem(position);

        // Verificar si convertView está vacío
        // Si es null, significa que hay que inflar (crear) una nueva vista desde el XML item_resumen
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_resumen, parent, false);
        }

        // Obtener las referencias a los TextView dentro del layout item_resumen
        TextView tvNombre = convertView.findViewById(R.id.tvNombreResumen);
        TextView tvCantidad = convertView.findViewById(R.id.tvCantidadResumen);
        TextView tvSubtotal = convertView.findViewById(R.id.tvSubtotal);

        // Asignar valores a los TextView

        // Nombre del producto
        tvNombre.setText(producto.getName());

        // Cantidad del producto (por ejemplo: x3)
        tvCantidad.setText("x" + producto.getCantidad());

        // Cálculo del subtotal (precio * cantidad)
        double subtotal = producto.getCantidad() * producto.getPrice();

        // Mostrar subtotal formateado con 2 decimales
        tvSubtotal.setText("€" + String.format("%.2f", subtotal));

        // Retornar la vista ya configurada para mostrarla en el ListView
        return convertView;
    }
}
