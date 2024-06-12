package br.unipar;

import br.unipar.domain.Cachorro;
import br.unipar.domain.Cor;
import br.unipar.domain.Pelagem;
import br.unipar.domain.Raca;
import br.unipar.exceptions.NegocioException;
import br.unipar.repositories.CachorroRepository;
import br.unipar.services.CachorroService;
import br.unipar.services.CorService;
import br.unipar.services.PelagemService;
import br.unipar.services.RacaService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {

            //Criando cores
            CorService corService = new CorService();
            Cor novaCor = new Cor();
            novaCor.setDesc("Marrom");
            System.out.println("\nInsert: " + corService.insert(novaCor));

            Cor novaCor2 = new Cor();
            novaCor2.setDesc("Caramelo");
            System.out.println("\nInsert: " + corService.insert(novaCor2));

            Cor novaCor3 = new Cor();
            novaCor3.setDesc("Preto");
            System.out.println("\nInsert: " + corService.insert(novaCor3));

            //Listando cores
            System.out.println(corService.findAll());

            //Alterado cor
            Cor corAlterada = corService.findById(1);
            corAlterada.setDesc("Preto");
            System.out.println("\nUpdate: " + corService.edit(corAlterada));

            //Deletando Cor
            int idCorDeletada = 2;
            System.out.println("\nDelete: " + corService.findById(idCorDeletada));
            corService.delete(idCorDeletada);

            //Listando cores
            System.out.println(corService.findAll());


//            ----- RAÇAS ----
            RacaService racaService = new RacaService();

            // INSERINDO NOVAS RAÇAS:

            Raca novaRaca = new Raca();
            novaRaca.setDesc("Pastor Alemão");
            System.out.println("\nInsert: " + racaService.insert(novaRaca));

            Raca novaRaca2 = new Raca();
            novaRaca2.setDesc("Golden Retriver");
            System.out.println("\nInsert: " + racaService.insert(novaRaca2));

            Raca novaRaca3 = new Raca();
            novaRaca3.setDesc("Bulldog");
            System.out.println("\nInsert: " + racaService.insert(novaRaca3));

            // LISTANDO RAÇAS:

            System.out.println("\nRaças: " + racaService.findAll());

            // ATUALIZANDO UMA RAÇA:

            Raca racaAlterada = racaService.findById(1);
            racaAlterada.setDesc("Yorkshire Terrier");
            System.out.println("\nUpdate: " + racaService.edit(racaAlterada));

            // DELETANDO RAÇA:
            int idRacaDeletada = 2;
            Raca racaDeletada = racaService.findById(idRacaDeletada);
            System.out.println("\nDelete: " + racaDeletada);
            racaService.delete(idRacaDeletada);


            // LISTAGEM DE RAÇA APÓS EXCLUSÃO:
            System.out.println("\nRaças: " + racaService.findAll());



            //Pelagem:
            PelagemService pelagemService = new PelagemService();
            
            Pelagem pelagem1 = new Pelagem();
            pelagem1.setDesc("Pelo curto e liso");
            System.out.println("\nInsert" + pelagemService.insert(pelagem1));
            
            Pelagem pelagem2 = new Pelagem();
            pelagem2.setDesc("Pelo curto e ondulado");
            System.out.println("\nInsert: " + pelagemService.insert(pelagem2));
            
            Pelagem pelagem3 = new Pelagem();
            pelagem3.setDesc("Pelo longo e liso");
            System.out.println("\nInsert: " + pelagemService.insert(pelagem3));
            
            System.out.println("\nPelagens: " + pelagemService.findAll());
            
            Pelagem pelagemAlteracao = pelagemService.findById(1);
            pelagemAlteracao.setDesc("Pelo longo ondulado");
            System.out.println("\nUpdate: " + pelagemService.update(pelagemAlteracao));
            
            int idPelagemDelete = 2;
            System.out.println("\nDelete: " + pelagemService.findById(idPelagemDelete));
            pelagemService.delete(idPelagemDelete);
            
            System.out.println(pelagemService.findAll());


            Cachorro novoCachorro = new Cachorro();
            CachorroService cachorroService = new CachorroService();

            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.YEAR, 2019);
            calendar.set(Calendar.MONTH, Calendar.MARCH);
            calendar.set(Calendar.DAY_OF_MONTH, 14);
            novoCachorro.setNome("Rex");
            novoCachorro.setCor(corService.findById(1));
            novoCachorro.setTamanho(0.6);
            novoCachorro.setPelagem(pelagemService.findById(1));
            novoCachorro.setStPerfume(false);
            novoCachorro.setRaca(racaService.findById(1));
            novoCachorro.setDtNascimento(calendar.getTime());
            System.out.println("\nInsert Cachorro: " + cachorroService.insert(novoCachorro));

            Cachorro novoCachorro2 = new Cachorro();
            calendar.set(Calendar.YEAR, 2020);
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            calendar.set(Calendar.DAY_OF_MONTH, 20);
            novoCachorro2.setNome("Luna");
            novoCachorro2.setCor(corService.findById(3));
            novoCachorro2.setTamanho(0.7);
            novoCachorro2.setPelagem(pelagemService.findById(3));
            novoCachorro2.setStPerfume(true);
            novoCachorro2.setDtNascimento(calendar.getTime());
            novoCachorro2.setRaca(racaService.findById(3));
            System.out.println("\nInserir Cachorro: " + cachorroService.insert(novoCachorro2));

            Cachorro novoCachorro3 = new Cachorro();
            calendar.set(Calendar.YEAR, 2023);
            calendar.set(Calendar.MONTH, Calendar.JULY);
            calendar.set(Calendar.DAY_OF_MONTH, 10);
            novoCachorro3.setNome("Thor");
            novoCachorro3.setCor(corService.findById(1));
            novoCachorro3.setTamanho(0.3);
            novoCachorro3.setPelagem(pelagemService.findById(3));
            novoCachorro3.setStPerfume(false);
            novoCachorro3.setDtNascimento(calendar.getTime());
            novoCachorro3.setRaca(racaService.findById(3));
            System.out.println("\nInserir Cachorro: " + cachorroService.insert(novoCachorro3));

            // Listando cachorros

            System.out.println("\nTodos cachorros: " + cachorroService.findAll());

            // Atualizando um cachorro

            Cachorro cachorroAlterado = cachorroService.findById(1);
            cachorroAlterado.setNome("Max");
            System.out.println("\nUpdate: " + cachorroService.edit(cachorroAlterado));

            // Deletando cachorro
            int idCachorroDeletado = 2;
            Cachorro cachorroDeletado = cachorroService.findById(idCachorroDeletado);

            System.out.println("\nDelete: " + cachorroDeletado);
            cachorroService.delete(idCachorroDeletado);


            // Listagem de cachorros após exclusão

            System.out.println("\nTodos os cachorros após exclusão: " + cachorroService.findAll());


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }catch (NegocioException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
