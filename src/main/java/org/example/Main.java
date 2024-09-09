package org.example;
import Entidades.*;
import lombok.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Factura factura1 = Factura.builder()
                    .detallesfactura(new ArrayList<DetalleFactura>())
                    .build();
            factura1.setNumero(12);
            factura1.setFecha("10/08/2024");
            Cliente cliente1 = Cliente.builder()
                    .nombre("Matias")
                    .apellido("Telo")
                    .dni(44904952)
                    .build();
            Domicilio dom1 = Domicilio.builder()
                    .nombreCalle("newbery")
                    .numero(2874)
                    .build();
            cliente1.setDomicilio(dom1);
            dom1.setCliente(cliente1);

            factura1.setCliente(cliente1);

            Categoria perecederos = Categoria.builder()
                    .denominacion("Perecederos")
                    .articulos(new ArrayList<Articulo>())
                    .build();
            Categoria lacteos = Categoria.builder()
                    .denominacion("Lacteos")
                    .articulos(new ArrayList<Articulo>())
                    .build();
            Categoria limpieza = Categoria.builder()
                    .denominacion("Limpieza")
                    .articulos(new ArrayList<Articulo>())
                    .build();

            Articulo art1 = Articulo.builder()
                    .cantidad(200)
                    .denominacion("Yogurt Ser sabor frutilla")
                    .precio(20)
                    .categorias(new ArrayList<Categoria>())
                    .detallesfactura(new ArrayList<DetalleFactura>())
                    .build();
            Articulo art2 = Articulo.builder()
                    .cantidad(300)
                    .denominacion("Yogurt Ser sabor frutilla")
                    .precio(80)
                    .categorias(new ArrayList<Categoria>())
                    .detallesfactura(new ArrayList<DetalleFactura>())
                    .build();
            art1.getCategorias().add(perecederos);
            art1.getCategorias().add(lacteos);
            lacteos.getArticulos().add(art1);
            perecederos.getArticulos().add(art1);

            art2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(art2);

            DetalleFactura det1 = DetalleFactura.builder().build();
            det1.setArticulo(art1);
            det1.setCantidad(2);
            det1.setSubtotal(40);

            art1.getDetallesfactura().add(det1);
            factura1.getDetallesfactura().add(det1);
            det1.setFactura(factura1);

            DetalleFactura det2 = DetalleFactura.builder().build();
            det2.setArticulo(art2);
            det2.setCantidad(1);
            det2.setSubtotal(80);

            art2.getDetallesfactura().add(det2);
            factura1.getDetallesfactura().add(det2);
            det2.setFactura(factura1);

            factura1.setTotal(120);

            entityManager.persist(factura1);
            entityManager.flush();
            entityManager.getTransaction().commit();

        }catch (Exception e){

            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase Persona");}

        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
