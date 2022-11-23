package com.example.testuiapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testuiapp.databinding.FragmentTestRecyclerItemBinding

class TestRecyclerFragmentAdapter :
    ListAdapter<Model, TestRecyclerFragmentViewHolder>(TestRecyclerItemsComparator()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TestRecyclerFragmentViewHolder {
        return TestRecyclerFragmentViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TestRecyclerFragmentViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it)
        }
    }
}

class TestRecyclerFragmentViewHolder(private val binding: FragmentTestRecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Model) {
        binding.tvItemRecycler.text = "${model.orderNumber}"
    }

    companion object {
        fun from(parent: ViewGroup): TestRecyclerFragmentViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = FragmentTestRecyclerItemBinding.inflate(
                inflater,
                parent,
                false
            )
            return TestRecyclerFragmentViewHolder(binding)
        }
    }
}

class TestRecyclerItemsComparator : DiffUtil.ItemCallback<Model>() {
    override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
        return oldItem.orderNumber == newItem.orderNumber
    }
}