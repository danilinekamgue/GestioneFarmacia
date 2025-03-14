package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdineModel {

    private String numero;

    private String time;
    private List<Farmaco> farmaci;
    private double prezzototale;

    public OrdineModel() {
        this.farmaci = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Farmaco> getFarmaci() {
        return farmaci;
    }

    public void setFarmaci(List<Farmaco> farmaci) {
        this.farmaci = farmaci;
    }

    public double getPrezzototale() {
        return prezzototale;
    }

    public void setPrezzototale(double prezzototale) {
        this.prezzototale = prezzototale;
    }
}
