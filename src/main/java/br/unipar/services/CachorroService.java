package br.unipar.services;

import br.unipar.domain.Cachorro;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import br.unipar.exceptions.NegocioException;
import br.unipar.repositories.CachorroRepository;


//Responsável pela lógica do negócio
public class CachorroService {
    
    //Valida os atributos de cachorro
    private void validate(Cachorro cachorro) throws NegocioException {
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
            throw new NegocioException("O tamanho do cachorro precisa ser menor que 1!");
        }
        
        if(cachorro.getDtNascimento() == null){
            throw new NegocioException("A data deve ser preenchida");
        }
         
        if (cachorro.getDtNascimento().after(new Date())) {
            throw new NegocioException("A data de nascimento não pode ser uma data futura.");
        }   
    }
    
    private void validateUpdate(Cachorro cachorro) throws NegocioException {
        if (cachorro.getId() <= 0) {
            throw new NegocioException("Insira um ID maior que 0! " );
        }
    }

    //Validação para buscar um único cachorro pela pk
    public void validateBusca (int id) throws SQLException, NegocioException{
        if(id <= 0){
            throw new NegocioException("Insira um ID maior que zero" );
        }
        
    }
        
    //Busca um unico cachorro pela pk
    public Cachorro findById(int id) throws SQLException, NegocioException {
        
        validateBusca(id);
        
        CachorroRepository cachorroRepository = new CachorroRepository();

        Cachorro cachorro = cachorroRepository.findById(id);

        if (cachorro == null) {
            throw new NegocioException("Não encontrado registro no banco de dados de referente ao id digitado");
        }
        
        
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
    public ArrayList<Cachorro> findAll() throws SQLException, NegocioException {
        
        CachorroRepository cachorroRepository = new CachorroRepository();
        ArrayList<Cachorro> resultado = cachorroRepository.findAll();
        
        return resultado;
    }
    
      public void delete(int id) throws SQLException, NegocioException {
 
        validateBusca(id);  
          
        CachorroRepository cachorroRepository = new CachorroRepository();
        cachorroRepository.delete(id);
    }
    
}


