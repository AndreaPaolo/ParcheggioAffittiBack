package com.ap.Parcheggio.parcheggio;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ap.Parcheggio.affitto.Affitto;
import com.ap.Parcheggio.sede.Sede;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql="UPDATE parcheggio SET deleted = true WHERE id = ?") //Soft deleting
@Where(clause = "deleted = false") //Ignora quelle eliminate
public class Parcheggio {
    private @Id @GeneratedValue Long id;
    private String nome;
    private boolean deleted = Boolean.FALSE;

    @JsonIgnore
    @OneToMany(mappedBy = "parcheggio")
    private Set<Affitto> affitto = new HashSet<>();

    @ManyToOne()
    @JoinColumn(name = "sede_id", referencedColumnName = "id")
    private Sede sede;

    public Parcheggio() {

    }

    public Parcheggio(String nome, boolean deleted) {
        this.nome = nome;
        this.deleted = deleted;
    }

    public void assegnazione(Sede sede){
        this.sede = sede;
    }

}
