$ git add Arquero.java

$ git add Caballero.java

$ git add Espadachin.java

$ git add Lancero.java

$ git add Soldado.java

$ git add Mapa.java

$ git add Videojuego.java

$ git commit -m "Clases del laboratorio 20"
[main fd8ba6a] Clases del laboratorio 20
 7 files changed, 426 insertions(+)
 create mode 100644 fase03/lab22/Arquero.java
 create mode 100644 fase03/lab22/Caballero.java
 create mode 100644 fase03/lab22/Espadachin.java
 create mode 100644 fase03/lab22/Lancero.java
 create mode 100644 fase03/lab22/Mapa.java
 create mode 100644 fase03/lab22/Soldado.java
 create mode 100644 fase03/lab22/Videojuego.java

$ git add Mapa
Mapa.java             MapaSuperior.java
MapaGUI.java          MapaSuperiorGUI.java

$ git add MapaGUI.java

$ git commit -m "Clase MapaGUI con atributos y constructor"
[main ef7b486] Clase MapaGUI con atributos y constructor
 1 file changed, 22 insertions(+)
 create mode 100644 fase03/lab22/MapaGUI.java

$ git add MapaGUI.java

$ git commit -m "Clase MapaGUI completa"
[main 7be2ea7] Clase MapaGUI completa
 1 file changed, 30 insertions(+)

$ git add Mapa.java

$ git add Soldado.java

$ git commit -m "Clase Mapa con interfaz grafica en MapaGUI"
[main 2979a36] Clase Mapa con interfaz grafica en MapaGUI
 2 files changed, 42 insertions(+), 76 deletions(-)

$ git add MapaSuperior.java

$ git commit -m "Clase MapaSuperior para manejar las batallas internas"
[main edec0a3] Clase MapaSuperior para manejar las batallas internas
 1 file changed, 39 insertions(+)
 create mode 100644 fase03/lab22/MapaSuperior.java

$ git add MapaSuperior.java

$ git add MapaSuperiorGUI.java

$ git commit -m "Clase MapaSuperiorGUI con atributos y constructor"
[main c0b4ba8] Clase MapaSuperiorGUI con atributos y constructor
 2 files changed, 51 insertions(+)
 create mode 100644 fase03/lab22/MapaSuperiorGUI.java

$ git add MapaSuperiorGUI.java

$ git commit -m "Clase MapaSuperiorGUI para manejar la interfaz grafica"
[main 8bfb33e] Clase MapaSuperiorGUI para manejar la interfaz grafica
 1 file changed, 34 insertions(+)

$ git add Videojuego.java

$ git commit -m "Clase Videojuego para el comportamiento iterativo"
[main 6377d8b] Clase Videojuego para el comportamiento iterativo
 1 file changed, 12 insertions(+), 8 deletions(-)

$ git push
Enumerating objects: 59, done.
Counting objects: 100% (59/59), done.
Delta compression using up to 4 threads
Compressing objects: 100% (53/53), done.
Writing objects: 100% (54/54), 16.91 KiB | 753.00 KiB/s, done.
Total 54 (delta 26), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (26/26), completed with 4 local objects.
To https://github.com/cmestasz/fp2-23b.git
   d31824d..6377d8b  main -> main