package ar.edu.unahur.obj2.caralibro

abstract class Permiso {
}

class Publico(): Permiso(){}
class SoloAmigos(): Permiso(){}
class PrivadoConListaPermitidos(): Permiso(){}
class PublicoConListaExcluidos(): Permiso(){}
