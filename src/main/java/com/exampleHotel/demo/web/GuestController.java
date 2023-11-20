package com.exampleHotel.demo.web;

import com.exampleHotel.demo.business.ReservationService;
import com.exampleHotel.demo.data.Guest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/")
public class GuestController {
    private final ReservationService reservationService;

    public GuestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/guests")
    public Collection<Guest> getGuests() {
        return this.reservationService.getHotelGuests();

    }
}

