package com.ap.Parcheggio.parcheggio;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ap.Parcheggio.sede.Sede;
import com.ap.Parcheggio.sede.SedeRepository;

@RestController
public class ParcheggioController {
    private final ParcheggioRepository parcheggioRepository;
    private final SedeRepository sedeRepository;

    ParcheggioController (ParcheggioRepository parcheggioRepository, SedeRepository sedeRepository) {
        this.parcheggioRepository = parcheggioRepository;
        this.sedeRepository = sedeRepository;
    }

    @GetMapping("/api/v1/parcheggio")
    Iterable<Parcheggio> getParcheggio(){
        return this.parcheggioRepository.findAll();
    }

    @GetMapping("/api/v1/parcheggio/{parcheggioId}")
    Parcheggio getParcheggio(@PathVariable Long parcheggioId){
        return this.parcheggioRepository.findById(parcheggioId).orElseThrow();
    }

    @PostMapping("/api/v1/parcheggio")
    Parcheggio createParcheggio(@RequestBody Parcheggio newParcheggio) {
        return this.parcheggioRepository.save(newParcheggio);
    }

    @PutMapping("/api/v1/parcheggio/{parcheggioId}")
    Parcheggio updateParcheggio(@PathVariable Long parcheggioId, @RequestBody Parcheggio updatedParcheggio){
        Parcheggio parcheggio = this.parcheggioRepository.findById(parcheggioId).orElseThrow();
        parcheggio.setNome(updatedParcheggio.getNome());
        return this.parcheggioRepository.save(parcheggio);
    }

    @PutMapping("/api/v1/parcheggio/{parcheggioId}/sede/{sedeId}")
    Parcheggio assegnazioneParcheggoASede(@PathVariable Long parcheggioId, @PathVariable Long sedeId){
        Parcheggio parcheggio = this.parcheggioRepository.findById(parcheggioId).orElseThrow();
        Sede sede = this.sedeRepository.findById(sedeId).orElseThrow();
        parcheggio.assegnazione(sede);
        return this.parcheggioRepository.save(parcheggio);
    }

    @DeleteMapping("/api/v1/parcheggio/{parcheggioId}")
    void deleteParcheggio(@PathVariable Long parcheggioId){
        Parcheggio parcheggio = this.parcheggioRepository.findById(parcheggioId).orElseThrow();
        this.parcheggioRepository.delete(parcheggio);
    }
}

