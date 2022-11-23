package com.example.testuiapp

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class TopFirstBottomLastMarginItemDecorator(private val valueInDP: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val lastItemIndex = state.itemCount - 1
        val itemPosition = parent.getChildAdapterPosition(view)
            // cast margin to dp
        val spase = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDP.toFloat(), view.resources.displayMetrics).toInt()
            // adding marginTop to first item and marginBottom to last item
        when (itemPosition) {
            0 -> outRect.top = spase
            lastItemIndex -> outRect.bottom = spase
        }
    }
}