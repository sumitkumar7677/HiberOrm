package com.springcore.springcore.hiberOrm;

import java.util.Scanner;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp {
	public static void main(String[] args) {

		// Load Hibernate configuration
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		// Build SessionFactory
		SessionFactory factory = cfg.buildSessionFactory();

		// Initialize service class
		StudentService studentService = new StudentService(factory);

		// Create Certificate
		Certificate certificate = new Certificate();
		certificate.setTitle("Java Foundation");
		certificate.setDuration("8 Hours");

		// Create Student and link certificate
		Student student = new Student();
		student.setName("Amit");
		student.setCity("Patna");
		student.setCertificate(certificate);

		// Set inverse relationship
		certificate.setStudent(student);

		// Save student (cascade will handle certificate)
		studentService.saveStudent(student);

		// ✅ Fetch the saved student using service
		

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Student ID to fetch: ");
		long searchId = sc.nextLong();
		
		sc.close();

		Student fetched = studentService.getById(searchId);

		if (fetched != null) {
			System.out.println("Fetched Student: " + fetched.getName() + " - " + fetched.getCity());
			if (fetched.getCertificate() != null) {
				System.out.println("Certificate: " + fetched.getCertificate().getTitle());
			}
		} else {
			System.out.println("❌ No student found with ID " + student.getId());
		}

		// Close factory
		factory.close();
	}
}
