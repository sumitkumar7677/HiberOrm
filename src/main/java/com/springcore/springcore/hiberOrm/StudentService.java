package com.springcore.springcore.hiberOrm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentService {

    private SessionFactory sessionFactory;

    public StudentService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Save student
    public void saveStudent(Student student) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(student);
            tx.commit();
            System.out.println("✅ Student saved successfully!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            System.out.println("❌ Failed to save student!");
        }
    }

    // Fetch student by ID using Hibernate 6/7 standard method
    
    public Student getById(long studentId) {
        Student student = null;

        try (Session session = sessionFactory.openSession()) {
            student = session.find(Student.class, studentId); // ✅ modern alternative to session.get()
            if (student != null) {
                System.out.println("✅ Student fetched successfully!");
            } else {
                System.out.println("⚠️ Student not found for ID: " + studentId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Failed to fetch student!");
        }

        return student;
    }
}
