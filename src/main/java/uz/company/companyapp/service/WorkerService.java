package uz.company.companyapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.company.companyapp.entity.Adress;
import uz.company.companyapp.entity.Department;
import uz.company.companyapp.entity.Worker;
import uz.company.companyapp.model.ApiResponse;
import uz.company.companyapp.model.WorkerDto;
import uz.company.companyapp.repasitory.AdressRepository;
import uz.company.companyapp.repasitory.DepartmentRepository;
import uz.company.companyapp.repasitory.WorkerRepository;

@Service
public class WorkerService {
	
	@Autowired
	WorkerRepository workerRepository;
	@Autowired
	AdressRepository adressRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	
	public List<Worker> getWorker(){
		return workerRepository.findAll();
	}
	
	public ApiResponse addWorker(WorkerDto workerDto) {
		
		if(workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber())) {
			return new ApiResponse("This phone number is already registered", false);
		}
		Worker worker = new Worker();
		worker.setName(workerDto.getName());
		worker.setPhoneNumber(workerDto.getPhoneNumber());
		
		Adress adress = new Adress();
		adress.setStreet(workerDto.getStreet());
		adress.setHomeNumber(workerDto.getHomeNumber());
		Adress savedAdress = adressRepository.save(adress);
		worker.setAdress(savedAdress);
		
		Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
		if(optionalDepartment.isEmpty()) {
			return new ApiResponse("Department is not found" , false);
		}
		Department department = optionalDepartment.get();
		worker.setDepartment(department);
		workerRepository.save(worker);
		return new ApiResponse("Succesfully registered", true);
	}
	
	public ApiResponse deleteDepartment(Integer id) {
		try {
			workerRepository.deleteById(id);
		} catch (Exception e) {
			return new ApiResponse("Not Found" , false);
		}
		return new ApiResponse("Succesfully deleted", true);
	}
	
	public ApiResponse editWorker(Integer id , WorkerDto workerDto) {
		Optional<Worker> optionalWorker = workerRepository.findById(id);
		if(optionalWorker.isEmpty()) {
			return new ApiResponse("Worker is not found" , false);
		}
		Worker updatedWorker = optionalWorker.get();
		updatedWorker.setName(workerDto.getName());
		updatedWorker.setPhoneNumber(workerDto.getPhoneNumber());
		
		Adress adress = updatedWorker.getAdress();
		adress.setStreet(workerDto.getStreet());
		adress.setHomeNumber(workerDto.getHomeNumber());
		adressRepository.save(adress);
		
		Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
		if(optionalDepartment.isPresent()) {
			Department department = optionalDepartment.get();
			updatedWorker.setDepartment(department);
		}
		
		workerRepository.save(updatedWorker);
		return new ApiResponse("Succesfully updated" , true);
	}
}
