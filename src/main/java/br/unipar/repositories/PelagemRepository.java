/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.repositories;

import br.unipar.domain.Pelagem;
import br.unipar.infrastructure.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author rgmac
 */

/**
 * Confeir posteriormente:
 * nomes das linhas da tabela.
 */

//Classe responsável pela conexão da classe Pelagem com o banco:
public class PelagemRepository {
    //Criar as contantes em SQL.
    private static final String INSERT=
            "INSERT INTO pelagem(ds_pelagem) VALUES(?)";
    
    private static final String UPDATE=
            "UPDATE pelagem SET ds_pelagem=? WHERE id=?";
    
    private static final String DELETE=
            "DELETE FROM pelagem WHERE id=?";
    
    private static final String FIND_BY_ID=
            "SELECT id, ds_pelagem FROM pelagem WHERE id=?";
    
    private static final String FIND_ALL=
            "SELECT id, ds_pelagem FROM pelagem";
    
    
    //Criação dos métodos para executar as QUERYS.
    
    //Inset.
    public Pelagem insert(Pelagem pelagem) throws SQLException{
        
        //Criar o atributo de conexão.
        Connection conexao = null;
        
        //Criar o atributo de manipulação.
        PreparedStatement prepared = null;
        
        //Criar o atributo de armazenamento.
        ResultSet result = null;
        
        //Iniciar o bloco para captura de erros durante o processo.
        try{
            //Inicializar a conexão.
            conexao = new ConnectionFactory().getConnection();
            
            //Enviar o comando ao banco.
            prepared = conexao.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            
            //Setar o comando enviado.
            prepared.setString(1, pelagem.getDesc());
            
            //Obter o ID gerado:
            result = prepared.getGeneratedKeys();
            
            //Avançar o contador.
            result.next();
            
            //Configurar o objeto para receber os dados.
            pelagem.setId(result.getInt(1));
        
        //Fechar a conexão com o banco.
        }finally{
            if(result != null){
                result.close();
            }
            
            if(prepared != null){
                result.close();
            }
            
            if(conexao != null){
                conexao.close();
            }
        }
        
        return pelagem;
    }
    
    //Update.
    public Pelagem update(Pelagem pelagem) throws SQLException{
        Connection conexao = null;
        PreparedStatement prepared = null;
        
        try{
            conexao = new ConnectionFactory().getConnection();
            prepared = conexao.prepareStatement(UPDATE);
            
            prepared.setString(1, pelagem.getDesc());
            prepared.setInt(1, pelagem.getId());
            prepared.executeUpdate();
        }finally{
            if(conexao != null){
                conexao.close();
            }
            
            if(prepared != null){
                prepared.close();
            }
        }
        
        return pelagem;
    }
    
    
    //Delete.
    public void delete(int id) throws SQLException{
        Connection conexao = null;
        PreparedStatement prepared = null;
        
        try{
            conexao = new ConnectionFactory().getConnection();
            prepared = conexao.prepareStatement(DELETE);
            
            prepared.setInt(1, id);
            prepared.execute();
            
        }finally{
            if(conexao != null){
                conexao.close();
            }
            
            if(prepared != null){
                prepared.close();
            }
        }        
    }
    
    //Procurar um registro.
    public Pelagem findById(int id) throws SQLException{
        Connection conexao = null;
        PreparedStatement prepared = null;
        ResultSet result = null;
        Pelagem retorno = null;
        
        try{
            conexao = new ConnectionFactory().getConnection();
            prepared = conexao.prepareStatement(FIND_BY_ID);
            prepared.setInt(1, id);
            result = prepared.executeQuery();
            
            if(result.next()){
                retorno = new Pelagem();
                retorno.setId(result.getInt("ID"));
                retorno.setDesc(result.getString("ds_pelagem"));
            }
        }finally{
            if(conexao != null){
                conexao.close();
            }
            
            if(prepared != null){
                prepared.close();
            }
            
            if(result != null){
                result.close();
            }
        }
        
        return retorno;
    }
    
    //Listar todos os registros.
    public ArrayList<Pelagem> findAll() throws SQLException{
        ArrayList<Pelagem> listaPelagem = new ArrayList<>();
        
        Connection conexao = null;
        PreparedStatement prepared = null;
        ResultSet result = null;
        
        try{
            conexao = new ConnectionFactory().getConnection();
            prepared = conexao.prepareStatement(FIND_ALL);
            result = prepared.executeQuery();
            
                    
            while(result.next()){
                Pelagem pelagem = new Pelagem();
                
                pelagem.setId(result.getInt("ID"));
                pelagem.setDesc(result.getString("ds_pelagem"));
                
                listaPelagem.add(pelagem);
            }
            
        }finally{
            if(conexao != null){
                conexao.close();
            }
            
            if(prepared != null){
                prepared.close();
            }
            
            if(result != null){
                result.close();
            }
        }        
        return listaPelagem;
    }
    
}
