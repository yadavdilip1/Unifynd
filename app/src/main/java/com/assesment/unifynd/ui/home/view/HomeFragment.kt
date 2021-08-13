package com.assesment.unifynd.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.assesment.unifynd.databinding.FragmentHomeBinding
import com.assesment.unifynd.ui.home.adapter.ParentHouseAdapter
import com.assesment.unifynd.data.viewModel.CommonViewModel
import com.assesment.unifynd.utils.Resource
import com.assesment.unifynd.utils.hide
import com.assesment.unifynd.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: CommonViewModel by viewModels()
    private lateinit var parentHouseAdapter: ParentHouseAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
        doObserveWork()

    }

    private fun setUpViews() {

        binding.parentRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        parentHouseAdapter = ParentHouseAdapter()
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
                    parentHouseAdapter.addData(result.value)
                    parentHouseAdapter.notifyDataSetChanged()
                }
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}