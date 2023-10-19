# CREACION DE CARPETAS Y ARCHIVOS COMO PLANTILLA
$ mkdir lab07
$ code lab08/Soldado.java
$ code lab08/VideoJuego5.java
$ git add fase02/lab08/Soldado.java
$ git add fase02/lab08/VideoJuego5.java
$ git commit -m "Plantilla en main y metodo simularBatalla()"
[main cf15a45] Plantilla en main y metodo simularBatalla()
 2 files changed, 145 insertions(+)
 create mode 100644 fase02/lab08/Soldado.java
 create mode 100644 fase02/lab08/VideoJuego5.java
$ git add fase02/lab08/VideoJuego5.java
# METODO QUE INICIALIZA LOS SOLDADOS
$ git commit -m "Metodo inicializarSoldados()"
[main a89a2a0] Metodo inicializarSoldados()
 1 file changed, 16 insertions(+), 1 deletion(-)
$ git add fase02/lab08/VideoJuego5.java
# METODO QUE IMPRIME EL TABLERO CON EL NOMBRE Y EL EQUIPO DE CADA SOLDADO
$ git commit -m "Metodo imprimirTablero() y auxiliares"
[main 97d9c09] Metodo imprimirTablero() y auxiliares
 1 file changed, 35 insertions(+)
$ git add fase02/lab08/VideoJuego5.java
# METODO QUE RETORNA EL SOLDADO CON MAYOR VIDA DE UN EJERCITO
$ git commit -m "Metodo soldadoMayorVida()"
[main 84b1c6b] Metodo soldadoMayorVida()
 1 file changed, 7 insertions(+)
$ git add fase02/lab08/VideoJuego5.java
# METODO QUE RETORNA EL PROMEDIO DE PUNTOS DE VIDA DE UN EJERCITO
$ git commit -m "Metodo promedioPuntosVida() y auxiliar"
[main ba62eee] Metodo promedioPuntosVida() y auxiliar
 1 file changed, 6 insertions(+)
$ git add fase02/lab08/VideoJuego5.java
# METODO QUE IMPRIME LA LISTA DE SOLDADOS DE UN EJERCITO
$ git commit -m "Metodo imprimirHashMap()"
[main fe60a09] Metodo imprimirHashMap()
 1 file changed, 2 insertions(+)
$ git add fase02/lab08/VideoJuego5.java
# METODO QUE COPIA LA LISTA DE SOLDADOS DE UN EJERCITO
$ git commit -m "Metodo imprimirArrayList()"
[main b32f9d2] Metodo imprimirArrayList()
 1 file changed, 2 insertions(+)
$ git add fase02/lab08/VideoJuego5.java
# METODO QUE COPIA EL MAPA DE SOLDADOS DE UN EJERCITO
$ git commit -m "Metodo copiarHashMap()"
[main 614a758] Metodo copiarHashMap()
 1 file changed, 2 insertions(+)
$ git add fase02/lab08/VideoJuego5.java
# METODO QUE ORDENA LOS SOLDADOS DE UN EJERCITO USANDO EL ORDENAMIENTO BURBUJA
$ git commit -m "Metodo ordenarSoldadosBurbuja() y auxiliar"
[main cea0f61] Metodo ordenarSoldadosBurbuja() y auxiliar
 1 file changed, 15 insertions(+)
$ git add fase02/lab08/VideoJuego5.java
# METODO QUE ORDENA LOS SOLDADOS DE UN EJERCITO USANDO EL ORDENAMIENTO SELECCION
$ git commit -m "Metodo ordenarSoldadosSeleccion()"
[main dab2537] Metodo ordenarSoldadosSeleccion()
 1 file changed, 14 insertions(+)
$ git add fase02/lab08/VideoJuego5.java
# METODO QUE IMPRIME EL EJERCITO GANADOR DE LA BATALLA
$ git commit -m "Metodo imprimirGanador()"
[main 92cb4d3] Metodo imprimirGanador()
 1 file changed, 8 insertions(+)
# PUSH FINAL
$ git push
Enumerating objects: 58, done.
Counting objects: 100% (58/58), done.
Delta compression using up to 4 threads
Compressing objects: 100% (56/56), done.
Writing objects: 100% (56/56), 6.21 KiB | 1.24 MiB/s, done.
Total 56 (delta 32), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (32/32), completed with 2 local objects.
To https://github.com/cmestasz/fp2-23b.git
   86677b2..92cb4d3  main -> main
