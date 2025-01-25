package com.pradise_grove_inn.Paradise_grove_inn.repository;

import com.pradise_grove_inn.Paradise_grove_inn.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    @Query("SELECT DISTINCT r.roomTypes FROM Room r")
    List<String> findDistinctRoomTypes();
}
