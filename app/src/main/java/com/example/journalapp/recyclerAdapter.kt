package com.example.journalapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.journalapp.databinding.JournalRowBinding

class recyclerAdapter(val context: Context, var journalList:List<Journal>)
    : RecyclerView.Adapter<recyclerAdapter.MyViewHolder>()
{

    lateinit var binding: JournalRowBinding


    // View Holder
    class MyViewHolder(var binding: JournalRowBinding) :
        RecyclerView.ViewHolder(binding.root ) {
        fun bind(journal: Journal){
            binding.journal  = journal




        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        binding = JournalRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )



        return  MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val journal = journalList[position]


        holder.bind(journal)

    }

    override fun getItemCount(): Int = journalList.size



}
