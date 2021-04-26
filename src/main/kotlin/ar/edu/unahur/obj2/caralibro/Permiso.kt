package ar.edu.unahur.obj2.caralibro

abstract class Permiso { }

class Publico(): Permiso(){}
class Amigos(): Permiso(){}
class Privado(): Permiso(){}
//class PublicoConListaExcluidos(): Permiso(){}
