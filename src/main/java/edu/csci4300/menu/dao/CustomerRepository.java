package edu.csci4300.menu.dao;

import edu.csci4300.menu.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
