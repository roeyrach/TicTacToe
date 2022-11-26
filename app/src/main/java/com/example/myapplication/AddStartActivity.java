package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AddStartActivity extends AppCompatActivity implements View.OnClickListener{

    boolean whoPlays = false; //x's plays is false
    int full;
    ImageView[][] imageViews = new ImageView[3][3];
    String[][] mat = new String[3][3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_start);
        ImageView img = findViewById(R.id.who_is_playing_img);
        img.setImageResource(R.drawable.xplay);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String imgViewId = "imageView_" + i +"_"+ j;
                int resId = getResources().getIdentifier(imgViewId, "id", getPackageName());
                imageViews[i][j] = findViewById(resId);
                imageViews[i][j].setOnClickListener(this);
                mat[i][j] = "";
            }
        }
        full=0;
        rest();
    }

    @Override
    public void onClick(View view) {
        ImageView img = (ImageView) view;
        String[] strings = img.getResources().getResourceEntryName(img.getId()).split("_");
        int i = Integer.parseInt(strings[1]);
        int j = Integer.parseInt(strings[2]);
        if(mat[i][j].equals("")){
            if(whoPlays){
                img.setImageResource(R.drawable.o);
                mat[i][j] = "o";
            }else {
                img.setImageResource(R.drawable.x);
                mat[i][j] = "x";
            }
            full++;
            changeWhoPlays();
        }
        isFinished();
    }
    public void changeWhoPlays(){
        ImageView img = findViewById(R.id.who_is_playing_img);
        if(whoPlays){
            img.setImageResource(R.drawable.xplay);
        }else{
            img.setImageResource(R.drawable.oplay);
        }
        whoPlays = !whoPlays;
    }
    public void isFinished(){
        boolean real_finish = false;
        String finish = "";
        ImageView img = findViewById(R.id.final_image);
        if(!mat[0][0].equals("") && (mat[0][0].equals(mat[1][1]) && mat[0][0].equals(mat[2][2]))){
            img.setImageResource(R.drawable.mark1);
            finish = mat[0][0];
            real_finish = true;
        }
        if(!mat[0][2].equals("") && (mat[0][2].equals(mat[1][1]) && mat[0][2].equals(mat[2][0]))){
            img.setImageResource(R.drawable.mark2);
            finish = mat[0][2];
            real_finish = true;
        }
        if(!mat[0][0].equals("") && (mat[0][0].equals(mat[1][0]) && mat[0][0].equals(mat[2][0]))){
            img.setImageResource(R.drawable.mark3);
            finish = mat[0][0];
            real_finish = true;
        }
        if(!mat[0][1].equals("") && (mat[0][1].equals(mat[1][1]) && mat[0][1].equals(mat[2][1]))){
            img.setImageResource(R.drawable.mark4);
            finish = mat[0][1];
            real_finish = true;
        }
        if(!mat[0][2].equals("") && (mat[0][2].equals(mat[1][2]) && mat[0][2].equals(mat[2][2]))){
            img.setImageResource(R.drawable.mark5);
            finish = mat[0][2];
            real_finish = true;
        }
        if(!mat[0][0].equals("") && (mat[0][0].equals(mat[0][1]) && mat[0][0].equals(mat[0][2]))){
            img.setImageResource(R.drawable.mark3);
            img.setRotation(90);
            finish = mat[0][0];
            real_finish = true;
        }
        if(!mat[1][0].equals("") && (mat[1][0].equals(mat[1][1]) && mat[1][0].equals(mat[1][2]))){
            img.setImageResource(R.drawable.mark4);
            img.setRotation(90);
            finish = mat[1][0];
            real_finish = true;
        }
        if(!mat[2][0].equals("") && (mat[2][0].equals(mat[2][1]) && mat[2][0].equals(mat[2][2]))){
            img.setImageResource(R.drawable.mark5);
            img.setRotation(90);
            finish = mat[2][0];
            real_finish = true;
        }
        if(!finish.equals("")){
            ImageView imageView = findViewById(R.id.who_is_playing_img);
            if(finish.equals("x")){
                imageView.setImageResource(R.drawable.xwin);
            }else{
                imageView.setImageResource(R.drawable.owin);
            }
        }
        if(full == 9){
            ImageView imageView = findViewById(R.id.who_is_playing_img);
            imageView.setImageResource(R.drawable.nowin);
            real_finish = true;
        }
        if(real_finish) {
            Button btn = findViewById(R.id.rest_btn);
            btn.setVisibility(View.VISIBLE);
        }
    }
    public void rest(){
        Button btn = findViewById(R.id.rest_btn);
        btn.setVisibility(Button.GONE);
        btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddStartActivity.class);
            finish();
            startActivity(intent);
            overridePendingTransition(0,0);
        });
    }
}