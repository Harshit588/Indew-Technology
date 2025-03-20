package in.indew.harshit.bean;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "2Education")
public class Education {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Pid;
	private String[] degrees;
	private String[] colleges;
	private String[] startYears;
	private String[] passoutYears;
	private String[] edu_address;
	private String[] edu_state;
	private String[] edu_country;
	private String[] cgpa;

	public String[] getEdu_address() {
		return edu_address;
	}

	public void setEdu_address(String[] edu_address) {
		this.edu_address = edu_address;
	}

	public String[] getEdu_state() {
		return edu_state;
	}

	public void setEdu_state(String[] edu_state) {
		this.edu_state = edu_state;
	}

	public String[] getEdu_country() {
		return edu_country;
	}

	public void setEdu_country(String[] edu_country) {
		this.edu_country = edu_country;
	}

	public String[] getCgpa() {
		return cgpa;
	}

	public void setCgpa(String[] cgpa) {
		this.cgpa = cgpa;
	}

	public String[] getDegrees() {
		return degrees;
	}

	public void setDegrees(String[] degrees) {
		this.degrees = degrees;
	}

	public String[] getColleges() {
		return colleges;
	}

	public void setColleges(String[] colleges) {
		this.colleges = colleges;
	}

	public String[] getStartYears() {
		return startYears;
	}

	public void setStartYears(String[] startYears) {
		this.startYears = startYears;
	}

	public String[] getPassoutYears() {
		return passoutYears;
	}

	public void setPassoutYears(String[] passoutYears) {
		this.passoutYears = passoutYears;
	}

	@Override
	public String toString() {
		return "Education [degrees=" + Arrays.toString(degrees) + ", colleges=" + Arrays.toString(colleges)
				+ ", startYears=" + Arrays.toString(startYears) + ", passoutYears=" + Arrays.toString(passoutYears)
				+ ", edu_address=" + Arrays.toString(edu_address) + ", edu_state=" + Arrays.toString(edu_state)
				+ ", edu_country=" + Arrays.toString(edu_country) + ", cgpa=" + Arrays.toString(cgpa) + "]";
	}

	public Integer getPid() {
		return Pid;
	}

	public void setPid(Integer pid) {
		Pid = pid;
	}

}