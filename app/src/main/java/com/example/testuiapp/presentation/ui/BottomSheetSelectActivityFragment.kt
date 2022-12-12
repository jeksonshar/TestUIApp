package com.example.testuiapp.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testuiapp.R
import com.example.testuiapp.databinding.BottomSheetSelectActivityLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetSelectActivityFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSheetSelectActivityLayoutBinding? = null
    private val binding: BottomSheetSelectActivityLayoutBinding
        get() = _binding!!

    override fun getTheme(): Int {
        return R.style.BottomSheetDialogSelectActivityTheme
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = BottomSheetSelectActivityLayoutBinding.inflate(inflater, container, false)

        binding.btnViewCalling.setOnClickListener {
            val intent = Intent(requireContext(), VideoCallingActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}