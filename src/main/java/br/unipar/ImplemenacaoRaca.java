package br.unipar;
import java.sql.SQLException;

public class RacaView {

    public static void main(String[] args) {

    try {

        RacaService racaService = new RacaService();

        // INSERINDO NOVAS RAÇAS:

        Raca novaRaca = new Raca();
        novaRaca.setDesc("Marrom");
        System.out.println("Insert: " + racaService.insert(novaRaca));

        Raca novaRaca2 = new Raca();
        novaRaca2.setDesc("Caramelo");
        System.out.println("Insert: " + racaService.insert(novaRaca2));

        Raca novaRaca3 = new Raca();
        novaRaca3.setDesc("Preto");
        System.out.println("Insert: " + racaService.insert(novaRaca3));

        // LISTANDO RAÇAS:

        System.out.println("All racas: " + racaService.findAll());

        // ATUALIZANDO UMA RAÇA:

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
        System.out.println("All racas after deletion: " + racaService.findAll());

    } catch (SQLException e) {
        System.out.println("SQL Exception: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Exception: " + e.getMessage());
    }
}
}



