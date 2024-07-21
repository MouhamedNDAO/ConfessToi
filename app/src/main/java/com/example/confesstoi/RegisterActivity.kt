package com.example.confesstoi

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import com.example.confesstoi.databinding.ActivityRegisterBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Le Listener pour le bouton se connecter
        binding.connecter.setOnClickListener{
            val intent = Intent (this,LoginActivity::class.java)
            startActivity(intent)
        }

        //Le Listener pour le bouton s'inscrire
        binding.inscrire.setOnClickListener{
            val pseudo = binding.user.text.toString()
            val mdp = binding.mdp.text.toString()
            val mdp2 = binding.mdp2.text.toString()
            val birth = binding.birth.text.toString()

            if (pseudo.isNotEmpty() && mdp.isNotEmpty() && mdp2.isNotEmpty() && birth.isNotEmpty()) {
                if (mdp == mdp2) {
                    firebaseAuth.createUserWithEmailAndPassword(pseudo,mdp).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent (this,AccueilActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                }else{
                    Toast.makeText(this, "Les mot de passe ne sont pas identiques", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Aucun champ ne doit etre vide", Toast.LENGTH_SHORT).show()
            }
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    /**
    class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

         fun onCreateDialog(savedInstanceState: Bundle): Dialog {
            // Use the current date as the default date in the picker.
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // Create a new instance of DatePickerDialog and return it.
            return DatePickerDialog(requireContext(), this, year, month, day)

        }

        override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
            // Do something with the date the user picks.
            findViewById<TextInputEditText>(R.id.birth).setOnClickListener {
                val newFragment = DatePickerFragment()
                val supportFragmentManager
                newFragment.show(supportFragmentManager, "datePicker")
            }

        }
    }
    **/
}