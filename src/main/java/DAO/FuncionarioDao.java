package DAO;

import models.Funcionario;
import utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {

    public void inserir(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios (id, matricula, setor) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, funcionario.getId());
            stmt.setString(2, funcionario.getMatricula());
            stmt.setString(3, funcionario.getSetor());
            stmt.executeUpdate();

            System.out.println("Funcionário inserido com sucesso.");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Erro: Matrícula duplicada ou ID de pessoa inexistente.");
            System.out.println("Log: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao inserir funcionário: " + e.getMessage());
        }
    }

    public List<Funcionario> listarTodos() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = """
            SELECT p.id, p.nome, p.email, f.matricula, f.setor
            FROM funcionarios f
            JOIN pessoas p ON f.id = p.id
            """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario f = new Funcionario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("matricula"),
                        rs.getString("setor")
                );
                funcionarios.add(f);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
        }
        return funcionarios;
    }

    public Funcionario buscarPorId(int id) {
        String sql = """
            SELECT p.id, p.nome, p.email, f.matricula, f.setor
            FROM funcionarios f
            JOIN pessoas p ON f.id = p.id
            WHERE f.id = ?
            """;
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Funcionario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("matricula"),
                        rs.getString("setor")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionário por ID: " + e.getMessage());
        }
        return null;
    }

    public void atualizar(int id, Funcionario funcionario) {
        String sqlPessoa = "UPDATE pessoas SET nome = ?, email = ? WHERE id = ?";
        String sqlFuncionario = "UPDATE funcionarios SET matricula = ?, setor = ? WHERE id = ?";

        try (Connection conn = Conexao.getConexao()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
                 PreparedStatement stmtFunc = conn.prepareStatement(sqlFuncionario)) {

                // Atualiza pessoa
                stmtPessoa.setString(1, funcionario.getNome());
                stmtPessoa.setString(2, funcionario.getEmail());
                stmtPessoa.setInt(3, id);
                stmtPessoa.executeUpdate();

                // Atualiza funcionário
                stmtFunc.setString(1, funcionario.getMatricula());
                stmtFunc.setString(2, funcionario.getSetor());
                stmtFunc.setInt(3, id);
                stmtFunc.executeUpdate();

                conn.commit();
                System.out.println("Funcionário atualizado com sucesso.");
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Erro: Matrícula ou email duplicado.");
            System.out.println("Log: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        try (Connection conn = Conexao.getConexao()) {
            ProjetoDao projetoDao = new ProjetoDao();
            int totalProjetos = projetoDao.contarProjetosPorFuncionario(id);
            if (totalProjetos > 0) {
                System.out.println("Erro: Funcionário vinculado a " + totalProjetos + " projeto(s). Não pode ser deletado.");
                return;
            }

            String sql = "DELETE FROM funcionarios WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Funcionário removido com sucesso (pessoa mantida).");
                } else {
                    System.out.println("Funcionário não encontrado com ID " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar funcionário: " + e.getMessage());
        }
    }
}
