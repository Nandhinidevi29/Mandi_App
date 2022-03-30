package com.example.mandi

import android.view.View

object ViewUitls {
    fun show(vararg views: View) {
        for (v in views) {
            v.show()
        }
    }

    fun View.show() {
        this.visibility = View.VISIBLE
    }
}