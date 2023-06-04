package com.example.journalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.journalapp.databinding.ActivityJournalsListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import java.lang.ref.Reference

class journalsList : AppCompatActivity() {
    lateinit var binding:ActivityJournalsListBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var user:FirebaseUser
  var db =FirebaseFirestore.getInstance()
    lateinit var storageReference: StorageReference
    lateinit var journalList:List<Journal>
    lateinit var adapter: recyclerAdapter
    var collectionReference:CollectionReference=db.collection("journal")
    lateinit var nopostTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journals_list)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_journals_list)
        firebaseAuth= Firebase.auth
        user=firebaseAuth.currentUser!!

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        journalList= arrayListOf<Journal>()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_add -> if (user!=null && firebaseAuth!=null){
                startActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}