/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.repositories;

/**
 *
 * @author Marlene Juliana
 */
import br.unipar.domain.Raca;
import br.unipar.infrastructure.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RacaRepository {
    private static final String INSERT =
            "INSERT INTO raca(ds_raca) VALUES(?)";
    private static final String UPDATE =
            "UPDATE raca SET ds_raca=? WHERE id=?";
    private static final String DELETE =
            "DELETE FROM raca WHERE id=?";
    private static final String FIND_BY_ID =
            "SELECT id, ds_raca FROM raca WHERE id = ?";
    private static final String FIND_ALL =
            "SELECT id, ds_raca FROM raca";

    public Raca insert(Raca raca) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, raca.getDesc());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            rs.next();
            raca.setId(rs.getInt(1));
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
            if (rs != null) rs.close();
        }

        return raca;
    }

    public Raca update(Raca raca) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, raca.getDesc());
            ps.setInt(2, raca.getId());
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return raca;
    }

    public Raca findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Raca retorno = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                retorno = new Raca();
                retorno.setId(rs.getInt("id"));
                retorno.setDesc(rs.getString("ds_raca"));
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return retorno;
    }

    public ArrayList<Raca> findAll() throws SQLException {
        ArrayList<Raca> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Raca raca = new Raca();
                raca.setId(rs.getInt("id"));
                raca.setDesc(rs.getString("ds_raca"));
                retorno.add(raca);
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return retorno;
    }

    public void delete(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.execute();
        } finally {
            if (ps != null) ps.close(); 
            if (conn != null) conn.close();
        }
    }
}
