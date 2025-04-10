package com.pradise_grove_inn.Paradise_grove_inn.service;

import com.pradise_grove_inn.Paradise_grove_inn.exception.InternalServerException;
import com.pradise_grove_inn.Paradise_grove_inn.exception.ResourceNotFoundException;
import com.pradise_grove_inn.Paradise_grove_inn.model.Room;
import com.pradise_grove_inn.Paradise_grove_inn.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService {

    private final RoomRepository roomRepository;
    @Override
    public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) throws SQLException, IOException {
        Room room = new Room();
        room.setRoomTypes(roomType);
        room.setRoomPrice(roomPrice);
        if (!file.isEmpty()) {
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            room.setPhoto(photoBlob);
        }
        return roomRepository.save(room);
    }

    @Override
    public List<String> getAllRoomTypes() {

        return roomRepository.findDistinctRoomTypes();
    }

    @Override
    public List<Room> getAllRooms() {

        return roomRepository.findAll();
    }

    @Override
    public byte[] getRoomPhotoByRoomId(long roomId) throws SQLException, ConfigDataResourceNotFoundException {
        Optional<Room> theRoom=roomRepository.findById(roomId);
        if(theRoom.isEmpty()){
            throw new ResourceNotFoundException("sorry,room not found!");
        }
        Blob photoBlob = theRoom.get().getPhoto();
        if(photoBlob != null){
            return photoBlob.getBytes(1, (int) photoBlob.length());
        }
        return null;
    }

    @Override
    public void deleteRoom(Long roomId) {
        Optional<Room> theRoom=roomRepository.findById(roomId);
        if(theRoom.isPresent()){
            roomRepository.deleteById(roomId);
        }
    }

    @Override
    public Room updateRoom(Long roomId, String roomTypes, BigDecimal roomPrice, byte[] photoBytes) {
        Room room =roomRepository.findById(roomId).orElseThrow(()->new ResourceNotFoundException("Room not found"));
        if(roomTypes!=null)room.setRoomTypes(roomTypes);
        if(roomPrice!=null)room.setRoomPrice(roomPrice);
        if(photoBytes!=null&& photoBytes.length>0){
            try{
                  room.setPhoto(new SerialBlob(photoBytes));
            }
            catch(SQLException ex) {
                    throw new InternalServerException("error in updating room");
            }
        }
        
        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> getRoomById(Long roomId) {
        return Optional.of(roomRepository.findById(roomId).get());
    }
}

