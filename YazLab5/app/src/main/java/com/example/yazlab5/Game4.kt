package com.example.yazlab5

import android.app.AlertDialog
import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.addCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Game4 : Fragment() {
    private val HarfSayisi=7
    private val oda="Oda4"
    private var database: DatabaseReference = Firebase.database.reference
    var ArananKelime="x"
    var BenimKelimem: String? =null
    lateinit var dialog1:AlertDialog
    private lateinit var KelimeyiGirdiMi : ValueEventListener
    private lateinit var Winner : ValueEventListener
    private lateinit var Rovans : ValueEventListener
    private lateinit var Afk : ValueEventListener
    private lateinit var RakipGor : ValueEventListener
    private lateinit var DenemeBitti : ValueEventListener
    private lateinit var Esitlik : ValueEventListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exit = view.findViewById<Button>(R.id.exit)
        val email = arguments?.getString("email")
        val id = arguments?.getString("id")
        val Rakip_id = arguments?.getString("Rakip_id")
        val Onay = view.findViewById<Button>(R.id.onay_btn)
        val btn_KelimeDene = view.findViewById<Button>(R.id.btn_kelime_dene)
        val btn_RakipeBak = view.findViewById<Button>(R.id.btn_rakibi_gor)
        val LayoutOyun= view.findViewById<LinearLayout>(R.id.LayoutOyun)
        val LayoutKelimeOnay=view.findViewById<ConstraintLayout>(R.id.LayoutKelimeOnay)
        val LayoutRakipGor=view.findViewById<LinearLayout>(R.id.LayoutRakipGor)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val progressBar1 = view.findViewById<ProgressBar>(R.id.progressBar1)
        val GirilenKelime = mutableListOf(
            view.findViewById<EditText>(R.id.Box_1),
            view.findViewById<EditText>(R.id.Box_2),
            view.findViewById<EditText>(R.id.Box_3),
            view.findViewById<EditText>(R.id.Box_4),
            view.findViewById<EditText>(R.id.Box_5),
            view.findViewById<EditText>(R.id.Box_6),
            view.findViewById<EditText>(R.id.Box_7)
        )
        val KelimeBulma1 = mutableListOf(
            view.findViewById<EditText>(R.id.Box_1_1),
            view.findViewById<EditText>(R.id.Box_1_2),
            view.findViewById<EditText>(R.id.Box_1_3),
            view.findViewById<EditText>(R.id.Box_1_4),
            view.findViewById<EditText>(R.id.Box_1_5),
            view.findViewById<EditText>(R.id.Box_1_6),
            view.findViewById<EditText>(R.id.Box_1_7)
        )
        val KelimeBulma2 = mutableListOf(
            view.findViewById<EditText>(R.id.Box_2_1),
            view.findViewById<EditText>(R.id.Box_2_2),
            view.findViewById<EditText>(R.id.Box_2_3),
            view.findViewById<EditText>(R.id.Box_2_4),
            view.findViewById<EditText>(R.id.Box_2_5),
            view.findViewById<EditText>(R.id.Box_2_6),
            view.findViewById<EditText>(R.id.Box_2_7)
        )
        val KelimeBulma3 = mutableListOf(
            view.findViewById<EditText>(R.id.Box_3_1),
            view.findViewById<EditText>(R.id.Box_3_2),
            view.findViewById<EditText>(R.id.Box_3_3),
            view.findViewById<EditText>(R.id.Box_3_4),
            view.findViewById<EditText>(R.id.Box_3_5),
            view.findViewById<EditText>(R.id.Box_3_6),
            view.findViewById<EditText>(R.id.Box_3_7)
        )
        val KelimeBulma4 = mutableListOf(
            view.findViewById<EditText>(R.id.Box_4_1),
            view.findViewById<EditText>(R.id.Box_4_2),
            view.findViewById<EditText>(R.id.Box_4_3),
            view.findViewById<EditText>(R.id.Box_4_4),
            view.findViewById<EditText>(R.id.Box_4_5),
            view.findViewById<EditText>(R.id.Box_4_6),
            view.findViewById<EditText>(R.id.Box_4_7)
        )
        val KelimeBulma5 = mutableListOf(
            view.findViewById<EditText>(R.id.Box_5_1),
            view.findViewById<EditText>(R.id.Box_5_2),
            view.findViewById<EditText>(R.id.Box_5_3),
            view.findViewById<EditText>(R.id.Box_5_4),
            view.findViewById<EditText>(R.id.Box_5_5),
            view.findViewById<EditText>(R.id.Box_5_6),
            view.findViewById<EditText>(R.id.Box_5_7)
        )
        val KelimeBulma6 = mutableListOf(
            view.findViewById<EditText>(R.id.Box_6_1),
            view.findViewById<EditText>(R.id.Box_6_2),
            view.findViewById<EditText>(R.id.Box_6_3),
            view.findViewById<EditText>(R.id.Box_6_4),
            view.findViewById<EditText>(R.id.Box_6_5),
            view.findViewById<EditText>(R.id.Box_6_6),
            view.findViewById<EditText>(R.id.Box_6_7)
        )
        val KelimeBulma7 = mutableListOf(
            view.findViewById<EditText>(R.id.Box_7_1),
            view.findViewById<EditText>(R.id.Box_7_2),
            view.findViewById<EditText>(R.id.Box_7_3),
            view.findViewById<EditText>(R.id.Box_7_4),
            view.findViewById<EditText>(R.id.Box_7_5),
            view.findViewById<EditText>(R.id.Box_7_6),
            view.findViewById<EditText>(R.id.Box_7_7)
        )
        val RakipGoremeListesi = mutableListOf(
            view.findViewById<EditText>(R.id.Box1),
            view.findViewById<EditText>(R.id.Box2),
            view.findViewById<EditText>(R.id.Box3),
            view.findViewById<EditText>(R.id.Box4),
            view.findViewById<EditText>(R.id.Box5),
            view.findViewById<EditText>(R.id.Box6),
            view.findViewById<EditText>(R.id.Box7)
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
        ////////////////////////////////////Süre Ayarlari////////////////////////////////////////////
        var kalansure=0
        val countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = ((millisUntilFinished.toFloat() / 60000) * 100).toInt()
                progressBar.progress = progress
                kalansure = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {
                database.child("KelimeOnaySuresi").child(id.toString()).setValue("Doldu")

                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    var x= database.child("KelimeOnaySuresi").child(Rakip_id.toString()).get().addOnSuccessListener {
                        if(it.value==null){
                            println("Kaybettin")
                        }
                        else{
                            this.start()
                        }
                    }

                }, 1000)
            }
        }

        countDownTimer.start()
        //////////////////////////////Girilen Kelime Ayarları///////////////////////////////////
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Rakip Bekleniyor...")
        progressDialog.setCancelable(false)

        for((j, i) in GirilenKelime.withIndex()){
            if(j>=HarfSayisi){
                i.visibility=View.GONE
                RakipGoremeListesi[j].visibility=View.GONE
            }
        }
        kutucuk(GirilenKelime)
        Onay.setOnClickListener(){
            var j=0
            for(i in GirilenKelime){
                if(i.text.toString()!="" && i.visibility==View.VISIBLE){
                    j++
                }
            }
            if(j==HarfSayisi){
                var s =""
                for(i in GirilenKelime){
                    s+=i.text.toString()
                }
                BenimKelimem=s
                countDownTimer.cancel()
                database.child("KelimeOnay").child(Rakip_id.toString()).setValue(BenimKelimem)
                progressDialog.show()
                if(ArananKelime != "x"){
                    println(BenimKelimem)
                    println(ArananKelime)
                    LayoutKelimeOnay.visibility=View.GONE
                    LayoutOyun.visibility=View.VISIBLE
                    btn_RakipeBak.visibility=View.VISIBLE
                    btn_KelimeDene.visibility=View.VISIBLE
                    progressDialog.dismiss()
                }

            }
            else{
                Toast.makeText(requireContext(), "Boş Alan Bırakma", Toast.LENGTH_SHORT).show()
            }

        }
        KelimeyiGirdiMi=object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.value!=null){
                    ArananKelime=snapshot.value.toString()
                }

                if(BenimKelimem!=null){
                    println(BenimKelimem)
                    println(ArananKelime)
                    LayoutKelimeOnay.visibility=View.GONE
                    LayoutOyun.visibility=View.VISIBLE
                    btn_RakipeBak.visibility=View.VISIBLE
                    btn_KelimeDene.visibility=View.VISIBLE
                    progressDialog.dismiss()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println(error)
            }

        }
        database.child("KelimeOnay").child(id.toString()).addValueEventListener(KelimeyiGirdiMi)
        ////////////////////////////Denenen Kelime Ayarları///////////////////////////////////////
        for((j, kelimebulma) in KelimeBulmaListesi.withIndex()){
            kutucuk(kelimebulma)
            var k =0
            kelimebulma.forEach(){
                if(j>=HarfSayisi || k>=HarfSayisi){
                    it.visibility=View.GONE
                }
                k++
            }
        }


        ////////////////////////////Kaznan Kaybedeni Bulma(Kaybeden)///////////////////////////////////////
        val builder = AlertDialog.Builder(context)
        var progressDialog1= ProgressDialog(context)
        var temp="no"
        progressDialog.setMessage("Rakip Cevabı Bekleniyor...")

        builder.setPositiveButton("Rövanş İste") { dialog, which ->
            database.child("Rovans").child(Rakip_id.toString()).setValue("TekrarOynayak")
            temp="yes"
            progressDialog1.show()
        }
        builder.setNegativeButton("Oyundan Çık") { dialog, which ->
            Navigation.findNavController(view).navigate(Game4Directions.actionGame4ToOda4(id.toString(),email.toString()))
            database.child("Oyunda").child(oda).child(id.toString()).removeValue()
        }
        builder.setTitle("OYUN SONUCU")
        builder.setMessage("KAYBETTİN !!!")
        dialog1=builder.create()

        dialog1.dismiss()
        val builder1 = AlertDialog.Builder(context)
        builder1.setTitle("RÖVANŞ İSTEĞİ")
        builder1.setMessage("Rakibin Tekrar Oynamak İstiyor!!!")
        builder1.setPositiveButton("Kabul Et") { dialog, which ->
            dialog1.dismiss()
            database.child("Rovans").child(Rakip_id.toString()).setValue("TekrarOynayak")
            database.child("Rovans").child(id.toString()).removeValue()
            Navigation.findNavController(view).navigate(Game4Directions.actionGame4Self(id.toString(),email.toString(),Rakip_id.toString()))
        }
        builder1.setNegativeButton("Reddet") { dialog, which ->
            dialog1.dismiss()
            Navigation.findNavController(view).navigate(Game4Directions.actionGame4ToOda4(id.toString(),email.toString()))
            database.child("Oyunda").child(oda).child(id.toString()).removeValue()
        }

        Winner= object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.value!=null){
                    dialog1.show()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                println(error)
            }

        }
        database.child("Winner").child(Rakip_id.toString()).addValueEventListener(Winner)



        Rovans= object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.value!=null){
                    if(temp=="yes"){
                        progressDialog1.dismiss()
                        database.child("Rovans").child(id.toString()).removeValue()
                        Navigation.findNavController(view).navigate(Game4Directions.actionGame4Self(id.toString(),email.toString(),Rakip_id.toString()))
                    }
                    else{
                        builder1.show()
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                println(error)
            }

        }
        database.child("Rovans").child(id.toString()).addValueEventListener(Rovans)


        var butonsay=0
        btn_KelimeDene.setOnClickListener(){
            var j=0
            for(i in KelimeBulmaListesi[butonsay]){
                if(i.text.toString()!="" && i.visibility==View.VISIBLE){
                    j++
                }
            }
            if(j==HarfSayisi){
                Game_Start(KelimeBulmaListesi,butonsay,builder,id.toString(),btn_KelimeDene,kalansure)
                butonsay++
            }
            else{
                Toast.makeText(requireContext(), "Boş Alan Bırakma", Toast.LENGTH_SHORT).show()
            }

        }

        Afk=object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.value!=null){
                    builder.setTitle("Rakip Oyundan Çıktı")
                    builder.setMessage("KAZANDIN !!!")
                    val dialog = builder.create()
                    dialog.show()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        database.child("Afk").child(Rakip_id.toString()).addValueEventListener(Afk)

        val builder2= AlertDialog.Builder(context)
        builder2.setPositiveButton("Evet") { dialog, which ->

            database.child("Afk").child(id.toString()).setValue("Win")
            database.child("Oyunda").child(oda).child(id.toString()).removeValue()
            Navigation.findNavController(view).navigate(Game4Directions.actionGame4ToOda4(id.toString(),email.toString()))
        }
        builder2.setNegativeButton("Hayır") { dialog, which ->

        }
        builder2.setTitle("Oyundan Çık")
        builder2.setMessage("Oyundan Çıkmaya Emin Misiniz ?")
        var dialog2=builder2.create()

        exit.setOnClickListener(){
            dialog2.show()
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            database.child("Oyunda").child(oda).child(id.toString()).removeValue()
            Navigation.findNavController(view).navigate(Game4Directions.actionGame4ToOda4(id.toString(),email.toString()))
        }

        var RakipGorKelime=""

        btn_RakipeBak.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    LayoutOyun.visibility=View.GONE
                    LayoutRakipGor.visibility=View.VISIBLE
                    btn_KelimeDene.visibility=View.GONE
                    btn_RakipeBak.visibility=View.GONE
                    for((i,j) in RakipGorKelime.withIndex()){
                        val text: CharSequence = j.toString()
                        val editableText: Editable = Editable.Factory.getInstance().newEditable(text)
                        RakipGoremeListesi[i].text=editableText

                    }

                    for((i,edittext) in RakipGoremeListesi.withIndex()){
                        if(edittext.visibility==View.VISIBLE){
                            val str2 = BenimKelimem?.get(i)?.toString()
                            val str1 =edittext.text.toString()


                            if (str1==str2) {
                                edittext.setBackgroundColor(Color.GREEN)
                            } else if (BenimKelimem?.contains(str1) == true) {
                                edittext.setBackgroundColor(Color.YELLOW)
                            } else {
                                edittext.setBackgroundColor(Color.GRAY)
                            }
                        }
                    }




                }
                MotionEvent.ACTION_UP -> {
                    LayoutOyun.visibility=View.VISIBLE
                    LayoutRakipGor.visibility=View.GONE
                    btn_KelimeDene.visibility=View.VISIBLE
                    btn_RakipeBak.visibility=View.VISIBLE
                }
            }
            true
        }

        RakipGor=object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.value!=null){
                    RakipGorKelime=snapshot.value.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        database.child("RakipGor").child(Rakip_id.toString()).addValueEventListener(RakipGor)

        var countDownTimer2:CountDownTimer
        DenemeBitti=object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.value!=null){
                    progressBar1.visibility=View.VISIBLE
                    val sure=((HarfSayisi-butonsay)*10000).toLong()

                    countDownTimer2 = object : CountDownTimer(sure, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            val progress = ((millisUntilFinished.toFloat() / 60000) * 100).toInt()
                            progressBar1.progress = progress
                        }

                        override fun onFinish() {
                            progressBar1.visibility=View.GONE
                            database.child("DenemeBitti").child(id.toString()).get().addOnSuccessListener {
                                if(it.key!=Rakip_id.toString()){
                                    println("girdi")
                                    KelimeBulmaListesi[HarfSayisi-1].forEach(){
                                        val alphabet = ('A'..'Z').toList()
                                        val text: CharSequence = alphabet.random().toString()
                                        val editableText: Editable = Editable.Factory.getInstance().newEditable(text)
                                        it.text=editableText
                                    }

                                    var j=0
                                    for(i in KelimeBulmaListesi[HarfSayisi-1]){
                                        if(i.text.toString()!="" && i.visibility==View.VISIBLE){
                                            j++
                                        }
                                    }
                                    if(j==HarfSayisi){
                                        Game_Start(KelimeBulmaListesi,HarfSayisi-1,builder,id.toString(),btn_KelimeDene,kalansure)
                                    }
                                }
                            }

                        }
                    }

                    countDownTimer2.start()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        database.child("DenemeBitti").child(Rakip_id.toString()).addValueEventListener(DenemeBitti)

        Esitlik=object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var x=0
                var a=""
                var b=""
                for(i in snapshot.children){
                    if(i.key==Rakip_id){
                        x++
                        a=i.value.toString()
                        println(a)
                    }
                    if(i.key==id){
                        x++
                        b=i.value.toString()
                        println(b)
                    }
                }
                if(x==2){

                    if(b>a){
                        dialog1.setTitle("Kazandınız")
                        dialog1.setMessage("Sen = $b \n Rakip = $a")
                        dialog1.show()
                    }
                    else{
                        dialog1.setTitle("Kaybettiniz")
                        dialog1.setMessage("Sen = $b \n Rakip = $a")
                        dialog1.show()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        database.child("Esitlik").addValueEventListener(Esitlik)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        val id = arguments?.getString("id")
        val Rakip_id = arguments?.getString("Rakip_id")
        database.child("KelimeOnay").child(id.toString()).removeEventListener(KelimeyiGirdiMi)
        database.child("Rovans").child(id.toString()).removeEventListener(Rovans)
        database.child("Winner").child(Rakip_id.toString()).removeEventListener(Winner)
        database.child("Afk").child(Rakip_id.toString()).removeValue()
        database.child("Afk").child(Rakip_id.toString()).removeEventListener(Afk)
        database.child("DenemeBitti").child(Rakip_id.toString()).removeEventListener(DenemeBitti)
        database.child("RakipGor").child(Rakip_id.toString()).removeEventListener(RakipGor)
        database.child("Esitlik").removeEventListener(Esitlik)
        database.child("Winner").child(id.toString()).removeValue()
        database.child("KelimeOnay").child(id.toString()).removeValue()
        database.child("DenemeBitti").child(id.toString()).removeValue()
        database.child("Esitlik").child(id.toString()).removeValue()
        database.child("RakipGor").child(id.toString()).removeValue()
        dialog1.dismiss()
    }

    fun kutucuk(editTexts:MutableList<EditText>){
        for (i in 0 until editTexts.size) {
            val currentEditText = editTexts[i]
            val previousEditText = if (i > 0) editTexts[i - 1] else editTexts[editTexts.size - 1]
            val nextEditText = if (i < editTexts.size - 1) editTexts[i + 1] else editTexts[0]

            currentEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.isEmpty() == true && i!=0) {
                        previousEditText.requestFocus()
                    } else if (s?.length == 1 && i!=editTexts.size-1) {
                        nextEditText.requestFocus()
                    }

                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }



    private fun Game_Start(KelimeBulmaListesi:MutableList<MutableList<EditText>>, HangiRow:Int, builder:AlertDialog.Builder,id:String,btn:Button,kalansure:Int){
        var sabit=0
        var yellow=0
        var greeen=0
        var s=""
        for((i,edittext) in KelimeBulmaListesi[HangiRow].withIndex()){
            if(edittext.visibility==View.VISIBLE){
                val str2 = ArananKelime[i].toString()
                val str1 =edittext.text.toString()
                s+=str1

                if (str1==str2) {
                    edittext.setBackgroundColor(Color.GREEN)
                    greeen++
                } else if (ArananKelime.contains(str1)) {
                    edittext.setBackgroundColor(Color.YELLOW)
                    sabit=1
                    yellow++
                } else {
                    edittext.setBackgroundColor(Color.GRAY)
                    sabit=2
                }
            }
            database.child("RakipGor").child(id.toString()).setValue(s)

        }
        if(sabit==0){
            btn.isEnabled=false
            database.child("Winner").child(id.toString()).setValue("Win")
            builder.setTitle("OYUN SONUCU")
            builder.setMessage("KAZANDIN !!!")
            val dialog=builder.create()
            dialog.show()
        }
        else if((HangiRow)==HarfSayisi-1){
            database.child("DenemeBitti").child(id.toString()).setValue("DenemeBitti")
            btn.isEnabled=false
            greeen *= 10
            yellow *= 5
            val toplam=kalansure+greeen+yellow
            val ss="$kalansure + $greeen + $yellow = $toplam"
            database.child("Esitlik").child(id.toString()).setValue(ss)
        }

        if(HarfSayisi>=HangiRow+1){
            KelimeBulmaListesi[HangiRow+1].forEach(){
                it.isEnabled=true
            }
        }

    }




}