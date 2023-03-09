package com.example.gntakip


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gntakip.databinding.ActivityPermissionBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Permission : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)
        val binding =ActivityPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var database:DatabaseReference = Firebase.database.reference
        var remainingRestDay = RestDay.GetRemainingRestDay()
        //Veri okuma işlemi
        var getdata = object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var tempRemainingRestDay = snapshot.child("remainingRestDay").getValue()
                if( tempRemainingRestDay is Int )
                    remainingRestDay = tempRemainingRestDay

                binding.remainingRestDay.setText("$tempRemainingRestDay")
            }
            override fun onCancelled(error: DatabaseError) {
            }
        }

        var requestedRestDay: Int = 1

        if(getdata!=null)
        {
            RestDay.SetRemainingRestDay(remainingRestDay)
        }
        else
        {
            RestDay.SetRemainingRestDay(30)
        }



        //Veri ekleme işlemi
        binding.plus.setOnClickListener{
            if (requestedRestDay < remainingRestDay) {
                requestedRestDay++
            }
            binding.requestedRestDay.setText("${requestedRestDay}")
        }
        binding.min.setOnClickListener{
            if(requestedRestDay >= 1){
                requestedRestDay--
            }
            binding.requestedRestDay.setText("${requestedRestDay}")
        }
        binding.approval.setOnClickListener{
            if(requestedRestDay == 0){
                binding.requestedRestDay.error = "Lütfen alacağınız izin gününü seçiniz"
            }
            else {
                var edit = binding.edit.text.toString()

                RestDay.CalculateRemainingRestDay(requestedRestDay)
                remainingRestDay = RestDay.GetRemainingRestDay()
                binding.remainingRestDay.setText("${remainingRestDay}")

                database.child("edit").setValue(edit)
                database.child("requestedRestDay").setValue(requestedRestDay)
                database.child("remainingRestDay").setValue(remainingRestDay)
                //onay butonuna bastıktan sonra sıfırlaması için
                binding.edit.setText("")
                requestedRestDay = 0
                binding.requestedRestDay.setText("0")
            }
        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)

    }
}