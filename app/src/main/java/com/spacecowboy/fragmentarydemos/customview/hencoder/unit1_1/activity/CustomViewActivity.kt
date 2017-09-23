package com.spacecowboy.fragmentarydemos.customview.hencoder.unit1_1.activity

import android.view.View
import com.spacecowboy.fragmentarydemos.R
import com.spacecowboy.fragmentarydemos.app.BaseActivity
import com.spacecowboy.fragmentarydemos.utils.UiUtil
import kotlinx.android.synthetic.main.activity_custom_view.*

class CustomViewActivity : BaseActivity() {
    override fun onClick(v: View?) {
    }

    override fun initView(view: View?) {
        tv_custom_view.text = "Modified"
        tv_custom_view.textSize = 90f
    }

    override fun getSubContentView(): View {

        return UiUtil.inflate(this, R.layout.activity_custom_view)
    }

}
