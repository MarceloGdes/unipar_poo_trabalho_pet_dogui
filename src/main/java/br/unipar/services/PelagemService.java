/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.services;

import br.unipar.domain.Pelagem;
import br.unipar.exceptions.NegocioException;
import br.unipar.repositories.PelagemRepository;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rgmac
 */

/**
 * Classe responsável pelas regras de negócio.
 * 
 * Tamanho da descricao (minimo e maximo);
 * Não pode ser null;
 * Não pode conter números;
*/
public class PelagemService {
    
    //Método para validação de regras.
    private void validate(Pelagem pelagem) throws NegocioException{
        if(pelagem.getDesc() == null){
            throw new NegocioException("A descrição deve ser preenchida.");
        }
        
        if(pelagem.getDesc().length() > 60){
            throw new NegocioException("A descrição da pelagem pode possuir até 60 caracteres.");
        }
        
        if(pelagem.getDesc().length() < 3){
            throw new NegocioException("A descrição deve possuir no mínimo 4 caracteres");
        }
        
        if(pelagem.getDesc().isBlank()){
            throw new NegocioException("A descrição deve ser preenchida.");
        }
        
        if (!pelagem.getDesc().matches("[a-zA-Z\\s]+")) { //Expressão regular (regex).
            throw new NegocioException("A descrição deve conter apenas letras e espaços.");
        }
    }
    
    //Validação extra: conferir se o registro realmente existe dentro do banco.
    //Não é usado pelo insert, mas para os demais, sim.
        private void validateUpdate(Pelagem pelagem) throws NegocioException{
            if(pelagem.getId() == 0){
                throw new NegocioException("ID inválido.");
            }
        }
    
        
    //Método para insrir um registro dentro do banco de dados.
    public Pelagem insert(Pelagem pelagem) throws SQLException, NegocioException{
        validate(pelagem);
        
        //Enviar esses dados para a classe Repository.
        PelagemRepository repository = new PelagemRepository();
        pelagem = repository.insert(pelagem);
        
        return pelagem;
    }
    
    
    //Método para atualizar um registro no banco.
    public Pelagem update(Pelagem pelagem) throws SQLException, NegocioException{
        validate(pelagem);
        validateUpdate(pelagem);
        
        PelagemRepository repository = new PelagemRepository();
        pelagem = repository.update(pelagem);
        
        return pelagem;
        
    }  
     
    //Método para deletar um registro dentro do banco de dados:
    public void delete(int id) throws SQLException{
        PelagemRepository repository = new PelagemRepository();
        repository.delete(id);
    }
    
    //Método para encontrar um registro dentro do banco.
    public Pelagem findById(int id) throws SQLException{
        PelagemRepository repository = new PelagemRepository();
        Pelagem pelagem = repository.findById(id);
        
        return pelagem;
    }
    
    //Método para listar todos os registros.
    public ArrayList<Pelagem> findAll(int id) throws SQLException{
        PelagemRepository repository = new PelagemRepository();
        ArrayList<Pelagem> retorno = repository.findAll();
        
        return retorno;
        
    }
    
    
   
}
