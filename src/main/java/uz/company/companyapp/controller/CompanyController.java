package uz.company.companyapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import uz.company.companyapp.entity.Company;
import uz.company.companyapp.model.ApiResponse;
import uz.company.companyapp.model.CompanyDto;
import uz.company.companyapp.service.CompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	@GetMapping
	public ResponseEntity<List<Company>> getCompany(){
		List<Company> listCompany = companyService.getCompany();
		return ResponseEntity.ok(listCompany);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> addCompany(@Valid @RequestBody CompanyDto companyDto) {
		ApiResponse apiResponse = companyService.addCompany(companyDto);
		if(!apiResponse.isResp()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
		}
		return ResponseEntity.ok(apiResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCompany(@PathVariable Integer id){
		ApiResponse apiResponse = companyService.deleteCompany(id);
		if(apiResponse.isResp()) {
			return ResponseEntity.ok(apiResponse);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> editCompany(@PathVariable Integer id , @Valid @RequestBody CompanyDto companyDto){
		ApiResponse apiResponse = companyService.editCompany(id, companyDto);
		if(!apiResponse.isResp()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
		}
		return ResponseEntity.ok(apiResponse);
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
