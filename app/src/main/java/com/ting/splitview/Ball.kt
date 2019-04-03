package com.ting.splitview

data class Ball(
    val color: Int,
    var x: Float,
    var y: Float,
    var r: Float,
    var vX: Float = 0f,
    var vY: Float = 0f,
    var aX: Float = 0f,
    var aY: Float = 0f

)