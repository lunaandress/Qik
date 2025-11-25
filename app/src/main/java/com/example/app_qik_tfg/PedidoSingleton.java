package com.example.app_qik_tfg;

import java.util.ArrayList;
import java.util.List;

public class PedidoSingleton {
    private static PedidoSingleton instance;
    private List<Product> pedido;

    private PedidoSingleton() {
        pedido = new ArrayList<>();
    }

    public static PedidoSingleton getInstance() {
        if (instance == null) {
            instance = new PedidoSingleton();
        }
        return instance;
    }

    public void agregarProducto(Product p) {
        pedido.add(p);
    }

    public List<Product> getPedido() {
        return pedido;
    }

    public void limpiarPedido() {
        pedido.clear();
    }
}
