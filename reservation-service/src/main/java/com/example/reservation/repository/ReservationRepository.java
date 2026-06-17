package com.example.reservation.repository;

import com.example.reservation.entity.Reservation;
import com.example.reservation.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository
        extends JpaRepository<Reservation, Long> {

    List<Reservation> findByStatus(ReservationStatus status);

}
