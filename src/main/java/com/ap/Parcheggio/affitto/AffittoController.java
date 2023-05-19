package com.ap.Parcheggio.affitto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ap.Parcheggio.affittuatario.Affittuatario;
import com.ap.Parcheggio.affittuatario.AffittuatarioRepository;
import com.ap.Parcheggio.parcheggio.Parcheggio;
import com.ap.Parcheggio.parcheggio.ParcheggioRepository;

@RestController
public class AffittoController {
    private final AffittoRepository affittoRepository;
    private final ParcheggioRepository parcheggioRepository;
    private final AffittuatarioRepository affittuatarioRepository;

    AffittoController(AffittoRepository affittoRepository, ParcheggioRepository parcheggioRepository, AffittuatarioRepository affittuatarioRepository){
        this.affittoRepository = affittoRepository;
        this.affittuatarioRepository = affittuatarioRepository;
        this.parcheggioRepository = parcheggioRepository;
    }

    @GetMapping("/api/v1/affitto")
    Iterable<Affitto> getAffitto(){
        return this.affittoRepository.findAll();
    }

    @PostMapping("/api/v1/affitto/parcheggio/{parcheggioId}/affittuatario/{affittuatarioId}")
    Affitto assegnazioneParcheggoAffittuatario(@RequestBody Affitto newAffitto, @PathVariable Long parcheggioId, @PathVariable Long affittuatarioId){
        Parcheggio parcheggio = this.parcheggioRepository.findById(parcheggioId).orElseThrow();
        Affittuatario affittuatario = this.affittuatarioRepository.findById(affittuatarioId).orElseThrow();
        newAffitto.setAffittuatario(affittuatario);
        newAffitto.setParcheggio(parcheggio);
        return this.affittoRepository.save(newAffitto);
    }

    @PutMapping("/api/v1/affitto/{affittoId}")
    Affitto updateAffitto(@RequestBody Affitto updatedAffitto, @PathVariable Long affittoId){
        Affitto affitto = this.affittoRepository.findById(affittoId).orElseThrow();
        affitto.setData_inizio(updatedAffitto.getData_inizio());
        affitto.setData_fine(updatedAffitto.getData_fine());
        return this.affittoRepository.save(affitto);
    }

    @DeleteMapping("/api/v1/affitto/{affittoId}")
    void deleteAffitto(@PathVariable Long affittoId){
        Affitto affitto = this.affittoRepository.findById(affittoId).orElseThrow();
        this.affittoRepository.delete(affitto);
    }
}
