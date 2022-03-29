package uz.company.companyapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.company.companyapp.entity.Adress;
import uz.company.companyapp.entity.Company;
import uz.company.companyapp.model.ApiResponse;
import uz.company.companyapp.model.CompanyDto;
import uz.company.companyapp.repasitory.AdressRepository;
import uz.company.companyapp.repasitory.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	AdressRepository adressRepository;
	
	public List<Company> getCompany() {
		return companyRepository.findAll();
	}
	
	public ApiResponse addCompany(CompanyDto companyDto) {
		if(adressRepository.existsByStreetAndHomeNumber(companyDto.getStreet() , companyDto.getHomeNumber())) {
			return new ApiResponse("This adress is already existed" , false);
		}
		Company company = new Company();
		company.setCompName(companyDto.getCompName());
		company.setDirectorName(companyDto.getDirectorName());
		
		Adress adress = new Adress();
		adress.setStreet(companyDto.getStreet());
		adress.setHomeNumber(companyDto.getHomeNumber());
		Adress savedAdress = adressRepository.save(adress);
		company.setAdress(savedAdress);
		companyRepository.save(company);
		return new ApiResponse("Succesfully added", true);
	}
	
	public ApiResponse deleteCompany(Integer id) {
		try {
			companyRepository.deleteById(id);
		} catch (Exception e) {
			return new ApiResponse("Not Found" , false);
		}
		return new ApiResponse("Succesfully deleted" , true);
	}
	
	public ApiResponse editCompany(Integer id , CompanyDto companyDto) {
		Optional<Company> optionalCompany = companyRepository.findById(id);
		if(optionalCompany.isEmpty()) {
			return new ApiResponse("This company is not found" , false);
		}
		
		if(adressRepository.existsByStreetAndHomeNumberAndIdNot(companyDto.getStreet(), companyDto.getHomeNumber(), id)) {
			return new ApiResponse("This adress is already existed" , false);
		}
		
		Company updatedCompany = optionalCompany.get();
		Adress updatedAdress = updatedCompany.getAdress();
		
		updatedCompany.setCompName(companyDto.getCompName());
		updatedCompany.setDirectorName(companyDto.getDirectorName());
		updatedAdress.setStreet(companyDto.getStreet());
		updatedAdress.setHomeNumber(companyDto.getHomeNumber());
		adressRepository.save(updatedAdress);
		updatedCompany.setAdress(updatedAdress);
		companyRepository.save(updatedCompany);
		return new ApiResponse("Succesfully updated", true);
	}
}
