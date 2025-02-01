# Backend du projet GeoLocation et Map

## Description

Ce projet constitue le backend de l'application mobile de géolocalisation. Il est responsable de la gestion des données de localisation, à savoir leur stockage et leur récupération.

### Fonctionnalités principales :

- Création de positions :
L'API createPosition.php reçoit les coordonnées GPS (longitude et latitude) envoyées par l'application mobile via une requête HTTP POST.
Ces données sont ensuite enregistrées dans la base de données.
- Récupération des positions :
L'API showPosition.php est sollicitée par l'application mobile pour obtenir la liste de toutes les positions enregistrées.
Elle interroge la base de données et renvoie les résultats au frontend via une requête HTTP POST.

### Architecture technique :

- Langage : PHP
- Base de données : MySQL
- Méthode de communication : Requêtes HTTP POST pour l'échange de données entre le frontend et le backend.


