package ziemansoft.ziemapp.chattestapp.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ziemansoft.ziemapp.chattestapp.MainActivity;
import ziemansoft.ziemapp.chattestapp.R;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private EditText editTextLogin;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        editTextLogin = findViewById(R.id.emailLoginRegister);
        editTextPassword = findViewById(R.id.passwordLoginRegister);
    }

    public void openSignin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void registerUser(View view) {
        String password = editTextPassword.getText().toString().trim();
        String email  = editTextLogin.getText().toString().trim();
        if (!email.isEmpty() && !password.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(RegisterActivity.this, "Authorized", Toast.LENGTH_SHORT).show();
                            }else {
                                     Toast.makeText(RegisterActivity.this, "not authorized: "+ task.getException(), Toast.LENGTH_SHORT).show();
                                Log.i("ErrorMessage", task.getException().getMessage());
                            }
                        }
                    });
        }
    }
}