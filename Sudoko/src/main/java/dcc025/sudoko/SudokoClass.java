/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dcc025.sudoko;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Gabriel
 */
public abstract class SudokoClass {
    private static int[][] board ={
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
        };;
    private static final int GRID_SIZE = 9;
 
    private static final Scanner teclado = new Scanner(System.in);
    
    public SudokoClass(){
        SudokoClass.board = reset();
    }
    
    private int[][] reset(){
        int[][] tabu = {
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
        
        return tabu;
    }
    
    public static void printBoard(){
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

    public static void implementnadoEscolhas(int i){
        switch (i) {
            case 1 -> {
                System.out.println("Digite um valor de casas a serem ocupadas: ");
                int casas_ocupadas = teclado.nextInt();
                teclado.nextLine();
                System.out.println();
                if(casas_ocupadas > 0 && casas_ocupadas < 82)
                {
                geraTabuleiroAleatorio(casas_ocupadas);
                printBoard();
                jogar();
                }
                else
                    System.out.println("Entrada invalida, jogue novamente");
            }
            case 2 -> {
                criarJogo();
            }
            case 3 -> System.exit(0);
            default -> System.exit(0);
        }
    }
    
    public static boolean numeroEmLinha(int valor, int row){
        for(int i = 0; i < GRID_SIZE; i++)
        {   
            if(board[row][i] == valor){
                return true;
             }
        }
        return false;
    }
    public static boolean numeroEmColuna(int valor, int column){
        for(int i = 0; i < GRID_SIZE; i++)
        {   
            if(board[i][column] == valor){
                return true;
             }
        }
        return false;
    }
    public static boolean numeroEmQuadrado(int valor, int column, int row){
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

    public static boolean validamentoPosicao(int valor, int column, int row){
        return  !numeroEmColuna(valor, column) &&
                !numeroEmLinha(valor, row) &&
                !numeroEmQuadrado(valor, column, row);
    }
    
    

    public static boolean geraTabuleiroAleatorio(int n_preenchidos){
         if (n_preenchidos == 0) {
        return true;
    }
    
    Random random = new Random();
    int row = random.nextInt(GRID_SIZE);
    int col = random.nextInt(GRID_SIZE);
    
    while (board[row][col] != 0) {
        row = random.nextInt(GRID_SIZE);
        col = random.nextInt(GRID_SIZE);
    }
    
    for (int value = 1; value <= GRID_SIZE; value++) {
        if (validamentoPosicao(value, col, row)) {
            board[row][col] = value;
            if (geraTabuleiroAleatorio(n_preenchidos - 1)) {
                return true;
            }
            board[row][col] = 0;
        }
    }
    
    return false;
    }
    private static boolean checaAlcance(int valor, int column, int row)
    {
        if(valor >= 1 && valor <= 9 && column >= 0 && column <= 8 && row >= 0 && row <= 8)
            return true;
        else return false;
    }
    private static void insertValue(int valor, int column, int row){
        if(checaAlcance(valor, column, row)){
            if(validamentoPosicao(valor, column, row)){
                board[row][column] = valor;
            }
        }
    }
    
    private static void criarJogo(){
        System.out.println("Digite valores no formato (linha,coluna,valor), para inserir no tabuleiro, para terminar digite 'sair'");
        leValor();
        jogar();
    }
    
    private static void leValor()
    {
        String input;
        input = teclado.nextLine();
        String sair = "sair";
        while(!input.equals(sair))
        {
         
         String [] entradas = input.split("\\)\\(");
         
            for(String entrada : entradas){
                String[] valores = entrada.replace("(", "").replace(")", "").split(",");
                int row = Integer.parseInt(valores[0]);
                int column = Integer.parseInt(valores[1]);
                int valor = Integer.parseInt(valores[2]);
                insertValue(valor, column - 1, row - 1);
            }
            printBoard();
            input = teclado.nextLine().toLowerCase().trim();
       }
    }
    
    private static void removeJogada(int row, int column){
        board[row][column] = 0;
    }
    
    private static boolean checaJogoFinalizado(){
        for(int i = 0; i <GRID_SIZE; i++){
            for(int j = 0; j <GRID_SIZE;j++){
                if(board[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }
    
    private static void jogar()
    {
        System.out.println("Pronto! Agora com o tabuleiro pronto, vamos jogar!!");
        System.out.println("Digite entradas de valores no formate(linha,coluna,valor)");
       
        System.out.println("1)Inserir Jogadas\n2)Remover Jogada\n3)Checar Tabuleiro(Finalizado)\n4)Dica!\n5)Sair");
         int opc = teclado.nextInt();
         teclado.nextLine();
        while(opc != 5){
          
            switch(opc){
                case 1 -> {
                    // Inserir Jogadas
                    String input;
                    System.out.println("Digite as entradas:");
                    input = teclado.nextLine();
                    String [] entradas = input.split("\\)\\(");
         
                    for(String entrada : entradas){
                            String[] valores = entrada.replace("(", "").replace(")", "").split(",");
                            int row = Integer.parseInt(valores[0]);
                            int column = Integer.parseInt(valores[1]);
                            int valor = Integer.parseInt(valores[2]);
                            insertValue(valor, column - 1, row - 1);
                    }
                    printBoard();
                }
                
                case 2 -> {
                    //Remove Jogada
                    System.out.println("Digite dois números no formato (linha ,coluna): ");
                    String input = teclado.nextLine();
                    String[] values = input.replace("(", "").replace(")", "").split(",");
                    int row = Integer.parseInt(values[0]);
                    int column = Integer.parseInt(values[1]);
                    removeJogada(row - 1, column - 1);
                    printBoard();
                }
                
                case 3 -> {
                    // Checa o tabuleiro
                    if(checaJogoFinalizado()){
                        System.out.println("Parabens! Voce venceu");
                    }
                    else
                        System.out.println("Ainda não");
                }
                
                case 4 -> // Dica
                    System.out.println(dicas());
                
                  
            }
            System.out.println();
            System.out.println("1)Inserir Jogadas\n2)Remover Jogada\n3)Checar Tabuleiro(Finalizado)\n4)Dica!\n5)Sair");
            opc = teclado.nextInt();
            teclado.nextLine();
        }
    }
    private static String dicas(){
        for(int i = 0; i <GRID_SIZE; i++){
            for(int j = 0; j <GRID_SIZE;j++){
                if(board[i][j] == 0){
                    for(int numeroTentativa = 1; numeroTentativa <= 9; numeroTentativa++){
                        if(validamentoPosicao(numeroTentativa, j, i)){
                           String dica = "Tente a jogado: (" + (i + 1) + "," + (j+1) + "," + (numeroTentativa) + ")";
                           return dica;
                        }
                    }
                }
            }
        }
        return "Tabueiro ja esta completo ou o Jogo nao eh possivel ser completo";
    }
}


