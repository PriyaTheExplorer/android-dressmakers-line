package com.example.myloginapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myloginapp.databinding.ActivityReadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityReadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val customerID=binding.searchCustomerID.text.toString()
            if(customerID.isNotEmpty()){
                readData(customerID)
            }else{
                Toast.makeText(this,"Enter valid ID",Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun readData(customerID:String){
        databaseReference=FirebaseDatabase.getInstance().getReference("Customer Information")
        databaseReference.child(customerID).get().addOnSuccessListener {
            if(it.exists()){
                val customerName=it.child("customerName").value
                val customerMeasurement=it.child("customerMeasurement").value
                val customerDesign=it.child("customerDesign").value
                val deliveryDate=it.child("deliveryDate").value
                Toast.makeText(this,"Results found",Toast.LENGTH_SHORT).show()
                binding.searchCustomerID.text.clear()
                binding.readCustomerName.text=customerName.toString()
                binding.readCustomerMeasurement.text=customerMeasurement.toString()
                binding.readCustomerDesign.text=customerDesign.toString()
                binding.readDeliveryDate.text=deliveryDate.toString()
            }else{
                Toast.makeText(this,"Customer ID does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Enter valid ID",Toast.LENGTH_SHORT).show()
        }

    }
}