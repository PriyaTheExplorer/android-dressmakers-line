package com.example.myloginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myloginapp.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener {
            val customerID=binding.referenceCustomerID.text.toString()
            val customerName=binding.updateCustomerName.text.toString()
            val customerMeasurement=binding.updateCustomerMeasurement.text.toString()
            val customerDesign=binding.updateFabricDesign.text.toString()
            val deliveryDate=binding.updateDeliveryDate.text.toString()

            updateData(customerID,customerName,customerMeasurement,customerDesign,deliveryDate)
        }

    }
    private fun updateData(customerID:String, customerName:String, customerMeasurement:String, customerDesign:String, deliveryDate:String){
        databaseReference=FirebaseDatabase.getInstance().getReference("Customer Information")
        val customerData= mapOf<String,String>("customerName" to customerName,"customerMeasurement" to customerMeasurement,"customerDesign" to customerDesign, "deliveryDate" to deliveryDate)
        databaseReference.child(customerID).updateChildren(customerData).addOnSuccessListener {
            binding.referenceCustomerID.text.clear()
            binding.updateCustomerName.text.clear()
            binding.updateCustomerMeasurement.text.clear()
            binding.updateFabricDesign.text.clear()
            binding.updateDeliveryDate.text.clear()
            Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }.addOnFailureListener{
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }

    }
}