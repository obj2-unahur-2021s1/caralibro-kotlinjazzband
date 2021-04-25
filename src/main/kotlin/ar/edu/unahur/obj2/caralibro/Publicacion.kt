package ar.edu.unahur.obj2.caralibro

abstract class Publicacion {
  val usuariosLesGusta = mutableListOf<Usuario>()
  var tipoPublicacion: String = "Publico"

  fun agregarTipoPublicacion(tipo: String) = tipo.also { tipoPublicacion = it }
  fun tipoPublicacion(): String { return tipoPublicacion }

  abstract fun espacioQueOcupa(): Int

  fun adMeGusta(nuevoUsuario: Usuario) {
    if (!usuariosLesGusta.contains(nuevoUsuario)) { usuariosLesGusta.add(nuevoUsuario) }
  }
  fun cantidadDeMeGusta(): Int { return usuariosLesGusta.size }
}

// PUBLICACIONES FOTOGRÁFICAS
class Foto(val alto: Int, val ancho: Int) : Publicacion() {
  val factorDeCompresion = 0.7
  override fun espacioQueOcupa() = Math.ceil(alto * ancho * factorDeCompresion).toInt()
}

// PUBLICACIONES DE TEXTO
class Texto(val contenido: String) : Publicacion() {
  override fun espacioQueOcupa() = contenido.length
}

// PUBLICACIONES DE VIDEO
class Video(var duracion: Int, val calidad: String) : Publicacion() {
  override fun espacioQueOcupa(): Int {
    return when(calidad){
      "hd_720" -> duracion * 3
      "hd_1080" -> duracion * 6
      else -> (duracion)
    }
  }

}
