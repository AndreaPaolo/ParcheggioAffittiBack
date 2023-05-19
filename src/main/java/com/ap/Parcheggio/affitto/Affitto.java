package com.ap.Parcheggio.affitto;

import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ap.Parcheggio.affittuatario.Affittuatario;
import com.ap.Parcheggio.parcheggio.Parcheggio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql="UPDATE affitto SET deleted = true WHERE id = ?") //Soft deleting
@Where(clause = "deleted = false") //Ignora quelle eliminate
public class Affitto{
    private @Id @GeneratedValue Long id;
    private LocalDate data_inizio;
    private LocalDate data_fine;
    private boolean deleted = Boolean.FALSE;

    @ManyToOne()
    @JoinColumn(name = "affittuatario_id", referencedColumnName = "id")
    private Affittuatario affittuatario;

    @ManyToOne()
    @JoinColumn(name = "parcheggio_id", referencedColumnName = "id")
    private Parcheggio parcheggio;

    public Affitto() {
    }

    public Affitto(Long id, LocalDate data_inizio, LocalDate data_fine, boolean deleted) {
        this.id = id;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
        this.deleted = deleted;
    }

}
