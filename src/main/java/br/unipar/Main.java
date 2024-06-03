package br.unipar;

import br.unipar.domain.Cor;
import br.unipar.services.CorService;

public class Main {
    public static void main(String[] args) {
        CorService corService = new CorService();

        Cor cor = new Cor();
        cor.setDesc("Vermelho");

        try {
            corService.insert(cor);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}