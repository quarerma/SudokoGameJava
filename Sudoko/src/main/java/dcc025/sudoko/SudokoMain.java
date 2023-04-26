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
    private static final int GRID_SIZE = 9;
    private static Scanner teclado = new Scanner(System.in);
    
    public static void main(String[] args) {
        int[][] board = resetaBoard();
        printBoard(board);
        
        System.out.println("Bem vindo ao Sudoku_DCC025");
        System.out.println("Para jogar escolha uma das opcoes abaixo");
        
        implementnadoEscolhas(incicio());
        
    }
    
    
    public static int[][] resetaBoard(){
        int[][] board = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
        };
        return board;
    }
    
    public static void printBoard(int[][] board){
        for(int row = 0; row < GRID_SIZE; row++){
            if(row % 3 == 0 && row != 0){
               System.out.println("-------------------");
            }
            for(int column = 0; column < GRID_SIZE; column++){
                if(column % 3 == 0 && column != 0){
                    System.out.print("|");
                }
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
    }

    public static int incicio(){
        int escolha;
        System.out.println("1) Gerar Jogo Aleatoio");
        System.out.println("2) Gerar Proprio Jogo");
        System.out.println("3)Encerrar programa");
        System.out.println();
        escolha = teclado.nextInt();
        if(escolha < 1 || escolha > 3){
            System.out.println("Numero Invalido, escolha novamente");
            incicio();
        }
    
       return escolha; 
    }
    public static void implementnadoEscolhas(int i){
        switch (i) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.exit(0);
        }
    }
    
    public static boolean numeroEmLinha(int[][] board, int valor, int row){
        for(int i = 0; i < GRID_SIZE; i++)
        {   
            if(board[row][i] == valor){
                return true;
             }
        }
        return false;
    }
    public static boolean numeroEmColuna(int[][] board, int valor, int column){
        for(int i = 0; i < GRID_SIZE; i++)
        {   
            if(board[i][column] == valor){
                return true;
             }
        }
        return false;
    }
    public static boolean numeroEmQuadrado(int[][] board, int valor, int column, int row){
        int linhaQuadrado = row - row % 3;
        int colunaQuadrado = column - column % 3;
        
        for(int i = linhaQuadrado; i < linhaQuadrado + 3; i++){
            for(int j = colunaQuadrado; j < colunaQuadrado + 3; j++){
                if(board[i][j] == valor)
                    return true;
            }
        }
        return false;
    }

    public static boolean validamentoPosicao(int[][] board, int valor, int column, int row){
        return  !numeroEmColuna(board, valor, column) &&
                !numeroEmLinha(board, valor, row) &&
                !numeroEmQuadrado(board, valor, column, row);
    }
}
    


