package DAO;

import models.Pessoa;
import utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDao {

    // CREATE
    public void inserir(Pessoa pessoa) {
        String sql = "INSERT INTO pessoas (nome, email) VALUES (?, ?)";
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmail());
            stmt.executeUpdate();
            System.out.println("Pessoa inserida com sucesso");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(
                    "--------------------------------------------------------\nNão pode ser inserido usuários com mesmo email!");
            System.out.println("Log: " + e.getMessage() + "\n--------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // GET ALL
    public List<Pessoa> listarTodas() {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM pessoas";
        try {

            Connection conn = Conexao.getConexao();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Pessoa pessoa = new Pessoa(rs.getInt("id"), rs.getString("nome"), rs.getString("email"));
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return pessoas;
    }

    // GET ONE
    public Pessoa buscarPorId(int id) {
        String sql = "SELECT * FROM pessoas WHERE id = ?";
        try {

            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Pessoa(rs.getInt("id"), rs.getString("nome"), rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    // UPDATE
    public void atualizar(int id, Pessoa pessoa) {
        String sql = "UPDATE pessoas SET nome = ?, email = ? WHERE id = ?";
        try {

            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmail());
            stmt.setInt(3, id);
            stmt.executeUpdate();
            System.out.println("Pessoa alterada com sucesso");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Não pode ser inserido usuários com mesmo email!");
            System.out.println("Log: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // DELETE
    public void deletar(int id) {
        String sql = "DELETE FROM pessoas WHERE id = ?";
        try {

            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Pessoa deletada com sucesso");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Não é possível deletar uma pessoa se ela esta vinculada a funcionário ou a um projeto");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}