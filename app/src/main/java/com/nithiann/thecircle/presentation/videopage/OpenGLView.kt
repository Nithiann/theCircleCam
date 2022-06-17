package com.nithiann.thecircle.presentation.videopage

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import com.nithiann.thecircle.presentation.videopage.OpenGLRenderer

class OpenGLView : GLSurfaceView {
    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {
        setEGLContextClientVersion(2) // OpenGL ES Version
        preserveEGLContextOnPause = true
        setRenderer(OpenGLRenderer())
    }
}