package com.example.connectfour547;

import java.util.Arrays;
import java.util.Vector;

public class Board {

    private int[][] board;

    public int getTurn() {
        return turn;
    }

    private int rows,cols;
    private int turn=1;
    Vector lastPlayedX,lastPlayedY;

    public int getBoard(int i,int j) {
        return board[i][j];
    }


    public Board(int rows, int cols)
    {
        this.rows =rows;
        this.cols = cols;
        board = new int[rows][cols];
        lastPlayedX = new Vector<Integer>();
        lastPlayedY = new Vector<Integer>();
        for(int i=0;i<rows;i++)
            Arrays.fill(board[i],0);
    }

    private void flipTurn(){
        turn*=-1;
    }

    public int play(int col){
        int i=rows-1;
        while(i!=-1&&board[i][col]!=0)i--;
        if(i!=-1) {
            board[i][col]=turn;
            lastPlayedX.add(i);
            lastPlayedY.add(col);
            flipTurn();
        }
        return i;
    }

    public int getrows() {
        return rows;
    }

    public int getcols() {
        return cols;
    }

    public int check()  { //returns 2 for nothing, 0 for draw, 1,-1 for player 1 wins and player 2 wins respectively

        if(verticalCheck()!=0) return verticalCheck();
        else if(horizontalCheck()!=0) return horizontalCheck();
        else if(diagonalCheck1()!=0) return diagonalCheck1();
        else if(diagonalCheck2()!=0) return diagonalCheck2();
        else if(boardFull()) return 0;
        return 2;
    }

    private boolean boardFull() {
        for(int i=0;i<cols;i++) if(board[0][i]==0)return false;
        return true;
    }

    private int horizontalCheck() {  //returns 0 for nothing 1,-1 for player 1 win,player 2 win
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols - 3; j++) {
                int k = board[i][j];
                if (k != 0 && board[i][j + 1] == k && board[i][j + 2] == k && board[i][j + 3] == k)
                    return k;
            }
        }
        return 0;
    }

    private int verticalCheck() {  //returns 0 for nothing 1,-1 for player 1 win,player 2 win
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows - 3; i++) {
                int k = board[i][j];
                if (k != 0 && board[i + 1][j] == k && board[i + 2][j] == k && board[i + 3][j] == k)
                    return k;
            }
        }
        return 0;
    }

    private int diagonalCheck1() {  //returns 0 for nothing 1,-1 for player 1 win,player 2 win
        for (int i = 3; i < rows; i++) {
            for (int j = 0; j < cols - 3; j++) {
                int k = board[i][j];
                if (k != 0 && board[i - 1][j + 1] == k && board[i - 2][j + 2] == k && board[i - 3][j + 3] == k)
                    return k;
            }
        }
        return 0;
    }

    private int diagonalCheck2() {  //returns 0 for nothing 1,-1 for player 1 win,player 2 win
        for (int i = 3; i < rows; i++) {
            for (int j = 3; j < cols; j++) {
                int k = board[i][j];
                if (k != 0 && board[i - 1][j - 1] == k && board[i - 2][j - 2] == k && board[i - 3][j - 3] == k)
                    return k;
            }
        }
        return 0;
    }

    public void reset() {
        for(int i=0;i<rows;i++)
            Arrays.fill(board[i],0);
        turn=1;
    }

    public void undo() {
        if (lastPlayedX.size()!=0&&lastPlayedY.size()!=0) {
            board[(int) lastPlayedX.lastElement()][(int) lastPlayedY.lastElement()] = 0;
            lastPlayedX.remove(lastPlayedX.size() - 1);
            lastPlayedY.remove(lastPlayedY.size() - 1);
            flipTurn();
        }
    }
}
