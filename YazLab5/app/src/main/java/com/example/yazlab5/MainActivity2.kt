package com.example.yazlab5

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class MainActivity2 : ComponentActivity() {
    val ArananKelime="SELAM"
    var HarfSayisi =5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.deneme)



        val KelimeBulma1 = mutableListOf(
            findViewById<EditText>(R.id.Box_1_1),
            findViewById<EditText>(R.id.Box_1_2),
            findViewById<EditText>(R.id.Box_1_3),
            findViewById<EditText>(R.id.Box_1_4),
            findViewById<EditText>(R.id.Box_1_5),
            findViewById<EditText>(R.id.Box_1_6),
            findViewById<EditText>(R.id.Box_1_7)
        )
        val KelimeBulma2 = mutableListOf(
            findViewById<EditText>(R.id.Box_2_1),
            findViewById<EditText>(R.id.Box_2_2),
            findViewById<EditText>(R.id.Box_2_3),
            findViewById<EditText>(R.id.Box_2_4),
            findViewById<EditText>(R.id.Box_2_5),
            findViewById<EditText>(R.id.Box_2_6),
            findViewById<EditText>(R.id.Box_2_7)
        )
        val KelimeBulma3 = mutableListOf(
            findViewById<EditText>(R.id.Box_3_1),
            findViewById<EditText>(R.id.Box_3_2),
            findViewById<EditText>(R.id.Box_3_3),
            findViewById<EditText>(R.id.Box_3_4),
            findViewById<EditText>(R.id.Box_3_5),
            findViewById<EditText>(R.id.Box_3_6),
            findViewById<EditText>(R.id.Box_3_7)
        )
        val KelimeBulma4 = mutableListOf(
            findViewById<EditText>(R.id.Box_4_1),
            findViewById<EditText>(R.id.Box_4_2),
            findViewById<EditText>(R.id.Box_4_3),
            findViewById<EditText>(R.id.Box_4_4),
            findViewById<EditText>(R.id.Box_4_5),
            findViewById<EditText>(R.id.Box_4_6),
           findViewById<EditText>(R.id.Box_4_7)
        )
        val KelimeBulma5 = mutableListOf(
            findViewById<EditText>(R.id.Box_5_1),
            findViewById<EditText>(R.id.Box_5_2),
            findViewById<EditText>(R.id.Box_5_3),
            findViewById<EditText>(R.id.Box_5_4),
            findViewById<EditText>(R.id.Box_5_5),
            findViewById<EditText>(R.id.Box_5_6),
            findViewById<EditText>(R.id.Box_5_7)
        )
        val KelimeBulma6 = mutableListOf(
            findViewById<EditText>(R.id.Box_6_1),
           findViewById<EditText>(R.id.Box_6_2),
            findViewById<EditText>(R.id.Box_6_3),
            findViewById<EditText>(R.id.Box_6_4),
            findViewById<EditText>(R.id.Box_6_5),
           findViewById<EditText>(R.id.Box_6_6),
           findViewById<EditText>(R.id.Box_6_7)
        )
        val KelimeBulma7 = mutableListOf(
            findViewById<EditText>(R.id.Box_7_1),
            findViewById<EditText>(R.id.Box_7_2),
            findViewById<EditText>(R.id.Box_7_3),
           findViewById<EditText>(R.id.Box_7_4),
            findViewById<EditText>(R.id.Box_7_5),
            findViewById<EditText>(R.id.Box_7_6),
            findViewById<EditText>(R.id.Box_7_7)
        )
        val KelimeBulmaListesi = mutableListOf(
            KelimeBulma1,
            KelimeBulma2,
            KelimeBulma3,
            KelimeBulma4,
            KelimeBulma5,
            KelimeBulma6,
            KelimeBulma7,
        )
        val btn = findViewById<Button>(R.id.onay_btn)

        val btn1 = findViewById<Button>(R.id.button11)

        btn1.setOnClickListener(){
            Game_Start(KelimeBulmaListesi,0)
        }
        val text: CharSequence = "Yeni metin"
        val editableText: Editable = Editable.Factory.getInstance().newEditable(text)
        KelimeBulma7[0].text=editableText


    }

    private fun Game_Start(KelimeBulmaListesi:MutableList<MutableList<EditText>>, HangiRow:Int){

        for((i,edittext) in KelimeBulmaListesi[HangiRow].withIndex()){
            if(edittext.visibility==View.VISIBLE){
                val str2 = ArananKelime[i].toString()
                val str1 =edittext.text.toString()

                if (str1==str2) {
                    edittext.setBackgroundColor(Color.GREEN)
                } else if (ArananKelime.contains(str1)) {
                    edittext.setBackgroundColor(Color.YELLOW)
                } else {
                    edittext.setBackgroundColor(Color.GRAY)
                }
            }

        }

        if(HarfSayisi>=HangiRow+1){
            KelimeBulmaListesi[HangiRow+1].forEach(){
                it.isEnabled=true
            }
        }

    }
}

