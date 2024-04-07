package com.aston.angularlaba.user.repository;

import com.aston.angularlaba.user.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    @NonNull
    List<Client> findAll();
}
