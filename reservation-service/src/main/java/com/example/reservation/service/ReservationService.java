package com.example.reservation.service;

import com.example.reservation.aspect.Auditable;
import com.example.reservation.dto.NotificationRequest;
import com.example.reservation.dto.VinylDto;
import com.example.reservation.entity.Reservation;
import com.example.reservation.entity.ReservationStatus;
import com.example.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository repository;
    private final RestTemplate restTemplate;

    @Auditable("Reservation created")
    public Reservation createReservation(
            Long userId,
            Long vinylId) {
        try {

            restTemplate.getForObject(
                    "http://catalog-service:8080/vinyls/" + vinylId,
                    VinylDto.class
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Vinyl not found"
            );
        }

        Reservation reservation = new Reservation();

        reservation.setUserId(userId);
        reservation.setVinylId(vinylId);
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setCreatedAt(LocalDateTime.now());

        Reservation saved = repository.save(reservation);

        NotificationRequest notification =
                new NotificationRequest(
                        "user@test.pl",
                        "Reservation created"
                );

        restTemplate.postForObject(
                "http://notification-service:8082/notifications",
                notification,
                Void.class
        );

        return saved;
    }

    public List<Reservation> getAll() {
        return repository.findAll();
    }

    @Auditable("Payment confirmed")
    public Reservation pay(Long reservationId) {

        Reservation reservation = repository.findById(reservationId)
                .orElseThrow(() ->
                        new RuntimeException("Reservation not found"));

        if (reservation.getStatus() != ReservationStatus.PENDING) {
            throw new RuntimeException(
                    "Only pending reservations can be paid");
        }

        reservation.setStatus(ReservationStatus.PAID);

        return repository.save(reservation);
    }
}