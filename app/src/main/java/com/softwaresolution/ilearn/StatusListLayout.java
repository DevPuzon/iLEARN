package com.softwaresolution.ilearn;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StatusListLayout extends ArrayAdapter<StudentScore> {
    Activity context;
    List<StudentScore> studentScoreList;
    public static int mystar;
    public StatusListLayout(Activity context, List<StudentScore> studentScoreList) {
        super(context, R.layout.list_status, studentScoreList);
        this.context = context;
        this.studentScoreList = studentScoreList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View gridviewitem = inflater.inflate(R.layout.list_status,null,true);

        TextView textView_statusQuizNo = (TextView) gridviewitem.findViewById(R.id.textView_statusQuizNo);
        TextView textView_score = (TextView) gridviewitem.findViewById(R.id.textView_score);
        ImageView imageView_star1 = (ImageView) gridviewitem.findViewById(R.id.imageView_star1);
        ImageView imageView_star2 = (ImageView) gridviewitem.findViewById(R.id.imageView_star2);
        ImageView imageView_star3 = (ImageView) gridviewitem.findViewById(R.id.imageView_star3);
        ImageView imageView_star4 = (ImageView) gridviewitem.findViewById(R.id.imageView_star4);
        ImageView imageView_star5 = (ImageView) gridviewitem.findViewById(R.id.imageView_star5);

        StudentScore studentScore = studentScoreList.get(position);

        textView_statusQuizNo.setText(studentScore.getActivityNo());
        int score = 0;
        score = Integer.parseInt(studentScore.getStudentScore());
        mystar = score + mystar;
        textView_score.setText("Score : " + String.valueOf(score));
        if(score >=2){
            imageView_star1.setBackgroundResource(R.drawable.ic_starget);
            Toast.makeText(getContext(),"yes",Toast.LENGTH_LONG).show();
        }
        if(score >= 4){
            imageView_star2.setBackgroundResource(R.drawable.ic_starget);
        }
        if(score >= 6){
            imageView_star3.setBackgroundResource(R.drawable.ic_starget);
        }
        if(score >= 8){
            imageView_star4.setBackgroundResource(R.drawable.ic_starget);
        }
        if(score >= 10){
            imageView_star5.setBackgroundResource(R.drawable.ic_starget);
        }
        return gridviewitem;
    }
}
