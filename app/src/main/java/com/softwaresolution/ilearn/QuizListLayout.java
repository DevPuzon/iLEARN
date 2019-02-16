package com.softwaresolution.ilearn;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class QuizListLayout extends ArrayAdapter<String> {
    private Activity context;
    private List<String> quizLists;
    public QuizListLayout(Activity context, List<String> quizLists){
        super(context, R.layout.list_quiz,quizLists);
        this.context = context;
        this.quizLists = quizLists;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listviewitem = inflater.inflate(R.layout.list_quiz,null,true);

        TextView textView_listQuiz = (TextView) listviewitem.findViewById(R.id.textView_listQuiz);

        String quizList = quizLists.get(position);

        textView_listQuiz.setText(quizList);
        return listviewitem;
    }
}
