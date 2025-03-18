package in.indew.harshit.controller;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import in.indew.harshit.bean.*; // Importing required beans
import in.indew.harshit.dao.InsertRecord;

@WebServlet("/resume")
public class ResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Collect Personal Details from the Form
		personalDetails personal = new personalDetails();
		personal.setName(request.getParameter("name"));
		personal.setEmail(request.getParameter("email"));
		personal.setPhoneNo(request.getParameter("phone"));
		personal.setAddress(request.getParameter("address"));

		// Collect Education Details
		Education education = new Education();
		education.setDegrees(request.getParameterValues("degree"));
		education.setColleges(request.getParameterValues("college"));
		education.setStartYears(request.getParameterValues("start_year"));
		education.setPassoutYears(request.getParameterValues("passout_year"));
		education.setEdu_address(request.getParameterValues("edu_address"));
		education.setEdu_state(request.getParameterValues("edu_state"));
		education.setEdu_country(request.getParameterValues("edu_country"));
		education.setCgpa(request.getParameterValues("cgpa"));

		// Collect Experience Details
		Experience experience = new Experience();
		experience.setCompany(request.getParameterValues("company"));
		experience.setYears(request.getParameterValues("years"));
		experience.setWorks(request.getParameterValues("work"));
		experience.setOffAddress(request.getParameterValues("exp_address"));
		experience.setRole(request.getParameterValues("role"));

		// Collect Projects Details
		Project project = new Project();
		project.setProject_name(request.getParameterValues("project_name"));
		project.setTechnologyUsed(request.getParameterValues("technology_used"));
		project.setFeatures(request.getParameterValues("features"));

		// Collect Certifications & Extra Activities
		CertificateExtraActivity activity = new CertificateExtraActivity();
		activity.setCertificate(request.getParameterValues("certifications"));
		activity.setActivity(request.getParameterValues("extra_activities"));

		// Call the method to create a PDF resume
		String fileName = personal.getName().replaceAll("\\s+", "_") + "_Resume.pdf";
		String filePath = writeDataInPdf(fileName, personal, education, experience, project, activity);

		// Insert record into the database
		String recordStatus = InsertRecord.insertRecord();

		// Set response type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Display success message with download link
		if (recordStatus != null && recordStatus.toLowerCase().contains("success")) {
			out.print("<h1 style='text-align:center; color: green;'>SUCCESS</h1>");
			out.print("<h6 style='text-align:center; color: green;'>Saved at :: " + filePath + "</h6>");
			out.print("<p style='text-align:center;'><a href='downloadResume?file=" + fileName
					+ "'>Download Your Resume</a></p>");
		} else {
			out.print("<h1 style='text-align:center; color: red;'>FAILURE</h1>");
		}
	}

	// Method to Write Resume Data into a PDF File
	private String writeDataInPdf(String fileName, personalDetails personal, Education education, Experience experience,
			Project project, CertificateExtraActivity activity) {
		String folderPath = "D:/Eclipse/eclipse_Codes/Indew Technology/resume Data/";
		String filePath = folderPath + fileName;

		try {
			File directory = new File(folderPath);
			if (!directory.exists()) {
				directory.mkdirs(); // Create the directory if it does not exist
			}

			File file = new File(filePath);
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			document.add(new Paragraph("============ RESUME ============\n\n"));

			// Add Personal Details
			if (!isEmpty(personal.getName())) {
				document.add(new Paragraph("🔹 Personal Details:"));
				document.add(new Paragraph("Name: " + personal.getName()));
				document.add(new Paragraph("Email: " + personal.getEmail()));
				document.add(new Paragraph("Phone: " + personal.getPhoneNo()));
				document.add(new Paragraph("Address: " + personal.getAddress() + "\n"));
			}

			// Add Education Details
			if (!isArrayEmpty(education.getDegrees())) {
				document.add(new Paragraph("🔹 Education:"));
				addArrayData(document, "Degrees", education.getDegrees());
				addArrayData(document, "Colleges", education.getColleges());
				addArrayData(document, "CGPA", education.getCgpa());
			}

			// Add Experience
			if (!isArrayEmpty(experience.getCompany())) {
				document.add(new Paragraph("🔹 Experience:"));
				addArrayData(document, "Company", experience.getCompany());
				addArrayData(document, "Role", experience.getRole());
			}

			// Add Projects
			if (!isArrayEmpty(project.getProject_name())) {
				document.add(new Paragraph("🔹 Projects:"));
				addArrayData(document, "Project Name", project.getProject_name());
				addArrayData(document, "Technology Used", project.getTechnologyUsed());
			}

			// Add Certifications
			if (!isArrayEmpty(activity.getCertificate())) {
				document.add(new Paragraph("🔹 Certifications:"));
				addArrayData(document, "Certifications", activity.getCertificate());
			}

			document.close();
		} catch (Exception e) {
			System.err.println("Error generating PDF: " + e.getMessage());
		}

		return filePath;
	}

	private boolean isEmpty(String str) {
		return (str == null || str.trim().isEmpty());
	}

	private boolean isArrayEmpty(String[] arr) {
		return (arr == null || arr.length == 0 || (arr.length == 1 && isEmpty(arr[0])));
	}

	private void addArrayData(Document document, String label, String[] data) throws DocumentException {
		if (data != null) {
			document.add(new Paragraph(label + ": "));
			for (String item : data) {
				document.add(new Paragraph("- " + item));
			}
		}
	}
}
