package controller;

import manager.Hibernate;
import model.Bodega;
import model.Campo;
import model.Entrada;
import model.Vid;
import utils.Enum;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Controller {
    private Campo campo;
    private Bodega bodega;
    private Hibernate hibernate;

    public Controller() {
        hibernate = new Hibernate();
    }

    public void init(){
        hibernate.initSession();

        ArrayList<Entrada> entradas = hibernate.getAllEntrada();
        int id;

        for (Entrada e : entradas) {
            System.out.println(e.getInstruccion());

            String[] instruccion = e.getInstruccion().split(" ");
            String tipo = instruccion[0];

            if (tipo.equals("B")) {
                bodega = new Bodega();

                String nombre = instruccion[1];

                bodega.setNombre(nombre);

                id = hibernate.insertBodega(bodega);
                bodega.setId(id);
            } else if (tipo.equals("C")) {
                campo = new Campo();

                id = hibernate.oneToOneCampoBodega(campo, bodega);
                campo.setId_campo(id);
            } else if (tipo.equals("V")) {
                Enum.TipoVid tipoVid;

                if (instruccion[1].toUpperCase().equals("BLANCA")) {
                    tipoVid = Enum.TipoVid.BLANCA;
                } else {
                    tipoVid = Enum.TipoVid.NEGRA;
                }

                int cantidad = Integer.parseInt(instruccion[2]);

                id = hibernate.insertVid(tipoVid, cantidad);

                ArrayList<Vid> vids = new ArrayList<>();
                vids.add(hibernate.getVid(id));
                campo.setVids(vids);

                hibernate.updateCampo(campo);
            } else if (tipo.equals("#")) {
                List<Vid> vids = campo.getVids();

                bodega.setVids(vids);
                hibernate.updateBodega(bodega);
            }
        }

        hibernate.endSession();
    }
}
