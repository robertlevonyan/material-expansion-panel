package com.robertlevonyan.views.expandable

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.setMargins
import com.robertlevonyan.view.expandable.ExpandableIconStyles
import getCircleBitmap
import getRoundedSquareBitmap
import kotlin.properties.Delegates
import android.view.Display
import androidx.core.content.ContextCompat.getSystemService
import android.view.WindowManager
import android.R.attr.x
import android.graphics.Point


class Expandable : FrameLayout {
    var icon: Drawable? = null
        set(value) {
            field = value
            buildView()
        }
    var iconStyle = ExpandableIconStyles.SQUARE
        set(value) {
            field = value
            buildView()
        }
    var animateExpand = false
        set(value) {
            field = value
            buildView()
        }
    @set:JvmName("setBackgroundColor_")
    var backgroundColor = 0
        set(value) {
            field = value
            buildView()
        }
    var expandIndicator: Drawable? = null
        set(value) {
            field = value
            buildView()
        }

    private val headerLayout = FrameLayout(context)
    private val iconImage = ImageView(context)
    private val expandIcon = ImageView(context)

    private var isExpanded by Delegates.observable(false) { _, _, newValue ->
        if (newValue) expandingListener?.onExpanded() else expandingListener?.onCollapsed()
    }
    private var headerView: View? = null
    private var expandableView: View? = null
    private var collapsedViewHeight = 0
    private var expandableHeight = 0

