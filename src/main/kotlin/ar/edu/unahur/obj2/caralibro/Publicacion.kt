package ar.edu.unahur.obj2.caralibro

import kotlin.math.ceil

abstract class Publicacion {
  abstract fun espacioQueOcupa(): Int
}

class Foto(val alto: Int, val ancho: Int) : Publicacion() {
  val factorDeCompresion = 0.7
  override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresion).toInt()
}

class Texto(val contenido: String) : Publicacion() {
  override fun espacioQueOcupa() = contenido.length
}

class Video(var duracion: Int, val calidad: String) : Publicacion() {

  override fun espacioQueOcupa(): Int {
    return when(calidad){
      "sd" -> duracion
      "hd_720" -> duracion * 3
      "hd_1080" -> duracion * 6
      else -> (0)
    }
  }
}
