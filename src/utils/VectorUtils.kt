package utils

import org.joml.*
import java.util.*

object Vectors{

    val vectors: Deque<Any> = ArrayDeque()

    fun clear() = vectors.clear()

    fun create(x: Float, y: Float): Vector2f{
        val vec = Vector2f(x, y)
        vectors.add(vec)
        return vec
    }

    fun create(x: Float, y: Float, z: Float): Vector3f{
        val vec = Vector3f(x, y, z)
        vectors.add(vec)
        return vec
    }

    fun create(x: Float, y: Float, z: Float, w: Float): Vector4f{
        val vec = Vector4f(x, y, z, w)
        vectors.add(vec)
        return vec
    }

    fun create(x: Int, y: Int): Vector2i{
        val vec = Vector2i(x, y)
        vectors.add(vec)
        return vec
    }

    fun create(x: Int, y: Int, z: Int): Vector3i{
        val vec = Vector3i(x, y, z)
        vectors.add(vec)
        return vec
    }

    fun create(x: Int, y: Int, z: Int, w: Int): Vector4i{
        val vec = Vector4i(x, y, z, w)
        vectors.add(vec)
        return vec
    }
}
