package com.example.testuiapp.presentation.ui.verticalrecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testuiapp.databinding.FragmentVerticalRecyclerItemBinding
import com.example.testuiapp.business.models.ModelVertical

class TestRecyclerVerticalFragmentAdapter :
    ListAdapter<ModelVertical, TestRecyclerVerticalFragmentViewHolder>(TestRecyclerVerticalItemsComparator()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TestRecyclerVerticalFragmentViewHolder {
        return TestRecyclerVerticalFragmentViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TestRecyclerVerticalFragmentViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it)
        }
    }
}

class TestRecyclerVerticalFragmentViewHolder(private val binding: FragmentVerticalRecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: ModelVertical) {
        binding.tvItemRecycler.text = "${model.orderNumber}"
    }

    companion object {
        fun from(parent: ViewGroup): TestRecyclerVerticalFragmentViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = FragmentVerticalRecyclerItemBinding.inflate(
                inflater,
                parent,
                false
            )
            return TestRecyclerVerticalFragmentViewHolder(binding)
        }
    }
}

class TestRecyclerVerticalItemsComparator : DiffUtil.ItemCallback<ModelVertical>() {
    override fun areItemsTheSame(oldItem: ModelVertical, newItem: ModelVertical): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ModelVertical, newItem: ModelVertical): Boolean {
        return oldItem.orderNumber == newItem.orderNumber
    }
}