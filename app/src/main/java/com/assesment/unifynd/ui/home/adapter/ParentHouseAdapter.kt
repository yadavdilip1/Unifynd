package com.assesment.unifynd.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assesment.unifynd.data.model.ModelData
import com.assesment.unifynd.databinding.ItemRowParentBinding

class ParentHouseAdapter : RecyclerView.Adapter<ParentHouseAdapter.ViewHolder>() {

    private var modelDataHouseList: List<ModelData> = ArrayList()

    inner class ViewHolder(val binding: ItemRowParentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemRowParentBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(modelDataHouseList[position]) {

                binding.contentTitle.text = this.name
                val childMembersAdapter = ChildMembersAdapter(this.members)
                binding.childRecyclerView.layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                binding.childRecyclerView.adapter = childMembersAdapter

                holder.itemView.setOnClickListener {

                }

            }
        }
    }

    override fun getItemCount(): Int {
        return modelDataHouseList.size
    }

    fun addData(list: List<ModelData>) {
        modelDataHouseList = list
        notifyDataSetChanged()
    }

}