# **SoapProject**

Ce dépôt contient un projet complet composé d'un **frontend Android** et d'un **backend Spring Boot**, mettant en œuvre des services SOAP grâce à la bibliothèque **KSoap2**. 
Ce projet illustre comment utiliser SOAP pour communiquer entre une application mobile et un serveur backend.

## **Description du projet**

Ce projet est une solution client-serveur permettant de gérer des comptes bancaires. 
L'application Android offre une interface utilisateur simple et interactive pour consulter, ajouter, modifier et supprimer des comptes. 
Le backend Spring Boot fournit des API SOAP qui gèrent les opérations côté serveur.

---

## **Technologies utilisées**

### **Frontend Android**
- **Langage :** Java
- **Réseau :** KSoap2 pour la consommation des services SOAP
- **Interface utilisateur :** RecyclerView pour afficher les comptes
- **IDE :** Android Studio

### **Backend Spring Boot**
- **Langage :** Java (Spring Framework)
- **Services :** SOAP via JAX-WS
- **Base de données :** H2

---

## **Structure du projet**

### **1. Frontend Android**
- **Adapters :** Gestion des RecyclerView
- **KSoap2 :** Intégration pour la communication SOAP
- **UI :** Affichage des comptes bancaires avec des options d'édition et de suppression

### **2. Backend Spring Boot**
- **Services SOAP :** Fourniture des services CRUD pour les comptes
- **Endpoints :** Gestion des comptes (ajout, mise à jour, suppression, récupération)
- **Base de données :** H2 pour la persistance des données





## Vidéo Démonstrative

La vidéo ci-contre montre le fonctionnement du projet :

<div align="center">

[Voir la vidéo](https://github.com/user-attachments/assets/9b39aed5-e76f-42ab-b13b-4178d8347a20)


</div>
