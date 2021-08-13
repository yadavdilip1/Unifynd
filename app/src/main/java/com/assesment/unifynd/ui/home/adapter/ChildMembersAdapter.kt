package com.assesment.unifynd.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.assesment.unifynd.data.model.ModelData
import com.assesment.unifynd.databinding.ItemRowChildBinding
import com.assesment.unifynd.ui.home.view.HomeFragmentDirections

class ChildMembersAdapter(private val memberData: List<ModelData.Member>) :
    RecyclerView.Adapter<ChildMembersAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemRowChildBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemRowChildBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(memberData[position]) {

                binding.name.text = this.name
                binding.slug.text = this.slug

                holder.itemView.setOnClickListener {

                    val action = HomeFragmentDirections.navHomeToNavDetail(this.name, this.slug)
                    it.findNavController().navigate(action)

                }
            }
        }
    }

    override fun getItemCount(): Int {
        return memberData.size
    }


}