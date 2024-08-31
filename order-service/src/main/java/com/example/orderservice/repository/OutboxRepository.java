package com.example.orderservice.repository;

import com.example.orderservice.entity.Outbox;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxRepository extends CrudRepository<Outbox, UUID> {

}
