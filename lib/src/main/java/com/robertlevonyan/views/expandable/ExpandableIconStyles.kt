package com.robertlevonyan.view.expandable

enum class ExpandableIconStyles {
    SQUARE, CIRCLE, ROUNDED_SQUARE;

    companion object {
        fun getByIndex(index: Int): ExpandableIconStyles {
            return when (index) {
                CIRCLE.ordinal -> CIRCLE
                ROUNDED_SQUARE.ordinal -> ROUNDED_SQUARE
                else -> SQUARE
            }
        }
    }
}