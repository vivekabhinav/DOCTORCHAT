package com.abhinav.youchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabase = FirebaseDatabase.getInstance().getReference("Message");

        final TextView myText = findViewById(R.id.textbox);
        myDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                myText.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                myText.setText("CANCELLED");

            }
        });
    }

    public void sendMessage(View view){
        EditText myEditText = findViewById(R.id.editText);

        myDatabase.child(Long.toString(System.currentTimeMillis())).setValue(myEditText.getText().toString());
        myEditText.setText("\n");
    }
}