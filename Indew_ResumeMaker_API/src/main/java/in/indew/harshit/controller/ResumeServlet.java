package in.indew.harshit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importing required beans
import in.indew.harshit.bean.CertificateExtraActivity;
import in.indew.harshit.bean.Education;
import in.indew.harshit.bean.Experience;
import in.indew.harshit.bean.Project;
import in.indew.harshit.bean.personalDetails;
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
		String filePath = ResumeGenerator.writeDataInPdf(fileName, personal, education, experience, project, activity);

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

	
	
}