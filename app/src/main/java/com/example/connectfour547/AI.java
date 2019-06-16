package com.example.connectfour547;


import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

public class AI {

    Board test;
    public AI(Board board) {
        test = new Board(board); //copy constructor called
    }

    public int getMove() {
        return pickBestMove(test);
    }

    private int occurrence(int numbers[],int value){
        int count = 0;
        for(int i=0;i<numbers.length;i++){
            if (numbers[i]==value) count++;
        }
        return count;
    }

    /*
    private int minimax(Board n,int depth,int turn) {
        Board node = new Board(n);
        //check returns 1,0,-1 for player,draw,comp respectively

        if (node.check() == -1) return 100000;  //end nodes
        else if(node.check()==1) return -100000;
        else if(node.check()==0) return 0;
        if(depth==0) return evaluateBoard(node);

        if (turn == -1) //then maximise, comp
        {
            int value = Integer.MIN_VALUE;
            for (int i = 0; i < node.getcols(); i++) {
                if (node.getBoard(0, i) != 0) continue; //if col is full then skip
                node.play(i);
                value = Integer.max(value,minimax(node,depth-1,node.getTurn()));
                node.undo();
            }
            return value;
        }
        else { //then minimise, player
            int value = Integer.MAX_VALUE;
            for (int i = 0; i < node.getcols(); i++) {
                if (node.getBoard(0, i) != 0) continue; //if col is full then skip
                node.play(i);
                value = Integer.min(value, minimax(node, depth - 1, node.getTurn()));
                node.undo();
            }
            return value;
        }
    }
    */

    private int evaluateWindow(int window[]){
        int score=0;
        if (occurrence(window, -1) == 4) score += 100;    //-1 stands for AI Piece and 0 for empty
        else if (occurrence(window, -1) == 3 && occurrence(window, 0) == 1) score += 10;
        else if(occurrence(window,-1) == 2 && occurrence(window,0)==2) score+=5;

        if (occurrence(window, 1) == 3 && occurrence(window, 0) == 1) score -= 80;
        return score;
    }
    private int evaluateBoard(Board node) {
        int score=0;
        int board[][] = node.entireBoard();

        //score center
        int center_array[] = getColumn(board,node.getrows(),node.getcols()/2);
        score += occurrence(center_array,-1)*6;

        //score horizontal
        for(int i=0;i<node.getrows();i++){
            int rowArray[] = board[i];
            for(int c=0;c<node.getcols()-3;c++){
                int window[] = Arrays.copyOfRange(rowArray,c,c+4);
                score+=evaluateWindow(window);
            }
        }

        //score vertical
        for(int i=0;i<node.getcols();i++){
            int colArray[] = getColumn(node.entireBoard(),node.getrows(),i);
            for(int c=0;c<node.getrows()-3;c++) {
                int window[] = Arrays.copyOfRange(colArray, c, c + 4);
                score+=evaluateWindow(window);
            }
        }

        //positive slope diagonals
        for(int r=0;r<node.getrows()-3;r++){
            for(int c=0;c<node.getcols()-3;c++){
                int window[] = getPositiveDiagonal(node.entireBoard(),r,c);
                score+=evaluateWindow(window);
            }
        }

        //negative slope diagonals
        for(int r=0;r<node.getrows()-3;r++){
            for(int c=0;c<node.getcols()-3;c++){
                int window[] = getNegativeDiagonal(node.entireBoard(),r,c);
                score+=evaluateWindow(window);
            }
        }
        return score;
    }

    private int[] getNegativeDiagonal(int[][] entireBoard, int r, int c) {
        int arr[] = new int[4];
        for(int i=0;i<4;i++){
            arr[i]=entireBoard[r+3-i][c+i];
        }
        return arr;
    }

    private int[] getPositiveDiagonal(int[][] entireBoard,int r, int c) {
        int arr[] = new int[4];
        for(int i=0;i<4;i++)
            arr[i]=entireBoard[r+i][c+i];
        return arr;
    }

    private Vector<Integer> getValidMoves(Board node){
        Vector array = new Vector<Integer>();
        for(int i=0;i<node.getcols();i++){
            if(node.getBoard(0,i)==0) array.add(i);
        }
        return array;
    }

    private int pickBestMove(Board node){
        int bestScore=-10000,bestCol;
        Vector array = getValidMoves(node);
        Random random = new Random();
        bestCol=(int)array.get(random.nextInt(array.size()));

        for(int i=0;i<array.size();i++){
            node.play((int)array.get(i));
            int score = evaluateBoard(node);
            if(score>bestScore){
                bestCol=i;
                bestScore=score;
            }
            node.undo();
        }
        return bestCol;
    }

    public  int[] getColumn(int[][] array,int rowSize,int index){
        int[] column = new int[rowSize];
        for(int i=0; i<rowSize; i++){
            column[i] = array[i][index];
        }
        return column;
    }
}
