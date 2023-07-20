package ch.so.agi.vsa.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.so.agi.vsa.data.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
