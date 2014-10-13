MasterMind-IA
=============

Intento de implementación para la resolución del mastermind utilizando algoritmos genéticos mediante el framework JGAP

Utilización
-----------
**java -jar masterMind** Esta secuencia correrá un juego con los parámetros normales tomando un código que será develado al principio de la jugada.

**java -jar masterMind Color1 Color2 Color3 Color4** Esta secuencia correrá con el código generado por la combinación y posición de los colores pasador pro argumento.

Configuración
-------------
Los parámetros que se pueden configurar mediante el archivo config.properties son:

**poblacionSize** tamaño de la población

**maximasEvoluciones** máxima cantidad de evoluciones

**archivoDeLog** el nombre del archivo (sin la extensión csv) que se utilizará para el log de los mejores genes de cada corrida. En caso de no definir este parámetro, es obviada la creación del mismo.

**selector** Para elegir el tipo selección que tomará nuestro sistema y puede tomar los valor de ranking o torneo.

**mutacion** Permite elegir el tipo de mutación utilizada. Puede tomar valores de simple, asc o desc.

**indiceDeMutacion** Este valor X será expresado como el denominador de la fracción 1 / X. Por ejemplo, 1000 daría lugar a 1/1000 genes que están mutado en promedio. Una tasa de mutación de cero desactiva completamente la mutación.
