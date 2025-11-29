package com.example.app_qik_tfg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PedidoSingleton {
    private static PedidoSingleton instance;
    private final List<Product> pedido;

    private PedidoSingleton() {
        pedido = new ArrayList<>();
    }

    public static PedidoSingleton getInstance() {
        if (instance == null) {
            instance = new PedidoSingleton();
        }
        return instance;
    }

    // Añadir 1 unidad
    public void agregarProducto(Product p) {
        for (Product existing : pedido) {
            if (existing.getName().equals(p.getName())) {
                existing.setCantidad(existing.getCantidad() + 1);
                return;
            }
        }
        p.setCantidad(1);
        pedido.add(p);
    }

    //  Quitar 1 unidad
    public void quitarUnidad(Product p) {
        Iterator<Product> it = pedido.iterator();
        while (it.hasNext()) {
            Product existing = it.next();
            if (existing.getName().equals(p.getName())) {

                int nuevaCantidad = existing.getCantidad() - 1;
                if (nuevaCantidad > 0) {
                    existing.setCantidad(nuevaCantidad);
                    p.setCantidad(nuevaCantidad);
                } else {
                    existing.setCantidad(0);
                    p.setCantidad(0);
                    it.remove();                      // lo sacamos del pedido
                }
                return;
            }
        }
        // Si no estaba en el pedido, nos aseguramos de dejar cantidad a 0
        p.setCantidad(0);
    }

    public List<Product> getPedido() {
        return pedido;
    }

    public void limpiarPedido() {
        pedido.clear();
    }
}
