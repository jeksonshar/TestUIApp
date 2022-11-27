package com.example.testuiapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.testuiapp.databinding.FragmentTestRecyclerBinding

class TestRecyclerFragment : Fragment() {

    private var _binding: FragmentTestRecyclerBinding? = null
    private val binding: FragmentTestRecyclerBinding
        get() = _binding!!

    private val adapterVertical by lazy { TestRecyclerVerticalFragmentAdapter() }
    private val adapterHorizontal by lazy { TestRecyclerHorizontalFragmentAdapter() }

    private val itemsListVertical = listOf(
        ModelVertical(1),
        ModelVertical(2),
        ModelVertical(3),
        ModelVertical(4),
        ModelVertical(5),
        ModelVertical(6),
        ModelVertical(7),
        ModelVertical(8),
        ModelVertical(9),
        ModelVertical(10),
        ModelVertical(11),
        ModelVertical(12),
        ModelVertical(13),
        ModelVertical(14),
        ModelVertical(15),
        ModelVertical(16),
        ModelVertical(17),
        ModelVertical(18),
        ModelVertical(19),
        ModelVertical(20),
        ModelVertical(21),
        ModelVertical(22),
        ModelVertical(23),
        ModelVertical(24),
    )

    private val itemsListHorizontal = listOf(
        ModelRecyclerHorizontal(3.5f, "Fantasy game", "Closed", 4),
        ModelRecyclerHorizontal(4f, "Action game", "Open", 8),
        ModelRecyclerHorizontal(2.5f, "Quest", "Closed", 1),
        ModelRecyclerHorizontal(4.5f, "Rally", "Open", 12),
        ModelRecyclerHorizontal(3.5f, "Fantasy game", "Closed", 4),
        ModelRecyclerHorizontal(4f, "Action game", "Open", 8),
        ModelRecyclerHorizontal(2.5f, "Quest", "Closed", 1),
        ModelRecyclerHorizontal(4.5f, "Rally", "Open", 12),
        ModelRecyclerHorizontal(3.5f, "Fantasy game", "Closed", 4),
        ModelRecyclerHorizontal(4f, "Action game", "Open", 8),
        ModelRecyclerHorizontal(2.5f, "Quest", "Closed", 1),
        ModelRecyclerHorizontal(4.5f, "Rally", "Open", 12),
    )

    private lateinit var itemHorizontalMarginDecorator: MarginHorizontalItemDecorator
    private lateinit var topFirstBottomLastMarginDecorator: MarginVerticalItemDecorator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestRecyclerBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.recyclerVertical.adapter = adapterVertical
        binding.recyclerHorizontal.adapter = adapterHorizontal

        val animationAlpha = AnimationUtils.loadAnimation(requireContext(), R.anim.alpha)

        binding.testBanner.circularProgressBarBanner.progress = load
        binding.testBanner.tvProgressBarLoad.text = requireContext().getString(R.string.val_percent, load)

        addHorizontalItemDecorator()
        topFirstBottomLastMarginDecorator = MarginVerticalItemDecorator(marginTopVerticalValueInDP)
        binding.recyclerVertical.addItemDecoration(topFirstBottomLastMarginDecorator)

        adapterHorizontal.submitList(itemsListHorizontal)
        adapterVertical.submitList(itemsListVertical)

        binding.testBanner.btnCloseBannerWithProgress.setOnClickListener {
            it.startAnimation(animationAlpha)
            binding.testBanner.root.visibility = View.GONE
            addHorizontalItemDecorator()
        }

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun addHorizontalItemDecorator() {
        itemHorizontalMarginDecorator = MarginHorizontalItemDecorator(
            marginTopHorizontalValueInDP,
            4,
            binding.testBanner.root.visibility
        )
        binding.recyclerHorizontal.addItemDecoration(itemHorizontalMarginDecorator)
    }

    companion object {
        const val load = 80
        const val marginTopVerticalValueInDP = 12
        const val marginTopHorizontalValueInDP = 32
    }

}