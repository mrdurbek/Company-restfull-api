package uz.company.companyapp.model;

import javax.validation.constraints.NotNull;

public class AdressDto {
	
	@NotNull
	private String street;
	@NotNull
	private Integer homeNumber;
	
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
