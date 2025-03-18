package in.indew.harshit.bean;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "5CertificateExtraActivity")
public class CertificateExtraActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Pid;
	private String[] certificate;
	private String[] activity;

	public String[] getCertificate() {
		return certificate;
	}

	public void setCertificate(String[] certificate) {
		this.certificate = certificate;
	}

	public String[] getActivity() {
		return activity;
	}

	public void setActivity(String[] activity) {
		this.activity = activity;
	}

	@Override
	public String toString() {
		return "CertificateExtraActivity [certificate=" + Arrays.toString(certificate) + ", activity="
				+ Arrays.toString(activity) + "]";
	}

	public Integer getPid() {
		return Pid;
	}

	public void setPid(Integer pid) {
		Pid = pid;
	}
}