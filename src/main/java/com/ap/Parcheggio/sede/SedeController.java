package com.ap.Parcheggio.sede;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SedeController {
    private final SedeRepository sedeRepository;

    SedeController (SedeRepository sedeRepository) {
        this.sedeRepository = sedeRepository;
    }

    @GetMapping("/api/v1/sede")
    Iterable<Sede> getSede(){
        return this.sedeRepository.findAll();
    }

    @GetMapping("/api/v1/sede/{sedeId}")
    Sede getSede(@PathVariable Long sedeId){
        return this.sedeRepository.findById(sedeId).orElseThrow();
    }

    @PostMapping("/api/v1/sede")
    Sede createSede(@RequestBody Sede newSede) {
        return this.sedeRepository.save(newSede);
    }

    @PutMapping("/api/v1/sede/{sedeId}")
    Sede updateSede(@PathVariable Long sedeId, @RequestBody Sede updatedSede){
        Sede sede = this.sedeRepository.findById(sedeId).orElseThrow();
        sede.setNome(updatedSede.getNome());
        return this.sedeRepository.save(sede);
    }

    @DeleteMapping("/api/v1/sede/{sedeId}")
    void deleteSede(@PathVariable Long sedeId){
        Sede sede = this.sedeRepository.findById(sedeId).orElseThrow();
        this.sedeRepository.delete(sede);
    }
}
