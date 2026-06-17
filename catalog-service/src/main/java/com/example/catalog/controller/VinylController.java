package com.example.catalog.controller;

import com.example.catalog.entity.Vinyl;
import com.example.catalog.service.VinylService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vinyls")
@RequiredArgsConstructor
public class VinylController {

    private final VinylService vinylService;

    @GetMapping
    public List<Vinyl> getAll() {
        return vinylService.getAll();
    }

    @GetMapping("/{id}")
    public Vinyl getById(@PathVariable Long id) {
        return vinylService.getById(id);
    }

    @PostMapping
    public Vinyl create(@RequestBody Vinyl vinyl) {
        return vinylService.save(vinyl);
    }

    @PutMapping("/{id}")
    public Vinyl update(
            @PathVariable Long id,
            @RequestBody Vinyl vinyl) {

        return vinylService.update(id, vinyl);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vinylService.delete(id);
    }
}
