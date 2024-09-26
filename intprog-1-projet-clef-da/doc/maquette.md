# 1. Maquettes d'interfaces pour une application de gestion de clef 
- [1. Maquettes d'interfaces pour une application de gestion de clef](#1-maquettes-dinterfaces-pour-une-application-de-gestion-de-clef)
  - [1.1. Description du besoin](#11-description-du-besoin)
  - [1.2. Navigation](#12-navigation)
  - [1.3. Maquettes](#13-maquettes)
    - [1.3.1. Liste des clefs (Liste)](#131-liste-des-clefs-liste)
    - [1.3.2. Détail d'une clef (Détail \<\<création>>)](#132-détail-dune-clef-détail-création)
    - [1.3.3. Détail d'une clef (Détail \<\<visualisation>>)](#133-détail-dune-clef-détail-visualisation)
    - [1.3.4. Détail d'une clef (Détail \<\<modification>>)](#134-détail-dune-clef-détail-modification)
    - [1.3.5. Détail d'une clef (Détail \<\<suppression>>)](#135-détail-dune-clef-détail-suppression)
    - [1.3.6. Liste des serrures (Liste)](#136-liste-des-serrures-liste)
    - [1.3.7. Détail d'une serrure (Détail \<\<création>>)](#137-détail-dune-serrure-détail-création)
    - [1.3.8. Détail d'une serrure (Détail \<\<visualisation>>)](#138-détail-dune-serrure-détail-visualisation)
    - [1.3.9. Détail d'une serrure (Détail \<\<modification>>)](#139-détail-dune-serrure-détail-modification)
    - [1.3.10. Détail d'une serrure (Détail \<\<suppression>>)](#1310-détail-dune-serrure-détail-suppression)
    - [1.3.11. Liste des utilisateurs (Liste)](#1311-liste-des-utilisateurs-liste)
    - [1.3.12. Détail d'un utilisateur (Détail \<\<création>>)](#1312-détail-dun-utilisateur-détail-création)
    - [1.3.13. Détail d'un utilisateur (Détail \<\<visualisation>>)](#1313-détail-dun-utilisateur-détail-visualisation)
    - [1.3.14. Détail d'un utilisateur (Détail \<\<modification>>)](#1314-détail-dun-utilisateur-détail-modification)
    - [1.3.15. Détail d'un utilisateur (Détail \<\<suppression>>)](#1315-détail-dun-utilisateur-détail-suppression)
    - [1.3.16. Liste des groupes (Liste)](#1316-liste-des-groupes-liste)
    - [1.3.17. Détail d'un groupe (Détail \<\<création>>)](#1317-détail-dun-groupe-détail-création)
    - [1.3.18. Détail d'un groupe (Détail \<\<visualisation>>)](#1318-détail-dun-groupe-détail-visualisation)
    - [1.3.19. Détail d'un groupe (Détail \<\<modification>>)](#1319-détail-dun-groupe-détail-modification)
    - [1.3.20. Détail d'un groupe (Détail \<\<suppression>>)](#1320-détail-dun-groupe-détail-suppression)
    - [1.3.21. Liste des lieux (Liste)](#1321-liste-des-lieux-liste)
    - [1.3.22. Détail d'un lieu (Détail \<\<création>>)](#1322-détail-dun-lieu-détail-création)
    - [1.3.23. Détail d'un lieu (Détail \<\<visualisation>>)](#1323-détail-dun-lieu-détail-visualisation)
    - [1.3.24. Détail d'un lieu (Détail \<\<modification>>)](#1324-détail-dun-lieu-détail-modification)
    - [1.3.25. Détail d'un lieu (Détail \<\<suppression>>)](#1325-détail-dun-lieu-détail-suppression)
  - [1.4. Références](#14-références)

## 1.1. Description du besoin

Le client souhaite une application pour gérer les clefs d'un batiment de manière centralisé
* un lieu peut être verrouillé par plusieurs serrures.
* une clé peut déverrouiller les serrures de plusieurs lieux.
* une personne est propriétaire d'une seul clé.
* une personne peut accéder à plusieurs lieux
* un lieu est composé de lieu.
* un historique des accès permet de connaître qui a accédé où et quand.
* un historique des modifications permet de connaître qui reçoit l'accès où et quand.

## 1.2. Navigation

![Navigation](navigation.svg)

## 1.3. Maquettes

### 1.3.1. Liste des clefs (Liste)

```
Liste des clefs
===================

[rechercher] : ________________________

[ajouter une clef]


|          action             |   numero de serie   |      status      |       utilisateur         | mise en service | fin de service |
+-----------------------------+---------------------+------------------+---------------------------+-----------------+----------------+
| [voir][modifier][supprimer] | 0000-0000-0000-0001 | ACTIVE           | caillet.cedric@rpn.ch     | 2022-02-05      |                |
| [voir][modifier][supprimer] | 0000-0000-0000-0002 | ACTIVE           | cyril.tobler@rpn.ch       | 2022-01-23      | 2022-04-16     |
| [voir][modifier][supprimer] | 0000-0000-0000-0003 | ACTIVE           | diogo.paivacosta@rpn.ch   | 2022-02-05      | 2022-04-12     |
| [voir][modifier][supprimer] | 0000-0000-0000-0004 | ACTIVE           | dominique.huguenin@rpn.ch | 2022-01-05      |                |
| [voir][modifier][supprimer] | 0000-0000-0000-0005 | ACTIVE           | darwin.ameli@rpn.ch       | 2022-01-24      | 2022-06-14     |
| [voir][modifier][supprimer] | 0000-0000-0000-0006 | DISFONCTIONNELLE |                           | 2022-01-06      | 2022-03-14     |
| [voir][modifier][supprimer] | 0000-0000-0000-0007 | PERDUE           |                           | 2021-12-10      | 2022-04-20     |
| [voir][modifier][supprimer] | 0000-0000-0000-0008 | INACTIVE         |                           | 2021-06-11      | 2021-11-23     |

```

### 1.3.2. Détail d'une clef (Détail \<\<création>>)

```
Détail d'une clef
===================

[annuler] [valider création]

numéro de série: 0000-0000-0000-0005
status: ACTIVE____________________________V
utilisateur: CailletC_____________________V
lieux: CIFOM - Etage 3 - 315______________V [supprimer]
       ___________________________________V

```

### 1.3.3. Détail d'une clef (Détail \<\<visualisation>>)

```
Détail d'une clef
===================

[annuler][modifier][supprimer]

numéro de série: 0000-0000-0000-0001
status: ACTIVE
utilisateur: cedric.caillet@rpn.ch
mise en service: 2022-02-05 00:00:00 
lieux d'accès: CIFOM - Etage 3 - 315

```

### 1.3.4. Détail d'une clef (Détail \<\<modification>>)

```
Détail d'une clef
=====================

[annuler] [valider modification]

numéro de série: 0000-0000-0000-0002
status: Active____________V  
utilisateur: ToblerC____________________V
lieu: CPLN - Etage 2 - B205_________V [supprimer] 
      ______________________________V
```

### 1.3.5. Détail d'une clef (Détail \<\<suppression>>)

```
Détail d'une clef
=====================

[annuler] [valider suppression]

numéro de série: 0000-0000-0000-0001
status: ACTIVE
utilisateur: cedric.caillet@rpn.ch
mise en service: 2022-02-05 00:00:00
lieu: CIFOM - Etage 3 - 315

```

### 1.3.6. Liste des serrures (Liste)

```
Liste des serrures
===================

[rechercher] : ________________________

[ajouter une serrure]

|          action             |        lieu           | cardinalite |                  numero de serie des clefs                    |           historique d'utilisateur            |
+-----------------------------+-----------------------+-------------+---------------------------------------------------------------+-----------------------------------------------+
| [voir][modifier][supprimer] | CIFOM - Etage 3 - 315 | NORD        | 0000-0000-0000-0004, 0000-0000-0000-0001                      | Dominique Huguenin, Cedric Caillet            |
| [voir][modifier][supprimer] | CIFOM - Etage 3 - 315 | SUD         | 0000-0000-0000-0004                                           | Dominique Huguenin                            |
| [voir][modifier][supprimer] | CPLN - Etage 1 - B101 | EST         | 0000-0000-0000-0003, 0000-0000-0000-0002, 0000-0000-0000-0005 | Diogo Paiva Costa, Cyril Tobler, Darwin Ameli |
| [voir][modifier][supprimer] | CPLN - Etage 1 - B102 | EST         | 0000-0000-0000-0003, 0000-0000-0000-0005                      | Diogo Paiva Costa, Darwin Ameli               |
| [voir][modifier][supprimer] | CPLN - Etage 2 - B205 | NORD        | 0000-0000-0000-0002                                           | Cyril Tobler                                  |
| [voir][modifier][supprimer] | CPLN - Etage 2 - B206 | SUD         |                                                               |                                               |
| [voir][modifier][supprimer] | CPLN - Etage 3 - B302 | EST         |                                                               |                                               |
| [voir][modifier][supprimer] | CPLN - Etage 3 - B303 | NORD        |                                                               |                                               |
| [voir][modifier][supprimer] | CPLN - Etage 4 - B401 | EST         |                                                               |                                               |

```

### 1.3.7. Détail d'une serrure (Détail \<\<création>>)

```
Détail d'une serrure
===================

[annuler] [valider création]

uuid: b02813c6-2f5e-4086-9cb3-dc35e16fd504
lieu: CPLN - Etage 1 - B102___V
cardinalité: OUEST____________V

```

### 1.3.8. Détail d'une serrure (Détail \<\<visualisation>>)

```
Détail d'une serrure
===================

[annuler][modifier][supprimer]

uuid: b02813c6-2f5e-4086-9cb3-dc35e16fd502
lieu: CIFOM - Etage 3 - 315
cardinalité: NORD
clef: 0000-0000-0000-0004
historique d'acces: Dominique Huguenin 2022-01-06 10:16:56

```

### 1.3.9. Détail d'une serrure (Détail \<\<modification>>)

```
Détail d'une serrure
=====================

[annuler] [valider modification]

uuid: b02813c6-2f5e-4086-9cb3-dc35e16fd501
lieu: CIFOM - Etage 3 - 315___V
cardinalité: NORD_____________V

```

### 1.3.10. Détail d'une serrure (Détail \<\<suppression>>)

```
Détail d'une serrure
=====================

[annuler] [valider suppression]

uuid: b02813c6-2f5e-4086-9cb3-dc35e16fd503
lieu: CPLN - Etage 2 - B205
cardinalité: NORD

```

### 1.3.11. Liste des utilisateurs (Liste)

```
Liste des utilisateurs
===================

[rechercher] : ________________________

[ajouter un utilisateur]


|           action            |     nom     |  prenom   |           groupes             |
+-----------------------------+-------------+-----------+-------------------------------+
| [voir][modifier][supprimer] | Ameli       | Darwin    | Etudiant CIFOM, Etudiant CPLN |
| [voir][modifier][supprimer] | Paiva Costa | Diogo     | Etudiant CIFOM                | 
| [voir][modifier][supprimer] | Tobler      | Cyril     | Etudiant CIFOM                |
| [voir][modifier][supprimer] | Caillet     | Cedric    | Enseignant de prog            |
| [voir][modifier][supprimer] | Huguenin    | Dominique | Enseignant de prog            |

```

### 1.3.12. Détail d'un utilisateur (Détail \<\<création>>)

```
Détail d'un utilisateur
===================

[annuler] [valider création]

nom: Cover______________________
prenom: Michel__________________
groupe: Enseignants____________V [supprimer]
        _______________________V
clef: 0000-0000-0000-0008______V
e-mail: michel.cover@rpn.ch_____

```

### 1.3.13. Détail d'un utilisateur (Détail \<\<visualisation>>)

```
Détail d'un utilisateur
===================

[annuler][modifier][supprimer]

nom: Ameli
prenom: Darwin
groupe: darwin.ameli@rpn.ch (groupe perso)
clef: 
e-mail: darwin.ameli@rpn.ch
historique d'accès: CPLN - Etage 1 - B101, CPLN - Etage 1 - B102

```

### 1.3.14. Détail d'un utilisateur (Détail \<\<modification>>)

```
Détail d'un utilisateur
=====================

[annuler] [valider modification]

nom: Ameli_____________________
prenom: Darwin_________________
groupe: darwin.ameli@rpn.ch ___V [supprimer] (groupe perso)
        _______________________V
clef:   _______________________V
e-mail: darwin.ameli@rpn.ch____

```

### 1.3.15. Détail d'un utilisateur (Détail \<\<suppression>>)

```
Détail d'un utilisateur
=====================

[annuler] [valider suppression]

nom: Ameli
prenom: Darwin
groupe: darwin.ameli@rpn.ch (groupe perso)
clef: 
e-mail: darwin.ameli@rpn.ch

```

### 1.3.16. Liste des groupes (Liste)
```
Liste des groupes
===================

[rechercher] : ________________________

[ajouter un groupe]


| action                      | nom                 |                          description                              | groupe parent     | 
+-----------------------------+---------------------+-------------------------------------------------------------------+-------------------+
| [voir][modifier][supprimer] | Conciergerie        | Groupe contenant tout le personnel de conciergerie des deux sites |                   |
| [voir][modifier][supprimer] | Conciergerie CPLN   | Groupe contenant tout le personnel de conciergerie du CIFOM       | Conciergerie      |
| [voir][modifier][supprimer] | Conciergerie CIFOM  | Groupe contenant tout le personnel de conciergerie du CPLN        | Conciergerie      |
| [voir][modifier][supprimer] | Enseignants         | Groupe contenant tous les enseignants des deux sites              |                   |
| [voir][modifier][supprimer] | Enseignants de prog | Groupe contenant tous les enseignants de prog des deux sites      | Enseignants       |
| [voir][modifier][supprimer] | Direction           | Groupe contenant les membres de direction des deux sites          |                   |
| [voir][modifier][supprimer] | Direction CPLN      | Groupe contenant les membres de direction du CPLN                 | Direction         |
| [voir][modifier][supprimer] | Direction CIFOM     | Groupe contenant les membres de direction du CIFOM                | Direction         |
| [voir][modifier][supprimer] | Etudiants           | Groupe contenant les etudiants des deux sites                     |                   |
| [voir][modifier][supprimer] | Etudiants CIFOM     | Groupe contenant les etudiants du CIFOM                           | Etudiants         |
| [voir][modifier][supprimer] | Etudiants CPLN      | Groupe contenant les etudiants du CPLN                            | Etudiants         |

```

### 1.3.17. Détail d'un groupe (Détail \<\<création>>)

```
Détail d'un groupe
===================

[annuler] [valider création]

uuid: b02813c6-2f5e-4086-9cb3-dc35e16fd514
nom: Enseignants informatique__________
groupe parent: Enseignants______________V [supprimer]
               _________________________V
description: Groupe contenant tous les enseignants informatique des deux sites__
utilisateurs: cedric.caillet@rpn.ch_____V [supprimer]
              dominique.huguenin@rpn.ch_V [supprimer]
              __________________________V

```

### 1.3.18. Détail d'un groupe (Détail \<\<visualisation>>)

```
Détail d'un groupe
===================

[annuler][modifier][supprimer]

uuid: b02813c6-2f5e-4086-9cb3-dc35e16fd511
nom: Enseignants de prog
groupe parent: Enseignants
description: Groupe contenant tous les enseignants de prog
utilisateurs: dominique.huguenin@rpn.ch, cedric.caillet@rpn.ch

```

### 1.3.19. Détail d'un groupe (Détail \<\<modification>>)

```
Détail d'un groupe
=====================

[annuler] [valider modification]

uuid: b02813c6-2f5e-4086-9cb3-dc35e16fd514
nom: Enseignants de prog
groupe parent: Enseignants______________V
description: Groupe contenant tous les enseignants de prog____
utilisateurs: cedric.caillet@rpn.ch_____V [supprimer]
              dominique.huguenin@rpn.ch_V [supprimer]
              __________________________V

```

### 1.3.20. Détail d'un groupe (Détail \<\<suppression>>)

```
Détail d'un groupe
=====================

[annuler] [valider suppression]

uuid: b02813c6-2f5e-4086-9cb3-dc35e16fd511
nom: Enseignants de prog
groupe parent: Enseignants
description: Groupe contenant tous les enseignants de prog
utilisateurs: dominique.huguenin@rpn.ch, cedric.caillet@rpn.ch

```

### 1.3.21. Liste des lieux (Liste)

```
Liste des lieux
===================

[rechercher] : ________________________

[ajouter un lieu]


|            action           |           nom           |
+-----------------------------+-------------------------+
| [voir][modifier][supprimer] | CIFOM                   |
| [voir][modifier][supprimer] | CIFOM - Etage 3         |
| [voir][modifier][supprimer] | CIFOM - Etage 3 - 315   |
| [voir][modifier][supprimer] | CPLN                    |
| [voir][modifier][supprimer] | CPLN - Etage 1          |
| [voir][modifier][supprimer] | CPLN - Etage 1 - B101   |
| [voir][modifier][supprimer] | CPLN - Etage 1 - B102   |
| [voir][modifier][supprimer] | CPLN - Etage 2          |
| [voir][modifier][supprimer] | CPLN - Etage 2 - B205   |
| [voir][modifier][supprimer] | CPLN - Etage 2 - B206   |
| [voir][modifier][supprimer] | CPLN - Etage 3          |
| [voir][modifier][supprimer] | CPLN - Etage 3 - B302   |
| [voir][modifier][supprimer] | CPLN - Etage 3 - B303   |
| [voir][modifier][supprimer] | CPLN - Etage 4          |
| [voir][modifier][supprimer] | CPLN - Etage 4 - B401   |

```

### 1.3.22. Détail d'un lieu (Détail \<\<création>>)

```
Détail d'un lieu
===================

[annuler] [valider création]

nom: 301_____________________
lieu parent: CIFOM - Etage 3__V

```

### 1.3.23. Détail d'un lieu (Détail \<\<visualisation>>)

```
Détail d'un lieu
===================

[annuler][modifier][supprimer]

nom: CPLN
lieu parent:

```

### 1.3.24. Détail d'un lieu (Détail \<\<modification>>)

```
Détail d'un lieu
=====================

[annuler] [valider modification]

nom: B101___________________
lieu parent: CPLN - Etage 1__V

```

### 1.3.25. Détail d'un lieu (Détail \<\<suppression>>)

```
Détail d'un lieu
=====================

[annuler] [valider suppression]

nom: CPLN - Etage 1
lieu parent: CPLN

```

### 1.3.26. Historique des ouvertures  (Liste)

```
Historique des ouvertures
===================

[rechercher] : ________________________

| acces  |           lieu         | cardinalite |    numero_serie     |    utilisateur      |
+--------+------------------------+-------------+---------------------+---------------------+
| Refusé | CIFOM - Etage 3 - 315  | SUD         | 0000-0000-0000-0004 | Huguenin Dominique  |
| Refusé | CIFOM - Etage 3 - 315  | EST         | 0000-0000-0000-0004 | Huguenin Dominique  |
| Refusé | CIFOM - Etage 3 - 315  | SUD         | 0000-0000-0000-0004 | Cover Michel        |
| Refusé | CIFOM - Etage 3 - 315  | EST         | 0000-0000-0000-0004 | Cover Michel        |

```

## 1.4. Références

[Adresse du CIFOM](https://www.local.ch/fr/d/le-locle/2400/ecole-professionnelle-specialisee-ecole-professionnelle/ecole-technique-et-cifom-edoQ0qp3CAwVJMl-nfg-KQ)
[Adresse du CPLN](https://www.local.ch/fr/d/neuchatel/2000/centre/cpln-centre-professionnel-du-littoral-neuchatelois-Ld1JjVGWdvYatP7e9YJirw)
