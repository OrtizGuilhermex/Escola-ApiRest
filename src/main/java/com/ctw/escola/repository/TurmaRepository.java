package com.ctw.escola.repository;

import com.ctw.escola.model.Curso;
import com.ctw.escola.model.Professor;
import com.ctw.escola.model.Turma;
import com.ctw.escola.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TurmaRepository {

    public Turma postTurma(Turma turma) throws SQLException {
        String query = """
                INSERT INTO turma (
                nome,
                curso_id,
                professor_id)
                VALUES (?, ?, ?)
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, turma.getNome());
            stmt.setLong(2, turma.getCurso().getId());
            stmt.setLong(3, turma.getProfessor().getId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                turma.setId(rs.getLong(1));
                return turma;
            }
        }
        return null;
    }

    public List<Turma> getTurmas() throws SQLException {
        List<Turma> turmaList = new ArrayList<>();
        String query = """
                SELECT
                t.id AS turma_id,
                t.nome AS turma_nome,
                c.id AS curso_id,
                c.nome AS curso_nome,
                c.codigo AS curso_codigo,
                p.id AS professor_id,
                p.nome AS professor_nome,
                p.email AS professor_email
                FROM turma t
                JOIN curso c ON t.curso_id = c.id
                JOIN professor p ON t.professor_id = p.id
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getLong("curso_id"));
                curso.setNome(rs.getString("curso_nome"));

                Professor professor = new Professor();
                professor.setId(rs.getLong("professor_id"));
                professor.setNome(rs.getString("professor_nome"));

                long idTurma = rs.getLong("turma_id");
                String nomeTurma = rs.getString("turma_nome");

                Turma turma = new Turma(idTurma, nomeTurma, curso, professor);
                turmaList.add(turma);
            }
        }
        return turmaList;
    }

    public Turma getTurmaPorId(long id) throws SQLException {
        String query = """
                SELECT
                t.id AS turma_id,
                t.nome AS turma_nome,
                c.id AS curso_id,
                c.nome AS curso_nome,
                c.codigo AS curso_codigo,
                p.id AS professor_id,
                p.nome AS professor_nome,
                p.email AS professor_email
                FROM turma t
                JOIN curso c ON t.curso_id = c.id
                JOIN professor p ON t.professor_id = p.id
                WHERE t.id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getLong("curso_id"));
                curso.setNome(rs.getString("curso_nome"));

                Professor professor = new Professor();
                professor.setId(rs.getLong("professor_id"));
                professor.setNome(rs.getString("professor_nome"));

                long idTurma = rs.getLong("turma_id");
                String nomeTurma = rs.getString("turma_nome");

                return new Turma(idTurma, nomeTurma, curso, professor);
            }
        }
        throw new RuntimeException("Turma não encontrada");
    }

    public Turma putTurma(Turma turma) throws SQLException {
        String query = """
                UPDATE turma
                SET nome = ?,
                curso_id = ?,
                professor_id = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, turma.getNome());
            stmt.setLong(2, turma.getCurso().getId());
            stmt.setLong(3, turma.getProfessor().getId());
            stmt.setLong(4, turma.getId());
            stmt.executeUpdate();
        }
        return turma;
    }

    public void deletarTurma(long id) throws SQLException {
        String query = "DELETE FROM turma WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}

