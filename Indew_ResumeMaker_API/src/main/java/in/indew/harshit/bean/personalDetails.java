package in.indew.harshit.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "1PersonalDetails")
public class personalDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Pid;
	private String name;
	private String email;
	private String phoneNo;
	private String address;

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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPid() {
		return Pid;
	}

	public void setPid(Integer pid) {
		Pid = pid;
	}

	@Override
	public String toString() {
		return "personalDetails [Pid=" + Pid + ", name=" + name + ", email=" + email + ", phoneNo=" + phoneNo
				+ ", address=" + address + "]";
	}

}
