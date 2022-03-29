package uz.company.companyapp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.company.companyapp.entity.Company;
import uz.company.companyapp.entity.Department;
import uz.company.companyapp.model.ApiResponse;
import uz.company.companyapp.model.DepartmentDto;
import uz.company.companyapp.repasitory.CompanyRepository;
import uz.company.companyapp.repasitory.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	CompanyRepository companyRepository;
	
	public List<Department> getDepartment(){
		return departmentRepository.findAll();
	}
	
	public ApiResponse addDepartment(DepartmentDto departmentDto) {
		if(departmentRepository.existsByName(departmentDto.getName())) {
			return new ApiResponse("This department is already existed" , false);
		}
		Department department = new Department();
		department.setName(departmentDto.getName());
		List<Company> listCompany = new ArrayList<Company>();
		Iterator<Integer> iterator = departmentDto.getCompanyId().iterator();
		while(iterator.hasNext()) {
			Integer id = iterator.next();
			Optional<Company> optionalCompany = companyRepository.findById(id);
			if(optionalCompany.isEmpty()) {
				return new ApiResponse("Not found company in this id = "+id, false);
			}
			Company company = optionalCompany.get();
			listCompany.add(company);
		}
		department.setCompany(listCompany);
		departmentRepository.save(department);
		return new ApiResponse("Succesfully added", true);
	}
	
	public ApiResponse deleteDepartment(Integer id) {
		try {
			departmentRepository.deleteById(id);
		} catch (Exception e) {
			return new ApiResponse("Not Found" , false);
		}		
		return new ApiResponse("Succesfully deleted" , true);
	}
}
