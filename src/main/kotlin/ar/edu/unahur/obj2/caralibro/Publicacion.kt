package ar.edu.unahur.obj2.caralibro

abstract class Publicacion {
  val usuariosLesGusta = mutableListOf<Usuario>()
  val tipoPublicacion = mutableListOf<Permiso>()
  val usuariosPrivados = mutableListOf<Usuario>()
  val usuariosExcluidos = mutableListOf<Usuario>()

  fun agregarTipoPublicacion(tipo: Permiso) = tipoPublicacion.add(tipo)
  fun agregarPrivado(usuario: Usuario)  = usuariosPrivados.add(usuario)
  fun agregarExcluido(usuario: Usuario) = usuariosExcluidos.add(usuario)

  fun esUnUsuarioPrivado(usuario: Usuario): Boolean { return usuariosPrivados.contains(usuario) }
  fun esUnUsuarioExcluido(usuario: Usuario): Boolean { return usuariosExcluidos.contains(usuario) }
  fun esUnUsuarioAmigo(usuario: Usuario): Boolean { return Usuario().usuariosAmigos.contains(usuario) }
  fun queTipoDePublicacion(permiso: Permiso): Boolean { return tipoPublicacion.contains(permiso) }
  /*
  Ver que tipo de publicación es.
  si es publica y no esta en la lista de usuariosExcluidos ok
  si es privado, pertenecer a lista de usuariosPrivados
  si es solo amigos pertenecer a lista de usuariosAmigos
  */

  fun usuarioPuedeVerPublicacion(usuario: Usuario): Boolean {
    return queTipoDePublicacion(Publico()) && !esUnUsuarioExcluido(usuario) ||
    queTipoDePublicacion(Amigos()) && esUnUsuarioAmigo(usuario) ||
    queTipoDePublicacion(Privado()) && esUnUsuarioPrivado(usuario)
  }
/*
  fun usuarioPuedeVerPublicacion1(usuario: Usuario): Boolean {
    if(this.tipoPublicacion.contains(Amigos())){
      return Usuario().usuariosAmigos.contains(usuario)
    }
    else if(tipoPublicacion.contains(PrivadoConListaPermitidos())){
      return this.usuariosPrivados.contains(usuario)
    }
    else if(tipoPublicacion.contains(PublicoConListaExcluidos())){
      return !this.usuariosExcluidos.contains(usuario)
    }
    else{
      //Publico() no es ninguno de los otros permisos, siempre es true
      return true
    }
  } */

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
