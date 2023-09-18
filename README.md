# Exercice pratique - La carte aux trésors - Carbon IT

---

## Objectif du projet

L'objectif du projet est de parvenir à simuler l'évolution d'aventuriers à la recherche de trésors.
Pour cela, le programme dispose en entrée d'instructions permettant de construire la carte de la zone, et doit produire un fichier avec le résultat final de la simulation.

## Exécuter le programme

Pour exécuter le programme en dehors d'un IDE, suivez les instructions suivantes :

1. Cloner ou télécharger le repo.
2. Exécuter la commande `mvn clean install` à la racine du projet.
3. Placer le fichier de la carte dans le dossier `./data/`.
4. Exécuter la commande `java -jar target/carbon-it-treasure-hunt-jar-with-dependencies.jar` à la racine du projet.
5. Suivre les instructions du programme.

## Exécuter les tests

1. Cloner ou télécharger le repo.
2. Exécuter la commande `mvn clean test` à la racine du projet.

## Architecture

### Général
Le projet utilise au maximum l'injection de dépendance afin de maintenir les classes indépendantes et testables. 
De plus, il utilise de l'inversion de dépendance lorsque cela aide à rendre le code plus modulaire (notamment sur la partie I/O).

### Carte de la région
Pour le modèle de données de la carte, le projet suit une approche préférant la composition à l'héritage. 
Ainsi, plutôt que de créer des classes Plain, Mountain, ou Treasure héritant d'une classe Tile, il n'existe qu'une classe Tile qui est une collection de comportements.
Créer une case de la carte d'un type donné revient alors à instancier une Tile avec les bons comportements.

Cette approche rend le code légèrement plus verbeux dans le cas où il n'y a qu'une poignée de types de cases.
En revanche, elle permet au programme d'être beaucoup plus facilement extensible en découplant les comportements les uns des autres.

En effet, si on voulait ajouter une case, par exemple un lac représenté par un "L" sur lequel un aventurier ne peut pas aller, on pourrait simplement réutiliser les comportements existants et les combiner d'une nouvelle façon plutôt que de créer une nouvelle classe en dupliquant potentiellement du code. 
Avec l'héritage, il faudrait ici soit dupliquer du code de la classe Mountain, soit créer une classe parente dont héritent Mountain et Lake ce qui impliquerait de changer pas mal de code, notamment le code de Mountain qui n'a rien demandé.
Au bout d'un moment, avec suffisamment de type de cases, on arriverait à une arborescence beaucoup trop compliquée et fragile.

### Gestion des aventuriers
Pour effectuer les actions des aventuriers, le projet suit un design pattern de type command afin d'avoir des unités autocontenues qui peuvent être exécutées par une classe sans qu'elle n'ait besoin de savoir comment l'action fonctionne. 

## Environnement technique

- Java 18
- Maven
- JUnit 5
- Mockito 5


*@ 2023 Copyright: Enzo FILANGI*