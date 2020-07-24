package com.geekbrains.spring;


import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("configs/HW3/hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            System.out.println("============\n=== READ ===\n============");
            session = factory.getCurrentSession();
            session.beginTransaction();
            Buyers buyersFromDb = session.get(Buyers.class, 3L);
            System.out.println(buyersFromDb + " купил:");
            for (Products p : buyersFromDb.getProducts()) {
                System.out.println(p.getName());
            }
            session.getTransaction().commit();

            System.out.println("============\n=== READ ===\n============");
            session = factory.getCurrentSession();
            session.beginTransaction();
            Products productsFromDb = session.get(Products.class, 2L);
            System.out.println(productsFromDb + " был куплен:");
            for (Buyers b : productsFromDb.getBuyers()) {
                System.out.println(b.getName());
            }
            session.getTransaction().commit();

            System.out.println("============\n=== DELETE ===\n============");
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.delete(session.get(Products.class, 1L));
            session.getTransaction().commit();

        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
