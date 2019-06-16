package com.example.connectfour547;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


//-1 for player -1 for comp
public class SinglePlayer extends Activity {

    Board board;
    GameView gameView;
    ImageView playerTurn;
    int rows,cols;
    MediaPlayer playSound,undoSound,resetSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
        gameView = findViewById(R.id.gameView);
        Intent in = getIntent();
        rows = in.getIntExtra("rows",0);
        cols = in.getIntExtra("cols",0);
        if(rows*cols!=0){
            board = new Board(rows,cols);
            gameView.setSize(rows,cols);
        }
        else board = new Board(gameView.getRows(),gameView.getCols());
        playerTurn = findViewById(R.id.playerTurn);
        playSound = MediaPlayer.create(this,R.raw.play);
        undoSound = MediaPlayer.create(this,R.raw.undo);
        resetSound = MediaPlayer.create(this,R.raw.reset);
    }

    private void exitToWin(int winner) {
        Intent exitToWin = new Intent(this,WinnerScreen.class);
        exitToWin.putExtra("winner",winner);
        exitToWin.putExtra("mode",2);
        if(rows*cols!=0)
        {
            exitToWin.putExtra("rows",rows);
            exitToWin.putExtra("cols",cols);
        }
        startActivity(exitToWin);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX(), y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: break;
            case MotionEvent.ACTION_MOVE: break;
            case MotionEvent.ACTION_UP:
                gameView.setX(x);
                if(board.play(gameView.getPlayed())!=-1)
                {
                    gameView.boardDraw(board);
                    playSound.start();
                    playerTurn.setImageDrawable(getResources().getDrawable(R.drawable.yellow));
                    AI computer = new AI(board);
                    board.play(computer.getMove());
                    gameView.boardDraw(board);
                    playSound.start();
                    playerTurn.setImageDrawable(getResources().getDrawable(R.drawable.red));
                }
                break;
        }
        if(board.check()!=2)exitToWin(board.check()*5);
        return super.onTouchEvent(event);
    }

    public void reset(View view) {
        resetSound.start();
        board.reset();
        gameView.boardDraw(board);
    }

    public void undo(View view) {
        undoSound.start();
        board.undo();  //undo comp move
        board.undo();  //undo player move
        gameView.boardDraw(board);
    }
}
