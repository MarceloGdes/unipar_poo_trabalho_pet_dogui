/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar;

import br.unipar.domain.Pelagem;
import br.unipar.exceptions.NegocioException;
import br.unipar.services.PelagemService;
import java.sql.SQLException;
/**
 *
 * @author rgmac
 */
public class ImplementacaoPelagem {
    public static void main(String[]args){
        try{
            PelagemService pelagemService = new PelagemService();
            
            Pelagem pelagem1 = new Pelagem();
            pelagem1.setDesc("Pelo curto e liso.");
            System.out.println("Insert" + pelagemService.insert(pelagem1));
            
            Pelagem pelagem2 = new Pelagem();
            pelagem2.setDesc("Pelo curto e duro.");
            System.out.println("Insert: " + pelagemService.insert(pelagem2));
            
            Pelagem pelagem3 = new Pelagem();
            pelagem3.setDesc("Pelo longo e liso.");
            System.out.println("Insert: " + pelagemService.insert(pelagem3));
            
            System.out.println("Pelagens: " + pelagemService.findAll());
            
            Pelagem pelagemAlteracao = pelagemService.findById(1);
            pelagemAlteracao.setDesc("Pelo longo ondulado.");
            System.out.println("Update: " + pelagemAlteracao.toString());
            
            int idPelagemDelete = 1;
            System.out.println("Delete: " + pelagemService.findById(idPelagemDelete));
            pelagemService.delete(idPelagemDelete);
            
            System.out.println(pelagemService.findAll());
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(NegocioException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
