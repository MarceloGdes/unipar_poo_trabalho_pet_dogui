package br.unipar;

import br.unipar.domain.Cachorro;
import br.unipar.domain.Cor;
import br.unipar.domain.Pelagem;
import br.unipar.domain.Raca;
import br.unipar.exceptions.NegocioException;
import br.unipar.services.CachorroService;
import br.unipar.services.PelagemService;
import br.unipar.services.RacaService;
import br.unipar.services.CorService;
import java.sql.SQLException;
import java.util.Calendar;

public class ImplementacaoCachorro {
    public static void main(String[] args) throws SQLException, NegocioException {
        CorService corService = new CorService();
        RacaService racaService = new RacaService();
        CachorroService cachorroService = new CachorroService();
        PelagemService pelagemService = new PelagemService();


             //  RAÇAS
        Raca novaRaca = new Raca();
        novaRaca.setDesc("Golden Retriever");
        System.out.println("Inserir Raça: " + racaService.insert(novaRaca));

        Raca novaRaca2 = new Raca();
        novaRaca2.setDesc("Pastor Alemão");
        System.out.println("Inserir Raça: " + racaService.insert(novaRaca2));

        Raca novaRaca3 = new Raca();
        novaRaca3.setDesc("Bulldog");
        System.out.println("Inserir Raça: " + racaService.insert(novaRaca3));

                    //CORES
        Cor novaCor = new Cor();
        novaCor.setDesc("Preto");
        System.out.println("Inserir cor: " + corService.insert(novaCor));

        Cor novaCor2 = new Cor();
        novaCor.setDesc("Caramelo");
        System.out.println("Inserir cor: " + corService.insert(novaCor2));

        Cor novaCor3 = new Cor();
        novaCor.setDesc("Marrom");
        System.out.println("Inserir cor: " + corService.insert(novaCor3));

                    //PELAGEM
        Pelagem novaPelagem = new Pelagem();
        novaPelagem.setDesc("Pelo curto e liso");
        System.out.println("Inserir pelagem: " + pelagemService.insert(novaPelagem));

        Pelagem novaPelagem2 = new Pelagem();
        novaPelagem.setDesc("Pelo curto e duro");
        System.out.println("Inserir pelagem: " + pelagemService.insert(novaPelagem2));

        Pelagem novaPelagem3 = new Pelagem();
        novaPelagem.setDesc("Pelo longo e liso");
        System.out.println("Inserir pelagem: " + pelagemService.insert(novaPelagem3));




        // Inseridno novo cachorro
        Cachorro novoCachorro = new Cachorro();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, Calendar.MARCH);
        calendar.set(Calendar.DAY_OF_MONTH, 14);
        novoCachorro.setNome("Rex");
        novoCachorro.setCor(novaCor);
        novoCachorro.setTamanho(0.60);
        novoCachorro.setPelagem(novaPelagem);
        novoCachorro.setStPerfume(false);
        novoCachorro.setIdade(5);
        novoCachorro.setRaca(novaRaca);
        System.out.println("Insert Cachorro: " + cachorroService.insert(novoCachorro));

        Cachorro novoCachorro2 = new Cachorro();
        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 20);
        novoCachorro2.setNome("Luna");
        novoCachorro2.setCor(novaCor2);
        novoCachorro2.setTamanho(0.70);
        novoCachorro2.setPelagem(novaPelagem2);
        novoCachorro2.setStPerfume(true);
        novoCachorro2.setIdade(4);
        novoCachorro2.setRaca(novaRaca2);
        System.out.println("Inserir Cachorro: " + cachorroService.insert(novoCachorro2));

        Cachorro novoCachorro3 = new Cachorro();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.JULY);
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        novoCachorro3.setNome("thor");
        novoCachorro3.setCor(novaCor3);
        novoCachorro3.setTamanho(0.30);
        novoCachorro3.setPelagem(novaPelagem3);
        novoCachorro3.setStPerfume(false);
        novoCachorro3.setIdade(1);
        novoCachorro3.setRaca(novaRaca3);
        System.out.println("Inserir Cachorro: " + cachorroService.insert(novoCachorro2));

        // Listando cachorros
        
        System.out.println("Todos cachorros: " + cachorroService.findAll());

        // Atualizando um cachorro
        
        Cachorro cachorroAlterado = cachorroService.findById(1);
        if (cachorroAlterado != null) {
            cachorroAlterado.setNome("Max");
            cachorroService.update(cachorroAlterado);
            System.out.println("Update: " + cachorroAlterado.toString());
        }

        // Deletando cachorro
        int idCachorroDeletado = 2;
        Cachorro cachorroDeletado = cachorroService.findById(idCachorroDeletado);
        if (cachorroDeletado != null) {
            System.out.println("Delete: " + cachorroDeletado);
            cachorroService.delete(idCachorroDeletado);
        }

        // Listagem de cachorros após exclusão

        System.out.println("Todos os cachorros após exclusão: " + cachorroService.findAll());

    } catch (SQLException e) {
        System.out.println("SQL Exception: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Exception: " + e.getMessage());
    }
}
