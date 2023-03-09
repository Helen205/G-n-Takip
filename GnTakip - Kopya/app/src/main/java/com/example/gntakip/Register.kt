package com.example.gntakip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.Calendar.getInstance
import androidx.activity.result.contract.ActivityResultContracts

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnregister: Button = findViewById(R.id.btn_register)
        val etregisteremail: EditText = findViewById(R.id.et_registeremail)
        val etregisterpassword: EditText = findViewById(R.id.et_registerpassword)
        val tvlogin: TextView = findViewById(R.id.tv_login)

        tvlogin.setOnClickListener{
            startActivity(Intent(this@Register, Login::class.java))
        }
        btnregister.setOnClickListener {
            when {
                TextUtils.isEmpty(etregisteremail.text.toString().trim { it <= ' ' }) -> {
                    etregisteremail.error = "Lütfen e-maili giriniz"
                }
                TextUtils.isEmpty(etregisterpassword.text.toString().trim() { it <= ' ' }) -> {
                    etregisterpassword.error = "Lütfen şifre giriniz."
                }
                else ->{
                    val email:String = etregisteremail.text.toString().trim() { it <= ' ' }
                    val password:String = etregisterpassword.text.toString().trim() { it <= ' ' }
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->
                                if (task.isSuccessful){
                                    val firebaseUser:FirebaseUser=task.result!!.user!!
                                    Toast.makeText(
                                        this@Register,
                                        "Kayıt Başarılı",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val intent =
                                        Intent(this@Register,Login::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    // intent.putExtra("user id",firebaseUser.uid)
                                    // intent.putExtra("email id",email)
                                    startActivity(intent)
                                    finish()
                                }else{
                                    Toast.makeText(
                                        this@Register,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                }
            }
        }
    }
}