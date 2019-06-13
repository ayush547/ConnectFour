package com.example.connectfour547;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;



public class MainActivity extends Activity {

    Board board;
    GameView gameView;
    ImageView playerTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameView = findViewById(R.id.gameView);
        board = new Board(gameView.getRows(),gameView.getCols());
        playerTurn = findViewById(R.id.playerTurn);
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
                    gameView.boardDraw(board);
                if(board.getTurn()==1)playerTurn.setImageDrawable(getResources().getDrawable(R.drawable.red));
                else playerTurn.setImageDrawable(getResources().getDrawable(R.drawable.yellow));
                break;
        }
        if(board.check()!=2)exitToWin(board.check());
        return super.onTouchEvent(event);
    }

    private void exitToWin(int winner) {
        Intent exitToWin = new Intent(this,WinnerScreen.class);
        exitToWin.putExtra("winner",winner);
        startActivity(exitToWin);
        finish();
    }

    public void reset(View view) {
        board.reset();
        gameView.boardDraw(board);
    }

    public void undo(View view) {
        board.undo();
        gameView.boardDraw(board);
    }
}
