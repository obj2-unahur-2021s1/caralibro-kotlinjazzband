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

  fun cantidadDePublicaciones() = publicaciones.size

  fun contarMeGustaDeUsuario(usuario: Usuario):Int { return publicaciones.count(){ it.leGustaEstaPublicacionA(usuario)}  }


  fun quienVeTodas(){
    // usuarios amigos que en las listas privada y no esta en la lista de excluidos (conjunto)
    //val amigosVenTodas = mutableListOf<Usuario>()

  }

  fun amigoMasPopular(){
    // Saber cual es el amigo más popular que tiene un usuario.
    // Es decir, el amigo que tiene mas me gusta entre todas sus publicaciones.
    // amigo con mas cantidad de me gusta (1 amigo)
   // return usuariosAmigos.count(it.cantidadDeMeGusta() )
  }

  fun noventaPorcientoDePublicaciones():Int = cantidadDePublicaciones()*90/100

  fun usuarioStalkea(usuario: Usuario):Boolean{
    return contarMeGustaDeUsuario(usuario) >= noventaPorcientoDePublicaciones()
  }

}

