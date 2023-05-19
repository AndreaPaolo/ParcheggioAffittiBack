package com.ap.Parcheggio.affittuatario;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ap.Parcheggio.affitto.Affitto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql="UPDATE affittuatario SET deleted = true WHERE id = ?") //Soft deleting
@Where(clause = "deleted = false") //Ignora quelle eliminate
public class Affittuatario {
    private @Id @GeneratedValue Long id;
    private String nome;
    private String cognome;
    private String numero_telefono;
    private boolean deleted = Boolean.FALSE;

    @JsonIgnore
    @OneToMany(mappedBy = "affittuatario")
    private Set<Affitto> affitto = new HashSet<>();

    public Affittuatario() {
        
    }

    public Affittuatario(String nome, String cognome, String numero_telefono) {
        this.nome = nome;
        this.cognome = cognome;
        this.numero_telefono = numero_telefono;
    }
}
