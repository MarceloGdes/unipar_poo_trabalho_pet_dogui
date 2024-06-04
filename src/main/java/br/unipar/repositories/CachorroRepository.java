package br.unipar.repositories;

import br.unipar.domain.Cachorro;
import br.unipar.infrastructure.ConnectionFactory;

import java.sql.*;

public class CachorroRepository {
    private static final String INSERT =
            "INSERT INTO cachorro(nome, vl_tamanho, st_perfume, dt_nascimento, id_raca, id_pelagem, id_cor) VALUES(?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_NOME =
            "UPDATE cachorro SET nome=? WHERE id=?";

    private static final String DELETE =
            "DELETE FROM cachorro WHERE id=?";

    private static final String FIND_BY_ID =
            "SELECT id, nome, vl_tamanho, st_perfume, dt_nascimento, id_raca, id_pelagem, id_cor " +
                    "FROM cachorro WHERE id = ?";
    private static final String FIND_ALL =
            "SELECT id, nome, vl_tamanho, st_perfume, dt_nascimento, id_raca, id_pelagem, id_cor FROM cachorro";


    public Cachorro insert(Cachorro cachorro) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, cachorro.getNome());
            pstmt.setDouble(2, cachorro.getTamanho());
            pstmt.setBoolean(3, cachorro.isStPerfume());
            pstmt.setDate(4, new Date(
                    cachorro.getDtNascimento().getTime()
            ));
            pstmt.setInt(5, cachorro.getRaca().getId());
            pstmt.setInt(6, cachorro.getPelagem().getId());
            pstmt.setInt(7, cachorro.getCor().getId());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            rs.next();

            cachorro.setId(rs.getInt(1));

        }finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
            if (rs != null) rs.close();
        }

        return cachorro;
    }

    public Cachorro updateNome(Cachorro cachorro) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(UPDATE_NOME);
            ps.setString(1, cachorro.getNome());
            ps.setInt(2, cachorro.getId());
            ps.executeUpdate();

        }finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }

        return cachorro;
    }

}

