/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.services;

import br.unipar.domain.Raca;
import br.unipar.exceptions.NegocioException;
import br.unipar.repositories.RacaRepository;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marlene Juliana
 */
public class RacaService {

    public Raca insert(Raca raca) throws SQLException, NegocioException {
        validate(raca);
        RacaRepository racaRepository = new RacaRepository();
        raca = racaRepository.insert(raca);
        return raca;
    }

    public Raca edit(Raca raca) throws SQLException, NegocioException {
        validate(raca);
        validateUpdate(raca);
        RacaRepository racaRepository = new RacaRepository();
        raca = racaRepository.update(raca);
        return raca;
    }

    public Raca findById(int id) throws SQLException {
        RacaRepository racaRepository = new RacaRepository();
        Raca raca = racaRepository.findById(id);
        return raca;
    }

    public ArrayList<Raca> findAll() throws SQLException {
        RacaRepository racaRepository = new RacaRepository();
        ArrayList<Raca> resultado = racaRepository.findAll();
        return resultado;
    }

    private void validate(Raca raca) throws NegocioException {
        if (raca.getDesc() == null) {
            throw new NegocioException("A descrição da Raça deve ser informada.");
        }
        if (raca.getDesc().isBlank()) {
            throw new NegocioException("A descrição da Raça deve ser informada.");
        }
        if (raca.getDesc().length() <= 4) {
            throw new NegocioException("A descrição da Raça deve possuir 4 ou mais caracteres.");
        }
        if (raca.getDesc().length() > 60) {
            throw new NegocioException("A descrição da Raça não deve possuir mais do que 60 caracteres.");
        }
    }

    private void validateUpdate(Raca raca) throws NegocioException {
        if (raca.getId() == 0) {
            throw new NegocioException("Informe um Código Válido para atualização da raça.");
        }
    }

    public void delete(int id) throws SQLException {
        RacaRepository racaRepository = new RacaRepository();
        racaRepository.delete(id);
    }
}  

