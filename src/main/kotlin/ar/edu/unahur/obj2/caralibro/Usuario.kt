package ar.edu.unahur.obj2.caralibro

// tipoDePublicacion = Publico, Amigos, Privado

open class Usuario {
  val publicaciones = mutableListOf<Publicacion>()
  val usuariosAmigos = mutableListOf<Amigo>()
  val usuariosPrivados = mutableListOf<Amigo>()
  val usuariosExcluidos = mutableListOf<Amigo>()

  fun agregarAmigo(amigo: Amigo) { usuariosAmigos.add(amigo) }
  fun agregarPublicacion(publicacion: Publicacion) { publicaciones.add(publicacion)  }
  fun agregarPrivado(amigo: Amigo) { usuariosPrivados.add(amigo) }
  fun agregarExcluido(amigo: Amigo) { usuariosExcluidos.add(amigo) }

  fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }
  fun cantidadDeAmigos() = usuariosAmigos.size
  fun esMasAmistoso(usuario: Usuario): Usuario {
   return if (this.cantidadDeAmigos() > usuario.cantidadDeAmigos()){ this } else { usuario }
  }
  fun usuarioPuedeVerPublicacion() {
    /*
    Ver que tipo de publicación es.
    si es publica y no esta en la lista de usuariosExcluidos ok
    si es privado, pertenecer a lista de usuariosPrivados
    si es solo amigos pertenecer a lista de usuariosAmigos
    */
  }
  fun quienVeTodas(){
    // el conjunto de sus amigos que pueden ver todas sus publicaciones
  }
  fun amigoMasPopular(){
    // el amigo que tiene mas me gusta entre todas sus publicaciones
  }
  fun usuarioStalkea(){
    // le dio me gusta a más del 90% de sus publicaciones.
  }

}// FIN CLASS

class Amigo : Usuario(){ } // verificar si debe heredar de usuario
