import java.util.List;
import java.util.Scanner;

import DAO.FuncionarioDao;
import DAO.PessoaDao;
import DAO.ProjetoDao;
import models.Funcionario;
import models.Pessoa;
import models.Projeto;

public class Main {
    public static void main(String[] args) {

        PessoaDao pDao = new PessoaDao();
        FuncionarioDao fDao = new FuncionarioDao();
        ProjetoDao projetoDao = new ProjetoDao();

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("--------------------------------------");
            System.out.println("Escolha a entidade a ser gerenciada:");
            System.out.println("1 - Pessoa");
            System.out.println("2 - Funcionário");
            System.out.println("3 - Projeto");
            System.out.println("4 - Sair");
            System.out.println("--------------------------------------");

            int resposta = sc.nextInt();
            sc.nextLine();

            while (resposta < 1 || resposta > 4) {
                System.out.println("Por favor, escolha uma opção válida (1-4):");
                resposta = sc.nextInt();
                sc.nextLine();
            }

            switch (resposta) {
                case 1 -> {
                    System.out.println("------------------------------------------");
                    System.out.println("Operações com Pessoas:");
                    System.out.println("1 - Listar");
                    System.out.println("2 - Inserir");
                    System.out.println("3 - Atualizar");
                    System.out.println("4 - Buscar por ID");
                    System.out.println("5 - Deletar");
                    System.out.println("------------------------------------------");

                    int opcaoPessoa = sc.nextInt();
                    sc.nextLine();

                    while (opcaoPessoa < 1 || opcaoPessoa > 5) {
                        System.out.println("Escolha uma opção válida (1-5):");
                        opcaoPessoa = sc.nextInt();
                        sc.nextLine();
                    }

                    switch (opcaoPessoa) {
                        case 1 -> {
                            List<Pessoa> pessoas = pDao.listarTodas();
                            System.out.println("Pessoas cadastradas: " + pessoas.size());
                            pessoas.forEach(System.out::println);
                        }
                        case 2 -> {
                            System.out.print("Nome: ");
                            String nome = sc.nextLine();
                            System.out.print("Email: ");
                            String email = sc.nextLine();
                            Pessoa p = new Pessoa(nome, email);
                            pDao.inserir(p);
                        }
                        case 3 -> {
                            System.out.print("ID da pessoa a atualizar: ");
                            int id = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Novo nome: ");
                            String nome = sc.nextLine();
                            System.out.print("Novo email: ");
                            String email = sc.nextLine();
                            Pessoa p = new Pessoa(nome, email);
                            pDao.atualizar(id, p);
                        }
                        case 4 -> {
                            System.out.print("ID da pessoa para buscar: ");
                            int id = sc.nextInt();
                            sc.nextLine();
                            Pessoa p = pDao.buscarPorId(id);
                            if (p != null) {
                                System.out.println(p);
                            } else {
                                System.out.println("Pessoa não encontrada.");
                            }
                        }
                        case 5 -> {
                            System.out.print("ID da pessoa a deletar: ");
                            int id = sc.nextInt();
                            sc.nextLine();
                            pDao.deletar(id);
                        }
                    }
                }
                case 2 -> {
                    System.out.println("------------------------------------------");
                    System.out.println("Operações com Funcionários:");
                    System.out.println("1 - Listar");
                    System.out.println("2 - Inserir");
                    System.out.println("3 - Atualizar");
                    System.out.println("4 - Buscar por ID");
                    System.out.println("5 - Remover vínculo");
                    System.out.println("------------------------------------------");

                    int opcaoFunc = sc.nextInt();
                    sc.nextLine();

                    while (opcaoFunc < 1 || opcaoFunc > 5) {
                        System.out.println("Escolha uma opção válida (1-5):");
                        opcaoFunc = sc.nextInt();
                        sc.nextLine();
                    }

                    switch (opcaoFunc) {
                        case 1 -> {
                            List<Funcionario> funcionarios = fDao.listarTodos();
                            System.out.println("Funcionários cadastrados: " + funcionarios.size());
                            funcionarios.forEach(System.out::println);
                        }
                        case 2 -> {
                            System.out.print("ID da pessoa vinculada: ");
                            int idPessoa = sc.nextInt();
                            sc.nextLine();

                            Pessoa p = pDao.buscarPorId(idPessoa);
                            if (p == null) {
                                System.out.println("Pessoa não encontrada. Cadastre a pessoa antes.");
                                break;
                            }

                            System.out.print("Matrícula: ");
                            String matricula = sc.nextLine();
                            System.out.print("Setor: ");
                            String setor = sc.nextLine();

                            Funcionario f = new Funcionario(idPessoa, p.getNome(), p.getEmail(), matricula, setor);
                            fDao.inserir(f);
                        }
                        case 3 -> {
                            System.out.print("ID do funcionário a atualizar: ");
                            int id = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Novo nome: ");
                            String nome = sc.nextLine();
                            System.out.print("Novo email: ");
                            String email = sc.nextLine();
                            System.out.print("Nova matrícula: ");
                            String matricula = sc.nextLine();
                            System.out.print("Novo setor: ");
                            String setor = sc.nextLine();

                            Funcionario f = new Funcionario(id, nome, email, matricula, setor);
                            fDao.atualizar(id, f);
                        }
                        case 4 -> {
                            System.out.print("ID do funcionário para buscar: ");
                            int id = sc.nextInt();
                            sc.nextLine();
                            Funcionario f = fDao.buscarPorId(id);
                            if (f != null) {
                                System.out.println(f);
                            } else {
                                System.out.println("Funcionário não encontrado.");
                            }
                        }
                        case 5 -> {
                            System.out.print("ID do funcionário para remover vínculo: ");
                            int id = sc.nextInt();
                            sc.nextLine();
                            fDao.deletar(id);
                        }
                    }
                }
                case 3 -> {
                    System.out.println("------------------------------------------");
                    System.out.println("Operações com Projetos:");
                    System.out.println("1 - Listar");
                    System.out.println("2 - Inserir");
                    System.out.println("3 - Atualizar");
                    System.out.println("4 - Buscar por ID");
                    System.out.println("5 - Deletar");
                    System.out.println("------------------------------------------");

                    int opcaoProj = sc.nextInt();
                    sc.nextLine();

                    while (opcaoProj < 1 || opcaoProj > 5) {
                        System.out.println("Escolha uma opção válida (1-5):");
                        opcaoProj = sc.nextInt();
                        sc.nextLine();
                    }

                    switch (opcaoProj) {
                        case 1 -> {
                            List<Projeto> projetos = projetoDao.listarTodos();
                            System.out.println("Projetos cadastrados: " + projetos.size());
                            projetos.forEach(System.out::println);
                        }
                        case 2 -> {
                            System.out.print("Título do projeto: ");
                            String titulo = sc.nextLine();
                            System.out.print("Detalhes do projeto: ");
                            String detalhes = sc.nextLine();
                            System.out.print("ID do funcionário responsável: ");
                            int idFunc = sc.nextInt();
                            sc.nextLine();

                            Projeto p = new Projeto(titulo, detalhes, idFunc);
                            projetoDao.inserir(p);
                        }
                        case 3 -> {
                            System.out.print("ID do projeto a atualizar: ");
                            int id = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Novo título: ");
                            String titulo = sc.nextLine();
                            System.out.print("Novos detalhes: ");
                            String detalhes = sc.nextLine();
                            System.out.print("Novo ID do funcionário responsável: ");
                            int idFunc = sc.nextInt();
                            sc.nextLine();

                            Projeto p = new Projeto(titulo, detalhes, idFunc);
                            projetoDao.atualizar(id, p);
                        }
                        case 4 -> {
                            System.out.print("ID do projeto para buscar: ");
                            int id = sc.nextInt();
                            sc.nextLine();
                            Projeto p = projetoDao.buscarPorId(id);
                            if (p != null) {
                                System.out.println(p);
                            } else {
                                System.out.println("Projeto não encontrado.");
                            }
                        }
                        case 5 -> {
                            System.out.print("ID do projeto para deletar: ");
                            int id = sc.nextInt();
                            sc.nextLine();
                            projetoDao.deletar(id);
                        }
                    }
                }
                case 4 -> {
                    continuar = false;
                    System.out.println("Sistema encerrado.");
                }
            }
        }

        sc.close();
    }
}
