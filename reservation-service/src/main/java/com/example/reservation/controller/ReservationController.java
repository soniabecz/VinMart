package com.example.reservation.controller;

import com.example.reservation.dto.ReservationRequest;
import com.example.reservation.entity.Reservation;
import com.example.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @PostMapping
    public Reservation create(
            @RequestBody ReservationRequest request) {

        return service.createReservation(
                request.getUserId(),
                request.getVinylId()
        );
    }

    @GetMapping
    public List<Reservation> getAll() {
        return service.getAll();
    }

    @PostMapping("/payments/{reservationId}")
    public Reservation pay(
            @PathVariable Long reservationId) {

        return service.pay(reservationId);
    }
}
