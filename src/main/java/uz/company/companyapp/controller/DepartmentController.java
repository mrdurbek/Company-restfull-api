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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import uz.company.companyapp.entity.Department;
import uz.company.companyapp.model.ApiResponse;
import uz.company.companyapp.model.DepartmentDto;
import uz.company.companyapp.service.DepartmentService;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	@GetMapping
	public ResponseEntity<List<Department>> getDepartment(){
		return ResponseEntity.ok(departmentService.getDepartment());
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> addDepartment(@Valid @RequestBody DepartmentDto departmentDto){
		ApiResponse apiResponse = departmentService.addDepartment(departmentDto);
		if(apiResponse.isResp()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
		}
		return ResponseEntity.ok(apiResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable Integer id){
		ApiResponse apiResponse =  departmentService.deleteDepartment(id);
		if(apiResponse.isResp()) {
			return ResponseEntity.ok(apiResponse);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
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
