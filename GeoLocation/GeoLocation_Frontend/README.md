# GeoLocalisation Et Map

## Description

Ce projet vise à développer une application mobile Android native, en Java, capable d'enregistrer et de visualiser des positions géographiques.

### Fonctionnalités principales :

- Enregistrement de la position: L'application capture les coordonnées GPS (longitude et latitude) de l'appareil et les transmet à une API PHP pour stockage dans une base de données.
- Visualisation des positions: L'application récupère l'historique des positions enregistrées depuis l'API PHP et les affiche sur une carte interactive sous forme de marqueurs.
  
### Architecture de l'application:

L'application est structurée en trois activités :

- Écran d'accueil: Affiche le logo de l'application et une animation d'introduction.
- Écran principal:
  * Demande les permissions de localisation à l'utilisateur.
  * Récupère les coordonnées GPS en arrière-plan.
  * Envoie les coordonnées à l'API PHP pour enregistrement.
- Écran de la carte:
  * Récupère l'historique des positions depuis l'API PHP.
  * Affiche les positions sur une carte à l'aide d'une bibliothèque de cartographie (Google Maps SDK).
    
## Vidéo Démonstrative

La vidéo ci-contre montre le fonctionnement du projet :

<div align="center">

[Voir la vidéo](https://github.com/user-attachments/assets/cd37b389-c84b-4193-845b-0598de4b403a)


</div>
