package renderers

import org.joml.Vector3f
import org.lwjgl.BufferUtils
import java.nio.FloatBuffer

data class Material(val name: String) {

    constructor() : this("Material")

    private companion object MaterialBuffer{
        val materialBuffer: FloatBuffer = BufferUtils.createFloatBuffer(16)
    }

    object Materials {
        val whiteSolid = Material()
    }

    var dissolveFactor: Float = 1f
    var specularFactor: Float = 0f

    var ambient: Vector3f = Vector3f(1f, 1f, 1f)
    var diffuse: Vector3f = Vector3f(1f, 1f, 1f)
    var specular: Vector3f = Vector3f(0f, 0f, 0f)
    var emissive: Vector3f = Vector3f(0f, 0f, 0f)

    var mapAmbient: Int = 0
    var mapDiffuse: Int = 0
    var mapSpecular: Int = 0
    var mapNormal: Int = 0
    var mapBump: Int = 0

    fun getBuffered(): FloatBuffer{
        val matArray = floatArrayOf(
                ambient.x, ambient.y, ambient.z, dissolveFactor,
                diffuse.x, diffuse.y, diffuse.z, 1f,
                specular.x, specular.y, specular.z, specularFactor,
                emissive.x, emissive.y, emissive.z, 1f
        )

        materialBuffer.put(matArray).flip()
        return materialBuffer
    }
}