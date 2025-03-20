package in.indew.harshit.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import in.indew.harshit.bean.CertificateExtraActivity;
import in.indew.harshit.bean.Education;
import in.indew.harshit.bean.Experience;
import in.indew.harshit.bean.Project;
import in.indew.harshit.bean.personalDetails;

public class ResumeGenerator {

	public static String writeDataInPdf(String fileName, personalDetails personal, Education education,
			Experience experience, Project project, CertificateExtraActivity activity) {

		String folderPath = "D:/Eclipse/eclipse_Codes/Indew Technology/resume Data/";
		String filePath = folderPath + fileName;

		try {
			File directory = new File(folderPath);
			if (!directory.exists()) {
				directory.mkdirs(); // Create directory if it does not exist
			}

			Document document = new Document(PageSize.A4, 40, 40, 40, 40);
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();

			// Define Fonts
			Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK);
			Font subHeadingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
			Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

			// Title
			Paragraph title = new Paragraph("RESUME", headingFont);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			document.add(new Paragraph("\n"));

			// Personal Details
			PdfPTable personalTable = new PdfPTable(2);
			personalTable.setWidthPercentage(100);
			addTableRow(personalTable, "Name", personal.getName(), subHeadingFont, normalFont);
			addTableRow(personalTable, "Email", personal.getEmail(), subHeadingFont, normalFont);
			addTableRow(personalTable, "Phone", personal.getPhoneNo(), subHeadingFont, normalFont);
			addTableRow(personalTable, "Address", personal.getAddress(), subHeadingFont, normalFont);
			document.add(personalTable);
			document.add(new Paragraph("\n"));

			// Education Section
			if (!isArrayEmpty(education.getDegrees())) {
				document.add(new Paragraph("Education", subHeadingFont));
				PdfPTable eduTable = new PdfPTable(3);
				eduTable.setWidthPercentage(100);
				addTableHeader(eduTable, "Degree", "Institution", "CGPA", subHeadingFont);
				addTableRows(eduTable, education.getDegrees(), education.getColleges(), education.getCgpa(),
						normalFont);
				document.add(eduTable);
				document.add(new Paragraph("\n"));
			}

			// Work Experience
			if (!isArrayEmpty(experience.getCompany())) {
				document.add(new Paragraph("Work Experience", subHeadingFont));
				PdfPTable expTable = new PdfPTable(2);
				expTable.setWidthPercentage(100);
				addTableHeader(expTable, "Company", "Role", subHeadingFont);
				addTableRows(expTable, experience.getCompany(), experience.getRole(), normalFont);
				document.add(expTable);
				document.add(new Paragraph("\n"));
			}

			// Projects
			if (!isArrayEmpty(project.getProject_name())) {
				document.add(new Paragraph("Projects", subHeadingFont));
				PdfPTable projectTable = new PdfPTable(2);
				projectTable.setWidthPercentage(100);
				addTableHeader(projectTable, "Project Name", "Technology Used", subHeadingFont);
				addTableRows(projectTable, project.getProject_name(), project.getTechnologyUsed(), normalFont);
				document.add(projectTable);
				document.add(new Paragraph("\n"));
			}

			// Skills (Extracted from Project Technology Used)
			String[] extractedSkills = getSkills(project);
			if (extractedSkills.length > 0) {
				document.add(new Paragraph("Skills", subHeadingFont));
				PdfPTable skillsTable = new PdfPTable(1);
				skillsTable.setWidthPercentage(100);

				for (String skill : extractedSkills) {
					PdfPCell cell = new PdfPCell(new Paragraph(skill, normalFont));
					cell.setMinimumHeight(20f);
					skillsTable.addCell(cell);
				}
				document.add(skillsTable);
				document.add(new Paragraph("\n"));
			}

			// Certifications & Extra Activities
			if (!isArrayEmpty(activity.getCertificate())) {
				document.add(new Paragraph("Certifications", subHeadingFont));
				PdfPTable certTable = new PdfPTable(1);
				certTable.setWidthPercentage(100);
				for (String cert : activity.getCertificate()) {
					certTable.addCell(new PdfPCell(new Paragraph(cert, normalFont)));
				}
				document.add(certTable);
			}

			document.close();
		} catch (Exception e) {
			System.err.println("Error generating PDF: " + e.getMessage());
		}

		return filePath;
	}

	// Method to extract unique skills from Project Technologies
	public static String[] getSkills(Project project) {
		Set<String> skillsSet = new HashSet<>();

		// Extract skills from 'Technology Used' in Projects
		if (project != null && project.getTechnologyUsed() != null) {
			for (String tech : project.getTechnologyUsed()) {
				String[] skills = tech.split(","); // Split by commas
				for (String skill : skills) {
					skillsSet.add(skill.trim()); // Trim spaces & add to Set
				}
			}
		}

		return skillsSet.toArray(new String[0]); // Convert Set to Array
	}

	// Utility Methods (Add Table Rows & Headers)
	private static void addTableRow(PdfPTable table, String column1, String column2, Font font1, Font font2) {
		table.addCell(new PdfPCell(new Paragraph(column1, font1)));
		table.addCell(new PdfPCell(new Paragraph(column2, font2)));
	}

	private static void addTableHeader(PdfPTable table, String col1, String col2, Font font) {
		table.addCell(new PdfPCell(new Paragraph(col1, font)));
		table.addCell(new PdfPCell(new Paragraph(col2, font)));
	}

	private static void addTableHeader(PdfPTable table, String col1, String col2, String col3, Font font) {
		table.addCell(new PdfPCell(new Paragraph(col1, font)));
		table.addCell(new PdfPCell(new Paragraph(col2, font)));
		table.addCell(new PdfPCell(new Paragraph(col3, font)));
	}

	private static void addTableRows(PdfPTable table, String[] col1, String[] col2, Font font) {
		for (int i = 0; i < col1.length; i++) {
			table.addCell(new PdfPCell(new Paragraph(col1[i], font)));
			table.addCell(new PdfPCell(new Paragraph(col2[i], font)));
		}
	}

	private static void addTableRows(PdfPTable table, String[] col1, String[] col2, String[] col3, Font font) {
		for (int i = 0; i < col1.length; i++) {
			table.addCell(new PdfPCell(new Paragraph(col1[i], font)));
			table.addCell(new PdfPCell(new Paragraph(col2[i], font)));
			table.addCell(new PdfPCell(new Paragraph(col3[i], font)));
		}
	}

	private static boolean isArrayEmpty(String[] array) {
		return array == null || array.length == 0 || (array.length == 1 && array[0].trim().isEmpty());
	}
}
