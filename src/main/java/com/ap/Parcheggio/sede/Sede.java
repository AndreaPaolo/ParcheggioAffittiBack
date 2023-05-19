package com.ap.Parcheggio.sede;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ap.Parcheggio.parcheggio.Parcheggio;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql="UPDATE sede SET deleted = true WHERE id = ?") //Soft deleting
@Where(clause = "deleted = false") //Ignora quelle eliminate
public class Sede {
    private @Id @GeneratedValue Long id;
    private String nome;
    private boolean deleted = Boolean.FALSE;

    @JsonIgnore
    @OneToMany(mappedBy =  "sede")
    private Set<Parcheggio> parcheggio = new HashSet<>();

    public Sede() {

    }

    public Sede(String nome, boolean deleted) {
        this.nome = nome;
        this.deleted = deleted;
    }

}
