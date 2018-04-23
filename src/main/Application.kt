package main

import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL11
import org.lwjgl.system.MemoryUtil
import java.awt.Dimension
import org.lwjgl.glfw.GLFW.glfwSetWindowPos
import org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor
import org.lwjgl.glfw.GLFW.glfwGetVideoMode
import org.lwjgl.opengl.GL
import org.lwjgl.system.MemoryStack.stackPush



class Application(var dimension: Dimension, fullScr: Boolean) {

    private var wnd: Long = 0L
    private var running: Boolean = false

    private var errorCallback: GLFWErrorCallback = GLFWErrorCallback.createPrint(System.err).set()

    private fun initializeContext() {
        GL.createCapabilities()

        GL11.glEnable(GL11.GL_COLOR)

        GL11.glClearColor(0.0f, 0.85f, 0.85f, 1.0f)

    }

    private fun run() {

        try {
            initializeContext()
        } catch (ex: ExceptionInInitializerError) {
            ex.printStackTrace()
        }

        running = true

        try {
            while (running) {
                if (GLFW.glfwWindowShouldClose(wnd)) shutdown()
                GL11.glClear(0x4100)



                GLFW.glfwSwapBuffers(wnd)

                GLFW.glfwPollEvents()
            }
        } catch (ex: RuntimeException) {
            ex.printStackTrace()
            shutdown()
        } finally {
            GLFW.glfwTerminate()
            errorCallback.free()
            System.exit(0)
        }
    }

    private fun shutdown() {
        running = false
    }

    fun runApplication() {

        if (!GLFW.glfwInit()) throw IllegalStateException("Unable to initialize GLFW context")

        GLFW.glfwSetErrorCallback(errorCallback)
        GLFW.glfwDefaultWindowHints()

        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE)
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE)

        wnd = GLFW.glfwCreateWindow(dimension.width, dimension.height, "GCEngine", MemoryUtil.NULL, MemoryUtil.NULL)
        if (wnd == MemoryUtil.NULL) {
            GLFW.glfwTerminate()
            throw RuntimeException("Unable to create window")
        }

        stackPush().use { stack ->
            val pWidth = stack.mallocInt(1)
            val pHeight = stack.mallocInt(1)

            GLFW.glfwGetWindowSize(wnd, pWidth, pHeight)

            // Get the resolution of the primary monitor
            val vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor())!!

            // Center the window
            glfwSetWindowPos(wnd, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2)
        }

        GLFW.glfwMakeContextCurrent(wnd)
        GLFW.glfwSwapInterval(1)
        GLFW.glfwShowWindow(wnd)

        run()
    }

}