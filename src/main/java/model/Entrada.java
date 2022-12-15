package model;

import javax.persistence.*;

@Entity
@Table(name = "entrada")
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private String instruccion;

    public Entrada() {}

    public Entrada(int id, String instruccion) {
        this.id = id;
        this.instruccion = instruccion;
    }

    public String getInstruccion() {
        return instruccion;
    }
}
