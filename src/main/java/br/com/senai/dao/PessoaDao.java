package br.com.senai.dao;

import br.com.senai.connection.SingleConnectionDB;
import br.com.senai.model.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDao {

    private Connection connection;

    public PessoaDao(){
        connection = SingleConnectionDB.getConnection();
    }

    public void addUser(Pessoa pessoa) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into pessoa(nome,endereco) values (?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getEndereco());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from pessoa where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(Pessoa pessoa) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update pessoa set nome=?, endereco=?" +
                            "where id=?");
            // Parameters start with 1
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getEndereco());
            preparedStatement.setInt(3, pessoa.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pessoa> getAllUsers() {
        List<Pessoa> listaDePessoa = new ArrayList<Pessoa>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from pessoa");
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEndereco(rs.getString("endereco"));
                listaDePessoa.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaDePessoa;
    }

    public Pessoa getUserById(int id) {
        Pessoa user = new Pessoa();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from pessoa where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setEndereco(rs.getString("endereco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
