package ar.edu.unahur.obj2.caralibro

open class Usuario {
  val publicaciones = mutableListOf<Publicacion>()
  val usuariosAmigos = mutableListOf<Amigo>()

  fun agregarAmigo(amigo: Amigo) { usuariosAmigos.add(amigo) }

  fun agregarPublicacion(publicacion: Publicacion) { publicaciones.add(publicacion)  }

  fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }

  fun cantidadDeAmigos() = usuariosAmigos.size

  fun esMasAmistoso(usuario: Usuario): Usuario {
    //return  this ; if (this.cantidadDeAmigos() > usuario.cantidadDeAmigos())
   //   else usuario

   return  if (this.cantidadDeAmigos() > usuario.cantidadDeAmigos()){ this } else { usuario }
  }

  // FIN CLASS
}

class Amigo : Usuario(){ }
