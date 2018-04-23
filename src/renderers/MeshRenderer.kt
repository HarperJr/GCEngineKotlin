package renderers

import java.util.*

class MeshRenderer : IRenderer{

    val vbos: Deque<Int> = ArrayDeque()
    var vao = 0

    override fun render() {

    }

    override fun dispose() {

    }
}