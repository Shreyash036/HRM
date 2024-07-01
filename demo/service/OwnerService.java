package com.example.demo.service;


import com.example.demo.entity.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    Owner saveOwner(Owner owner);
    Optional<Owner> getOwnerById(Long id);
    List<Owner> getAllOwners();
    void deleteOwner(Long id);
}
