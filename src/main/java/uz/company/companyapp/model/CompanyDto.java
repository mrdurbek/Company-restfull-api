package uz.company.companyapp.model;

import javax.validation.constraints.NotNull;

public class CompanyDto {
	
	@NotNull(message = "compName can't be empty")
	private String compName;
	@NotNull(message = "directorName can't be empty")
	private String directorName;
	@NotNull(message = "street can't be empty")
	private String street;
	@NotNull(message = "homeNumber can't be empty")
	private Integer homeNumber;
	
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getHomeNumber() {
		return homeNumber;
	}
	public void setHomeNumber(Integer homeNumber) {
		this.homeNumber = homeNumber;
	}
	
	
}
