package com.zad1oro.repository;

import com.zad1oro.entity.Order;
import com.zad1oro.entity.Part;
import com.zad1oro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    long countOrdersByPart(Part part);

    long countOrdersByUser(User user);
    
    long countOrdersByUser_Email(String userEmail);
}
