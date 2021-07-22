package com.robertlevonyan.views.expandable

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

fun Expandable.doOnExpand(expand: () -> Unit) {
  expandingListener = object : Expandable.ExpandingListener {
    override fun onExpanded() {
      expand()
    }

    override fun onCollapsed() {

    }
  }
}

fun Expandable.doOnCollapse(collapse: () -> Unit) {
  expandingListener = object : Expandable.ExpandingListener {
    override fun onExpanded() {

    }

    override fun onCollapsed() {
      collapse()
    }
  }
}

internal fun Drawable.getCircleBitmap(color: Int): Bitmap {
  val bitmap = this.toBitmap()

  val output = Bitmap.createBitmap(
    bitmap.width,
    bitmap.height, Bitmap.Config.ARGB_8888
  )
  val canvas = Canvas(output)

  val paint = Paint()
  val rect = Rect(0, 0, bitmap.width, bitmap.height)

  paint.isAntiAlias = true
  canvas.drawARGB(0, 0, 0, 0)
  paint.color = color
  canvas.drawCircle(
    (bitmap.width / 2).toFloat(), (bitmap.height / 2).toFloat(),
    (bitmap.width / 2).toFloat(), paint
  )
  paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
  canvas.drawBitmap(bitmap, rect, rect, paint)

  return output
}

internal fun Drawable.getRoundedSquareBitmap(color: Int): Bitmap {
  val radius = 150.0f
  val bitmap = this.toBitmap()

  val output = Bitmap.createBitmap(
    bitmap.width,
    bitmap.height, Bitmap.Config.ARGB_8888
  )
  val canvas = Canvas(output)

  val paint = Paint()
  val rect = Rect(0, 0, bitmap.width, bitmap.height)
  val rectF = RectF(0f, 0f, bitmap.width.toFloat(), bitmap.height.toFloat())

  paint.isAntiAlias = true
  canvas.drawARGB(0, 0, 0, 0)
  paint.color = color
  canvas.drawRoundRect(rectF, radius, radius, paint)
  paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
  canvas.drawBitmap(bitmap, rect, rect, paint)

  return output
}

private fun Drawable.toBitmap(): Bitmap {
  val bitmap: Bitmap?

  if (this is BitmapDrawable) {
    if (this.bitmap != null) {
      return this.bitmap
    }
  }

  if (this.intrinsicWidth <= 0 || this.intrinsicHeight <= 0) {
    bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
  } else {
    bitmap = Bitmap.createBitmap(this.intrinsicWidth, this.intrinsicHeight, Bitmap.Config.ARGB_8888)
  }

  val canvas = Canvas(bitmap!!)
  this.setBounds(0, 0, canvas.width, canvas.height)
  this.draw(canvas)
  return bitmap
}