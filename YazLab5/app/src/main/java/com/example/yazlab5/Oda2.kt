package com.example.yazlab5

import android.app.AlertDialog
import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.Navigation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Oda2 : Fragment() {
    private val oda ="Oda2"
    private var database: DatabaseReference = Firebase.database.reference
    private lateinit var postListener:ValueEventListener
    private lateinit var istekgelenListener:ValueEventListener
    private lateinit var istekgonderenListener:ValueEventListener
    private lateinit var oyundaListener:ValueEventListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_oda2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exit=view.findViewById<Button>(R.id.exit)

        val btn1 = view.findViewById<Button>(R.id.button1)
        val btn2 = view.findViewById<Button>(R.id.button2)
        val btn3 = view.findViewById<Button>(R.id.button3)
        val btn4 = view.findViewById<Button>(R.id.button4)
        val btn5 = view.findViewById<Button>(R.id.button5)
        val btn6 = view.findViewById<Button>(R.id.button6)
        val btn7 = view.findViewById<Button>(R.id.button7)
        val btn8 = view.findViewById<Button>(R.id.button8)
        val btn9 = view.findViewById<Button>(R.id.button9)
        val btn10 = view.findViewById<Button>(R.id.button10)

        val dizi = arrayOf(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10)
        val Rakipler = ArrayList<Button>()

        val email = arguments?.getString("email")
        val id = arguments?.getString("id")
        var Rakip_id="-1"

        istekgelenListener= object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val builder = AlertDialog.Builder(context)
                Rakip_id=snapshot.value.toString()

                if(snapshot.value!=null){
                    builder.setTitle("Oyun İsteği")
                    builder.setMessage("${snapshot.value} Tarafından Gelen Oyun İsteğini Kabul Ediyor musun?")
                    val inflater = layoutInflater
                    val dialogView = inflater.inflate(R.layout.zaman, null)
                    val progressBar = dialogView.findViewById<ProgressBar>(R.id.progressBar)
                    builder.setView(dialogView)

                    builder.setPositiveButton("Evet") { dialog, which ->
                        database.child("KabulEdildiMi").child(snapshot.value.toString()).setValue("Evet")
                        database.child("İstekler").child(id.toString()).removeValue()
                        database.child("Oyunda").child(oda).child(id.toString()).setValue("Evet")
                        Navigation.findNavController(view).navigate(Oda2Directions.actionOda2ToGame2(id.toString(),email.toString(),Rakip_id))
                    }

                    builder.setNegativeButton("Hayır") { dialog, which ->
                        database.child("KabulEdildiMi").child(snapshot.value.toString()).setValue("Hayır")
                        database.child("İstekler").child(id.toString()).removeValue()
                    }

                    val dialog = builder.create()
                    dialog.setCancelable(false)
                    dialog.show()

                    val timer = object : CountDownTimer(10000, 100) {
                        override fun onTick(millisUntilFinished: Long) {
                            val progress = ((10000 - millisUntilFinished) / 100).toInt() // ProgressDialog'un ilerlemesini ayarla
                            progressBar.progress = progress
                        }

                        override fun onFinish() {
                            database.child("İstekler").child(id.toString()).removeValue()
                            dialog.dismiss()
                        }
                    }.start()

                }}
            override fun onCancelled(error: DatabaseError) {
                println("htta")
            }
        }

        database.child("İstekler").child(id.toString()).addValueEventListener(istekgelenListener)

        val progressDialog = ProgressDialog(context)
        istekgonderenListener=object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.value!=null){
                    if(snapshot.value=="Evet"){
                        database.child("Oyunda").child(oda).child(id.toString()).setValue("Evet")
                        database.child("KabulEdildiMi").child(id.toString()).removeValue()
                        Toast.makeText(requireContext(), "İstek Kabul Edildi", Toast.LENGTH_SHORT).show()
                        progressDialog.dismiss()
                        Navigation.findNavController(view).navigate(Oda2Directions.actionOda2ToGame2(id.toString(),email.toString(),Rakip_id))
                    }
                    else if(snapshot.value=="Hayır"){
                        database.child("KabulEdildiMi").child(id.toString()).removeValue()
                        Toast.makeText(requireContext(), "İstek Reddedildi", Toast.LENGTH_SHORT).show()
                        progressDialog.dismiss()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("hatta")
            }

        }
        database.child("KabulEdildiMi").child(id.toString()).addValueEventListener(istekgonderenListener)



        postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (i in 0 until 10) {
                    dizi[i].visibility= View.VISIBLE
                    val childSnapshot = dataSnapshot.children.elementAtOrNull(i)
                    val value = childSnapshot?.value
                    val key = childSnapshot?.key
                    if (key != null) {
                        dizi[i].text = key.toString()
                        if(value!=email){
                            Rakipler.add(dizi[i])
                            dizi[i].setBackgroundColor(Color.parseColor("#FCFCAF"))
                        }
                        else{
                            dizi[i].setBackgroundColor(Color.parseColor("#80ff80"))
                        }


                    }
                    else{
                        dizi[i].visibility= View.INVISIBLE
                    }
                }

                Rakipler.forEach { btn ->

                    btn.setOnClickListener {
                        Toast.makeText(requireContext(), "İstek Gönderilen Kişi:${btn.text}", Toast.LENGTH_SHORT).show()
                        database.child("İstekler").child(btn.text.toString()).setValue(id.toString())
                        Rakip_id=btn.text.toString()

                        progressDialog.setTitle("Cevap Bekleniyor")
                        progressDialog.setMessage("İşlem devam ediyor...")
                        progressDialog.setCancelable(false) // Kullanıcı geri tuşunu kullanarak kapatamaz
                        progressDialog.show()
                        val handler = Handler(Looper.getMainLooper())
                        handler.postDelayed({
                            progressDialog.dismiss()
                        }, 10000)

                    }
                }


            }
            override fun onCancelled(databaseError: DatabaseError) {
                println("Hataaa")
            }
        }

        database.child("OnlineUsers").child(oda).addValueEventListener(postListener)

        oyundaListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(r in Rakipler){
                    if(snapshot.children.count()==0){
                        r.isEnabled=true
                    }
                    for(i in snapshot.children){
                        if(i.key.toString()==r.text.toString()){
                            r.isEnabled=false
                            break
                        }
                        else{
                            r.isEnabled=true
                        }
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                println(error)
            }

        }
        database.child("Oyunda").child(oda).addValueEventListener(oyundaListener)

        exit.setOnClickListener(){

            val id = arguments?.getString("id")
            database.child("OnlineUsers").child(oda).child(id.toString()).removeValue()
            Navigation.findNavController(it).navigate(Oda2Directions.actionOda2ToFragment2(email.toString(),id.toString()))
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val id = arguments?.getString("id")
            val email = arguments?.getString("email")
            database.child("OnlineUsers").child(oda).child(id.toString()).removeValue()
            Navigation.findNavController(view).navigate(Oda2Directions.actionOda2ToFragment2(email.toString(),id.toString()))
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        val id = arguments?.getString("id")
        val email = arguments?.getString("email")
        database.child("OnlineUsers").child(oda).removeEventListener(postListener)
        database.child("İstekler").child(id.toString()).removeEventListener(istekgelenListener)
        database.child("KabulEdildiMi").child(id.toString()).removeEventListener(istekgonderenListener)
        database.child("Oyunda").child(oda).removeEventListener(oyundaListener)
    }



}