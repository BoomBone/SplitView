package com.ting.splitview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import javax.xml.parsers.DocumentBuilderFactory

class MapView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    init {
        val inputStream = context.resources.openRawResource(R.raw.china)
        //取得DocumentBuilderFactory实例
        val factory = DocumentBuilderFactory.newInstance()
        //从Factory获取DocumentBuilder实例
        val builder = factory.newDocumentBuilder()
        val doc = builder.parse(inputStream)
        //解析输入流 得到Document实例
        val rootElement = doc.documentElement
        rootElement.getElementsByTagName("path")
    }
}