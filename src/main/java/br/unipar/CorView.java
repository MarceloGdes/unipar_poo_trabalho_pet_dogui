package br.unipar;

import br.unipar.domain.Cor;
import br.unipar.exceptions.NegocioException;
import br.unipar.services.CorService;

import javax.swing.*;
import java.sql.SQLException;

public class CorView {
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
            novaCor2.setDesc("Preto");
            System.out.println("Insert: " + corService.insert(novaCor2));

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

        }catch (SQLException e){
            System.out.println(e);
        }catch (NegocioException e){
            System.out.println(e);
        }catch (Exception e){
            System.out.println(e);
        }


    }
}
