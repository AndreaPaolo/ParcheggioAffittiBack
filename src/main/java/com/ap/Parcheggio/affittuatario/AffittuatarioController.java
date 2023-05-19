package com.ap.Parcheggio.affittuatario;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AffittuatarioController {
    private final AffittuatarioRepository affittuatarioRepository;

    AffittuatarioController (AffittuatarioRepository affittuatarioRepository) {
        this.affittuatarioRepository = affittuatarioRepository;
    }

    @GetMapping("/api/v1/affittuatario")
    Iterable<Affittuatario> getAffittuatario(){
        return this.affittuatarioRepository.findAll();
    }

    @GetMapping("/api/v1/affittuatario/{affittuatarioId}")
    Affittuatario getAffittuatario(@PathVariable Long affittuatarioId){
        return this.affittuatarioRepository.findById(affittuatarioId).orElseThrow();
    }

    @PostMapping("/api/v1/affittuatario")
    Affittuatario createAffittuatario(@RequestBody Affittuatario newAffittuatario) {
        return this.affittuatarioRepository.save(newAffittuatario);
    }

    @PutMapping("/api/v1/affittuatario/{affittuatarioId}")
    Affittuatario updateAffittuatario(@PathVariable Long affittuatarioId, @RequestBody Affittuatario updatedAffittuatario){
        Affittuatario affittuatario = this.affittuatarioRepository.findById(affittuatarioId).orElseThrow();
        affittuatario.setNome(updatedAffittuatario.getNome());
        affittuatario.setCognome(updatedAffittuatario.getCognome());
        affittuatario.setNumero_telefono(updatedAffittuatario.getNumero_telefono());
        return this.affittuatarioRepository.save(affittuatario);
    }

    @DeleteMapping("/api/v1/affittuatario/{affittuatarioId}")
    void deleteAffittuatario(@PathVariable Long affittuatarioId){
        Affittuatario affittuatario = this.affittuatarioRepository.findById(affittuatarioId).orElseThrow();
        this.affittuatarioRepository.delete(affittuatario);
    }
}
