package uz.company.companyapp.model;

import java.util.List;

import javax.validation.constraints.NotNull;

public class DepartmentDto {
	@NotNull(message = "name can't be empty")
	private String name;
	@NotNull(message = "companyId can't be empty")
	private List<Integer> companyId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getCompanyId() {
		return companyId;
	}
	public void setCompanyId(List<Integer> companyId) {
		this.companyId = companyId;
	}
	
	
}