    var expandingListener: ExpandingListener? = null

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initTypedArray(attrs)
    }

    private fun initTypedArray(attrs: AttributeSet?) {
        if (attrs == null) return

        val ta = context.theme.obtainStyledAttributes(attrs, R.styleable.Expandable, 0, 0)

        icon = ta.getDrawable(R.styleable.Expandable_exp_icon)
        iconStyle = ExpandableIconStyles.getByIndex(ta.getInt(R.styleable.Expandable_exp_iconStyle, ExpandableIconStyles.SQUARE.ordinal))
        animateExpand = ta.getBoolean(R.styleable.Expandable_exp_animateExpand, false)
        backgroundColor = ta.getColor(R.styleable.Expandable_exp_backgroundColor, ContextCompat.getColor(context, R.color.colorDefaultBackground))
        expandIndicator = ta.getDrawable(R.styleable.Expandable_exp_expandIndicator)

        collapsedViewHeight = resources.getDimension(R.dimen.collapsed_size).toInt()
        setBackgroundColor(backgroundColor)

        ta.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        buildView()
    }

    private fun buildView() {
        if (childCount == 0) return
        if (childCount > 2) throw IllegalStateException("ExpandableView can have only two child views$childCount")

        if (headerView == null) headerView = getChildAt(0)
                ?: throw IllegalStateException("Add a HeaderView to your ExpandableView")
        if (expandableView == null) expandableView = getChildAt(1)
                ?: throw IllegalStateException("Add a ContentView to your ExpandableView")

        removeView(headerView)
        creteHeader()
        createExpandableHeight()
        resetExpandableHeight()
    }

    private fun creteHeader() {
        headerLayout.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, collapsedViewHeight)
        createHeaderContent()
        createHeaderIcon()
        createExpandIcon()
        headerLayout.setOnClickListener { if (isExpanded) collapse() else expand() }
        expandableView?.setOnClickListener { collapse() }
        if (indexOfChild(headerLayout) == -1) addView(headerLayout)
    }

    private fun createHeaderIcon() {
        if (icon == null) return

        iconImage.setImageDrawable(when (iconStyle) {
            ExpandableIconStyles.CIRCLE -> icon?.getCircleBitmap(backgroundColor)?.toDrawable(resources)
            ExpandableIconStyles.ROUNDED_SQUARE -> icon?.getRoundedSquareBitmap(backgroundColor)?.toDrawable(resources)
            else -> icon
        })
        iconImage.scaleType = ImageView.ScaleType.CENTER_CROP

        val padding = context.resources.getDimensionPixelSize(R.dimen.icon_margin)
        val iconSize = context.resources.getDimensionPixelSize(R.dimen.collapsed_icon_size)

        iconImage.apply {
            layoutParams = LayoutParams(iconSize, iconSize, Gravity.START or Gravity.CENTER_VERTICAL)
                    .apply { setMargins(padding) }
        }

        if (headerLayout.indexOfChild(iconImage) == -1) headerLayout.addView(iconImage)
    }

    private fun createExpandIcon() {
        val padding = context.resources.getDimensionPixelSize(R.dimen.icon_margin)
        val iconSize = context.resources.getDimensionPixelSize(R.dimen.expand_drawable_size)

        expandIcon.apply {
            layoutParams = LayoutParams(iconSize, iconSize, Gravity.END or Gravity.CENTER_VERTICAL)
                    .apply { setMargins(padding, 0, padding, 0) }
            setImageDrawable(if (expandIndicator == null) ContextCompat.getDrawable(context, R.drawable.ic_down) else expandIndicator)
        }

        if (headerLayout.indexOfChild(expandIcon) == -1) headerLayout.addView(expandIcon)
    }

    private fun createHeaderContent() {
        if (headerView == null) return

        val padding = context.resources.getDimensionPixelSize(R.dimen.icon_margin)
        val headerIconSize = context.resources.getDimensionPixelSize(R.dimen.collapsed_icon_size)
        val indicatorIconSize = context.resources.getDimensionPixelSize(R.dimen.expand_drawable_size)
        val startPadding = if (icon == null) 0 else padding * 2 + headerIconSize
        val endPadding = padding * 2 + indicatorIconSize

        val headerLayoutParams = headerView!!.layoutParams as LayoutParams
        headerView!!.setPadding(startPadding, 0, endPadding, 0)
        headerView!!.layoutParams = headerLayoutParams

        if (headerLayout.indexOfChild(headerView) == -1) headerLayout.addView(headerView)
    }

    private fun collapse() {
        if (animateExpand) {
            expandIcon.animate().rotation(0f).duration = 200
            iconImage.animate().scaleX(1f).scaleY(1f).translationX(1f).translationY(1f).duration = 200
            ValueAnimator.ofInt(expandableHeight, 0).apply {
                duration = 200
                addUpdateListener {
                    val height = it.animatedValue as Int
                    val layoutParams = expandableView?.layoutParams
                    layoutParams?.height = height
                    expandableView?.layoutParams = layoutParams
                }
                doOnEnd { isExpanded = false }
            }.start()
        } else {
            expandIcon.rotation = 0f
            iconImage.apply {
                scaleX = 1f
                scaleY = 1f
                translationX = 1f
                translationY = 1f
            }
            val layoutParams = expandableView?.layoutParams
            layoutParams?.height = 0
            expandableView?.layoutParams = layoutParams
            isExpanded = false
        }
    }

    private fun expand() {
        createExpandableHeight()
        if (animateExpand) {
            expandIcon.animate().rotation(180f).duration = 200
            iconImage.animate().scaleX(1.2f).scaleY(1.2f).translationX(10f).translationY(10f).duration = 200
            ValueAnimator.ofInt(0, expandableHeight).apply {
                duration = 200
                addUpdateListener {
                    val height = it.animatedValue as Int
                    val layoutParams = expandableView?.layoutParams
                    layoutParams?.height = height
                    expandableView?.layoutParams = layoutParams
                }
                doOnEnd { isExpanded = true }
            }.start()
        } else {
            expandIcon.rotation = 180f
            iconImage.apply {
                scaleX = 1.2f
                scaleY = 1.2f
                translationX = 10f
                translationY = 10f
            }
            val layoutParams = expandableView?.layoutParams
            layoutParams?.height = expandableHeight
            expandableView?.layoutParams = layoutParams
            isExpanded = true
        }
    }

    private fun createExpandableHeight() {
        if (expandableView == null) return

        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        val deviceWidth = size.x
        expandableView?.measure(MeasureSpec.makeMeasureSpec(deviceWidth, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
        expandableHeight = expandableView?.measuredHeight ?: 0
    }

    private fun resetExpandableHeight() {
        expandableView?.let {
            val expandableViewParams = it.layoutParams as LayoutParams
            expandableViewParams.setMargins(0, collapsedViewHeight, 0, 0)
            if (!isExpanded) expandableViewParams.height = 0
            it.layoutParams = expandableViewParams
        }
    }

    interface ExpandingListener {
        fun onExpanded()
        fun onCollapsed()
    }
}