package com.softwaresolution.ilearn;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StatusFragment extends Fragment {
    View v;
    TextView text_mystar;
    private GridView gridView_status;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_staus, container, false);
        gridView_status = (GridView) v.findViewById(R.id.gridView_status);
        text_mystar =(TextView) v.findViewById(R.id.text_mystar);
        studentScoreList = new ArrayList<>();
        return  v;
    }
    List<StudentScore> studentScoreList;

    @Override
    public void onStart() {
        super.onStart();
        text_mystar.setText("Your start is : "+StatusListLayout.mystar );
        DatabaseReference dbStudentStatus = FirebaseDatabase.getInstance().getReference("StudentForm").
                child(StudentLoginForm.STUDENT_NAME).child("Quiz");
        dbStudentStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentScoreList.clear();
                for (DataSnapshot dsStudentScore : dataSnapshot.getChildren()){
                    StudentScore studentScore = dsStudentScore.getValue(StudentScore.class);
                    studentScoreList.add(studentScore);
                }
                StatusListLayout statusAdapter = new StatusListLayout(getActivity(), studentScoreList);
                gridView_status.setAdapter(statusAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Database Error",Toast.LENGTH_LONG).show();
            }
        });
    }
}