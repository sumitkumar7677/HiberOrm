package com.springcore.springcore.hiberOrm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainApp {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Create Certificate
        Certificate certificate = new Certificate();
        certificate.setTitle("Java Foundation");
        certificate.setDuration("8 Hours");

        // Create Student and set relationship
        Student student = new Student();
        student.setName("Amit");
        student.setCity("Patna");
        student.setCertificate(certificate);

        // Set inverse relationship
        certificate.setStudent(student);

        // Persist both — persist the owner entity first
        session.persist(certificate);
        session.persist(student);

        tx.commit();
        session.close();
        factory.close();

        System.out.println("✅ Student and Certificate saved successfully!");
    }
}
