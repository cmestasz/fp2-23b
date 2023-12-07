# CREACION DE CARPETAS Y ARCHIVOS COMO PLANTILLA
$ mkdir lab07
$ code lab08/Soldado.java
$ code lab08/VideoJuego5.java
$ git add .
# MENU PRINCIPAL Y LOS METODOS A USAR, CLASE SOLDADO
$ git commit -m "Menu principal en main"
[main ec73362] Menu principal en main
 2 files changed, 185 insertions(+)
 create mode 100644 fase02/lab12/Soldado.java
 create mode 100644 fase02/lab12/VideoJuego9.java
$ git add .
# JUEGO RAPIDO, YA DESARROLLADO EN EL LABORATORIO ANTERIOR
$ git commit -m "Juego rapido (Laboratorio anterior)"
[main 7ce4e90] Juego rapido (Laboratorio anterior)
 1 file changed, 296 insertions(+)
$ git add .
# MENU DE JUEGO PERSONALIZADO Y LOS METODOS A USAR
$ git commit -m "Base del menu del Juego Personalizado"
[main 922afbe] Base del menu del Juego Personalizado
 1 file changed, 86 insertions(+)
$ git add .
# METODO QUE PERMITE CREAR UN SOLDADO
$ git commit -m "Metodo crearSoldado"
[main 7aa0571] Metodo crearSoldado
 1 file changed, 34 insertions(+), 8 deletions(-)
$ git add .
# METODO QUE PERMITE ELIMINAR UN SOLDADO
$ git commit -m "Metodo eliminarSoldado"
[main 360384d] Metodo eliminarSoldado
 1 file changed, 18 insertions(+)
$ git add .
# METODO QUE PERMITE COPIAR UN SOLDADO A OTRA POSICION
$ git commit -m "Metodo clonarSoldado"
[main 52db412] Metodo clonarSoldado
 1 file changed, 26 insertions(+)
$ git add .
# METODO QUE PERMITE MODIFICAR LOS ATRIBUTOS DE UN SOLDADO
$ git commit -m "Metodo modificarSoldado"
[main fcbcc3d] Metodo modificarSoldado
 1 file changed, 32 insertions(+)
$ git add .
# METODO QUE PERMITE COMPARAR LOS ATRIBUTOS DE DOS SOLDADOS
$ git commit -m "Metodo compararSoldados"
[main 08fe8fa] Metodo compararSoldados
 1 file changed, 31 insertions(+)
$ git add .
#METODO QUE PERMITE INTERCAMBIAR LA POSICION DE DOS SOLDADOS
$ git commit -m "Metodo intercambiarSoldados"
[main b5f210a] Metodo intercambiarSoldados
 1 file changed, 31 insertions(+)
$ git add .
# METODO QUE MUESTRA UN SOLDADO POR NOMBRE
$ git commit -m "Metodo verSoldado"
[main a386c0d] Metodo verSoldado
 1 file changed, 9 insertions(+)
# PUSH SECUNDARIO
$ git push
Enumerating objects: 60, done.
Counting objects: 100% (60/60), done.
Delta compression using up to 4 threads
Compressing objects: 100% (56/56), done.
Writing objects: 100% (56/56), 8.76 KiB | 1.75 MiB/s, done.
Total 56 (delta 32), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (32/32), completed with 3 local objects.
To https://github.com/cmestasz/fp2-23b.git
   ce3d609..a386c0d  main -> main
$ git add .
# METODO QUE MUESTRA LA SUMA DE ATRIBUTOS DEL EJERCITO
$ git commit -m "Metodo sumarNiveles"
[main 593e33d] Metodo sumarNiveles
 1 file changed, 8 insertions(+)
# PUSH FINAL
$ git push
Enumerating objects: 9, done.
Counting objects: 100% (9/9), done.
Delta compression using up to 4 threads
Compressing objects: 100% (5/5), done.
Writing objects: 100% (5/5), 589 bytes | 589.00 KiB/s, done.
Total 5 (delta 3), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (3/3), completed with 3 local objects.
To https://github.com/cmestasz/fp2-23b.git
   a386c0d..593e33d  main -> main


