package com.example.journalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.startup.StartupException
import com.example.journalapp.databinding.ActivityMainBinding
import com.example.journalapp.databinding.ActivitySignUpBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.createAcctBTN.setOnClickListener(){
            val intent= Intent(this,signUpActivity::class.java)
            startActivity(intent)
        }
    }
}