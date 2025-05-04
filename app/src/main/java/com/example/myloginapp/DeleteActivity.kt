package com.example.myloginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myloginapp.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener {
            val customerID=binding.deleteCustomerID.text.toString()

            if(customerID.isNotEmpty()){
                deleteData(customerID)
            }else{
                Toast.makeText(this,"Enter Valid Id",Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun deleteData(customerID:String){
        databaseReference=FirebaseDatabase.getInstance().getReference("Customer Information")
        databaseReference.child(customerID).removeValue().addOnSuccessListener {
            binding.deleteCustomerID.text.clear()
            Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()

        }.addOnFailureListener{
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }
    }
}