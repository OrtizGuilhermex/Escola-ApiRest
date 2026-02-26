package com.ctw.escola.repository;

import com.ctw.escola.model.Aluno;
import com.ctw.escola.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AlunoRepository {

    public Aluno postAluno(Aluno aluno) throws SQLException {
        String query = """
                INSERT INTO aluno (
                nome,
                email,
                matricula,
                data_nascimento
                )
                VALUES
                (?,?,?,?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getMatricula());
            stmt.setDate(4,Date.valueOf(aluno.getData_nascimento()));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                aluno.setId(rs.getInt(1));
                return aluno;
            }
        }
        return null;
    }

    public List<Aluno> getAlunos() throws SQLException{
        List<Aluno> alunos = new ArrayList<>();

        String query = """
                SELECT id
                ,nome
                ,email
                ,matricula
                ,data_nascimento
                FROM aluno
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                long id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String matricula = rs.getString("matricula");
                LocalDate data_nascimento = rs.getDate("data_nascimento").toLocalDate();
                Aluno aluno = new Aluno(id, nome,email,matricula,data_nascimento);
                alunos.add(aluno);
            }
        }
        return alunos;
    }

    public Aluno getAlunosPorId(int id) throws SQLException{
        String query = """
                SELECT id
                ,nome
                ,email
                ,matricula
                ,data_nascimento
                FROM aluno
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                long idAluno = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String matricula = rs.getString("matricula");
                LocalDate data_nascimento = rs.getDate("data_nascimento").toLocalDate();
                Aluno aluno = new Aluno(idAluno, nome,email,matricula,data_nascimento);
                return  aluno;
            }
        }
        throw new RuntimeException("Livro não encontrado");
    }

    public Aluno putAluno(Aluno aluno) throws SQLException{
        String query = """
                UPDATE aluno
                set nome = ?,
                email = ?,
                matricula = ?,
                data_nascimento = ?
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getMatricula());
            stmt.setDate(4,Date.valueOf(aluno.getData_nascimento()));
            stmt.setLong(5,aluno.getId());
            stmt.executeUpdate();
        }
        return aluno;
    }

    public void deletarEmprestimo(int id)throws SQLException{
        String query = """
                DELETE FROM aluno
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
