package com.example.testuiapp.presintation.ui.horizontalrecycler

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testuiapp.R
import com.example.testuiapp.databinding.FragmentHorizontalRecyclerItemBinding
import com.example.testuiapp.business.models.ModelRecyclerHorizontal

class TestRecyclerHorizontalFragmentAdapter(
    private val clickListener: ModelHorizontalClickListener
) : ListAdapter<ModelRecyclerHorizontal, TestRecyclerHorizontalFragmentViewHolder>(
    TestRecyclerHorizontalItemsComparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestRecyclerHorizontalFragmentViewHolder {
        return TestRecyclerHorizontalFragmentViewHolder.from(parent, clickListener)
    }

    override fun onBindViewHolder(holder: TestRecyclerHorizontalFragmentViewHolder, position: Int) {
        val animationShake = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.animation_shake)
        val item = getItem(position)

        holder.bind(item)

        if (item.isAnimate) {
            Log.d("TAG", "onBindViewHolder: position = $position")
            holder.itemView.startAnimation(animationShake)
            item.changeAnimatedFlag(false)
        }
    }

}

class TestRecyclerHorizontalFragmentViewHolder(
    private val binding: FragmentHorizontalRecyclerItemBinding,
    private val clickListener: ModelHorizontalClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    private var localModel: ModelRecyclerHorizontal? = null
    private val animationScale = AnimationUtils.loadAnimation(binding.root.context, R.anim.animation_scale)

    init {
        binding.root.setOnLongClickListener { view ->
            localModel?.let {
                clickListener.onLongClickListener(it)
                view.startAnimation(animationScale)
            }
            /** возвращая true получаю двойную вибрацию: системную + казанную в clickListener */
            return@setOnLongClickListener true
        }
        animationScale.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                clickListener.setShakeAnimationState()
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
    }

    fun bind(model: ModelRecyclerHorizontal) {
        localModel = model
        binding.apply {
            rbHorizontalItem.rating = model.rating
            tvTitleHorizontalItem.text = model.title
            tvDescriptionHorizontalItem.text = model.description
            tvEventsCount.text = model.eventsCount.toString()
        }
    }

    companion object {
        fun from(parent: ViewGroup, clickListener: ModelHorizontalClickListener): TestRecyclerHorizontalFragmentViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = FragmentHorizontalRecyclerItemBinding.inflate(
                inflater,
                parent,
                false
            )
            return TestRecyclerHorizontalFragmentViewHolder(binding, clickListener)
        }
    }
}

class TestRecyclerHorizontalItemsComparator : DiffUtil.ItemCallback<ModelRecyclerHorizontal>() {
    override fun areItemsTheSame(oldItem: ModelRecyclerHorizontal, newItem: ModelRecyclerHorizontal): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ModelRecyclerHorizontal, newItem: ModelRecyclerHorizontal): Boolean {
        return oldItem.title == newItem.title  && oldItem.isAnimate == newItem.isAnimate
    }
}

