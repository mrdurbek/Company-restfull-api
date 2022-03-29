package uz.company.companyapp.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.company.companyapp.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
	boolean existsByPhoneNumber(String phoneNumber);
}
