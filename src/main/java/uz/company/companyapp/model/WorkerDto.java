package uz.company.companyapp.model;

import javax.validation.constraints.NotNull;

public class WorkerDto {
	@NotNull(message = "name can't be empty")
	private String name;
	@NotNull(message = "phoneNumber can't be empty")
	private String phoneNumber;
	@NotNull(message = "street can't be empty")
	private String street;
	@NotNull(message = "homeNumber can't be empty")
	private Integer homeNumber;
	@NotNull(message = "departmentId can't be empty")
	private Integer departmentId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	
}
