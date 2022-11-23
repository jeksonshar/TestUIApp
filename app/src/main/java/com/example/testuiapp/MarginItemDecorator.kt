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
            // cast margin to dp
        val spase = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDP.toFloat(), view.resources.displayMetrics).toInt()
            // adding marginTop to first item and marginBottom to last item
        when {
            parent.getChildAdapterPosition(view) == 0 -> outRect.top = spase
            parent.getChildAdapterPosition(view) == state.itemCount - 1 -> outRect.bottom = spase
        }
    }
}