package com.springcore.springcore.hiberOrm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainApp {
    public static void main(String[] args) {
        // Load Hibernate configuration
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        
        // Build SessionFactory
        SessionFactory factory = cfg.buildSessionFactory();
        
        // Open session
        Session session = factory.openSession();
        
        // Begin transaction
        Transaction tx = session.beginTransaction();
        
        // Create and set student data
        Student student = new Student();
        student.setId(184848);
        student.setName("Sumit");
        student.setCity("Cochin");
        
        // Save object (use persist for Hibernate 6)
        session.persist(student);
        
        // Commit and close
        tx.commit();
        session.close();
        factory.close();
        
        System.out.println("✅ Student data saved successfully!");
    }
}
