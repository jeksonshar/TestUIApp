package com.example.testuiapp

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginHorizontalItemDecorator(
    private val topValueInDP: Int,
    private val startValueInDP: Int,
    private val bannerVisibility: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemPosition = parent.getChildAdapterPosition(view)
        val topSpase = calculateIntValue(view, topValueInDP)
        val startSpase = calculateIntValue(view, startValueInDP)

        if(bannerVisibility != View.VISIBLE) outRect.top = topSpase
        when (itemPosition) {
            0 -> outRect.left = startSpase
        }
    }

    private fun calculateIntValue(view: View, valueInDP: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDP.toFloat(), view.resources.displayMetrics).toInt()
    }
}