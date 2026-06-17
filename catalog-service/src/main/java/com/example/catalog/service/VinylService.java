package com.example.catalog.service;

import com.example.catalog.entity.Vinyl;
import com.example.catalog.repository.VinylRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VinylService {

    private final VinylRepository vinylRepository;

    public List<Vinyl> getAll() {
        return vinylRepository.findAll();
    }

    public Vinyl getById(Long id) {
        return vinylRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Vinyl not found"));
    }

    public Vinyl save(Vinyl vinyl) {
        return vinylRepository.save(vinyl);
    }

    public Vinyl update(Long id, Vinyl vinyl) {

        Vinyl existing = getById(id);

        existing.setArtist(vinyl.getArtist());
        existing.setTitle(vinyl.getTitle());
        existing.setGenre(vinyl.getGenre());
        existing.setReleaseYear(vinyl.getReleaseYear());
        existing.setPrice(vinyl.getPrice());
        existing.setStock(vinyl.getStock());

        return vinylRepository.save(existing);
    }

    public void delete(Long id) {
        vinylRepository.deleteById(id);
    }
}
