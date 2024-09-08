package org.example;
import Entidades.Domicilio;
import lombok.*;
import Entidades.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            /*Cliente cliente1 = Cliente.builder()
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

            entityManager.persist(cliente1);
            Domicilio dom2 = entityManager.find(Domicilio.class,1L);
            Cliente cli1 = entityManager.find(Cliente.class, 1L);

            System.out.println("Cliente de domicilio: " + dom2.getCliente().getDni());
            System.out.println("domicilio de Cliente: "+cli1.getDomicilio().getNombreCalle());*/

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
