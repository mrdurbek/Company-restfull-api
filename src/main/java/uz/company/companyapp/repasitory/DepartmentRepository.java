package uz.company.companyapp.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.company.companyapp.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	boolean existsByName(String name);
	
}
