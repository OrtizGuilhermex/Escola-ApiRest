package com.ctw.escola.repository;

import com.ctw.escola.model.Aluno;
import com.ctw.escola.model.Aula;
import com.ctw.escola.model.Nota;
import com.ctw.escola.utils.Conexao;
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

            stmt.setLong(1, nota.getAluno().getId());
            stmt.setLong(2,nota.getAula().getId());
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
                SELECT
                n.id AS nota_id,
                n.valor,
                a.id AS aluno_id, 
                a.nome AS aluno_nome,
                u.id AS aula_id, 
                u.assunto AS aula_assunto, 
                u.data_hora AS aula_data
                FROM nota n
                JOIN aluno a ON n.aluno_id = a.id
                JOIN aula u ON n.aula_id = u.id
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Aluno aluno = new Aluno();
                aluno.setId(rs.getLong("aluno_id"));
                aluno.setNome(rs.getString("aluno_nome"));

                Aula aula = new Aula();
                aula.setId(rs.getLong("aula_id"));
                aula.setAssunto(rs.getString("aula_assunto"));
                aula.setData_hora(rs.getObject("aula_data", LocalDateTime.class));

                long idNota = rs.getLong("nota_id");
                double valor = rs.getDouble("valor");

                Nota nota = new Nota(idNota, aluno, aula, valor);

                notaList.add(nota);
            }
        }
        return notaList;
    }

    public Nota getNotaPorId(long id) throws SQLException{
        String query = """
                SELECT
                n.id AS nota_id,
                n.valor,
                a.id AS aluno_id,
                a.nome AS aluno_nome,
                u.id AS aula_id,
                u.assunto AS aula_assunto,
                u.data_hora AS aula_data
                FROM nota n
                JOIN aluno a ON n.aluno_id = a.id
                JOIN aula u ON n.aula_id = u.id
                WHERE n.id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Aluno aluno = new Aluno();
                aluno.setId(rs.getLong("aluno_id"));
                aluno.setNome(rs.getString("aluno_nome"));

                Aula aula = new Aula();
                aula.setId(rs.getLong("aula_id"));
                aula.setAssunto(rs.getString("aula_assunto"));
                aula.setData_hora(rs.getObject("aula_data", LocalDateTime.class));

                long idNota = rs.getLong("nota_id");
                double valor = rs.getDouble("valor");

                Nota nota = new Nota(idNota, aluno, aula, valor);

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

            stmt.setLong(1, nota.getAluno().getId());
            stmt.setLong(2, nota.getAula().getId());
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
