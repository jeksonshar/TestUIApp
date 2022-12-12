package com.example.testuiapp.presentation.ui.horizontalrecycler

import com.example.testuiapp.business.models.ModelRecyclerHorizontal

interface ModelHorizontalClickListener {

    fun onLongClickListener(model: ModelRecyclerHorizontal)

    fun setShakeAnimationState()
}