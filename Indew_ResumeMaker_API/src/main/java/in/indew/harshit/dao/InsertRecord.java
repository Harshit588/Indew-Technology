package in.indew.harshit.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.indew.harshit.bean.CertificateExtraActivity;
import in.indew.harshit.bean.Education;
import in.indew.harshit.bean.Experience;
import in.indew.harshit.bean.Project;
import in.indew.harshit.bean.personalDetails;
import in.indew.harshit.util.HibernateUtil;

public class InsertRecord {

    public static String insertRecord() {
        String status = "";
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSession()) { // Use try-with-resources for session
            if (session == null) {
                return "Failed to get Hibernate session!";
            }

            transaction = session.beginTransaction();

            // Creating objects with values
            personalDetails personalDetails = new personalDetails();
            Education education = new Education();
            Experience experience = new Experience();
            Project project = new Project();
            CertificateExtraActivity certificate = new CertificateExtraActivity();

            // Saving objects
            Integer id = (Integer) session.save(personalDetails);
            session.save(education);
            session.save(experience);
            session.save(project);
            session.save(certificate);

            // Commit transaction
            transaction.commit();
            status = "Successfully Inserted data at Id :: " + id;

        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback(); // Rollback on error
            }
            he.printStackTrace();
            status = "Error occurred during insertion.";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            status = "Unexpected error occurred.";
        }

        return status;
    }
}
