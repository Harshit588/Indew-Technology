
package in.indew.harshit.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.indew.harshit.bean.CertificateExtraActivity;
import in.indew.harshit.bean.Education;
import in.indew.harshit.bean.Experience;
import in.indew.harshit.bean.Project;
import in.indew.harshit.bean.ResumeData;
import in.indew.harshit.bean.personalDetails;

public class HibernateUtil {

	private static SessionFactory sessionFactory = null;
	private static Session session;

	static {
		sessionFactory = new Configuration().configure()
				.addAnnotatedClass(ResumeData.class)
				.addAnnotatedClass(CertificateExtraActivity.class)
				.addAnnotatedClass(Education.class)
				.addAnnotatedClass(Experience.class)
				.addAnnotatedClass(Project.class)
				.addAnnotatedClass(personalDetails.class)
				.buildSessionFactory();
	}

	// get Session Object
	public static Session getSession() {
		if (sessionFactory != null)
			session = sessionFactory.openSession();
		return session;
	}

	// Close Session Object
	public static void CloseSession() {
		if (session != null) {
			session.close();
		}
	}
}