package meshes.loaders

import org.joml.Vector3i
import renderers.Material
import utils.Vectors

class MeshLoaderWaveFront : MeshLoader() {

    private companion object Prefixes {

        const val POSITION = "v "
        const val COLOR = "c "
        const val TEXCOORD = "vt "
        const val NORMAl = "vn "
        const val FACE = "f "

        const val MATERIAL_NEW = "newmtl "
        const val MATERIAL_USE = "usemtl "
        const val MATERIAL_LIB = "mtllib "

        const val DISSOVLE_F = "d "
        const val SPECULAR_F = "NS "
        const val AMBIENT = "Ka "
        const val DIFFUSE = "Kd "
        const val SPECULAR = "Ks "
        const val EMISSIVE = "Ke "
        const val ILLUMINATION = "illum "

        const val MAP_AMBIENT = "map_Ka "
        const val MAP_DIFFUSE = "map_Kd "
        const val MAP_SPECULAR = "map_Ks"
    }

    override fun processMeshData(line: String) {

        process(line, POSITION, { DataContainer.processedVertices.add(toVector3f(it.split(" "))) })

        process(line, TEXCOORD, { DataContainer.processedTexcoords.add(toVector2f(it.split(" "))) })

        process(line, NORMAl, { DataContainer.processedNormals.add(toVector3f(it.split(" "))) })

        process(line, FACE, {
            it.split(" ").forEach {
                if (!it.contains("/")) {
                    DataContainer.processedIndices.add(it.toInt())
                } else if (it.contains("//")) {
                    it.split("//").forEach { DataContainer.processedIndices.add(it.toInt()) }
                } else {
                    it.split("/").forEach { DataContainer.processedIndices.add(it.toInt()) }
                }
            }
        })

        process(line, MATERIAL_LIB, { loadMaterial(it) })

    }

    override fun processMaterialData(material: Material, line: String) {

    }

}