import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuarPrograma = true;
        Pessoa pessoa;
        boolean aposentado;
        int qtdFuncionarios;
        int qtdMesesDesempregado;
        int totalUsuariosLidos = 0;
        int totalValorConcedido = 0;
        ArrayList<Pessoa> doisBeneficiariosMaiorValor = new ArrayList(2);
        ArrayList<Pessoa> doisBeneficiariosMaiorTempo = new ArrayList(2);

        while(continuarPrograma){
            pessoa = new Pessoa();
            aposentado = false;
            qtdFuncionarios = 0;
            qtdMesesDesempregado = 0;

            System.out.println("Digite o nome completo");
            pessoa.setNomeCompleto(scanner.nextLine());

            System.out.println("Digite a data de nascimento");
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            pessoa.setDataNascimento(LocalDate.parse(scanner.next(), df));

            System.out.println("Escolha a categoria: \n 1- Empregado \n 2- Empregador \n 3- Desempregado");
            switch (scanner.nextInt()){
                case 1:
                    pessoa.setCategoria(Categorias.Empregado);
                    System.out.println("O beneficiário é aposentado? \n S - Sim  \n N - Não");
                    if(scanner.next().equalsIgnoreCase("S")){
                        aposentado = true;
                    } else{
                        aposentado = false;
                    }
                    break;
                case 2:
                    pessoa.setCategoria(Categorias.Empregador);
                    System.out.println("O beneficiário possui quantos funcionários?");
                    qtdFuncionarios = scanner.nextInt();
                    break;
                case 3:
                    pessoa.setCategoria(Categorias.Desempregado);
                    System.out.println("O beneficiário está desempregado há quantos meses?");
                    qtdMesesDesempregado = scanner.nextInt();
                    break;
            }

            System.out.println("Digite a sua UF");
            pessoa.setEstado(scanner.next());

            int mesesBeneficio = pessoa.obterMesesBeneficio(aposentado);
            double valorBeneficio = pessoa.obterValorBeneficio(qtdMesesDesempregado, qtdFuncionarios);
            totalValorConcedido += valorBeneficio * mesesBeneficio;

            if(totalUsuariosLidos == 0 || totalUsuariosLidos == 1){
                doisBeneficiariosMaiorValor.add(pessoa);
                doisBeneficiariosMaiorTempo.add(pessoa);
            } else{
                if(doisBeneficiariosMaiorValor.get(0).getValorBeneficio() >= doisBeneficiariosMaiorValor.get(1).getValorBeneficio()){
                    if(valorBeneficio > doisBeneficiariosMaiorValor.get(0).getValorBeneficio()){
                        doisBeneficiariosMaiorValor.set(0, pessoa);
                    }
                } else{
                    if(valorBeneficio > doisBeneficiariosMaiorValor.get(1).getValorBeneficio()){
                        doisBeneficiariosMaiorValor.set(1, pessoa);
                    }
                }
                if(doisBeneficiariosMaiorTempo.get(0).getTempoBeneficio() >= doisBeneficiariosMaiorTempo.get(1).getTempoBeneficio()){
                    if(mesesBeneficio > doisBeneficiariosMaiorTempo.get(0).getTempoBeneficio()){
                        doisBeneficiariosMaiorTempo.set(0, pessoa);
                    }
                } else{
                    if(mesesBeneficio > doisBeneficiariosMaiorTempo.get(1).getTempoBeneficio()){
                        doisBeneficiariosMaiorTempo.set(1, pessoa);
                    }
                }
            }

            System.out.println("Nome Completo: " + pessoa.getNomeCompleto());
            System.out.println("Data de Nascimento: " + pessoa.getDataNascimento());
            System.out.println("Categoria: " + pessoa.getCategoria());
            System.out.println("Regras atendidas: " + pessoa.getRegrasAtendidas());
            System.out.println("Meses de benefício: " + mesesBeneficio);
            System.out.println("Valor do benefício: " + valorBeneficio);

            totalUsuariosLidos++;

            System.out.println("Deseja informar um novo beneficiário? \n S - Sim  \n N - Não");
            if(!scanner.next().equalsIgnoreCase("S")){
                continuarPrograma = false;
            }
            scanner.nextLine();
        }

        System.out.println("Total de Usuários Lidos: " + totalUsuariosLidos);
        System.out.println("Total de valor concedido: " + totalValorConcedido);
        System.out.println("Beneficiários que irão receber os maiores valores: " + doisBeneficiariosMaiorValor.get(0).getNomeCompleto() + " e " + doisBeneficiariosMaiorValor.get(1).getNomeCompleto());
        System.out.println("Beneficiários que irão receber por mais tempo: " + doisBeneficiariosMaiorTempo.get(0).getNomeCompleto() + " e " + doisBeneficiariosMaiorTempo.get(1).getNomeCompleto());
    }
}
