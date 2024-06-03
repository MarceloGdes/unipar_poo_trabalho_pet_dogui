package br.unipar.domain;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cachorro {
    private int id;
    private String nome;
    private Raca raca;
    private Pelagem pelagem;
    private Cor cor;
    private Double tamanho;
    private boolean stPerfume;
    private Date dtNascimento;
}
