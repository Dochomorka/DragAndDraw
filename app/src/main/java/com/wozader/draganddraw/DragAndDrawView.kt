package com.wozader.draganddraw

import android.content.Context
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

    init {

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val current = PointF(event?.x!!, event?.y!!)
        var action = when(event.action){
            MotionEvent.ACTION_DOWN -> ACTION_DOWN
            MotionEvent.ACTION_UP -> ACTION_UP
            MotionEvent.ACTION_CANCEL -> ACTION_CANCEL
            MotionEvent.ACTION_MOVE -> ACTION_MOVE
            else-> ""
        }
        Log.i(TAG, "$action at x = ${current.x}, y = ${current.y}")
        return true
    }
}