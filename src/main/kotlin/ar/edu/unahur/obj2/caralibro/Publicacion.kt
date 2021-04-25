package ar.edu.unahur.obj2.caralibro

//import kotlin.math.ceil

abstract class Publicacion {
  val usuariosLesGusta = mutableListOf<Usuario>()

  //val tipoPermiso=
  abstract fun espacioQueOcupa(): Int
  //, val amigos: MutableList<Amigos> = mutableListOf()

  fun adMeGusta(nuevoUsuario: Usuario) {
    if (!usuariosLesGusta.contains(nuevoUsuario)) {
      usuariosLesGusta.add(nuevoUsuario)
    }
  }

  fun cantidadDeMeGusta(): Int {
    return usuariosLesGusta.size
  }
}

class Foto(val alto: Int, val ancho: Int) : Publicacion() {
  val factorDeCompresion = 0.7
  override fun espacioQueOcupa() = Math.ceil(alto * ancho * factorDeCompresion).toInt()
}

class Texto(val contenido: String) : Publicacion() {
  override fun espacioQueOcupa() = contenido.length
}

class Video(var duracion: Int, val calidad: String) : Publicacion() {
  override fun espacioQueOcupa(): Int {
    return when(calidad){
      "hd_720" -> duracion * 3
      "hd_1080" -> duracion * 6
      else -> (duracion)
    }
  }
}
