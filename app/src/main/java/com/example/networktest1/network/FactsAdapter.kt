package com.example.networktest1.network

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.networktest1.databinding.FactsListBinding
import com.example.networktest1.model.Fact

class FactsAdapter(private var factsList: Fact): RecyclerView.Adapter<FactsAdapter.FactsViewHolder>(){

    class FactsViewHolder(val binding: FactsListBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        return FactsViewHolder(FactsListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return factsList.data?.size ?: 0
    }

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        val model = factsList.data?.get(position)

        holder.binding.factLengthTv.text = "Fact no. ${model?.length}"
        holder.binding.factTv.text = model?.fact
    }

    fun setNewList(newList: Fact){
        factsList = newList
        notifyDataSetChanged()
    }

}