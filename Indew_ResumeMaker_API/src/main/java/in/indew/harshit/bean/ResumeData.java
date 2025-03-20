package in.indew.harshit.bean;

import java.util.Arrays;

import javax.persistence.*;

@Entity
@Table(name = "resume_data")
public class ResumeData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String email;
	private String phone;
	private String address;
	private String PdfAddress;

	@Lob
	@Column(name = "pdf_resume", columnDefinition = "LONGBLOB")
	private byte[] pdfResume; // Store PDF as binary data

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte[] getPdfResume() {
		return pdfResume;
	}

	public void setPdfResume(byte[] pdfBytes) {
		this.pdfResume = pdfBytes;
	}

	public String getPdfAddress() {
		return PdfAddress;
	}

	public void setPdfAddress(String pdfAddress) {
		PdfAddress = pdfAddress;
	}

	@Override
	public String toString() {
		return "ResumeData [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address="
				+ address + ", PdfAddress=" + PdfAddress + ", pdfResume=" + Arrays.toString(pdfResume) + "]";
	}

}
