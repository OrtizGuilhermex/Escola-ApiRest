package com.ctw.escola.repository;

import com.ctw.escola.model.Aula;
import com.ctw.escola.model.Nota;
import com.ctw.escola.utils.Conexao;
import com.sun.source.tree.BreakTree;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NotaRepository {


    public Nota postNota(Nota nota) throws SQLException {
        String query = """
                INSERT INTO nota(
                aluno_id,
                aula_id,
                valor,
                )
                VALUES
                (?,?,?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS)){

            stmt.setLong(1, nota.getAluno_id());
            stmt.setLong(2,nota.getAula_id());
            stmt.setDouble(3,nota.getValor());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                nota.setId(rs.getInt(1));
                return nota;
            }
        }
        return null;
    }

    public List<Nota> getNotas() throws SQLException{
        List<Nota> notaList = new ArrayList<>();

        String query = """
                SELECT n.id
                ,n.aluno_id
                ,n.aula_id
                ,n.valor
                FROM nota n
                JOIN aluno a ON a.aluno_id = n.id
                JOIN aula u ON u.id = n.aula_id
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                long id = rs.getLong("id");
                long aluno_id = rs.getLong("aluno_id");
                long aula_id = rs.getLong("aula_id");
                double valor = rs.getDouble("valor");
                Nota nota = new Nota(id,aluno_id,aula_id,valor);
                notaList.add(nota);
            }
        }
        return notaList;
    }

    public Nota getNotaPorId(long id) throws SQLException{
        String query = """
                SELECT n.id
                ,n.aluno_id
                ,n.aula_id
                ,n.valor
                FROM nota n
                JOIN aluno a ON a.aluno_id = n.id
                JOIN aula u ON u.id = n.aula_id
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                long nota_id = rs.getLong("id");
                long aluno_id = rs.getLong("aluno_id");
                long aula_id = rs.getLong("aula_id");
                double valor = rs.getDouble("valor");
                Nota nota = new Nota(nota_id,aluno_id,aula_id,valor);
                return nota;
            }
        }
        throw new RuntimeException("Nota não encontrado");
    }

    public Nota putAluno(Nota nota) throws SQLException{
        String query = """
                UPDATE nota
                set aluno_id = ?,
                aula_id = ?,
                valor = ?
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1, nota.getAluno_id());
            stmt.setLong(2, nota.getAula_id());
            stmt.setDouble(3,nota.getValor());
            stmt.executeUpdate();
        }
        return nota;
    }

    public void deletarNota(long id)throws SQLException{
        String query = """
                DELETE FROM nota
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
