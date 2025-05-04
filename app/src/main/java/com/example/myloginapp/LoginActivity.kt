package com.example.myloginapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myloginapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()

        Log.d("AppStart","Login screen visible")

        binding.loginButton.setOnClickListener {
            val email=binding.loginEmail.text.toString()
            val password=binding.loginPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this) {task->
                        if(task.isSuccessful){
                            Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
                            val intent= Intent(this,MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this,"Enter valid email and password",Toast.LENGTH_SHORT).show()
            }


        }
        binding.signupText.setOnClickListener{
            startActivity(Intent(this,SignupActivity::class.java))
            finish()
        }

    }
}