package in.indew.harshit.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;

	public static void main(String[] args) {
		System.out.println(getSessionFactory().getClass().getName());
	}

	static {
		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			System.out.println("✅ Hibernate SessionFactory initialized successfully.");
		} catch (Throwable ex) {
			System.err.println("❌ Hibernate initialization failed: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
