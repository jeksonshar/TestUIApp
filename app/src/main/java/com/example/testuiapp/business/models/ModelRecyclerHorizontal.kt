package com.example.testuiapp.business.models

data class ModelRecyclerHorizontal(
    val rating: Float,
    val title: String,
    val description: String,
    val eventsCount: Int,
) {
    val isAnimate: Boolean get() = _isAnimate
    private var _isAnimate: Boolean = false

    fun changeAnimatedFlag(isNeedToAnimate: Boolean) {
        _isAnimate = isNeedToAnimate
    }
}
