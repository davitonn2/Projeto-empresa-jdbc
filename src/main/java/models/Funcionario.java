package models;

public class Funcionario extends Pessoa {

    private String matricula;
    private String setor;

    public Funcionario() {}

    public Funcionario(String nome, String email, String matricula, String setor) {
        super(nome, email);
        this.matricula = matricula;
        this.setor = setor;
    }

    public Funcionario(int id, String nome, String email, String matricula, String setor) {
        super(id, nome, email);
        this.matricula = matricula;
        this.setor = setor;
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }

    @Override
    public String toString() {
        return "Funcionario id = " + getId() + ", nome = " + getNome() + ", email = " + getEmail() +
                ", matricula = " + matricula + ", setor = " + setor;
    }
}
