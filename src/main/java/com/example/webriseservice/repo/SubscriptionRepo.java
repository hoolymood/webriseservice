package com.example.webriseservice.repo;


import com.example.webriseservice.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, UUID> {

    @Query("""
            SELECT s.name
            FROM Subscription s
            GROUP BY name ORDER BY COUNT (s.name) DESC limit 3
            """)
    List<String> top();

}
