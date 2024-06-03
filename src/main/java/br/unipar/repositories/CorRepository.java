package br.unipar.repositories;

import br.unipar.domain.Cor;
import br.unipar.infrastructure.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

//Responsável por todas as querys na tabela de cores
public class CorRepository {
    //Cada valor a ser inserido, coloca-se ?; Posteriormente esta query vai ser passado para o PreparedStatement
    //Constantes com SQLs
    //Static Final -> Imutavel/Constante
    private static final String INSERT =
            "INSERT INTO COR(DS_COR) VALUES(?)";

    private static final String UPDATE =
            "UPDATE cor SET ds_cor=? WHERE id=?";

    private static final String DELETE =
            "DELETE FROM cor WHERE id=?";

    private static final String FIND_BY_ID =
            "SELECT id, ds_cor " +
                    "FROM cor WHERE id = ?";
    private static final String FIND_ALL =
            "SELECT id, ds_cor FROM cor";


    //Insert de uma nova cor
    public Cor insert(Cor cor) throws SQLException {

        Connection conn = null; //Conexão com o banco
        PreparedStatement pstmt = null; //Consulta e Parametros
        ResultSet rs = null; //Resultados da colsulta, com todas as linhas em colunas

        try {

            conn = new ConnectionFactory().getConnection(); //Pego uma conexão de banco
            pstmt = conn.prepareStatement(INSERT,
                    Statement.RETURN_GENERATED_KEYS); //Prepara a execução no banco, recebe a querry, Enum que informa para o metodo retornar a pk gerada
            pstmt.setString(1, cor.getDesc()); //Seta os parametos da consulta (?)

            //executa a query o banco
            pstmt.executeUpdate();

            //Recupera o id gerado pelo banco e joga para o result set
            rs = pstmt.getGeneratedKeys();
            //Ativa o cursor
            rs.next();

            cor.setId(rs.getInt(1)); //Atributo id de cor recebe o id do banco

        } finally {
            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();

            if (rs != null)
                rs.close();
        }

        return cor;
    }
    public Cor update(Cor cor) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, cor.getDesc());
            ps.setInt(2, cor.getId());
            ps.executeUpdate();

        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }

        return cor;
    }

    //Busca uma unica cor pela pk
    public Cor findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Cor retorno = null;

        try {

            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                retorno = new Cor();
                retorno.setId(rs.getInt("ID"));
                retorno.setDesc(rs.getString("DS_COR"));
            }

        } finally {

            if (rs != null)
                rs.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();

        }

        return retorno;

    }
    public ArrayList<Cor> findAll() throws SQLException {

        ArrayList<Cor> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = new ConnectionFactory().getConnection();

            pstmt = conn.prepareStatement(FIND_ALL);

            //Executa a consulta no banco e recupera os resultados
            rs = pstmt.executeQuery();

            while (rs.next()) {

                Cor cor = new Cor();
                cor.setId(rs.getInt("ID"));
                cor.setDesc(rs.getString("DS_COR"));

                retorno.add(cor);

            }

        } finally {

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

