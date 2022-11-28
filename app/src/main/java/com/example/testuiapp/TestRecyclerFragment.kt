package com.example.testuiapp

import android.os.*
import android.util.Log
import android.view.HapticFeedbackConstants
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

//    lateinit var vibrator: Vibrator

    private val adapterVertical by lazy { TestRecyclerVerticalFragmentAdapter() }
    private val adapterHorizontal by lazy {
        TestRecyclerHorizontalFragmentAdapter(
            clickListener = object : ModelHorizontalClickListener {
                override fun onLongClickListener(model: ModelRecyclerHorizontal) {
                    Log.d("TAG", "vibrator +++ ")
//      работает с дефолтной длительностью
//                    if (Build.VERSION.SDK_INT >= 26) {
//                        (requireContext().getSystemService(VIBRATOR_SERVICE) as Vibrator).vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
//                    } else {
//                        (requireContext().getSystemService(VIBRATOR_SERVICE) as Vibrator).vibrate(1000)
//                    }

//     при -1 нестабильные мибрации дефолтной длительности, при 0 и положит числах - бесконечные повторения
//                    val delay = 0L
//                    val vibrate = 100L
//                    val sleep = 300L
//                    val repeat = 0
//                    val pattern: LongArray = longArrayOf(delay, vibrate, sleep)
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                        vibrator.vibrate(VibrationEffect.createWaveform(pattern, repeat))
//                    } else {
//                        vibrator.vibrate(pattern, repeat)
//                    }

                    //          работает стабильно, пермишн не нужен
                    requireView().performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                }
            }
        )
    }

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

//        vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            val vibratorManager = requireContext().getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager
//            vibratorManager.defaultVibrator
//        } else {
//            requireActivity().getSystemService(VIBRATOR_SERVICE) as Vibrator
//        }

        binding.recyclerVertical.adapter = adapterVertical
        binding.recyclerHorizontal.adapter = adapterHorizontal

        val animationAlpha = AnimationUtils.loadAnimation(requireContext(), R.anim.animation_alpha)

        binding.testBanner.circularProgressBarBanner.progress = load
        binding.testBanner.tvProgressBarLoad.text = requireContext().getString(R.string.val_percent, load)

        addHorizontalItemDecorator()
        topFirstBottomLastMarginDecorator = MarginVerticalItemDecorator(marginTopValueInDPForVerticalItem)
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
            marginTopValueInDPForHorizontalItem,
            marginStartValueInDPForHorizontalItem,
            binding.testBanner.root.visibility
        )
        binding.recyclerHorizontal.addItemDecoration(itemHorizontalMarginDecorator)
    }

    companion object {
        const val load = 80
        const val marginTopValueInDPForVerticalItem = 12
        const val marginTopValueInDPForHorizontalItem = 32
        const val marginStartValueInDPForHorizontalItem = 4
    }

}