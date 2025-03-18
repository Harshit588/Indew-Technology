package in.indew.harshit.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "4Project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Pid;
	private String[] project_name;
	private String[] technologyUsed;
	private String[] features;

	public String[] getProject_name() {
		return project_name;
	}

	public void setProject_name(String[] project_name) {
		this.project_name = project_name;
	}

	public String[] getTechnologyUsed() {
		return technologyUsed;
	}

	public void setTechnologyUsed(String[] technologyUsed) {
		this.technologyUsed = technologyUsed;
	}

	public String[] getFeatures() {
		return features;
	}

	public void setFeatures(String[] features) {
		this.features = features;
	}

	@Override
	public String toString() {
		return "Project [project_name=" + project_name + ", technologyUsed=" + technologyUsed + ", features=" + features
				+ "]";
	}

	public Integer getPid() {
		return Pid;
	}

	public void setPid(Integer pid) {
		Pid = pid;
	}
}