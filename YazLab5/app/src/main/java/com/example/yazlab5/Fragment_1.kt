package com.example.yazlab5

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.checkerframework.checker.units.qual.K

class Fragment_1 : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var database: DatabaseReference = Firebase.database.reference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_1, container, false)

        val btn1 = view.findViewById<Button>(R.id.btn1)
        val btn2 = view.findViewById<Button>(R.id.btn2)
        val emailEditText = view.findViewById<EditText>(R.id.email)
        val passwordEditText = view.findViewById<EditText>(R.id.sifre)

        auth = FirebaseAuth.getInstance()

        btn2.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Kayıt Başarılı", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(requireContext(), "Kayıt Başarısız", Toast.LENGTH_SHORT).show()
                }
            }
        }
        btn1.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val ref = database.child("OnlineUsers").child("Genel").push()
            ref.setValue(email)
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Giriş Başarılı", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(it).navigate(Fragment_1Directions.actionFragment1ToFragment2(email,ref.key.toString()))

                } else {
                    Toast.makeText(requireContext(), "Giriş Başarısız", Toast.LENGTH_SHORT).show()
                }
            }
        }




        return view
    }




}