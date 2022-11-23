package com.example.testuiapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testuiapp.databinding.FragmentTestRecyclerBinding

class TestRecyclerFragment : Fragment() {

    private var _binding: FragmentTestRecyclerBinding? = null
    private val binding: FragmentTestRecyclerBinding
        get() = _binding!!

    private val adapter by lazy { TestRecyclerFragmentAdapter() }

    private val itemsList = listOf(
        Model(1),
        Model(2),
        Model(3),
        Model(4),
        Model(5),
        Model(6),
        Model(7),
        Model(8),
        Model(9),
        Model(10),
        Model(11),
        Model(12),
        Model(13),
        Model(14),
        Model(15),
        Model(16),
        Model(17),
        Model(18),
        Model(19),
        Model(20),
        Model(21),
        Model(22),
        Model(23),
        Model(24),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestRecyclerBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.recycler.adapter = adapter
        adapter.submitList(itemsList)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}