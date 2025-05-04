package com.example.myloginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myloginapp.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val customerID=binding.uploadCustomerID.text.toString()
            val customerName=binding.uploadCustomerName.text.toString()
            val customerMeasurement=binding.uploadCustomerMeasurement.text.toString()
            val customerDesign=binding.uploadFabricDesign.text.toString()
            val deliveryDate=binding.uploadDeliveryDate.text.toString()

            databaseReference=FirebaseDatabase.getInstance().getReference("Customer Information")
            val customerData=CustomerData(customerID,customerName,customerMeasurement,customerDesign,deliveryDate)

            databaseReference.child(customerID).setValue(customerData).addOnSuccessListener {
                binding.uploadCustomerID.text.clear()
                binding.uploadCustomerName.text.clear()
                binding.uploadCustomerMeasurement.text.clear()
                binding.uploadFabricDesign.text.clear()
                binding.uploadDeliveryDate.text.clear()

                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed to saves",Toast.LENGTH_SHORT).show()
            }
        }

    }
}