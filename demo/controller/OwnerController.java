package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.OwnerDTO;
import com.example.demo.entity.Owner;
import com.example.demo.service.OwnerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @PostMapping
    public OwnerDTO createOwner(@Valid @RequestBody OwnerDTO ownerDTO) {
        Owner owner = new Owner();
        // Map DTO to entity
        owner.setUsername(ownerDTO.getUsername());
        owner.setPassword(ownerDTO.getPassword());
        owner.setFirstname(ownerDTO.getFirstname());
        owner.setLastname(ownerDTO.getLastname());
        owner.setEmail(ownerDTO.getEmail());
        owner.setMobile(ownerDTO.getMobile());

        Owner savedOwner = ownerService.saveOwner(owner);

        // Map entity to DTO
        ownerDTO.setId(savedOwner.getId());
        return ownerDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> getOwnerById(@PathVariable Long id) {
        Optional<Owner> ownerOpt = ownerService.getOwnerById(id);
        if (ownerOpt.isPresent()) {
            Owner owner = ownerOpt.get();
            OwnerDTO ownerDTO = new OwnerDTO();
            // Map entity to DTO
            ownerDTO.setId(owner.getId());
            ownerDTO.setUsername(owner.getUsername());
            ownerDTO.setPassword(owner.getPassword());
            ownerDTO.setFirstname(owner.getFirstname());
            ownerDTO.setLastname(owner.getLastname());
            ownerDTO.setEmail(owner.getEmail());
            ownerDTO.setMobile(owner.getMobile());
            return ResponseEntity.ok(ownerDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<OwnerDTO> getAllOwners() {
        return ownerService.getAllOwners().stream().map(owner -> {
            OwnerDTO ownerDTO = new OwnerDTO();
            // Map entity to DTO
            ownerDTO.setId(owner.getId());
            ownerDTO.setUsername(owner.getUsername());
            ownerDTO.setPassword(owner.getPassword());
            ownerDTO.setFirstname(owner.getFirstname());
            ownerDTO.setLastname(owner.getLastname());
            ownerDTO.setEmail(owner.getEmail());
            ownerDTO.setMobile(owner.getMobile());
            return ownerDTO;
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
    }

    @PutMapping("/{id}")
    public OwnerDTO updateOwner(@PathVariable Long id, @Valid @RequestBody OwnerDTO ownerDTO) {
        return ownerService.getOwnerById(id).map(owner -> {
            // Map DTO to entity
            owner.setUsername(ownerDTO.getUsername());
            owner.setPassword(ownerDTO.getPassword());
            owner.setFirstname(ownerDTO.getFirstname());
            owner.setLastname(ownerDTO.getLastname());
            owner.setEmail(ownerDTO.getEmail());
            owner.setMobile(ownerDTO.getMobile());

            Owner updatedOwner = ownerService.saveOwner(owner);

            // Map entity to DTO
            ownerDTO.setId(updatedOwner.getId());
            return ownerDTO;
        }).orElseGet(() -> {
            Owner owner = new Owner();
            // Map DTO to entity
            owner.setId(id);
            owner.setUsername(ownerDTO.getUsername());
            owner.setPassword(ownerDTO.getPassword());
            owner.setFirstname(ownerDTO.getFirstname());
            owner.setLastname(ownerDTO.getLastname());
            owner.setEmail(ownerDTO.getEmail());
            owner.setMobile(ownerDTO.getMobile());

            Owner savedOwner = ownerService.saveOwner(owner);

            // Map entity to DTO
            ownerDTO.setId(savedOwner.getId());
            return ownerDTO;	
        });
    }
}
