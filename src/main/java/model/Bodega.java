package model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "bodega")
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private String nombre;

    @OneToMany
    @JoinColumn(name = "bodega_id")
    private List<Vid> vids;

    public Bodega() {}

    public Bodega(int id, String nombre, List<Vid> vids) {
        this.id = id;
        this.nombre = nombre;
        this.vids = vids;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVids(List<Vid> vids) {
        this.vids = vids;
    }
}
