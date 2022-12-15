package model;

import utils.Enum;

import javax.persistence.*;

@Entity
@Table(name = "vid")
public class Vid {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private Enum.TipoVid tipo;

    @Column
    private int cantidad;

    public Vid() {}

    public Vid(int id, Enum.TipoVid tipo, int cantidad) {
        this.id = id;
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(Enum.TipoVid tipo) {
        this.tipo = tipo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
