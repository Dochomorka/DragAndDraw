package com.wozader.draganddraw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class DragAndDrawView(context: Context, attributeSet: AttributeSet? = null): View(context,attributeSet) {
    val TAG = "DragAndDrawView"

    private val ACTION_DOWN = "ACTION_DOWN"
    private val ACTION_UP = "ACTION_UP"
    private val ACTION_CANCEL = "ACTION_CANCEL";
    private val ACTION_MOVE = "ACTION_MOVE"

    private var currentBox: Box? = null
    private val boxen = mutableListOf<Box>()
    private val boxPaint = Paint().apply {
        color = 0x2200ff00.toInt()
    }
    private val linePaint = Paint().apply {
        color = 0x220ff0000.toInt()
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 10f
        style = Paint.Style.FILL
    }
    private val backgroundPaint = Paint().apply {
        color = 0xfff8efe0.toInt()
    }

    init {

    }

    override fun onDraw(canvas: Canvas?) {
        /**
         * draw the background with light red color
         */
        canvas?.drawPaint(backgroundPaint)
        boxen.forEach {
              canvas?.drawRect(it.left,it.top ,it.right,it.bottom,boxPaint)
              canvas?.drawLine(it.left,it.top ,it.right,it.bottom,linePaint)
        }
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val current = PointF(event?.x!!, event?.y!!)
        var action = when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentBox = Box(current).also {
                    boxen.add(it)
                }
                ACTION_DOWN
            }
            MotionEvent.ACTION_UP -> {
                updateCurrentBox(current)
                currentBox = null
                ACTION_UP
            }
            MotionEvent.ACTION_CANCEL -> {
                currentBox = null
                ACTION_CANCEL
            }
            MotionEvent.ACTION_MOVE -> {
                updateCurrentBox(current)
                ACTION_MOVE
            }
            else -> ""
        }
        Log.i(TAG, "$action at x = ${current.x}, y = ${current.y}")
        return true
    }
    private fun updateCurrentBox(current: PointF){
        currentBox?.let {
            it.end = current
            invalidate()
        }
    }

}