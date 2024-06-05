package br.unipar.repositories;

import br.unipar.domain.Cachorro;
import br.unipar.domain.Cor;
import br.unipar.domain.Pelagem;
import br.unipar.infrastructure.ConnectionFactory;
import br.unipar.services.CorService;
import br.unipar.services.PelagemService;

import java.sql.*;
import java.util.ArrayList;

public class CachorroRepository {

    private PelagemService pelagemService = new PelagemService();
    private CorService corService = new CorService();
    private static final String INSERT =
            "INSERT INTO cachorro(nome, vl_tamanho, st_perfume, dt_nascimento, id_raca, id_pelagem, id_cor) VALUES(?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_NOME =
            "UPDATE cachorro SET nome=? WHERE id=?";
    private static final String UPDATE_VL_TAMANHO =
            "UPDATE cachorro SET vl_tamanho=? WHERE id=?";
    private static final String UPDATE_ST_PERFUME =
            "UPDATE cachorro SET st_perfume=? WHERE id=?";
    private static final String UPDATE_DT_NASCIMENTO =
            "UPDATE cachorro SET dt_nascimento=? WHERE id=?";
    private static final String UPDATE_ID_RACA =
            "UPDATE cachorro SET id_raca=? WHERE id=?";
    private static final String UPDATE_ID_PELAGEM =
            "UPDATE cachorro SET id_pelagem=? WHERE id=?";
    private static final String UPDATE_ID_COR =
            "UPDATE cachorro SET id_cor=? WHERE id=?";

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
    public Cachorro updateVlTamanho(Cachorro cachorro) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(UPDATE_VL_TAMANHO);
            ps.setDouble(1, cachorro.getTamanho());
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
    public Cachorro updateStPerfume(Cachorro cachorro) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(UPDATE_ST_PERFUME);
            ps.setBoolean(1, cachorro.isStPerfume());
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
    public Cachorro updateDtNascimento(Cachorro cachorro) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(UPDATE_DT_NASCIMENTO);
            ps.setDate(1, new Date(
                    cachorro.getDtNascimento().getTime()
            ));
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
    public Cachorro updateIdRaca(Cachorro cachorro, int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(UPDATE_ID_RACA);
            ps.setInt(1, id);
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
    public Cachorro updateIdPelagem(Cachorro cachorro, int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(UPDATE_ID_PELAGEM);
            ps.setInt(1, id);
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
    public Cachorro updateIdCor(Cachorro cachorro, int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(UPDATE_ID_COR);
            ps.setInt(1, id);
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


    public Cachorro findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Cachorro retorno = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if(rs.next()) {
                retorno = new Cachorro();
                retorno.setId(rs.getInt("id"));
                retorno.setNome(rs.getString("nome"));
                retorno.setTamanho(rs.getDouble("vl_tamanho"));
                retorno.setStPerfume(rs.getBoolean("st_perfume"));
                retorno.setDtNascimento(rs.getDate("dt_nascimento"));

                Pelagem pelagem = pelagemService.findById(rs.getInt("id_pelagem"));
                retorno.setPelagem(pelagem);

                Cor cor = corService.findById(rs.getInt("id_cor"));
                retorno.setCor(cor);


                //TODO: Consulta no banco de raca, pelagem e cor para ser criado o objeto e então setado no objeto cachorro
                //Aguardando o repositório ser criado
//                retorno.setRaca();

            }
        }finally {
            if (rs != null)
                rs.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }

        return retorno;
    }

    public ArrayList<Cachorro> findAll() throws SQLException {
        ArrayList<Cachorro> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                Cachorro cachorro = new Cachorro();
                cachorro.setId(rs.getInt("id"));
                cachorro.setNome(rs.getString("nome"));
                cachorro.setTamanho(rs.getDouble("vl_tamanho"));
                cachorro.setStPerfume(rs.getBoolean("st_perfume"));
                cachorro.setDtNascimento(rs.getDate("dt_nascimento"));

                Pelagem pelagem = pelagemService.findById(rs.getInt("id_pelagem"));
                cachorro.setPelagem(pelagem);

                Cor cor = corService.findById(rs.getInt("id_cor"));
                cachorro.setCor(cor);
                //TODO: Consulta no banco de raca, pelagem e cor para ser criado o objeto e então setado no objeto cachorro
                //Aguardando o repositório ser criado
//                cachorro.setRaca();

                retorno.add(cachorro);

            }
        }finally {
            if (rs != null)
                rs.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
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
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
    }

}

