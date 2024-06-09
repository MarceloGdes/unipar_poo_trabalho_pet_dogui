package br.unipar;

import br.unipar.domain.Cachorro;
import br.unipar.domain.Cor;
import br.unipar.domain.Pelagem;
import br.unipar.domain.Raca;
import br.unipar.exceptions.NegocioException;
import br.unipar.repositories.CachorroRepository;
import br.unipar.services.CorService;
import br.unipar.services.PelagemService;
import br.unipar.services.RacaService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {

            //Criando cores
            CorService corService = new CorService();
            Cor novaCor = new Cor();
            novaCor.setDesc("Marrom");
            System.out.println("Insert: " + corService.insert(novaCor));

            Cor novaCor2 = new Cor();
            novaCor2.setDesc("Caramelo");
            System.out.println("Insert: " + corService.insert(novaCor2));

            Cor novaCor3 = new Cor();
            novaCor3.setDesc("Preto");
            System.out.println("Insert: " + corService.insert(novaCor3));

            //Listando cores
            System.out.println(corService.findAll());

            //Alterado cor
            Cor corAlterada = corService.findById(1);
            corAlterada.setDesc("Preto");
            System.out.println("Update: " + corAlterada.toString());

            //Deletando Cor
            int idCorDeletada = 2;
            System.out.println("Delete: " + corService.findById(idCorDeletada));
            corService.delete(idCorDeletada);

            //Listando cores
            System.out.println(corService.findAll());


//            ----- RAÇAS ----
            RacaService racaService = new RacaService();

            // INSERINDO NOVAS RAÇAS:

            Raca novaRaca = new Raca();
            novaRaca.setDesc("Pastor Alemão");
            System.out.println("Insert: " + racaService.insert(novaRaca));

            Raca novaRaca2 = new Raca();
            novaRaca2.setDesc("Golden Retriver");
            System.out.println("Insert: " + racaService.insert(novaRaca2));

            Raca novaRaca3 = new Raca();
            novaRaca3.setDesc("Bulldog");
            System.out.println("Insert: " + racaService.insert(novaRaca3));

            // LISTANDO RAÇAS:

            System.out.println("Raças: " + racaService.findAll());

            // ATUALIZANDO UMA RAÇA:

            Raca racaAlterada = racaService.findById(1);
            racaAlterada.setDesc("Yorkshire Terrier");
            racaService.edit(racaAlterada);
            System.out.println("Update: " + racaAlterada.toString());

            // DELETANDO RAÇA:
            int idRacaDeletada = 2;
            Raca racaDeletada = racaService.findById(idRacaDeletada);
            System.out.println("Delete: " + racaDeletada);
            racaService.delete(idRacaDeletada);


            // LISTAGEM DE RAÇA APÓS EXCLUSÃO:
            System.out.println("Raças: " + racaService.findAll());



        }catch (SQLException e){
            System.out.println(e);
        }catch (NegocioException e){
            System.out.println(e);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}