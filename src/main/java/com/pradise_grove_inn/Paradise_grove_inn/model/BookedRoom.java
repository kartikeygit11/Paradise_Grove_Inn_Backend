package com.pradise_grove_inn.Paradise_grove_inn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "booking_id", nullable = false, unique = true)
    private long bookingId;
    @Column(name = "check_In")
    private LocalDate checkInDate;
    @Column(name = "check_Out")
    private LocalDate checkOutDate;
    @Column(name = "Guest_FullName")
    private String guestFullName;
    @Column(name = "Guest_Email")
    private String guestEmail;
    @Column(name = "Adults")
    private int NumOfAdults;
    @Column(name = "children")
    private int NumOfChildren;
    @Column(name = "total_guest")
    private int totalNumOfGuest;
    @Column(name = "confirmation_code")
    private String bookingConfirmationCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public void calculateTotalNumberOfGuest(){
        this.totalNumOfGuest=this.NumOfAdults+NumOfChildren;
    }

    public void setNumOfChildren(int numOfChildren) {
        NumOfChildren = numOfChildren;
        calculateTotalNumberOfGuest();
    }

    public void setNumOfAdults(int numOfAdults) {
        NumOfAdults = numOfAdults;
        calculateTotalNumberOfGuest();
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }


}
