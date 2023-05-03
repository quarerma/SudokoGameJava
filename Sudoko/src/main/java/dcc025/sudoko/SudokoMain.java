/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dcc025.sudoko;


import java.util.Scanner;

/**
 *
 * @author Gabriel
 */
public class SudokoMain {

    private static final Scanner teclado = new Scanner(System.in);
    
    public static void main(String[] args) {

        System.out.println("Bem vindo ao Sudoku_DCC025");
        System.out.println("Para jogar escolha uma das opcoes abaixo");
        
        do{
            SudokoClass sudoko = new SudokoClass(); 
            sudoko.implementnadoEscolhas(incicio());
    
        }while(jogarNovamente());
        
    }
    public static boolean jogarNovamente(){
        System.out.println("Deseja Jogar Novamente?(Y/N):");
        
        String jogar = teclado.nextLine().toLowerCase().trim();
        
        return jogar.equals("y");
    }
    
    public static int incicio(){
        int escolha;
        System.out.println("1) Gerar Jogo Aleatoio");
        System.out.println("2) Gerar Proprio Jogo");
        System.out.println("3)Encerrar programa");
        System.out.println();
        escolha = teclado.nextInt();
        teclado.nextLine();
        if(escolha < 1 || escolha > 3){
            System.out.println("Numero Invalido, escolha novamente");
            incicio();
        }
    
       return escolha; 
    }

}
    


