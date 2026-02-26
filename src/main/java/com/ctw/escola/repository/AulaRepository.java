package com.ctw.escola.repository;

import com.ctw.escola.model.Aluno;
import com.ctw.escola.model.Aula;
import com.ctw.escola.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AulaRepository {


    public Aula postAula(Aula aula) throws SQLException {
        String query = """
                INSERT INTO aula(
                turma_id,
                data_hora,
                assunto,
                )
                VALUES
                (?,?,?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS)){

            stmt.setLong(1, aula.getTurma_id());
            stmt.setTimestamp(2,Timestamp.valueOf(aula.getData_hora()));
            stmt.setString(3, aula.getAssunto());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                aula.setId(rs.getInt(1));
                return aula;
            }
        }
        return null;
    }

    public List<Aula> getAulas() throws SQLException{
        List<Aula> aulaList = new ArrayList<>();

        String query = """
                SELECT a.id
                ,t.turma_id
                ,a.data_hora
                ,a.assunto
                FROM aula a
                JOIN turma t ON a.turma_id = t.id
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                long id = rs.getLong("id");
                long turma_id = rs.getLong("turma_id");
                LocalDateTime data_hora = rs.getTimestamp("data_hora").toLocalDateTime();
                String assunto = rs.getString("assunto");
                Aula aula = new Aula (id,turma_id,data_hora,assunto);
                aulaList.add(aula);
            }
        }
        return aulaList;
    }

    public Aula getAulaPorId(long id) throws SQLException{
        String query = """
                SELECT a.id
                ,t.turma_id
                ,a.data_hora
                ,a.assunto
                FROM aula a
                JOIN turma t ON a.turma_id = t.id
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                long idAula = rs.getLong("id");
                long turma_id = rs.getLong("turma_id");
                LocalDateTime data_hora = rs.getTimestamp("data_hora").toLocalDateTime();
                String assunto = rs.getString("assunto");
                Aula aula = new Aula (idAula,turma_id,data_hora,assunto);
                return aula;
            }
        }
        throw new RuntimeException("Aula não encontrado");
    }

    public Aula putAluno(Aula aula) throws SQLException{
        String query = """
                UPDATE aula
                set turma_id = ?,
                data_hora = ?,
                assunto = ?
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1, aula.getTurma_id());
            stmt.setTimestamp(2,Timestamp.valueOf(aula.getData_hora()));
            stmt.setString(3, aula.getAssunto());
            stmt.setLong(4,aula.getId());
            stmt.executeUpdate();
        }
        return aula;
    }

    public void deletarAula(long id)throws SQLException{
        String query = """
                DELETE FROM aula
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
