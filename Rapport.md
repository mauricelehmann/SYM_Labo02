# SYM - Laboratoire 02 - Réponses aux questions

### 3.2 Transmission différée
TODO Notre implémentation présente les limitations suivantes...
TODO La meilleure manière de le faire est ...

## 4 Questions
### 4.1 Traitement des erreurs 
#### Q:
>Les classes et interfaces SymComManager et CommunicationEventListener, utilisées au point 3.1, restent très >>(et certainement trop) simples pour être utilisables dans une vraie application: que se passe-t-il si le serveur n’est pas joignable dans l’immédiat ou s’il retourne un code HTTP d’erreur? Veuillez proposer une nouvelle version, mieux adaptée,de ces deux classes / interfaces pour vous aider à illustrer votre réponse.

#### R:
TODO

### 4.2 Authentification
#### Q:
>Si une authentification par le serveur est requise, peut-on utiliser un protocole asynchrone ? Quelles seraient les restrictions ? Peut-on utiliser une transmission différée ?

#### R:
TODO




### 4.3 Threads concurrents
#### Q:
>Lors  de  l'utilisation  de  protocoles  asynchrones,  c'est  généralement  deux  threads  différents  qui  se répartissent les différentes étapes (préparation, envoi, réception et traitement des données) de la communication. Quels problèmes cela peut-il poser ?

#### R:
TODO




### 4.4 Écriture différée
#### Q:
>Lorsque l'on implémente l'écriture différée, il arrive que l'on ait soudainement plusieurs transmissions en  attente  qui  deviennent  possibles  simultanément.  Comment  implémenter  proprement  cette situation ? Voici deux possibilités:
>
>- Effectuer une connexion par transmission différée
>- Multiplexer toutes les connexions vers un même serveur en une seule connexion de transport. Dans ce dernier cas, comment implémenter le protocole applicatif, quels avantages peut-on espérer de ce multiplexage, et surtout, comment doit-on planifier les réponses du serveur lorsque ces dernières s'avèrent nécessaires ?
>
>Comparer les deux techniques (et éventuellement d'autres que vous pourriez imaginer) et discuter des avantages et inconvénients respectifs.

#### R:
TODO




### 4.5 Transmission d’objets
#### Q:
>a. Quel  inconvénient  y  a-t-il  à  utiliser  une  infrastructure de  type  REST/JSON  n'offrant  aucun service de validation (DTD, XML-schéma, WSDL) par rapport à une infrastructure comme SOAP offrant ces possibilités ? Est-ce qu’il y aen revanche des avantages que vous pouvez citer ?
>
>b. L’utilisation  d’un  mécanisme  comme Protocol   Buffers5est-elle  compatible  avec  une architecture basée sur HTTP? Veuillez discuter des éventuelles avantages ou limitations par rapport à un protocole basé sur JSON ou XML?
>
>c. Par rapport à l’API GraphQL mise à disposition pour ce laboratoire. Avez-vous constaté des points qui pourraient être améliorés pour une utilisation mobile?Veuillez en discuter, vous pouvez élargir votre réflexion à une problématique plus large que la manipulation effectuée.

#### R:
TODO



### 4.6 Transmission compressée
#### Q:
>Quel gain de compression(en volume et en temps)peut-on constater en moyenne sur des fichiers texte (xml et json sont aussi du texte) en utilisant de la compression du point 3.4 ?Vous comparerez plusieurs tailles et types de contenu.

#### R:
TODO