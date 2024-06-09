package br.unipar;
import java.sql.SQLException;

public class RacaView {

    public static void main(String[] args) {

        try {
    
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
                System.out.println(e.getMessage());
        }catch (NegocioException e){
                System.out.println(e.getMessage());
        }catch (Exception e){
                System.out.println(e.getMessage());
        }
    }
}



