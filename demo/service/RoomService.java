package com.example.demo.service;


import com.example.demo.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Room saveRoom(Room room);
    Optional<Room> getRoomById(Long id);
    List<Room> getAllRooms();
    void deleteRoom(Long id);
}
