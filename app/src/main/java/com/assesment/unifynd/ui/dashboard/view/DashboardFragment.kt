package com.assesment.unifynd.ui.dashboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.assesment.unifynd.databinding.FragmentDashboardBinding
import com.assesment.unifynd.ui.dashboard.adapter.GridDashAdapter
import com.assesment.unifynd.ui.dashboard.adapter.TopDashAdapter
import com.assesment.unifynd.data.viewModel.CommonViewModel
import com.assesment.unifynd.utils.Resource
import com.assesment.unifynd.utils.hide
import com.assesment.unifynd.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: CommonViewModel by viewModels()
    private lateinit var parentHouseAdapter: GridDashAdapter
    private lateinit var topDashAdapter: TopDashAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
        doObserveWork()

    }

    private fun setUpViews() {

        binding.topRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        topDashAdapter = TopDashAdapter()
        binding.topRecyclerView.adapter = topDashAdapter

        binding.parentRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        parentHouseAdapter = GridDashAdapter()
        binding.parentRecyclerView.adapter = parentHouseAdapter
    }

    private fun doObserveWork() {

        homeViewModel.homeListItemsLiveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> binding.progressBar.show()

                is Resource.Failure -> {

                    binding.progressBar.hide()
                }
                is Resource.Success -> {

                    binding.progressBar.hide()
                    topDashAdapter.notifyDataSetChanged()
                    topDashAdapter.addData(result.value)

                    parentHouseAdapter.notifyDataSetChanged()
                    parentHouseAdapter.addData(result.value)
                }
            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}