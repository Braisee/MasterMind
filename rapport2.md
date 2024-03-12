# Rapport de Conception Détaillé - Projet Mastermind (Mise à jour)

## Introduction
Le projet Mastermind vise à créer une application Java pour le jeu du Mastermind, en respectant les principes de conception et en utilisant le modèle MVC (Modèle-Vue-Contrôleur). Ce rapport détaillera notre conception en mettant l'accent sur les changements et évolutions apportés au code existant, en particulier en ce qui concerne l'utilisation des patrons de conception tels que l'Observer, la Strategy, et la Factory.

### Ajouts et Modifications dans le Package Controllers
#### Nouvelle Classe
- **ControllerGlobal:** La classe ControllerGlobal a été ajoutée pour gérer les paramètres globaux du jeu tels que le nombre de manches, de pions disponibles, etc. Elle interagit avec le plateau et permet de configurer ces paramètres.

### Ajouts et Modifications dans le Package Models
#### Nouvelles Classes
- **ObserverManche:** Une nouvelle interface ObserverManche a été introduite pour observer les changements au niveau de la manche. Les classes Jeu et ControllerJeu implémentent cette interface.

- **StrategyMode:** Une nouvelle interface StrategyMode a été créée pour définir les différentes stratégies qui permettront de définir l’affichage voulu d’une ligne d’indice en fonction de son mode de jeu.

- **Classes Classique, Facile, Numerique:** Ces classes ont été introduites pour implémenter l'interface StrategyMode, fournissant des méthodes spécifiques d'affichage des indices en fonction du mode choisi.

#### Modifications dans les Classes Existants
- **Partie:** La classe Partie a été étendue pour inclure une référence vers la FactoryMode, permettant de créer dynamiquement les stratégies d'affichage en fonction des préférences du joueur.

- **Manche:** La classe Manche a été mise à jour pour implémenter l'interface ObserverManche et pour observer les changements au niveau de la manche.

### Ajouts et Modifications dans le Package View
#### Nouvelles Classes
- **FactoryMode:** Une nouvelle classe FactoryMode a été ajoutée dans le package View pour créer les différentes stratégies d'affichage (Classique, Facile, Numérique).

- **StrategyMode:** Une nouvelle interface StrategyMode a été introduite pour définir les différentes stratégies qui permettront de définir l’affichage voulu d’une ligne d’indice en fonction de son mode de jeu.

- **Classes Classique, Facile, Numerique:** Ces classes ont été ajoutées pour implémenter l'interface StrategyMode et fournir des méthodes spécifiques d'affichage des indices en fonction du mode choisi.

#### Modifications dans les Classes Existants
- **Jeu:** La classe Jeu a été mise à jour pour observer les changements au niveau de la manche en implémentant l'interface ObserverManche. Elle possède également une référence vers l'interface StrategyMode pour changer dynamiquement le comportement d'affichage des indices en fonction du mode choisi par le joueur.

### Utilisation des Patrons de Conception
#### Observer
- Le patron de conception Observer est toujours utilisé pour permettre à la vue (`Jeu`) d'observer les changements dans le modèle (`Manche`). La classe `Manche` implémente l'interface `ObserverManche`, qui définit les méthodes de mise à jour du plateau en cas de validation ou de modification des pions.

#### Strategy
- Le patron de conception Strategy est toujours employé pour gérer les différentes stratégies d'affichage des lignes d'indices. L'interface `StrategyMode` définit la méthode `AfficherIndice`, et des classes concrètes telles que `Classique`, `Facile`, et `Numerique` implémentent cette interface. La classe `Jeu` possède une référence vers l'interface `StrategyMode`, ce qui lui permet de changer dynamiquement le comportement d'affichage des indices en fonction du mode choisi par le joueur.

#### Factory
- Le patron de conception Factory est toujours utilisé pour créer les différentes stratégies d'affichage (`Classique`, `Facile`, `Numerique`). La classe `FactoryMode` agit comme une factory qui crée une instance de la stratégie demandée. Le contrôleur (`ControllerGlobal` et `ControllerJeu`) utilise cette factory pour configurer le mode d'affichage en fonction des préférences du joueur. L'utilisation de la Factory permet de déléguer la création d'objets à une classe spécialisée, rendant le code client plus flexible et évitant une dépendance directe aux classes concrètes.

### Relations entre les Classes
#### Nouvelles Relations
- Une relation a été ajoutée entre les classes `ControllerGlobal`, `FactoryMode`, et `StrategyMode` pour montrer la gestion des paramètres globaux du jeu et la création des stratégies d'affichage.

#### Modifications dans les Relations Existants
- Les relations existantes ont été modifiées pour refléter les nouvelles dépendances introduites par les classes et interfaces ajoutées.

## Conclusion
En conclusion, cette mise à jour de la conception du jeu Mastermind a introduit de nouvelles classes, interfaces et relations pour améliorer la modularité, l'extensibilité et l'organisation du code. Les changements apportés visent à renforcer la flexibilité et la maintenabilité du code tout au long du développement du projet. Ce rapport met en lumière les évolutions dans l'utilisation des patrons de conception Observer, Strategy, et Factory, soulignant leur importance dans la réalisation d'une application robuste et évolutive.
