# :rocket: Sample spring-app

Projet template d'une application springboot 

Le projet expose principalement des APIs CRUD de gestion de bateau :boat:

**Choix d'implémentations** : 
- Architecture hexagonale
- API REST : Gestion des contrats d'interfaces via OpenAPI
- Stockage : Fake BDD via stockage dans des fichiers Json
- Authentification : Token JWT géré via spring-security + BDD H2 embarqué

<br/>

**Les points qui sont pas traités/aboutis** :
- Gestion des retours 403 --> On ne sait pas si l'utilisateur n'existe pas, si il n'est pas loggué ou si le token a expiré
- Desactivation de la partie `spring-security` via un profil spring --> actuellement en commentant une dep maven
- Pas de gestion de version de l'app
- Brique de testing d'intégration bout en bout

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

## :key: Authentification via spring-security

Mise en place d'une authentification via spring-security et l'utilisation de token JWT.

Il sera nécessaire de d'abord passer par l'api d'authentification pour récupérer un token, qui devra ensuite être fournit pour utiliser les APIs.

La partie gestion de l'authentification est isolé dans le module `api-security` qui gère ça en autonomie.

Il est donc possible de facilement supprimer la partie securité en commentant la dependance dans le module `app` :
```xml
  <dependency>
      <groupId>fr.forge.sample.spring</groupId>
      <artifactId>spring-sample-api-security</artifactId>
  </dependency>
```

### JWT

Récupération du token JWT via l'API `POST /api/v1/login`. 
Par exemple avec l'utilisateur fournit par defaut (avec un body json ou un header `Content-Type` = `application/json`) :
```json
{
  "email": "jack.sparrow@caraibe.com",
  "password": "test"
}
```

Il faut ensuite fournir le token lors de l'appel des APIs (avec un header `Authorization` = `Bearer {{token}}`).

### BDD H2

Pour stocker les utilisateurs autorisés, on passe par une BDD embarquée H2 disponible uniquement dans le module `api-security`

C'est un choix d'implémentation car dans cette appli la gestion des users est uniquement nécessaire pour l'authentification.

Les users sont initialisés au démarrage via le script `data.sql`.

Le mot de passe du user est hashé via bcrypt (outil pour générer un hash : https://bcrypt.online/)

** Info sur la console **
- Console de BDD embarqué H2 disponible ici : http://localhost:8080/h2-console
- Accès via le user/pass : test/test (spécifié dans la config)
- Pour pouvoir accèder à la console sans token, il y une exception dans la conf spring-security.

### HTTPS & TLS 1.3 & SSL-2-WAY

Tentative de mise en place de SSL-2-WAY pour forcer l'utilisation de certificat coté client.

Génération clé publique/privée via keytool :
```shell
keytool -genkeypair -alias forge -dname "CN=localhost" \
  -keyalg RSA -keysize 4096 -validity 3650 \
  -keystore keystore.pfx -keypass fake-keypass-mdp \
  -storeType PKCS12 -storepass fake-storepass-mdp
```



<br/>

## :whale: Image docker

Build d'une image docker via le fichier `Dockerfile`.

**Commande docker** :

```shell
# Build image
sudo docker build --tag=sample-spring-app:latest .
# Run image
sudo docker run -d -p 8080:8080 sample-spring-app:latest
```


<br/>

## :gear: Toolings

### CI-CD via Github

Le pipeline github actions est dispo ici : `.github/workflows/build-and-publish.yml`

Il permet de faire un `maven package`, puis de builder/publier l'image docker.

L'image est ensuite disponible dans la section `Packages` du projet Github.


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



<br/>

## :scroll: Ressources utilisées

Documentation : 
- BDD H2 : https://www.baeldung.com/spring-boot-h2-database