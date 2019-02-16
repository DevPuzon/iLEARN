package com.softwaresolution.ilearn;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeacherActivity extends Fragment implements View.OnClickListener{
    View v;
    private TextView textView_quizno;
    private EditText editText_question, editText_a,editText_b,editText_c,editText_d,editText_answer;
    private Button button_next;
    String getTeacherName = TeacherGetSetData.teacherName;
    DatabaseReference databaseReference;
    DatabaseReference databaseReferenceQuiz;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.teacher_activity, container, false);
        GetQuizNumber();
        textView_quizno = (TextView) v.findViewById(R.id.textview_quizno);
        editText_question = (EditText) v.findViewById(R.id.edittxt_question);
        editText_a = (EditText) v.findViewById(R.id.edittxt_a);
        editText_b = (EditText) v.findViewById(R.id.edittxt_b);
        editText_c = (EditText) v.findViewById(R.id.edittxt_c);
        editText_d = (EditText) v.findViewById(R.id.edittxt_d);
        editText_answer = (EditText) v.findViewById(R.id.edittxt_correct);
        button_next = (Button) v.findViewById(R.id.button_next);
        button_next.setOnClickListener(this);
        return  v;
    }

    @Override
    public void onClick(View view) {
        if(view == button_next){
            next();
        }
    }
    quizData qData;
    QuizList setQuiz;
    private void GetQuizNumber(){
        //GetQuizNumber]
        databaseReferenceQuiz = FirebaseDatabase.getInstance().getReference("TeacherForm").child("qw").child("QuizNumber");
        databaseReferenceQuiz.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    QuizNumber getQuiz = dataSnapshot.getValue(QuizNumber.class);
                    retrieveNo = getQuiz.getQuizNumber();
                    textView_quizno.setText("Activity No : "+getQuiz.getQuizNumber());
                }catch (Exception ex){
                    Toast.makeText(getContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();
            }
        });
    }
    String retrieveNo;
    private  void SetQuizNumber(String setNumber){
        try{
            setQuiz = new QuizList(setNumber);
            databaseReferenceQuiz.setValue(setQuiz);
            Toast.makeText(getContext(),"Saved",Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Toast.makeText(getContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    private  void next(){
        databaseReference = FirebaseDatabase.getInstance().
                getReference("TeacherForm")
                .child(getTeacherName).child("Quiz").child("Activity No "+String.valueOf(retrieveNo));
        String getNumber = TeacherGetSetData.getnumber.get(0);
        String question = editText_question.getText().toString().trim();
        String choice_a = editText_a.getText().toString().trim();
        String choice_b = editText_b.getText().toString().trim();
        String choice_c = editText_c.getText().toString().trim();
        String choice_d  = editText_d.getText().toString().trim();
        String correct_answer = editText_answer.getText().toString().trim();
        if(TextUtils.isEmpty(question) && TextUtils.isEmpty(choice_a)&&
                TextUtils.isEmpty(choice_b)&&TextUtils.isEmpty(choice_c)
                && TextUtils.isEmpty(choice_d) && TextUtils.isEmpty(correct_answer)){
            Toast.makeText(getContext(),"Please fill the all the data information", Toast.LENGTH_LONG).show();
        }else{
            try{
                qData = new quizData(question,choice_a,
                        choice_b,choice_c,choice_d,correct_answer);
                databaseReference.child(getNumber).setValue(qData);
                Toast.makeText(getContext(),"Saved",Toast.LENGTH_LONG).show();
                TeacherGetSetData.getnumber.remove(0);
                if(Integer.parseInt(getNumber) == 10) {
                    //another Data
                    SetQuizNumber(String.valueOf(Integer.parseInt(retrieveNo) + 1));
                    TeacherGetSetData.getnumber.clear();
                    for (int ie = 1; ie <= 10; ++ie) {
                        TeacherGetSetData.getnumber.add(String.valueOf(ie));
                    }
                }
            }catch (Exception ex){
                Toast.makeText(getContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }
}
