package model;

import java.util.Date;

/**
 *
 * @author a248488
 */
public class Tratamento {
    private int id;
    private String nome;
    private Date data_incio;
    private Date data_fim;
    private int idAnimal;
    private boolean terminou;

    public Tratamento(int id, String nome, Date data_incio, Date data_fim, int idAnimal, boolean terminou) {
        this.id = id;
        this.nome = nome;
        this.data_incio = data_incio;
        this.data_fim = data_fim;
        this.idAnimal = idAnimal;
        this.terminou = terminou;
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData_incio() {
        return data_incio;
    }

    public void setData_incio(Date data_incio) {
        this.data_incio = data_incio;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public boolean isTerminou() {
        return terminou;
    }

    public void setTerminou(boolean terminou) {
        this.terminou = terminou;
    }
    
    @Override
    public String toString() {
        return "Tratamento{" + "id=" + id + ", nome=" + nome + ", idAnimal=" + idAnimal  + ", data_incio=" + data_incio + ", data_fim=" + data_fim + ", terminou=" + terminou + "}" + "\n";
    }
}
