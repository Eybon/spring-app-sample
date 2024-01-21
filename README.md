# Sample spring-app

Sample d'une application springboot 

**Choix d'implémentations** : 
- Architecture hexagonale
- API REST : Gestion des contrats d'interfaces via OpenAPI
- BDD : Fake BDD via stockage dans des fichiers Json



<br/>

## :squid: API REST - OpenAPI

La couche API est gérée dans le module `api`.

Definition des contrats d'interfaces via OpenAPI. Le fichier OpenAPI est disponible dans le dossier `api`.

**Exemple de requête sur les APIs**
- Description d'un bateau :
    - OK -> GET http://localhost:8080/api/v1/boat?boatName=Hollandais_Volant
    - KO -> GET http://localhost:8080/api/v1/boat?boatName=Titanic
- Récupération de l'image d'un bateau :
    - OK -> GET http://localhost:8080/api/v1/boat/img?boatName=Black_Pearl
    - KO -> GET http://localhost:8080/api/v1/boat/img?boatName=Hollandais_Volant



<br/>

## :clipboard: BDD - Stockage Json

La couche BDD est gérée dans le module `infrastructure`.

Plutot que d'utiliser une BDD embarqué de type H2, on stockera les données dans des fichiers Json (choix purement arbitraire).

L'implémentation est basé sur un librairie custom : [json-datafile](https://github.com/Eybon/json-datafile)

Les fichiers de la BDD sont disponible dans le dossier `bdd`.



<br/>

## :package: CI-CD via Github

TODO



<br/>

## :gear: Toolings

### Client REST de test : Bruno

Alternative aux outils Postman/Insomnia/etc... --> https://github.com/usebruno/bruno

La collection est disponible dans le dossier `tools/bruno`

### Documentation API : Redoc

Installation outil : 
```shell
# Installation outil via npm
npm install -g http-server
# Demarrage de la doc
http-server ./ -p 8081
```

Doc dispo ici : http://127.0.0.1:8081/redoc.html