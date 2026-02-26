package com.ctw.escola.repository;

import com.ctw.escola.model.*;
import com.ctw.escola.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoRepository {

    public Curso postCurso(Curso curso) throws SQLException {
        String query = """
                INSERT INTO curso (
                nome,
                codigo,
                )
                VALUES
                (?,?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                curso.setId(rs.getInt(1));
                return curso;
            }
        }
        return null;
    }

    public List<Curso> getCursos() throws SQLException{
        List<Curso> cursoList = new ArrayList<>();

        String query = """
                SELECT id
                ,nome
                ,codigo
                FROM curso
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                long id = rs.getInt("id");
                String nome = rs.getString("nome");
                String codigo = rs.getString("codigo");
                Curso curso = new Curso(id,nome,codigo);
                cursoList.add(curso);
            }
        }
        return cursoList;
    }

    public Curso getCursoPorId(long id) throws SQLException{
        String query = """
                SELECT id
                ,nome
                ,codigo
                FROM curso
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                long curso_id = rs.getInt("id");
                String nome = rs.getString("nome");
                String codigo = rs.getString("codigo");
              Curso curso = new Curso(curso_id,nome,codigo);
              return curso;
            }
        }
        throw new RuntimeException("Curso não encontrado");
    }

    public Curso putCurso(Curso curso) throws SQLException{
        String query = """
                UPDATE curso
                set nome = ?,
                codigo = ?
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());
            stmt.setLong(5,curso.getId());
            stmt.executeUpdate();
        }
        return curso;
    }

    public void deletarAluno(long id)throws SQLException{
        String query = """
                DELETE FROM curso
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

}
