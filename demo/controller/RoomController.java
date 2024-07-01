package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.RoomDTO;
import com.example.demo.entity.Room;
import com.example.demo.entity.Owner;
import com.example.demo.entity.Customer;
import com.example.demo.service.RoomService;
import com.example.demo.service.OwnerService;
import com.example.demo.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public RoomDTO createRoom(@Valid @RequestBody RoomDTO roomDTO) {
        Room room = new Room();
        // Map DTO to entity
        room.setType(roomDTO.getType());
        room.setStatus(roomDTO.getStatus());

        // Set owner
        Owner owner = ownerService.getOwnerById(roomDTO.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        room.setOwner(owner);

        // Set customers
        Set<Customer> customers = roomDTO.getCustomerIds().stream()
                .map(customerId -> customerService.getCustomerById(customerId)
                        .orElseThrow(() -> new RuntimeException("Customer not found")))
                .collect(Collectors.toSet());
        room.setCustomers(customers);

        Room savedRoom = roomService.saveRoom(room);

        // Map entity to DTO
        roomDTO.setId(savedRoom.getId());
        return roomDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
        Optional<Room> roomOpt = roomService.getRoomById(id);
        if (roomOpt.isPresent()) {
            Room room = roomOpt.get();
            RoomDTO roomDTO = new RoomDTO();
            // Map entity to DTO
            roomDTO.setId(room.getId());
            roomDTO.setType(room.getType());
            roomDTO.setStatus(room.getStatus());
            roomDTO.setOwnerId(room.getOwner().getId());
            roomDTO.setCustomerIds(room.getCustomers().stream().map(Customer::getId).collect(Collectors.toSet()));
            return ResponseEntity.ok(roomDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRooms().stream().map(room -> {
            RoomDTO roomDTO = new RoomDTO();
            // Map entity to DTO
            roomDTO.setId(room.getId());
            roomDTO.setType(room.getType());
            roomDTO.setStatus(room.getStatus());
            roomDTO.setOwnerId(room.getOwner().getId());
            roomDTO.setCustomerIds(room.getCustomers().stream().map(Customer::getId).collect(Collectors.toSet()));
            return roomDTO;
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }

    @PutMapping("/{id}")
    public RoomDTO updateRoom(@PathVariable Long id, @Valid @RequestBody RoomDTO roomDTO) {
        return roomService.getRoomById(id).map(room -> {
            // Map DTO to entity
            room.setType(roomDTO.getType());
            room.setStatus(roomDTO.getStatus());

            // Set owner
            Owner owner = ownerService.getOwnerById(roomDTO.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Owner not found"));
            room.setOwner(owner);

            // Set customers
            Set<Customer> customers = roomDTO.getCustomerIds().stream()
                    .map(customerId -> customerService.getCustomerById(customerId)
                            .orElseThrow(() -> new RuntimeException("Customer not found")))
                    .collect(Collectors.toSet());
            room.setCustomers(customers);

            Room updatedRoom = roomService.saveRoom(room);

            // Map entity to DTO
            roomDTO.setId(updatedRoom.getId());
            return roomDTO;
        }).orElseGet(() -> {
            Room room = new Room();
            // Map DTO to entity
            room.setId(id);
            room.setType(roomDTO.getType());
            room.setStatus(roomDTO.getStatus());

            // Set owner
            Owner owner = ownerService.getOwnerById(roomDTO.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Owner not found"));
            room.setOwner(owner);

            // Set customers
            Set<Customer> customers = roomDTO.getCustomerIds().stream()
                    .map(customerId -> customerService.getCustomerById(customerId)
                            .orElseThrow(() -> new RuntimeException("Customer not found")))
                    .collect(Collectors.toSet());
            room.setCustomers(customers);

            Room savedRoom = roomService.saveRoom(room);

            // Map entity to DTO
            roomDTO.setId(savedRoom.getId());
            return roomDTO;
        });
    }
}
