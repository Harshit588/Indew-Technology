package in.indew.harshit.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.indew.harshit.bean.User;
import in.indew.harshit.util.HibernateUtil;

public class UserDao {

	// Method to register a new user
	public boolean registerUser(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	// Method to validate user login
	public boolean validateUser(String email, String password) {
		Transaction transaction = null;
		User user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			Query<User> query = session.createQuery("FROM User WHERE email = :email AND password = :password",
					User.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			user = query.uniqueResult();

			transaction.commit();

			if (user != null) {
				System.out.println("User found: " + user.getEmail());
				return true;
			} else {
				System.out.println("User not found for email: " + email);
				return false;
			}
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	// Method to fetch user by email
	public User getUserByEmail(String email) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM User WHERE email = :email", User.class).setParameter("email", email)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
