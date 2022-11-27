package com.example.testuiapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testuiapp.databinding.FragmentHorizontalRecyclerItemBinding

class TestRecyclerHorizontalFragmentAdapter : ListAdapter<ModelRecyclerHorizontal, TestRecyclerHorizontalFragmentViewHolder>(
    TestRecyclerHorizontalItemsComparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestRecyclerHorizontalFragmentViewHolder {
        return TestRecyclerHorizontalFragmentViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TestRecyclerHorizontalFragmentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class TestRecyclerHorizontalFragmentViewHolder(
    private val binding: FragmentHorizontalRecyclerItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: ModelRecyclerHorizontal) {
        binding.apply {
            rbHorizontalItem.rating = model.rating
            tvTitleHorizontalItem.text = model.title
            tvDescriptionHorizontalItem.text = model.description
            tvEventsCount.text = model.eventsCount.toString()
        }
    }

    companion object {
        fun from(parent: ViewGroup): TestRecyclerHorizontalFragmentViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = FragmentHorizontalRecyclerItemBinding.inflate(
                inflater,
                parent,
                false
            )
            return TestRecyclerHorizontalFragmentViewHolder(binding)
        }
    }
}

class TestRecyclerHorizontalItemsComparator : DiffUtil.ItemCallback<ModelRecyclerHorizontal>() {
    override fun areItemsTheSame(oldItem: ModelRecyclerHorizontal, newItem: ModelRecyclerHorizontal): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ModelRecyclerHorizontal, newItem: ModelRecyclerHorizontal): Boolean {
        return oldItem.title == newItem.title
    }
}

