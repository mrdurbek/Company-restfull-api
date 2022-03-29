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

import uz.company.companyapp.entity.Worker;
import uz.company.companyapp.model.ApiResponse;
import uz.company.companyapp.model.WorkerDto;
import uz.company.companyapp.service.WorkerService;

@RestController
@RequestMapping(value = "/api/worker")
public class WorkerController {
	
	@Autowired
	WorkerService workerService;
	
	@GetMapping
	public ResponseEntity<List<Worker>> getWorker(){
		return ResponseEntity.ok(workerService.getWorker());
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> addWorker(@Valid @RequestBody WorkerDto workerDto){
		ApiResponse apiResponse =  workerService.addWorker(workerDto);
		if(!apiResponse.isResp()) {
			if(apiResponse.getMessage().equals("Department is not found")) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
			}
			return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
		}
		return ResponseEntity.ok(apiResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteWorker(@PathVariable Integer id){
		ApiResponse apiResponse = workerService.deleteDepartment(id);
		if(!apiResponse.isResp()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
		}
		return ResponseEntity.ok(apiResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> editWorker(@PathVariable Integer id , @Valid @RequestBody WorkerDto workerDto){
		ApiResponse apiResponse = workerService.editWorker(id, workerDto);
		if(!apiResponse.isResp()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
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
