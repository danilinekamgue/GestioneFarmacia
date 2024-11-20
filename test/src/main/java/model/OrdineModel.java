package model;

import java.time.LocalDateTime;
import java.util.List;

public class OrdineModel {

    private String numero;

    private LocalDateTime time;
    private List<Farmaco> farmaci;
    private int prezzototale;




    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<Farmaco> getFarmaci() {
        return farmaci;
    }

    public void setFarmaci(List<Farmaco> farmaci) {
        this.farmaci = farmaci;
    }

    public int getPrezzototale() {
        return prezzototale;
    }

    public void setPrezzototale(int prezzototale) {
        this.prezzototale = prezzototale;
    }
}
