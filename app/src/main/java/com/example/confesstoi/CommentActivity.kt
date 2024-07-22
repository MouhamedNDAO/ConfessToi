package com.example.confesstoi

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.confesstoi.databinding.ActivityCommentBinding
import com.google.firebase.Firebase
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.app
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

//Pour cette partie je vais m'en charger
// je dois faire la liaison entre la class et la bd
// afin de pouvoir recuper les publications faites et de le recuperer dans la page accueil
class CommentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommentBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //Ecoute du bouton publier
        binding.publier.setOnClickListener {
            var confession = Comment(binding.champs.text.toString())
            if (confession.name.isNotEmpty()) {
                //Recuperer les donnees saisis dans le champ EditText
                database = Firebase.firestore
                database.collection("comments").add(confession)
                val intent = Intent(this,AccueilActivity::class.java)
                startActivity(intent)
            }
        }
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_comment)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.champs)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}