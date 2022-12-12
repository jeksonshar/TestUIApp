package com.example.testuiapp.presentation.ui.util

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.example.testuiapp.R
import com.google.android.material.shape.EdgeTreatment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.ShapePath

class CustomToolbar(
    context: Context,
    attributeSet: AttributeSet
) : Toolbar(context, attributeSet) {

    override fun onFinishInflate() {
        val shapeAppearanceModel = ShapeAppearanceModel()
            .toBuilder()
            .setBottomEdge(RoundedEdgeTreatment())
            .build()
        val shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
            setTint(ContextCompat.getColor(context, R.color.toolbar_color))
        }
        ViewCompat.setBackground(this, shapeDrawable)

        super.onFinishInflate()
    }

    inner class RoundedEdgeTreatment : EdgeTreatment() {

        private val arcAngle = 800f

        override fun getEdgePath(
            length: Float,
            center: Float,
            interpolation: Float,
            shapePath: ShapePath
        ) {
            val interpolatedRadius = arcAngle * interpolation
            shapePath.addArc(
                0f - center,
                0f,
                length + center,
                interpolatedRadius,
                180f,
                180f
            )
        }
    }

}