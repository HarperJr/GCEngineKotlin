package meshes.objects

import org.joml.Vector2f
import org.joml.Vector3f

data class Mesh(val name: String) {

    constructor() : this("")

    val subMeshes: ArrayList<Mesh> = ArrayList()

    val vertices: ArrayList<Vector3f> = ArrayList()
    val colors: ArrayList<Vector3f> = ArrayList()
    val texcoords: ArrayList<Vector2f> = ArrayList()
    val normals: ArrayList<Vector3f> = ArrayList()

    val indices: ArrayList<Int> = ArrayList()

    var vboCount: Int = 0
    var indexCount: Int = 0


}