package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
  describe("Caralibro") {
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val saludoCumpleanios2 = Texto("Muchas Felicidades, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)
    val videoVacaciones = Video(10000, "sd")
    val videoCordoba = Video(10000, "hd_720")
    val videoShow = Video(10000, "hd_1080")

    val juana = Usuario()
    val naty = Usuario()
    val horacio = Usuario()
    val pedro = Usuario()
    val miranda = Usuario()

    juana.agregarAmigo(naty)
    juana.agregarAmigo(horacio)
    juana.agregarAmigo(pedro)

    // Agregar Me Gusta
    videoCordoba.adMeGusta(juana)
    videoCordoba.adMeGusta(naty)

          // Stalkea
    videoCordoba.adMeGusta(horacio)
    fotoEnCuzco.adMeGusta(horacio)
    videoVacaciones.adMeGusta(horacio)
    saludoCumpleanios.adMeGusta(horacio)
    videoShow.adMeGusta(horacio)
    saludoCumpleanios2.adMeGusta(horacio)

    // tipoDePublicacion = Publico, Amigos, Privado
    fotoEnCuzco.agregarTipoPublicacion(Privado())

    videoCordoba.agregarTipoPublicacion(Amigos())

    saludoCumpleanios.agregarTipoPublicacion(Publico())
    saludoCumpleanios2.agregarTipoPublicacion(Publico())
    videoShow.agregarTipoPublicacion(Publico())

    saludoCumpleanios2.agregarExcluido(pedro)
    videoShow.agregarExcluido(miranda)

    fotoEnCuzco.agregarPrivado(pedro)
    fotoEnCuzco.agregarPrivado(miranda)


    // TEST PARA PUBLICACIONES
    describe("Una publicación") {
      describe("de tipo foto") {
        it("ocupa ancho * alto * compresion bytes") {
          fotoEnCuzco.espacioQueOcupa().shouldBe(550503)
        }
      }
      describe("de tipo texto") {
        it("ocupa tantos bytes como su longitud") {
          saludoCumpleanios.espacioQueOcupa().shouldBe(45)
        }
      }
      describe("de tipo video calidad sd") {
        it("ocupa tantos bytes como su duracion") {
          videoVacaciones.espacioQueOcupa().shouldBe(10000)
        }
      }
      describe("de tipo video calidad hd_720") {
        it("ocupa tantos bytes como su duracion x 3") {
          videoCordoba.espacioQueOcupa().shouldBe(30000)
        }
      }
      describe("de tipo video calidad hd_1080") {
        it("ocupa tantos bytes como su duracion x 6") {
          videoShow.espacioQueOcupa().shouldBe(60000)
        }
      }
      describe("la publicación me gusta"){
        it ("cantidad de me gusta"){
          videoCordoba.cantidadDeMeGusta().shouldBe(3)
        }

        //agregamos un nuevo Me gusta de horacio y debería seguir dando resultado 3 meGusta
        videoCordoba.adMeGusta(horacio)
        it ("cantidad de me gusta sumando un de usuario repetido"){
          videoCordoba.cantidadDeMeGusta().shouldBe(3)
        }
      }

      // Verificar Permisos
      describe("Tipo de publicación foto"){
        it("foto en cuzco PRIVADO usuario en Lista Permitidos (da falso)"){
          fotoEnCuzco.usuarioPuedeVerPublicacion(pedro).shouldBe(true)
        }
      }
      describe("Tipo de publicación foto falso") {
        it("foto en cuzco PRIVADO sin usuario en Lista Permitidos(ok)") {
          fotoEnCuzco.usuarioPuedeVerPublicacion(horacio).shouldBe(false)
        }
      }

      describe("Tipo de publicación video"){
        it(" Video Cordoba con usuario en lista de AMIGOS (da falso)"){
          videoCordoba.usuarioPuedeVerPublicacion(naty).shouldBe(true)
        }
      }
      describe("Tipo de publicación video falso"){
        it("Video Cordoba sin usuario en lista de AMIGOS(OK)"){
          videoCordoba.usuarioPuedeVerPublicacion(naty).shouldBe(false)
        }
      }

      describe("Tipo de publicación: publica usuario excluido en lista de excluido"){
        it("Video con usuario fuera de Lista Excluidos (da falso) "){
          videoShow.usuarioPuedeVerPublicacion(pedro).shouldBe(true)
        }
      }
      describe("Tipo de publicación: publica usuario Incluido en lista de excluido"){
        it("Video con usuario en Lista Excluidos (ok)"){
          videoShow.usuarioPuedeVerPublicacion(miranda).shouldBe(false)
        }
      }

      describe("Tipo de publicación saludo"){
        it(" Saludo Cumpleaños publico "){
          saludoCumpleanios.usuarioPuedeVerPublicacion(naty).shouldBe(true)
        }
      }


    }
    //  TEST USUARIOS
    describe("Un usuario") {
     it("puede calcular el espacio que ocupan sus publicaciones") {
        juana.agregarPublicacion(fotoEnCuzco)
        juana.agregarPublicacion(saludoCumpleanios)
        juana.agregarPublicacion(videoVacaciones)
        juana.agregarPublicacion(videoCordoba)
        juana.agregarPublicacion(videoShow)
        juana.espacioDePublicaciones().shouldBe(650548)
      }
        describe("cuantos usuarios amigos"){
          juana.agregarAmigo(pedro)
          juana.agregarAmigo(miranda)
          naty.agregarAmigo(pedro)
          it("cuantos amigos tiene naty"){
            naty.cantidadDeAmigos().shouldBe(1)
          }
          it("cuantos amigos tiene juana"){
            juana.cantidadDeAmigos().shouldBe(2)
          }
          it("usuario mas amistoso juana"){
            juana.esMasAmistoso(naty).shouldBe(juana)
          }
        }
    }

  /* fuera del test de publicaciones y test de usuario */

  }
})
