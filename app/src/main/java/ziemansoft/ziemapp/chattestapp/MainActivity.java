package ziemansoft.ziemapp.chattestapp;

//hello android

//Created by Jahongir Zokirov


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ziemansoft.ziemapp.chattestapp.adapters.ChatAdapter;
import ziemansoft.ziemapp.chattestapp.auth.RegisterActivity;
import ziemansoft.ziemapp.chattestapp.pojo.Message;

public class MainActivity extends AppCompatActivity {
    //firebase components
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private RecyclerView recyclerView;

    @Inject
    ChatAdapter adapter;

    private EditText messageField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App)getApplication()).getComponent().inject(this);

        //check for user authorize
//        mAuth = FirebaseAuth.getInstance();
//        if (mAuth.getCurrentUser() == null) {
//            Intent intent = new Intent(this, RegisterActivity.class);
//            startActivity(intent);
//        }
        db = FirebaseFirestore.getInstance();

        //init
        recyclerView = findViewById(R.id.recyclerView);
        messageField = findViewById(R.id.editTextTextMultiLine);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //getting firestore objects
        db.collection("messages").orderBy("date").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    List<Message> list = value.toObjects(Message.class);
                    adapter.setList(list);
                } else {
                    Log.i("Wrong thing happened", error.getMessage());
                }
            }
        });

    }

    public void sendMedia(View view) {
    }

    public void sendMessage(View view) {
        if (!messageField.getText().toString().isEmpty()) {
            String message = messageField.getText().toString();
            Message messageValue = new Message("John", message, System.currentTimeMillis());
            db.collection("messages").add(messageValue).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    messageField.setText(null);
                    recyclerView.scrollToPosition(adapter.getItemCount() - 1);
                    Log.i("Succes", messageField.getText().toString());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i("Failure", e.getMessage());
                }
            });
        }
    }
}