package uz.company.companyapp.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.company.companyapp.entity.Adress;

public interface AdressRepository extends JpaRepository<Adress , Integer> {
	boolean existsByStreetAndHomeNumber(String street , Integer homeNumber);
	
	boolean existsByStreetAndHomeNumberAndIdNot(String street , Integer homeNumber , Integer id);
	
}
