package model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author a248488
 */
public class Consulta {
    private int id;
    private Calendar data;
    private int hora;
    private String comentarios;
    private int idAnimal;
    private int idVet;
    private int idTratamento;
    private boolean terminou;

    public Consulta(int id, Calendar data, int hora, String comentarios, int idAnimal, int idVet, int idTratamento, boolean terminou) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.comentarios = comentarios;
        this.idAnimal = idAnimal;
        this.idVet = idVet;
        this.idTratamento = idTratamento;
        this.terminou = terminou;
    }

    public int getId() {
        return id;
    }
    
    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdVet() {
        return idVet;
    }

    public void setIdVet(int idVet) {
        this.idVet = idVet;
    }

    public int getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }

    public boolean isTerminou() {
        return terminou;
    }

    public void setTerminou(boolean terminou) {
        this.terminou = terminou;
    }
    
    @Override
    public String toString() {
        return "Consulta{" + "id=" + id + ", data=" + data + ", hora=" + hora  + ", comentarios=" + comentarios + ", idAnimal=" + idAnimal + ", idVet=" + idVet + ", idTratamento=" + idTratamento + ", terminou=" + terminou + "}" + "\n";
    }
}
