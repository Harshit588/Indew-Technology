package in.indew.harshit.bean;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "3Experience")
public class Experience {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Pid;
	private String[] company;
	private String[] years;
	private String[] works;
	private String[] offAddress;
	private String[] role;

	public String[] getCompany() {
		return company;
	}

	public void setCompany(String[] company) {
		this.company = company;
	}

	public String[] getYears() {
		return years;
	}

	public void setYears(String[] strings) {
		this.years = strings;
	}

	public String[] getWorks() {
		return works;
	}

	public void setWorks(String[] works) {
		this.works = works;
	}

	public String[] getOffAddress() {
		return offAddress;
	}

	public void setOffAddress(String[] offAddress) {
		this.offAddress = offAddress;
	}

	public String[] getRole() {
		return role;
	}

	public void setRole(String[] role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Experience [company=" + Arrays.toString(company) + ", years=" + Arrays.toString(years) + ", works="
				+ Arrays.toString(works) + ", offAddress=" + Arrays.toString(offAddress) + ", role="
				+ Arrays.toString(role) + "]";
	}

	public Integer getPid() {
		return Pid;
	}

	public void setPid(Integer pid) {
		Pid = pid;
	}

}