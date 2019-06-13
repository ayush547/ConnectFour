package com.example.connectfour547;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class GameView extends View {

    int cols,rows;
    private Paint bgPaint,blankPaint,redPaint,yellowPaint,paint;
    private float height,width,sideL,sideW,radius;
    public float x;
    private int played;
    public Canvas canvas;
    private Board board;

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bgPaint = new Paint();
        blankPaint = new Paint();
        redPaint = new Paint();
        yellowPaint = new Paint();
        TypedArray data = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GameView, 0, 0);
        try {
            rows = data.getInteger(R.styleable.GameView_rows, 6);
            cols = data.getInteger(R.styleable.GameView_cols, 7);
        } finally {
            data.recycle();
        }
        board = new Board(rows,cols);
    }

    @Override
    protected void onDraw(Canvas c){
        this.canvas = c;
        super.onDraw(canvas);

        bgPaint.setColor(Color.parseColor("#3549B8"));
        blankPaint.setColor(Color.parseColor("#687DEC"));
        yellowPaint.setColor(Color.parseColor("#F2F222"));
        redPaint.setColor(Color.parseColor("#EE1717"));

        height = getMeasuredHeight(); width = getMeasuredWidth();
        canvas.drawRect(0,0,width,height,bgPaint);
        sideL = height/rows; sideW = width/cols; radius = sideW/2 -10;

        for(int i=0;i<board.getrows();i++)
            for(int j=0;j<board.getcols();j++)
                draw(i,j,board.getBoard(i,j));

    }

    public void draw(int cy,int cx,int state){
        if(state==0) paint = blankPaint;
        else if(state==1)paint = redPaint;
        else if(state==-1)paint = yellowPaint;
        canvas.drawCircle((2*cx+1)*sideW/2,(2*cy+1)*sideL/2,radius,paint);
    }

    public void boardDraw(Board board){
        this.board = board;
        invalidate();
        requestLayout();
    }

    @Override
    public void setX(float x) {
        this.x = x;
        if(x/sideW>=cols) setPlayed(cols-1);
        else setPlayed((int)(x/sideW));

    }

    protected int getPlayed() {
        return played;
    }

    protected void setPlayed(int played) {
        this.played = played;
    }
}
