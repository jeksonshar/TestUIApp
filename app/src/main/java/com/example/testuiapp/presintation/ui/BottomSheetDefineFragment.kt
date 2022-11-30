package com.example.testuiapp.presintation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testuiapp.R
import com.example.testuiapp.databinding.BottomSheetDefineLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDefineFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSheetDefineLayoutBinding? = null
    private val binding: BottomSheetDefineLayoutBinding
        get() = _binding!!

    override fun getTheme() = R.style.BottomSheetDialogDefineTheme

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = BottomSheetDefineLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}