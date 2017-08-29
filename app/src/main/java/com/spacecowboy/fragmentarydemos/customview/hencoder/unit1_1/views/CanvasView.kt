package com.spacecowboy.fragmentarydemos.customview.hencoder.unit1_1.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.spacecowboy.fragmentarydemos.R
import com.spacecowboy.fragmentarydemos.utils.UiUtil

/**
 * @author: SpaceCowboy
 * @date: 2017/8/26
 * @description:
 */
class CanvasView : View {
    var mPaint = Paint()

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, theme: Int) : super(context, attrs, theme)

    fun init(context: Context) {
        mPaint.color = UiUtil.getResColor(R.color.black)
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 5f
        mPaint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawCircle(300f, 300f, 150f, mPaint)
    }
}