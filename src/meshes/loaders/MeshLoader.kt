package meshes.loaders

import main.getResource
import meshes.objects.Mesh
import org.joml.Vector2f
import org.joml.Vector3f
import renderers.Material
import utils.Vectors
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

private const val OBJECTS_FOLDER = "objects/"

abstract class MeshLoader {

    protected object DataContainer {

        val processedVertices: ArrayList<Vector3f> = ArrayList()
        val processedTexcoords: ArrayList<Vector2f> = ArrayList()
        val processedNormals: ArrayList<Vector3f> = ArrayList()

        val processedIndices: Deque<Int> = ArrayDeque()
    }

    abstract fun processMeshData(line: String)

    abstract fun processMaterialData(material: Material, line: String)

    fun loadMesh(path: String): Mesh {
        val mesh: Mesh = Mesh(path)

        val stream = getResource(OBJECTS_FOLDER + path) ?: throw IOException("Unable to load object $path")
        BufferedReader(InputStreamReader(stream)).use {
            it.readLines().forEach {
                processMeshData(it)
            }
        }

        return mesh
    }

    fun loadMaterial(path: String) {

    }

    protected fun process(line: String, prefix: CharSequence, action: (property: String) -> Unit) {
        try {
            if (line.startsWith(prefix)) {
                val prop = line.substring(prefix.length).trim()
                action(prop)
            }
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace()
        }
    }

    protected fun toVector2f(values: List<String>): Vector2f {
        if (values.size < 2) throw IllegalArgumentException("Unable to convert in Vector3f")
        return Vectors.create(values[0].toFloat(), values[1].toFloat())
    }

    protected fun toVector3f(values: List<String>): Vector3f {
        if (values.size < 3) throw IllegalArgumentException("Unable to convert in Vector3f")
        return Vectors.create(values[0].toFloat(), values[1].toFloat(), values[2].toFloat())
    }


}