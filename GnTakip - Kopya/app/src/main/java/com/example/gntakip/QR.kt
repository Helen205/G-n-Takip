package com.example.gntakip

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class QR : AppCompatActivity() {
    private lateinit var qrcode:ImageView
    private lateinit var creat:LinearLayout
    private lateinit var edit:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)
        qrcode = findViewById(R.id.qrcode)
        creat=findViewById(R.id.creating)
        edit=findViewById(R.id.edit)

        creat.setOnClickListener {
            val data =edit.text.toString().trim()
            if (data.isEmpty()){
                Toast.makeText(this,"lütfen adınızı giriniz",Toast.LENGTH_SHORT).show()
            }else {
                val creating = QRCodeWriter()
                try {
                    val bitMatrix = creating.encode(data,BarcodeFormat.QR_CODE,512,512)
                    val width = bitMatrix.width
                    val height = bitMatrix.height
                    val bmp = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565)
                    for (x in 0 until width ){
                        for (y in 0 until height){
                            bmp.setPixel(x,y,if (bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                        }
                    }
                    qrcode.setImageBitmap(bmp)
                } catch (e: WriterException) {
                    e.printStackTrace()
                }
            }
        }
    }
}