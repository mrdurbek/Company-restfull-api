package uz.company.companyapp.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.company.companyapp.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

}
