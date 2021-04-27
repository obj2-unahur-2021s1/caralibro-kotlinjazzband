package ar.edu.unahur.obj2.caralibro

abstract class Publicacion {
  val usuariosLesGusta = mutableListOf<Usuario>()
  val usuariosPrivados = mutableListOf<Usuario>()
  val usuariosExcluidos = mutableListOf<Usuario>()

  // Publico Amigos, Privado
  var tipoPublicacion: String = "Publico"
  fun agregarTipoPublicacion(tipo: String) = tipo.also { tipoPublicacion = it }
  //fun tipoPublicacion(): String { return tipoPublicacion }

  fun agregarPrivado(usuario: Usuario)  = usuariosPrivados.add(usuario)
  fun agregarExcluido(usuario: Usuario) = usuariosExcluidos.add(usuario)

  fun esUnUsuarioPrivado(usuario: Usuario): Boolean { return usuariosPrivados.contains(usuario) }
  fun esUnUsuarioExcluido(usuario: Usuario): Boolean { return usuariosExcluidos.contains(usuario) }
  fun esUnUsuarioAmigo(usuario: Usuario): Boolean { return Usuario().usuariosAmigos.contains(usuario) }
  fun elTipoDePublicacionEs(permiso: String): Boolean { return tipoPublicacion.contains(permiso) }

  /*  Ver que tipo de publicación es. Público y no esta en la lista de usuariosExcluidos ok
  si es privado, pertenecer a lista de usuariosPrivados y si es solo amigos pertenecer a lista de usuariosAmigos */

  fun usuarioPuedeVerPublicacion(usuario: Usuario): Boolean {
    return elTipoDePublicacionEs("Amigos") && esUnUsuarioAmigo(usuario) ||
            elTipoDePublicacionEs("Publico") && !esUnUsuarioExcluido(usuario) ||
            elTipoDePublicacionEs("Privado") && esUnUsuarioPrivado(usuario)
  }

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
