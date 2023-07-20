package ch.so.agi.vsa.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.so.agi.vsa.data.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
