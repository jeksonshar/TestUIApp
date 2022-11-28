package com.example.testuiapp

import android.graphics.Rect
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginVerticalItemDecorator(
    private val topValueInDP: Int,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemPosition = parent.getChildAdapterPosition(view)
        // cast margin to dp
        val topSpase = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, topValueInDP.toFloat(), view.resources.displayMetrics).toInt()
        // adding marginTop to first item and marginBottom to last item
        when (itemPosition) {
            0 -> outRect.top = topSpase/2
        }
        outRect.bottom = topSpase
    }
}