package com.example.networktest1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.networktest1.FactApplication
import com.example.networktest1.MainViewModelFactory
import com.example.networktest1.network.FactsAdapter
import com.example.networktest1.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var factsViewModel: FactsViewModel

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        factsViewModel = (requireActivity().application as FactApplication).factory.create(FactsViewModel::class.java)

        binding.buttonFirst.setOnClickListener {
            factsViewModel.getFacts()
        }

        observeData()

    }

    fun observeData(){

        factsViewModel.isLoading.observe(viewLifecycleOwner){
            binding.factsLoading.isVisible = it
        }

        factsViewModel.result.observe(viewLifecycleOwner){
            if(it != null){
                binding.factsRecyclerView.adapter = FactsAdapter(it)
                (binding.factsRecyclerView.adapter as FactsAdapter).setNewList(it)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}