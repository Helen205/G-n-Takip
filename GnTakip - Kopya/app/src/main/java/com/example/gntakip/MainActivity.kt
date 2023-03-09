package com.example.gntakip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnext: LinearLayout = findViewById(R.id.remainingRestDay)
        val btnprmsn: LinearLayout = findViewById(R.id.prmsn)
        val btnqr: LinearLayout = findViewById(R.id.qr)

        btnprmsn.setOnClickListener{
            startActivity(Intent(this@MainActivity, Permission::class.java))
        }
        btnqr.setOnClickListener{
            startActivity(Intent(this@MainActivity, QR::class.java))
        }
        btnext.setOnClickListener {
            startActivity(Intent(this@MainActivity,Login::class.java))
        }
    }
}