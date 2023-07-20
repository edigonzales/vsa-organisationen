package ch.so.agi.vsa.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.so.agi.vsa.data.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
