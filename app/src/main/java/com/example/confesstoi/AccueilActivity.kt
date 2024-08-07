package com.example.confesstoi

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.confesstoi.databinding.ActivityAccueilBinding
import com.example.confesstoi.databinding.ActivityCommentBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

//recuperer les donnees stockees dans la base concernant les informations de l'utilisateur
// les commentaires effectuer et la possibilites de voir les nombres de like et de commentaires
data class Comment(val name: String)
//data class CommentBox(val champconfess:String)
class AccueilActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var items:ArrayList<String>
    private lateinit var desc:ArrayList<String>
    private var comments = mutableListOf<Comment>()
    //private lateinit var binding: ActivityAccueilBinding
   // private lateinit var auth: FirebaseAuth
    //private lateinit var confess : FirebaseFirestore
    private lateinit var database : FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Pour le nom des utilisateurs
        items = ArrayList<String>()
        items.add("Loulou Vroumett")
        items.add("Ghost Face")
        items.add("The Valerian")
        items.add("Heartbreak boy")
        items.add("Gossip girl")
        items.add("Eli Hyuga")
        items.add("windBreaker")

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        adapter = Adapter(this, items)
        recyclerView.setAdapter(adapter)


        //Pour les donnees du commentaire
        desc = ArrayList<String>()
        desc.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam in scelerisque sem. Mauris\n" +
                "        volutpat, dolor id interdum ullamcorper, risus dolor egestas lectus, sit amet mattis purus\n" +
                "        dui nec risus.")
        desc.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam in scelerisque sem. Mauris\n" +
                "        volutpat, dolor id interdum ullamcorper, risus dolor egestas lectus, sit amet mattis purus\n" +
                "        dui nec risus.")
        desc.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam in scelerisque sem. Mauris\n" +
                "        volutpat, dolor id interdum ullamcorper, risus dolor egestas lectus, sit amet mattis purus\n" +
                "        dui nec risus.")
        desc.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam in scelerisque sem. Mauris\n" +
                "        volutpat, dolor id interdum ullamcorper, risus dolor egestas lectus, sit amet mattis purus\n" +
                "        dui nec risus.")
        desc.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam in scelerisque sem. Mauris\n" +
                "        volutpat, dolor id interdum ullamcorper, risus dolor egestas lectus, sit amet mattis purus\n" +
                "        dui nec risus.")
        desc.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam in scelerisque sem. Mauris\n" +
                "        volutpat, dolor id interdum ullamcorper, risus dolor egestas lectus, sit amet mattis purus\n" +
                "        dui nec risus.")
        desc.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam in scelerisque sem. Mauris\n" +
                "        volutpat, dolor id interdum ullamcorper, risus dolor egestas lectus, sit amet mattis purus\n" +
                "        dui nec risus.")
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        adapter = Adapter(this, desc)
        recyclerView.setAdapter(adapter)


        database = Firebase.firestore
        database.collection("comments").get().addOnSuccessListener { documents ->

            for (document in documents) {
                val item = document.toObject(Comment::class.java)
                comments.add(item)
            }
        }
        class CommentViewModel : ViewModel() {
        //Afficher les publications faites dans la page commentActivity j'ai deja ecris
        // le code qui me permettait de recuperer les donnees dans firebase
        }
            enableEdgeToEdge()
        setContentView(R.layout.activity_accueil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.accueil)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }


}