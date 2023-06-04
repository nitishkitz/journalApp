package com.example.journalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.journalapp.databinding.ActivityJournalsListBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference

class journalsList : AppCompatActivity() {
    lateinit var binding: ActivityJournalsListBinding

    // Firebase References
    lateinit var  firebaseAuth:FirebaseAuth
    lateinit var  user: FirebaseUser
    var db =  FirebaseFirestore.getInstance()
    lateinit var storageReference: StorageReference
    var collectionReference: CollectionReference = db.collection("Journal")


    lateinit var journalList: MutableList<Journal>
    lateinit var adapter: recyclerAdapter

    lateinit var noPostsTextView: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_journals_list)


        // Firebase Auth
        firebaseAuth = Firebase.auth
        user = firebaseAuth.currentUser!!

        // RecyclerView
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this)

        // Posts arraylist
        journalList = arrayListOf<Journal>()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_add -> if (user != null && firebaseAuth != null){
                val intent = Intent(this, AddJournalActivity::class.java)
                startActivity(intent)
            }

            R.id.action_signout -> {
                if (user != null && firebaseAuth != null){
                    firebaseAuth.signOut()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }

        }
        return super.onOptionsItemSelected(item)
    }

    // Getting all posts
    override fun onStart() {
        super.onStart()


        collectionReference.whereEqualTo("userId",
            user.uid)
            .get()
            .addOnSuccessListener {
                Log.i("TAGY","sizey: ${it.size()}" )
                if (!it.isEmpty){

                    Log.i("TAGY","Elements: ${it}" )

                    for (document in it){
                        var journal = Journal(
                            document.data["title"].toString(),
                            document.data.get("thoughts").toString(),
                            document.data.get("imageUrl").toString(),
                            document.data.get("userId").toString(),
                            document.data.get("timeAdded") as Timestamp,
                            document.data.get("username").toString(),

                            )

                        journalList.add(journal)

                    }


                    // RecyclerView
                    adapter = recyclerAdapter(
                        this, journalList
                    )
                    binding.recyclerView.setAdapter(adapter)
                    adapter.notifyDataSetChanged()
                }
                else{
                    binding.listNoPosts.visibility = View.VISIBLE
                }
            }.addOnFailureListener {
                Toast.makeText(
                    this,
                    "Opps! Something went wrong!",
                    Toast.LENGTH_SHORT
                ).show()
            }



    }


}