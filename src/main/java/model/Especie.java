package model;

/**
 *
 * @author a248488
 */
public class Especie {
    private int id;
    private String nome;

    public Especie(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    
    @Override
    public String toString() {
        return "Especie{" + "id=" + id + ", nome=" + nome + "}" + "\n";
    }
}
