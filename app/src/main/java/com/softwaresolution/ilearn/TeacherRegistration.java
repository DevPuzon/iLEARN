package com.softwaresolution.ilearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeacherRegistration extends AppCompatActivity {
    private EditText editText_TeacherUsernameReg, editText_TeacherPasswordReg;
    private Button button_TeacherRegister;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_registration);
        editText_TeacherUsernameReg = (EditText) findViewById(R.id.edittext_TeacherUsernameReg);
        editText_TeacherPasswordReg = (EditText) findViewById(R.id.edittext_TeacherPasswordReg);
        button_TeacherRegister = (Button) findViewById(R.id.button_TeacherRegister);
        button_TeacherRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teacherReg();
            }
        });
    }
    private void teacherReg(){
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("TeacherForm");
        String teacherName = editText_TeacherUsernameReg.getText().toString().trim();
        String teacherPassword = editText_TeacherPasswordReg.getText().toString().trim();
        if(TextUtils.isEmpty(teacherName)){
            Toast.makeText(this,"Please enter your teacher name", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(teacherPassword)){
            Toast.makeText(this,"Please enter your password", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            LoginTeacher teacherData = new LoginTeacher(teacherName,teacherPassword);
            databaseReference.child(teacherName).setValue(teacherData);
            SetQuizNumber();
            Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private  void SetQuizNumber(){

        DatabaseReference databaseReferenceQuiz = FirebaseDatabase.getInstance().getReference("TeacherForm")
                .child(editText_TeacherUsernameReg.getText().toString().trim()).child("QuizNumber");
        try{
            QuizList setQuiz = new QuizList("1");
            databaseReferenceQuiz.setValue(setQuiz);
            Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}

