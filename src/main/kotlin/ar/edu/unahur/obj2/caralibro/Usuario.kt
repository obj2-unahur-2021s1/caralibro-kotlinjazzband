package ar.edu.unahur.obj2.caralibro


open class Usuario {
  val publicaciones = mutableListOf<Publicacion>()
  val usuariosAmigos = mutableListOf<Usuario>()

  fun agregarAmigo(usuario: Usuario) { usuariosAmigos.add(usuario) }
  fun agregarPublicacion(publicacion: Publicacion) { publicaciones.add(publicacion) }
  fun publicaciones(): MutableList<Publicacion> =  publicaciones

  fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }
  fun cantidadDeAmigos() = usuariosAmigos.size
  fun esMasAmistoso(usuario: Usuario): Usuario {
   return if (this.cantidadDeAmigos() > usuario.cantidadDeAmigos()){ this } else { usuario }
  }

  fun usuarioLeGustaPublicacion(usuario: Usuario): Boolean {
    return publicaciones.map(it.leGustaEstaPublicacionA(usuario))
  }

  fun permisoQueTienePublicacion(){  }

  fun quienVeTodas(){
    // usuarios amigos que en las listas privada y no esta en la lista de excluidos (conjunto)
    //val amigosVenTodas = mutableListOf<Usuario>()

    //usuarioPuedeVerPublicacion
  }
  fun amigoMasPopular(){
    // Saber cual es el amigo más popular que tiene un usuario.
    // Es decir, el amigo que tiene mas me gusta entre todas sus publicaciones.
    // amigo con mas cantidad de me gusta (1 amigo)
    //return usuariosAmigos.map(a -> it.publicaciones -> a.cantidadDeMeGusta() )
  }



  fun cantidadDePublicaciones() = publicaciones.size

  fun usuarioStalkea(usuario: Usuario):Boolean{
    // Saber si un usuario stalkea a otro.
    // Lo cual ocurre si el stalker le dio me gusta a más del 90% de sus publicaciones.
    // tengo una lista de publicaciones -> usuario le dio me gusta
    // publicaciones.usuariosLesGusta
    return true
  }

}

