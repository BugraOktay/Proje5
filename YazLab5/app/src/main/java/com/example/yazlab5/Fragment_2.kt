package com.example.yazlab5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Fragment_2 : Fragment() {
    private var database: DatabaseReference = Firebase.database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_2, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn=view.findViewById<Button>(R.id.button)
        val oda1=view.findViewById<Button>(R.id.oda1)
        val oda2=view.findViewById<Button>(R.id.oda2)
        val oda3=view.findViewById<Button>(R.id.oda3)
        val oda4=view.findViewById<Button>(R.id.oda4)
        val oda5=view.findViewById<Button>(R.id.oda5)
        val oda6=view.findViewById<Button>(R.id.oda6)
        val oda7=view.findViewById<Button>(R.id.oda7)
        val oda8=view.findViewById<Button>(R.id.oda8)
        val email = arguments?.getString("email")
        val id = arguments?.getString("id")
        btn.setOnClickListener(){
            database.child("OnlineUsers").child("Genel").child(id.toString()).removeValue()
            Navigation.findNavController(it).navigate(Fragment_2Directions.actionFragment2ToFragment1())
        }
        oda1.setOnClickListener(){
            val ref = database.child("OnlineUsers").child("Oda1").child(id.toString())
            ref.setValue(email)
            Navigation.findNavController(it).navigate(Fragment_2Directions.actionFragment2ToOda1(ref.key.toString(),email.toString()))
        }

        oda2.setOnClickListener(){
            val ref = database.child("OnlineUsers").child("Oda2").child(id.toString())
            ref.setValue(email)
            Navigation.findNavController(it).navigate(Fragment_2Directions.actionFragment2ToOda2(ref.key.toString(),email.toString()))
        }

        oda3.setOnClickListener(){
            val ref = database.child("OnlineUsers").child("Oda3").child(id.toString())
            ref.setValue(email)
            Navigation.findNavController(it).navigate(Fragment_2Directions.actionFragment2ToOda3(ref.key.toString(),email.toString()))
        }

        oda4.setOnClickListener(){
            val ref = database.child("OnlineUsers").child("Oda4").child(id.toString())
            ref.setValue(email)
            Navigation.findNavController(it).navigate(Fragment_2Directions.actionFragment2ToOda4(ref.key.toString(),email.toString()))
        }

        oda5.setOnClickListener(){
            val ref = database.child("OnlineUsers").child("Oda5").child(id.toString())
            ref.setValue(email)
            Navigation.findNavController(it).navigate(Fragment_2Directions.actionFragment2ToOda5(ref.key.toString(),email.toString()))
        }

        oda6.setOnClickListener(){
            val ref = database.child("OnlineUsers").child("Oda6").child(id.toString())
            ref.setValue(email)
            Navigation.findNavController(it).navigate(Fragment_2Directions.actionFragment2ToOda6(ref.key.toString(),email.toString()))
        }

        oda7.setOnClickListener(){
            val ref = database.child("OnlineUsers").child("Oda7").child(id.toString())
            ref.setValue(email)
            Navigation.findNavController(it).navigate(Fragment_2Directions.actionFragment2ToOda7(ref.key.toString(),email.toString()))
        }

        oda8.setOnClickListener(){
            val ref = database.child("OnlineUsers").child("Oda8").child(id.toString())
            ref.setValue(email)
            Navigation.findNavController(it).navigate(Fragment_2Directions.actionFragment2ToOda8(ref.key.toString(),email.toString()))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    fun dugumdekiCocukSayisi(s1: String,s2: String, completion: (Int) -> Unit) {
        database.child(s1).child(s2).get().addOnSuccessListener { dataSnapshot ->
            val cocukSayisi = dataSnapshot.children.count()
            completion(cocukSayisi)
        }.addOnFailureListener {
            println("Hata: $it")
            completion(-1)
        }
    }

}