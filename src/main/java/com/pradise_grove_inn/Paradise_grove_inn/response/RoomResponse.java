package com.pradise_grove_inn.Paradise_grove_inn.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import org.apache.commons.codec.binary.Base64;

import java.util.List;
@Data
@NoArgsConstructor
public class RoomResponse {
    private long id;
    private String roomType;
    private BigDecimal roomPrice;
    private boolean isBooked;
    private String photo;
    private List<BookingResponse> bookings;

    public RoomResponse(long id, String roomType, BigDecimal roomPrice, boolean isBooked, byte[] photoBytes) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.isBooked = isBooked;
        this.photo = photoBytes!=null? Base64.encodeBase64String(photoBytes):null;
        //this.bookings = bookings;
    }

    public RoomResponse(long id, String roomType, BigDecimal roomPrice){
        this.id=id;
        this.roomType=roomType;
        this.roomPrice=roomPrice;



    }
}
