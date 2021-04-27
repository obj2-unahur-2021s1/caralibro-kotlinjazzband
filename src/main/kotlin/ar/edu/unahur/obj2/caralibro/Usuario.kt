package ar.edu.unahur.obj2.caralibro


open class Usuario {
  val publicaciones = mutableListOf<Publicacion>()
  val usuariosAmigos = mutableListOf<Usuario>()

  fun agregarAmigo(usuario: Usuario) { usuariosAmigos.add(usuario) }
  fun agregarPublicacion(publicacion: Publicacion) { publicaciones.add(publicacion)  }

  fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }
  fun cantidadDeAmigos() = usuariosAmigos.size
  fun esMasAmistoso(usuario: Usuario): Usuario {
   return if (this.cantidadDeAmigos() > usuario.cantidadDeAmigos()){ this } else { usuario }
  }

  fun permisoQueTienePublicacion(){
    }

  fun quienVeTodas(){
    // el conjunto de sus amigos que pueden ver todas sus publicaciones
    //val amigosVenTodas = mutableListOf<Usuario>()

    //usuarioPuedeVerPublicacion
  }
  fun amigoMasPopular(){
    // el amigo que tiene mas me gusta entre todas sus publicaciones
  }
  fun usuarioStalkea(){
    // le dio me gusta a más del 90% de sus publicaciones.
  }

}
// FIN CLASS

//class Amigo : Usuario(){ } // verificar si debe heredar de usuario
