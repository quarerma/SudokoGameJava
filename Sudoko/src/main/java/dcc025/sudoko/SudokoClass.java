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
public class SudokoClass {
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
    private static int[][] matrizBase = {
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
    private static final int GRID_SIZE = 9; // tamanho da lateral do tabuleiro
 
    private static final Scanner teclado = new Scanner(System.in);
    
    public SudokoClass(){
        int[][] tabu ={
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
        board = tabu; //Reinicia o Objeto caso o usuario queira jogar novamente
        matrizBase = tabu;
    }
    

    
    public static void printBoard(){
        for(int row = 0; row < GRID_SIZE; row++){
            if(row % 3 == 0 && row != 0){ // a cada 3 linhas imprime | para dividir os quadrados
               System.out.println("-------------------");
            }
            for(int column = 0; column < GRID_SIZE; column++){
                if(column % 3 == 0 && column != 0){ // a cada 3 colunas imrpime uma divisória para os quadrados
                    System.out.print("|");
                }
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
    }

    public static void implementnadoEscolhas(int i){
        switch (i) {
            case 1 -> { // Caso o usuário queira um jogo aleatorio
                int [][] matAleatoria = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
                };
                
                matrizBase = matAleatoria;
                
                System.out.println("Digite um valor de casas a serem ocupadas: ");
                int casas_ocupadas = teclado.nextInt();
                teclado.nextLine();
                System.out.println();
                if(casas_ocupadas >= 0 && casas_ocupadas < 81) // checa se o valor está dentro das casas possiveis do tabuleiro
                {
                geraTabuleiroAleatorio(81 - casas_ocupadas);
                printBoard();
                jogar();
                }
                else //caso contrario o jogo reinicia e o usuario é jogado para a tela de fim de jogo
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
        //percorre cada linha passada como parametro retornando True se o valor já estiver presente
        for(int i = 0; i < GRID_SIZE; i++)
        {   
            if(board[row][i] == valor){
                return true;
             }
        }
        return false;
    }
    public static boolean numeroEmColuna(int valor, int column){
        //percorre cada coluna passada como parametro retornando True se o valor já estiver presente
        for(int i = 0; i < GRID_SIZE; i++)
        {   
            if(board[i][column] == valor){
                return true;
             }
        }
        return false;
    }
    public static boolean numeroEmQuadrado(int valor, int column, int row){
        //percorre cada quadrado retornando True se o valor já estiver presente
        int linhaQuadrado = row - row % 3; // variavel que armazena a primeira linha do quadrado
        int colunaQuadrado = column - column % 3; // variavel que armazena a primeira coluna do quadrado
        
        for(int i = linhaQuadrado; i < linhaQuadrado + 3; i++){ // percorre até um espaço de 3 adiante do quadrado ao inves da linha inteira
            for(int j = colunaQuadrado; j < colunaQuadrado + 3; j++){ // percorre a colunainicial + 3 unidades formando um quadrado 3x3
                if(board[i][j] == valor)
                    return true;
            }
        }
        return false;
    }

    public static boolean validamentoPosicao(int valor, int column, int row){
        //função que resume todas as verificações retornando true caso TODAS retornem false
        return  !numeroEmColuna(valor, column) &&
                !numeroEmLinha(valor, row) &&
                !numeroEmQuadrado(valor, column, row);
    }
    
    

    public static void geraTabuleiroAleatorio(int casasParaRemover){
        
        
        Random rand = new Random();
        int count = 0;
        
        while (count < casasParaRemover) { // Busca valores aleatoriamente para remover
        int row = rand.nextInt(GRID_SIZE);
        int column = rand.nextInt(GRID_SIZE);
        if (board[row][column] != 0) {
            matrizBase[row][column] = 0;
            count++;
        }
            board = matrizBase;
        }
    }
   
    private static boolean checaAlcance(int valor, int column, int row)
    {
        //função para checar se os parametros do usuario estão no padrão do jogo
        
        if(valor >= 1 && valor <= 9 && column >= 0 && column <= 8 && row >= 0 && row <= 8)
            return true;
        else return false;
    }
    private static void insertValue(int valor, int column, int row){
        //Inserção de um valor do usuario checando se é possivel a inserção
        if(checaAlcance(valor, column, row)){
            if(validamentoPosicao(valor, column, row)){
                if(board[row][column] == 0)
                board[row][column] = valor;
                 else 
                System.out.println("Valores invalidos");
                    
            }
             else 
            System.out.println("Valores invalidos");
        }
        else 
            System.out.println("Valores invalidos");
    }
    
    private static void criarJogo(){
        //função utilizada para o usuario criar seu proprio jogo
        System.out.println("Digite valores no formato (linha,coluna,valor), para inserir no tabuleiro, para terminar digite 'sair'");
        leValor();
        matrizBase = board;
        jogar();
    }
    
    private static void leValor()
    {
        String input;
        input = teclado.nextLine();
        String sair = "sair";
        while(!input.equals(sair))
        {
         
         String [] entradas = input.split("\\)\\("); // separa a String em um vetor de Strings com criterio se separação os char ')' e '('
         
            for(String entrada : entradas){
                String[] valores = entrada.replace("(", "").replace(")", "").split(",");
                //em cada String do vetro de String separa os valores entre vírgulas ignorando os parenteses e armazenando cada valor a cada virgula em 1 outro vetor
                // agora convertendo os valores de String para Integer
                int row = Integer.parseInt(valores[0]);
                int column = Integer.parseInt(valores[1]);
                int valor = Integer.parseInt(valores[2]);
                insertValue(valor, column - 1, row - 1); // column e row são passados com -1 para que o usuario não precise converter
            }
            printBoard();
            input = teclado.nextLine().toLowerCase().trim();
       }
    }
    
    private static void removeJogada(int row, int column){
        if(matrizBase[row][column] == 0)
        board[row][column] = 0;
        else
            System.out.println("Valor pre-estabelecido");
    }
    
    private static boolean checaJogoFinalizado(){
        for(int i = 0; i <GRID_SIZE; i++){
            for(int j = 0; j <GRID_SIZE;j++){
                if(board[i][j] == 0){ // se encontrar algum 0 retorna false pois o jogo já está completo
                    return false;
                }
            }
        }
        return true;
    }
    
    private static void jogar()
    {
        System.out.println("Pronto! Agora com o tabuleiro pronto, vamos jogar!!");
        System.out.println("Digite entradas de valores no formato(linha,coluna,valor)");
       
        int opc;
        do{
            System.out.println("1)Inserir Jogadas\n2)Remover Jogada\n3)Checar Tabuleiro\n4)Dica!\n5)Sair");
            opc = teclado.nextInt();
            teclado.nextLine();
            
            switch(opc){
                case 1 -> {
                    // Inserir Jogadas
                    //Processamento parecido com a função leValor() porém sem o while()
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
                    //Processamento parecido com a função leValor() porém sem o while
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
                    if(!checaJogoFinalizado()){
                        System.out.println("Ainda não"); // Caso o tabuleiro ainda possua Zeros
                    }
                }
                
                case 4 -> // Dica
                {
                    if(!dicas()){
                        opc = 5;
                    }
                }
 
            }
            if(checaJogoFinalizado()){
                System.out.println("Parabens! Voce venceu");
                opc = 5;
            }
        }while(opc != 5);
    
    }
    
    private static boolean dicas(){
        //Percorre o tabuleiro procurando espaços vazios e testando possiveis valores nele retornando uma String para que o usuariio insira a jogada possível
        for(int i = 0; i <GRID_SIZE; i++){
            for(int j = 0; j <GRID_SIZE;j++){
                if(board[i][j] == 0){
                    for(int numeroTentativa = 1; numeroTentativa <= 9; numeroTentativa++){
                        if(validamentoPosicao(numeroTentativa, j, i)){
                           System.out.println("Tente a jogado: (" + (i + 1) + "," + (j+1) + "," + (numeroTentativa) + ")");
                           return true;
                        }
                    }
                }
            }
        }
        System.out.println("Tabueiro ja esta completo ou o Jogo nao eh possivel ser completo");// Caso o Tabuleiro já esteja completo ou Seja um tabuleiro Impossível de ser resolvido
        return false;
    }
}


