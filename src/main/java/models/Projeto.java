package models;

public class Projeto {

    private int id;
    private String titulo;
    private String detalhes;
    private int id_funcionario;

    public Projeto() {}

    public Projeto(String titulo, String detalhes, int id_funcionario) {
        this.titulo = titulo;
        this.detalhes = detalhes;
        this.id_funcionario = id_funcionario;
    }

    public Projeto(int id, String titulo, String detalhes, int id_funcionario) {
        this.id = id;
        this.titulo = titulo;
        this.detalhes = detalhes;
        this.id_funcionario = id_funcionario;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDetalhes() { return detalhes; }
    public void setDetalhes(String detalhes) { this.detalhes = detalhes; }

    public int getId_funcionario() { return id_funcionario; }
    public void setId_funcionario(int id_funcionario) { this.id_funcionario = id_funcionario; }

    @Override
    public String toString() {
        return "Projeto: id = " + id + ", titulo = " + titulo + ", detalhes = " + detalhes +
                ", id do funcionario responsável = " + id_funcionario;
    }
}