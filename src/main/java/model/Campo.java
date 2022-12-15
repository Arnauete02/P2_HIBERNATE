package model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "campo")
public class Campo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @OneToMany
    @JoinColumn(name = "campo_id")
    private List<Vid> vids;

    @OneToOne
    @JoinColumn(name = "id_bodega")
    private Bodega bodega;

    public Campo() {}

    public Campo(int id, List<Vid> vids, Bodega bodega) {
        this.id = id;
        this.vids = vids;
        this.bodega = bodega;
    }

    public void setId_campo(int id) {
        this.id = id;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public void setVids(List<Vid> vids) {
        this.vids = vids;
    }

    public List<Vid> getVids() {
        return vids;
    }
}
