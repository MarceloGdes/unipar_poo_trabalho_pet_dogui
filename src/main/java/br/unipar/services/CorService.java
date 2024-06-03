package br.unipar.services;

import br.unipar.domain.Cor;
import br.unipar.exceptions.NegocioException;
import br.unipar.repositories.CorRepository;

import java.sql.SQLException;
import java.util.ArrayList;

//Camada de serviço
public class CorService {
    //Responsavel pela logica de negocio
    //Insert de uma nova cor
    public Cor insert(Cor cor) throws SQLException, NegocioException {

        validate(cor);

        CorRepository corRepository = new CorRepository();
        cor = corRepository.insert(cor);

        return cor;
    }

    //Update de cor
    public Cor edit(Cor cor) throws SQLException, NegocioException {

        validate(cor);
        validateUpdate(cor);

        CorRepository corRepository = new CorRepository();
        cor = corRepository.update(cor);

        return cor;

    }

    //Busca uma unica cor pela pk
    public Cor findById(int id) throws SQLException {

        CorRepository corRepository = new CorRepository();
        Cor cor = corRepository.findById(id);

        return cor;

    }

    //Busca todas as Cores cadastradas no banco
    public ArrayList<Cor> findAll() throws SQLException {

        CorRepository corRepository = new CorRepository();
        ArrayList<Cor> resultado = corRepository.findAll();

        return resultado;
    }

    //Valida os atributos de Cor
    private void validate(Cor cor) throws NegocioException {
        if (cor.getDesc() == null) {
            throw new NegocioException("A descrição da Cor deve ser "
                    + "Informada.");
        }
        if (cor.getDesc().isBlank()) {
            throw new NegocioException("A descrição da Cor "
                    + "deve ser Informada.");
        }
        if (cor.getDesc().length() <= 4) {
            throw new NegocioException("A descrição da Cor "
                    + "deve possuir 4 "
                    + "ou mais caracteres.");
        }
        if (cor.getDesc().length() > 60) {
            throw new NegocioException("A descrição da Cor "
                    + "não deve possuir "
                    + "mais do que 60 caracteres");
        }
    }

    private void validateUpdate(Cor cor) throws NegocioException {
        if (cor.getId() == 0) {
            throw new NegocioException("Informe um Código Válido "
                    + "para atualização da cor");
        }
    }

    public void delete(int id) throws SQLException {
        CorRepository corRepository = new CorRepository();
        corRepository.delete(id);
    }


}
