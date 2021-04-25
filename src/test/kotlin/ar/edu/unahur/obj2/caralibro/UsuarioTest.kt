package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
  describe("Caralibro") {
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)
    val videoVacaciones = Video(10000, "sd")
    val videoCordoba = Video(10000, "hd_720")
    val videoShow = Video(10000, "hd_1080")
    val juana = Usuario()
    val naty = Usuario()
    val horacio = Usuario()
    videoCordoba.adMeGusta(juana)
    videoCordoba.adMeGusta(naty)
    videoCordoba.adMeGusta(horacio)

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
      }

    }
    //  TEST USUARIOS
    describe("Un usuario") {
      //val juana = Usuario()
      val pedro = Amigo()
      //val naty = Usuario()
      val miranda = Amigo()
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
