public class Funcionario implements Comparable<Funcionario> {
    private int id;
    private String nome;
    private double salario;

    public Funcionario(int id, String nome, double salario) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
    }



    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public int compareTo(Funcionario outroFuncionario) {
        return Integer.compare(this.id, outroFuncionario.id);
    }

    @Override
    public String toString() {
        return String.format("Funcionário [ID: %d, Nome: %s, Salário: R$%.2f]", id, nome, salario);
    }


}
