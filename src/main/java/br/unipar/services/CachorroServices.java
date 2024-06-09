/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema.cachorro.service;

import Domain.Cachorro;
import exceptions.NegocioException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import repositorie.CachorroRepository;
import repositorie.CorRepository;


//Responsável pela lógica do negócio
public class CachorroServices {
    
    //Valida os atributos de cachorro
    private void validate(Cachorro cachorro) throws NegocioException{
        if(cachorro.getNome() == null){
            throw new NegocioException("O nome deve ser preenchido");
        }
        
        if(cachorro.getNome().length() > 60){
            throw new NegocioException("O nome do cachorro deve possuir até 60 caracteres.");
        }
        
        if(cachorro.getNome().length() < 3){
            throw new NegocioException("O nome deve possuir no mínimo 4 caracteres");
        }
        
        if(cachorro.getNome().isBlank()){
            throw new NegocioException("O nome do cachorro deve ser preenchido");
        }
        
        if (!cachorro.getNome().matches("[a-zA-Z\\s]+")){
            throw new NegocioException("A descrição deve conter apenas letras e espaços.");
        }
        
        if(cachorro.getTamanho()== null){
            throw new NegocioException("O tamanho do cachorro deve ser preenchido");
        }
        
        if(cachorro.getTamanho() < 0.1){
            throw new NegocioException("O tamanho do cachorro precisa ser maior que 0!");
        }
        
        if(cachorro.getTamanho() < 0.99){
            throw new NegocioException("O tamanho do cachorro precisa ser maior que 1!");
        }
        
        if(cachorro.getDtNascimento() == null){
            throw new NegocioException("A data deve ser preenchida");
        }
         
        if (cachorro.getDtNascimento().after(new Date())) {
            throw new NegocioException("A data de nascimento não pode ser uma data futura.");
        }   
    }
    
    private void validateUpdate(Cachorro cachorro) throws NegocioException {
        if (cachorro.getId()== 0) {
            throw new NegocioException("Informe um Código Válido "
                    + "para atualização do cachorro");
        }
    }

    //Busca um unico cachorro pela pk
    public Cachorro findById(int id) throws SQLException {
        
        CachorroRepository cachorroRepository = new CachorroRepository();
        Cachorro cachorro = cachorroRepository.findById(id);
        
        return cachorro;
        
    }

    //Inserir um novo cachorro 
    public Cachorro insert(Cachorro cachorro) throws SQLException, NegocioException {
        
        validate(cachorro);
        
        CachorroRepository cachorroRepository = new CachorroRepository();
        cachorro = cachorroRepository.insert(cachorro);
        
        return cachorro;
    }
     
    //Update de cachorro 
    public Cachorro edit(Cachorro cachorro) throws SQLException, NegocioException {
        
        validate(cachorro);
        validateUpdate(cachorro);
        
        CachorroRepository cachorroRepository = new CachorroRepository();
        cachorro = cachorroRepository.update(cachorro);
        
        return cachorro; 
    }
    
        
    //Buscar todos os cachorros cadastrados no banco
    public ArrayList<Cachorro> findAll() throws SQLException {
        
        CachorroRepository cachorroRepository = new CachorroRepository();
        ArrayList<Cachorro> resultado = cachorroRepository.findAll();
        
        return resultado;
    }
    
      public void delete(int id) throws SQLException {
        CachorroRepository cachorroRepository = new CachorroRepository();
        cachorroRepository.delete(id);
    }
    
}


