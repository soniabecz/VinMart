package com.example.reservation.scheduler;

import com.example.reservation.entity.Reservation;
import com.example.reservation.entity.ReservationStatus;
import com.example.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class ReservationScheduler {

    private final ReservationRepository repository;

    @Scheduled(fixedRate = 10000)
    public void cancelExpiredReservations() {

        List<Reservation> pendingReservations =
                repository.findByStatus(
                        ReservationStatus.PENDING);

        LocalDateTime limit =
                LocalDateTime.now()
                        .minusSeconds(30);

        pendingReservations.stream()
                .filter(r ->
                        r.getCreatedAt()
                                .isBefore(limit))
                .forEach(r -> {

                    r.setStatus(
                            ReservationStatus.CANCELLED);

                    repository.save(r);

                    log.info(
                            "Reservation {} cancelled",
                            r.getId()
                    );
                });
    }
}
