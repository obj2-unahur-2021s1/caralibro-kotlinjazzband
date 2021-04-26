package ar.edu.unahur.obj2.caralibro

abstract class Publicacion {
  val usuariosLesGusta = mutableListOf<Usuario>()
  val tipoPublicacion = mutableListOf<Permiso>()
  val usuariosPrivados = mutableListOf<Usuario>()
  val usuariosExcluidos = mutableListOf<Usuario>()

  fun agregarTipoPublicacion(tipo: Permiso) = tipoPublicacion.add(tipo)
  fun agregarPrivado(usuario: Usuario)  = usuariosPrivados.add(usuario)
  fun agregarExcluido(usuario: Usuario) = usuariosExcluidos.add(usuario)

  fun usuarioPuedeVerPublicacion(usuario: Usuario): Boolean {
    if(this.tipoPublicacion.contains(SoloAmigos())){
      return Usuario().usuariosAmigos.contains(usuario)
    }else if(tipoPublicacion.contains(PrivadoConListaPermitidos())){
      return this.usuariosPrivados.contains(usuario)
    }else if(tipoPublicacion.contains(PublicoConListaExcluidos())){
      return !this.usuariosExcluidos.contains(usuario)
    }else{
      //Publico() no es ninguno de los otros permisos, siempre es true
      return true
    }
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
