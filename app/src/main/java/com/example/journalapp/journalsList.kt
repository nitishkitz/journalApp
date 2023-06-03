package com.example.journalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.journalapp.databinding.ActivityJournalsListBinding

class journalsList : AppCompatActivity() {
    lateinit var binding:ActivityJournalsListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journals_list)
    }
}