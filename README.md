# Qik – Aplicación de pedidos en restaurante mediante QR

Qik es una aplicación móvil Android desarrollada como **Trabajo de Fin de Grado (TFG)** del ciclo **Desarrollo de Aplicaciones Multiplataforma (DAM)**.
Su objetivo es permitir a los clientes de un restaurante realizar pedidos directamente desde su mesa mediante el escaneo de un código QR, eliminando la necesidad de interacción inicial con el personal y agilizando el proceso de pedido.

---

## 📱 Descripción general

La aplicación sigue un modelo similar al utilizado en restaurantes de comida rápida (tipo KFC), donde:

* El **cliente escanea un código QR** ubicado en la mesa.
* El QR identifica de forma única el **restaurante** y la **mesa**.
* La aplicación carga el **menú desde la base de datos**.
* El cliente selecciona productos, revisa el pedido y lo envía.
* El pedido queda **almacenado en la nube** y asociado a la mesa.

La aplicación está orientada exclusivamente al **cliente en mesa**.
No incluye, por el momento, aplicaciones para camareros, cocina o administración.

---

## 🎯 Funcionalidades principales

* Escaneo de códigos QR desde la aplicación.
* Interpretación automática del restaurante y la mesa.
* Visualización del menú organizado por categorías:

  * Bebidas
  * Comidas
  * Postres
* Listado dinámico de productos con imágenes y precios.
* Gestión del pedido:

  * Añadir y eliminar productos.
  * Cálculo automático del total.
* Visualización del resumen del pedido.
* Envío del pedido a la base de datos en la nube.
* Almacenamiento del pedido asociado a restaurante y mesa.

---

## 🏗️ Arquitectura del sistema

La aplicación sigue una arquitectura **cliente–servidor**:

* **Cliente**: Aplicación Android nativa.
* **Backend**: Firebase Cloud Firestore.
* **Comunicación**: API oficial de Firebase.

Los datos se almacenan utilizando una estructura orientada a documentos, adecuada para aplicaciones móviles y escalable a múltiples restaurantes.

---

## 🛠️ Tecnologías utilizadas

* **Lenguaje**: Java
* **Entorno de desarrollo**: Android Studio
* **Base de datos**: Firebase Cloud Firestore
* **Escaneo QR**: ZXing Android Embedded
* **Carga de imágenes**: Glide
* **Diseño de diagramas**: diagrams.net (draw.io)
* **Gestión del proyecto**: Git / GitHub

---

## 📂 Estructura del proyecto

* `MainActivity`
  Escaneo del código QR.

* `MenuActivity`
  Visualización de las categorías del menú.

* `ProductsActivity`
  Listado de productos filtrados por categoría.

* `OrderSummaryActivity`
  Resumen y envío del pedido.

* `PedidoSingleton`
  Gestión global del pedido durante la sesión.

* `ProductAdapter / ResumenAdapter`
  Adaptadores para la visualización de listas.

---

## ☁️ Base de datos (Firestore)

Estructura general:

* **restaurantes**

  * `restaurante_id`

    * **producto**
    * **pedidos**

      * Pedido

        * fecha
        * total
        * estado
        * mesaId
        * productos (lista embebida)

Los productos del pedido se almacenan como una estructura embebida dentro del documento del pedido.

---

## 🔍 Estado del proyecto

✅ Proyecto funcional
✅ Probado en emulador y dispositivo físico
✅ Memoria académica finalizada
🚧 Posibles ampliaciones futuras

---

## 🚀 Posibles mejoras futuras

* Aplicación para camareros y cocina.
* Panel de administración web.
* Autenticación de usuarios o mesas.
* Métodos de pago integrados.
* Notificaciones en tiempo real.
* Versión para dispositivos iOS.

---

## 🎓 Contexto académico

Este proyecto ha sido desarrollado como **Trabajo de Fin de Grado (TFG)** del ciclo formativo **Desarrollo de Aplicaciones Multiplataforma (DAM)**, aplicando conocimientos de:

* Programación Android
* Diseño de interfaces
* Modelado de sistemas
* Bases de datos en la nube
* Arquitectura de aplicaciones móviles

---

## 📄 Licencia

Proyecto desarrollado con fines **académicos**.
Uso y modificación permitidos únicamente con fines educativos.

---
**Curso:** Desarrollo de Aplicaciones Multiplataforma (DAM)
**Año:** 2025
