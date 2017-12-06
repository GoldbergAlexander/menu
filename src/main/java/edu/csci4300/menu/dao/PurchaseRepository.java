package edu.csci4300.menu.dao;

import edu.csci4300.menu.pojo.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
