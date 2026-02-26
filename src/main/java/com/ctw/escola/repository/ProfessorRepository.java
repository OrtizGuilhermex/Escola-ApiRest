package com.ctw.escola.repository;

import com.ctw.escola.model.Professor;
import com.ctw.escola.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfessorRepository {

    public Professor postProfessor(Professor professor) throws SQLException {
        String query = """
                INSERT INTO professor (
                nome,
                email,
                displina,
                )
                VALUES
                (?,?,?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getDisciplina());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                professor.setId(rs.getInt(1));
                return professor;
            }
        }
        return null;
    }

    public List<Professor> getProfessores() throws SQLException{
        List<Professor> professorList = new ArrayList<>();

        String query = """
                SELECT id
                ,nome
                ,email
                ,disciplina
                FROM professor
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                long id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String displina = rs.getString("disciplina");
                Professor professor = new Professor(id, nome,email,displina);
                professorList.add(professor);
            }
        }
        return professorList;
    }

    public Professor getProfessorPorId(long id) throws SQLException{
        String query = """
                SELECT id
                ,nome
                ,email
                ,displina
                FROM professor
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                long idProfessor = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String displina = rs.getString("displina");
                Professor professor = new Professor(idProfessor, nome,email,displina);
                return  professor;
            }
        }
        throw new RuntimeException("Livro não encontrado");
    }

    public Professor putProfessor(Professor professor) throws SQLException{
        String query = """
                UPDATE professor
                set nome = ?,
                email = ?,
                disciplina = ?,
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getDisciplina());
            stmt.setLong(4,professor.getId());
            stmt.executeUpdate();
        }
        return professor;
    }

    public void deletarAluno(long id)throws SQLException{
        String query = """
                DELETE FROM professor
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

}
