package com.softwaresolution.ilearn;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class QuizActivity extends Fragment {
    View v;
    String quizNumber = "1";
    private TextView textView_quizNoActivity,textView_qustionNumberAct,textView_qustionActivity,
            textView_aActivity,textView_bActivity,textView_cActivity,textView_dActivity;
    private EditText editText_chooseAnswer;
    private Button button_Submit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.quiz_activity, container, false);
        textView_quizNoActivity = (TextView) v.findViewById(R.id.textView_quizNoActivity);
        textView_qustionNumberAct = (TextView) v.findViewById(R.id.textView_qustionNumberAct);
        textView_qustionActivity = (TextView) v.findViewById(R.id.textView_qustionActivity);
        textView_aActivity = (TextView) v.findViewById(R.id.textView_aActivity);
        textView_bActivity = (TextView) v.findViewById(R.id.textView_bActivity);
        textView_cActivity = (TextView) v.findViewById(R.id.textView_cActivity);
        textView_dActivity = (TextView) v.findViewById(R.id.textView_dActivity);
        editText_chooseAnswer = (EditText) v.findViewById(R.id.editText_chooseAnswer);
        button_Submit = (Button) v.findViewById(R.id.button_Submit);
        _activity();
        button_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizNumber = String.valueOf(Integer.parseInt(quizNumber) + 1);
                if(editText_chooseAnswer.getText().toString().trim().equals(qData.getQuizCorrectAnswer())){
                    userAnswer = String.valueOf(Integer.parseInt(userAnswer) + 1);
                }
                if(Integer.parseInt(quizNumber) == 2){
                    _saveScore();
                    Toast.makeText(getActivity(),"You got " + userAnswer,Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new ActivityFragment()).commit();
                }else{
                    clear();
                    _activity();
                }
            }
        });
        return v;
    }
    String userAnswer = "0";
    quizData qData;
    private void _activity(){
        DatabaseReference dbQuiz = FirebaseDatabase.getInstance().getReference("TeacherForm").
                child(StudentLoginForm.TEACHER_ASSIGNED).child("Quiz").child(ActivityFragment.QUIZ_NUMBER);
        dbQuiz.child(quizNumber).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    qData = dataSnapshot.getValue(quizData.class);
                    textView_quizNoActivity.setText(ActivityFragment.QUIZ_NUMBER);
                    textView_qustionNumberAct.setText("Question no. " + quizNumber);
                    textView_qustionActivity.setText("Qustion : " + qData.getQuizQuestion());
                    textView_aActivity.setText("A. " + qData.getQuizChoice_a());
                    textView_bActivity.setText("B. " + qData.getQuizChoice_b());
                    textView_cActivity.setText("C. " + qData.getQuizChoice_c());
                    textView_dActivity.setText("D. " + qData.getQuizChoice_d());
                }catch (Exception ex){
                    Toast.makeText(getContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Database Error",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void clear (){
        textView_quizNoActivity.setText( " ");
        textView_qustionNumberAct.setText( " ");
        textView_qustionActivity.setText( " ");
        textView_aActivity.setText( " ");
        textView_bActivity.setText( " ");
        textView_cActivity.setText( " ");
        textView_dActivity.setText( " ");
        editText_chooseAnswer.setText( " ");
    }
    private void _saveScore(){
        DatabaseReference dbScore = FirebaseDatabase.getInstance().getReference("StudentForm").
                child(StudentLoginForm.STUDENT_NAME).child("Quiz").child(ActivityFragment.QUIZ_NUMBER);
        StudentScore studentScore = new StudentScore(userAnswer,ActivityFragment.QUIZ_NUMBER);
        dbScore.setValue(studentScore);
    }
}
