import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void quicksort(Funcionario[] funcionarios, int inicio, int fim, String criterio) {
        if (inicio < fim) {
            int indiceParticao = particionar(funcionarios, inicio, fim, criterio);
            quicksort(funcionarios, inicio, indiceParticao - 1, criterio);
            quicksort(funcionarios, indiceParticao + 1, fim, criterio);
        }
    }

    private static int particionar(Funcionario[] funcionarios, int inicio, int fim, String criterio) {
        Funcionario pivo = funcionarios[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (compare(funcionarios[j], pivo, criterio) < 0) {
                i++;
                trocar(funcionarios, i, j);
            }
        }

        trocar(funcionarios, i + 1, fim);
        return i + 1;
    }

    private static int compare(Funcionario func1, Funcionario func2, String criterio) {
        switch (criterio.toUpperCase()) {
            case "ID":
                return Integer.compare(func1.getId(), func2.getId());
            case "NOME":
                return func1.getNome().compareToIgnoreCase(func2.getNome());
            default:
                throw new IllegalArgumentException("Critério de ordenação inválido");
        }
    }

    private static void trocar(Funcionario[] funcionarios, int i, int j) {
        Funcionario temp = funcionarios[i];
        funcionarios[i] = funcionarios[j];
        funcionarios[j] = temp;
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Gabriel\\IdeaProjects\\robson\\src\\dados_Funcionarios.txt"));

            int numFuncionarios = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após o número

            Funcionario[] funcionarios = new Funcionario[numFuncionarios];

            for (int i = 0; i < numFuncionarios; i++) {
                String linha = scanner.nextLine();
                String[] dados = linha.split("\\s+");

                if (dados.length >= 4) {
                    int id = Integer.parseInt(dados[0]);
                    int salarioIndex = linha.lastIndexOf(dados[dados.length - 1]);
                    String nome = linha.substring(dados[0].length(), salarioIndex).trim();
                    double salario = Double.parseDouble(dados[dados.length - 1]);
                    funcionarios[i] = new Funcionario(id, nome, salario);
                } else {
                    System.err.println("Formato de linha inválido: " + linha);
                }
            }

            scanner.close();

            System.out.println("Escolha o critério de ordenação (ID ou Nome): ");
            Scanner consoleScanner = new Scanner(System.in);
            String criterio = consoleScanner.nextLine();

            System.out.println("Funcionários antes da ordenação:");
            for (Funcionario f : funcionarios) {
                System.out.println(f);
            }

            quicksort(funcionarios, 0, numFuncionarios - 1, criterio);

            System.out.println("\nFuncionários após a ordenação:");
            for (Funcionario f : funcionarios) {
                System.out.println(f);
            }

            consoleScanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
