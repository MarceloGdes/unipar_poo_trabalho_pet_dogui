package br.unipar;

import br.unipar.domain.Cachorro;
import br.unipar.domain.Cor;
import br.unipar.domain.Pelagem;
import br.unipar.domain.Raca;
import br.unipar.repositories.CachorroRepository;
import br.unipar.services.CorService;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        CachorroRepository cachorroRepository = new CachorroRepository();
        CorService corService = new CorService();

        Cor cor = new Cor();
        cor.setId(1);
        cor.setDesc("Vermelho");

        Pelagem pelagem = new Pelagem();
        pelagem.setId(1);
        pelagem.setDesc("Pelagem");

        Raca raca = new Raca();
        raca.setId(1);

        Cachorro cachorro = new Cachorro();
        cachorro.setId(1);
        cachorro.setCor(cor);
        cachorro.setRaca(raca);
        cachorro.setPelagem(pelagem);
        cachorro.setNome("Teste 2");
        cachorro.setStPerfume(true);
        cachorro.setTamanho(0.9);
        cachorro.setDtNascimento(new Date());

        //Teste Cachorro
        try {
            cachorroRepository.updateNome(cachorro);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}