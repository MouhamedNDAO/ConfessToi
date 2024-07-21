package com.example.confesstoi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.confesstoi.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()

        //Le Listener pour le bouton se connecter
        binding.connecter.setOnClickListener{
            val pseudo = binding.user.text.toString()
            val pwd = binding.mdp.text.toString()

            if(pseudo.isNotEmpty() && pwd.isNotEmpty()){
            firebaseAuth.signInWithEmailAndPassword(pseudo,pwd).addOnCompleteListener{
                if (it.isSuccessful){
            val intent = Intent (this,AccueilActivity::class.java)
            startActivity(intent)
             }else{
                    Toast.makeText(this,it.exception.toString() , Toast.LENGTH_SHORT).show()
            }
            }
        }else {
                Toast.makeText(this, "Aucun champ ne doit etre vide", Toast.LENGTH_SHORT).show()
        }
        }

        //Le Listener pour le bouton s'inscrire
        binding.inscrire.setOnClickListener{
            val intent = Intent (this,RegisterActivity::class.java)
            startActivity(intent)
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /**
    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        if (firebaseAuth.currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }
    **/
}