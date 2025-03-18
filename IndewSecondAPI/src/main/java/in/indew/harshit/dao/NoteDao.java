package in.indew.harshit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.indew.harshit.bean.Note;
import in.indew.harshit.util.HibernateUtil;

public class NoteDao {

	// ✅ Save a new note
	public boolean saveNote(Note note) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(note);
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

	// ✅ Fetch all notes for a specific user
	public List<Note> getNotesByUser(String userEmail) {
		List<Note> notes = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Note> query = session.createQuery("FROM Note WHERE userEmail = :userEmail", Note.class);
			query.setParameter("userEmail", userEmail);
			notes = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notes;
	}

	// ✅ Fetch a single note by ID
	public Note getNoteById(int id) {
		Note note = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			note = session.get(Note.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return note;
	}

	// ✅ Update a note by ID
	public boolean updateNote(int id, String title, String content) {
		boolean updated = false;
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Note note = session.get(Note.class, id);

			if (note != null) {
				note.setTitle(title);
				note.setContent(content);
				session.update(note);
				updated = true;
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return updated;
	}

	// ✅ Delete a note by ID
	public boolean deleteNote(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Note note = session.get(Note.class, id);

			if (note != null) {
				session.delete(note);
				transaction.commit();
				return true;
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	// ✅ Fetch notes by user email
	public List<Note> getNotesByEmail(String email) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Note WHERE userEmail = :email", Note.class).setParameter("email", email)
					.list();
		}
	}

	// ✅ Delete note by ID & user email (for security)
	public boolean deleteNoteById(int noteId, String userEmail) {
		boolean isDeleted = false;
		Transaction tx = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			Query<?> query = session.createQuery("DELETE FROM Note WHERE id = :noteId AND userEmail = :userEmail");
			query.setParameter("noteId", noteId);
			query.setParameter("userEmail", userEmail);

			int result = query.executeUpdate();
			tx.commit();
			isDeleted = (result > 0);
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		return isDeleted;
	}
}
