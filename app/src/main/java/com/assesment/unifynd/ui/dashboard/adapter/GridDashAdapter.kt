package com.assesment.unifynd.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.assesment.unifynd.data.model.ModelData
import com.assesment.unifynd.databinding.ItemRowDashBinding
import com.assesment.unifynd.ui.dashboard.view.DashboardFragmentDirections

class GridDashAdapter : RecyclerView.Adapter<GridDashAdapter.ViewHolder>() {

    private var memberData: List<ModelData> = ArrayList()

    inner class ViewHolder(val binding: ItemRowDashBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemRowDashBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(memberData[position]) {

                binding.name.text = this.name
                binding.slug.text = this.slug

                holder.itemView.setOnClickListener {

                    val action =
                        DashboardFragmentDirections.navHomeToNavDetail(this.name, this.slug)
                    it.findNavController().navigate(action)

                }
            }
        }
    }

    override fun getItemCount(): Int {
        return memberData.size
    }

    fun addData(list: List<ModelData>) {
        memberData = list
        notifyDataSetChanged()
    }

}