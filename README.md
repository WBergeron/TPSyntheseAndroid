# TPSyntheseAndroid
TP finale du cours d'Android en Kotlin

## Description
Le projet consistait, en équipe de 3, de développer une application android en kotlin pour suivre l'état d'un réseau fictif fourni par l'api de notre professeur.
Cet api répondait à des requêtes diverse en JSON et nous traiton l'information / interagissait avec l'api pour les différents modules demander.

Projet effectuer avec Julius Leblanc et Raphaël Nadeau

## Image
### 1er module
![Login](https://github.com/WBergeron/TPSyntheseAndroid/assets/70408290/a1160440-c14e-4df5-88ec-60f9d7b3c2d0)![Billets](https://github.com/WBergeron/TPSyntheseAndroid/assets/70408290/344cc290-fd76-48bf-9fba-fa62f42801b4)![BilletsDetailler](https://github.com/WBergeron/TPSyntheseAndroid/assets/70408290/0b6e6550-594a-4618-804c-121ee72d8d3c)

Au début de l'application des 10 secondes de connexion sont effectuées.

Le premier module affiche des billets enregistrés trier par niveau de criticité. La liste se met à jour à chaque minute.

Après le clic d'un module, les détails du billet s'affichent avec un pays indiquer. Si on clic sur l'icône de carte, une intégration de googler map sort l'endroit que le billet à été ouvert. Deux boutons peuvent être clicker, résoudre, qui permet de changer le statut du billet et installer, qui permet d'installer une borne à l'endroit du billet à l'aide d'un scan QR a partir de la caméra du téléphone.

### 2ème module
![Passerelle](https://github.com/WBergeron/TPSyntheseAndroid/assets/70408290/cbf0c97f-e701-4dec-8a26-ac5e20e183a2)![PasserelleDetail](https://github.com/WBergeron/TPSyntheseAndroid/assets/70408290/ee95748d-a1a4-427b-a999-5cc609f6f6e8)

Le deuxième module gère les différentes bornes déjà installer. La liste se met à jour chaque minute.

Après avoir clicker sur une borne, ces détails s'affichent. Sa latence est mise à jour à chaque fois que nous appuyons sur le bouton "mise à jour". Redémarer permet de rafraichir toutes les données.

### 3ème module
![Reseau](https://github.com/WBergeron/TPSyntheseAndroid/assets/70408290/829b04bc-e509-44e6-b4b2-bdc6ae94a6fa)

Le troisième module est une liste de réseau qui est présent. Les informations sont mise à jour périodiquement

## Note
A noté que l'application est disponible en anglais et français et aussi, est adapté sur un format paysage
