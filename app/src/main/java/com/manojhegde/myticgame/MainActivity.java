package com.manojhegde.myticgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int Player = 0;//0 is O and 1 is X
    int[] Positions = {2, 2, 2, 2, 2, 2, 2, 2, 2};//2 indicates that position not played
    int[][] WinningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};//All the winning positions
    boolean gameState = true;


    public void onCheck(View view) {

        ImageView counter = (ImageView) view;
        int clickedTag = Integer.parseInt(counter.getTag().toString());
        if (Positions[clickedTag] == 2 && gameState) {
            Positions[clickedTag] = Player;
            System.out.println("\nPositions: " + Positions);
            if (Player == 0) {
                System.out.println("\nClicked O!!! @ tag:" + clickedTag);
                counter.setImageResource(R.drawable.o_new);
                Player = 1;
            } else {
                System.out.println("\nClicked X!!! @ tag:" + clickedTag);
                counter.setImageResource(R.drawable.x_new);
//                Positions[clickedTag]=Player;
                Player = 0;
            }

            for (int[] winningPosition : WinningPositions) {
                if (Positions[winningPosition[0]] == Positions[winningPosition[1]] &&
                        Positions[winningPosition[1]] == Positions[winningPosition[2]] &&
                        Positions[winningPosition[0]] != 2) {
                    //Someone has won
                    gameState = false;
                    String Winner = "O";
                    System.out.println("Winner: " + Positions[winningPosition[0]]);
                    if (Positions[winningPosition[0]] == 0) {
                        System.out.println("O Player has won");
                        Winner = "O";
                    }
                    if (Positions[winningPosition[0]] == 1) {
                        System.out.println("X Player has won");
                        Winner = "X";
                    }

                    TextView winnerText = (TextView) findViewById(R.id.winnerText);
                    winnerText.setText(Winner + " has won.");
                    LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerMessageLayout);
                    winnerLayout.setVisibility(View.VISIBLE);

                } else {
                    boolean drawStatus=true;
                    for (int i = 0; i < Positions.length; i++) {
                        if(Positions[i]==2){
                            drawStatus=false;
                        }
                    }
                    if(drawStatus){
                        TextView winnerText = (TextView) findViewById(R.id.winnerText);
                        winnerText.setText("Great, It was a tough match");
                        LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerMessageLayout);
                        winnerLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }

}

    public void playAgainFunction(View view){
        gameState=true;
        LinearLayout winnerLayout=(LinearLayout)findViewById(R.id.winnerMessageLayout);
        winnerLayout.setVisibility(View.INVISIBLE);

        Player=0;//0 is O and 1 is X
        for(int i=0;i<Positions.length;i++){
            Positions[i]=2;
        }

        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridView);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    public void testFunction(View view){
        System.out.println("Calling test function");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
