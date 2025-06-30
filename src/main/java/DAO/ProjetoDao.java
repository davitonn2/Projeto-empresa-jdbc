package DAO;

import models.Projeto;
import utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDao {

    public void inserir(Projeto projeto) {
        String verificarFuncionario = "SELECT id FROM funcionarios WHERE id = ?";
        String sql = "INSERT INTO projetos (titulo, detalhes, id_funcionario) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConexao()) {
            try (PreparedStatement stmtVerif = conn.prepareStatement(verificarFuncionario)) {
                stmtVerif.setInt(1, projeto.getId_funcionario());
                ResultSet rs = stmtVerif.executeQuery();

                if (!rs.next()) {
                    System.out.println("Erro: Funcionário com ID " + projeto.getId_funcionario() + " não existe.");
                    return;
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, projeto.getTitulo());
                stmt.setString(2, projeto.getDetalhes());
                stmt.setInt(3, projeto.getId_funcionario());
                stmt.executeUpdate();

                System.out.println("Projeto inserido com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir projeto: " + e.getMessage());
        }
    }

    public List<Projeto> listarTodos() {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM projetos";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Projeto p = new Projeto(rs.getInt("id"), rs.getString("titulo"),
                        rs.getString("detalhes"), rs.getInt("id_funcionario"));
                projetos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar projetos: " + e.getMessage());
        }
        return projetos;
    }

    public Projeto buscarPorId(int id) {
        String sql = "SELECT * FROM projetos WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Projeto(rs.getInt("id"), rs.getString("titulo"),
                        rs.getString("detalhes"), rs.getInt("id_funcionario"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar projeto: " + e.getMessage());
        }
        return null;
    }

    public void atualizar(int id, Projeto projeto) {
        String sql = "UPDATE projetos SET titulo = ?, detalhes = ?, id_funcionario = ? WHERE id = ?";
        String sqlCheck = "SELECT id FROM funcionarios WHERE id = ?";

        try (Connection conn = Conexao.getConexao()) {
            try (PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck)) {
                stmtCheck.setInt(1, projeto.getId_funcionario());
                ResultSet rs = stmtCheck.executeQuery();

                if (!rs.next()) {
                    System.out.println("Erro: Funcionário com ID " + projeto.getId_funcionario() + " não existe.");
                    return;
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, projeto.getTitulo());
                stmt.setString(2, projeto.getDetalhes());
                stmt.setInt(3, projeto.getId_funcionario());
                stmt.setInt(4, id);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Projeto atualizado com sucesso.");
                } else {
                    System.out.println("Projeto não encontrado com ID " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar projeto: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM projetos WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Projeto deletado com sucesso.");
            } else {
                System.out.println("Projeto não encontrado com ID " + id);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar projeto: " + e.getMessage());
        }
    }

    public int contarProjetosPorFuncionario(int idFuncionario) {
        String sql = "SELECT COUNT(*) AS total FROM projetos WHERE id_funcionario = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao contar projetos: " + e.getMessage());
        }
        return 0;
    }
}
