package uz.company.companyapp.model;

public class ApiResponse {
	private String message;
	private boolean resp;
	
	public ApiResponse() {
	}	
	
	public ApiResponse(String message, boolean resp) {
		this.message = message;
		this.resp = resp;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isResp() {
		return resp;
	}
	public void setResp(boolean resp) {
		this.resp = resp;
	}
	
	
}
