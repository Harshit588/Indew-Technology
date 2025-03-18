package in.indew.harshit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/downloadResume")
public class DownloadResumeServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("file"); // Get file name from request
		String folderPath = "D:/Eclipse/eclipse_Codes/Indew Technology/resume Data/";
		String filePath = folderPath + fileName;

		File file = new File(filePath);
		if (!file.exists()) {
			response.getWriter().write("File not found.");
			return;
		}

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentLength((int) file.length());

		FileInputStream fis = new FileInputStream(file);
		ServletOutputStream os = response.getOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead;
		while ((bytesRead = fis.read(buffer)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		fis.close();
		os.flush();
		os.close();
	}
}
