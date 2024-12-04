# MaVille

Description du projet:
> <i>MaVille</i> est une application dont les buts principaux sont d'informer les résidents sur les projets de construction à venir et de donner une voix aux résidants quant aux choix qui sont faits par rapport aux projets de construction. En effet, les responsables des projets de construction déposent des projets futurs qui peuvent être consultés par les résidents et ceux-ci peuvent même soumettre des requêtes de travaux. De plus, les résidents peuvent être notifiés lorsque des nouveaux projets qui affectent une rue ou un quartier qu'ils fréquentent apparaîssent. Avec cette application, on souhaite donc atténuer les sentiments de frustration des résidents surpris par l'apparition de nouveaux chantiers de construction.

Liste des fonctionnalités de l'application par rôle :
Résidents :
<ul>
<li>Créer un compte.</li>
<li>Se connecter et déconnecter.</li>
<li>Modifier leurs préférences horaires.</li>
<li>Consulter les travaux en cours et à venir.</li>
<li>Rechercher ou filtrer les travaux par quartier.</li>
<li>Consulter les entraves et rechercher par rue.</li>
<li>Soumettre une requête de travail et suivre son avancement.</li>
<li>Consulter leurs requêtes de travail.</li>
<li>Recevoir des notifications.</li>
</ul>

<ul>Intervenants :
<li>Créer un compte.</li>
<li>Soumettre des candidatures pour des requêtes de travail.</li>
<li>Soustraire leurs candidatures.</li>
<li>Soumettre des projets.</li>
<li>Modifier le statut des projets.</li>
<li>Envoyer des notifications aux résidents concernés.</li>
</ul>

Organisation du répertoire:
> [/prototype2](/prototype2/): Ce folder contient tous les fichiers nécessaire pour éxécuter le prototype.
>> [/src](/prototype2/src/): Ce folder contient les fichiers Java et les fichiers csv.
>>> [/test](/prototype2/src/test/): Ce folder contient les fichiers pour les tests dont des fichiers CSV.<br/>
>>>> [/java](/prototype2/src/test/java/): Ce folder contient le fichier Java pour les tests.<br/>

>>> [/main/java/org/prototype](/prototype2/src/main/java/org/prototype/) Ce folder contient toutes les classes présentes dans le prototype.
>>>> [/API](/prototype2/src/main/java/org/prototype/API/) Ce folder contient les fichiers Java nécessaires pour effectuer des requêtes HTTP. <br/>
>>>> [/Controllers](/prototype2/src/main/java/org/prototype/Controllers/) Ce folder contient toutes les classes qui représente les Controller dans l'architecture MVC. <br/>
>>>> [/Models](/prototype2/src/main/java/org/prototype/Models/) Ce folder contient toutes les classes qui représente les Modèles dans l'architecture MVC. <br/>
>>>> [/Views](/prototype2/src/main/java/org/prototype/Views/) Ce folder contient toutes les classes qui représente les Vues dans l'architecture MVC. <br/>

> [/diagrammes](/diagrammes/): Ce folder contient tous les images de diagrammes présentes dans le rapport.<br/>
> [rapport.html](/rapport.html): Ce fichier contient le rapport du devoir.

<h2> Instructions pour éxécuter et tester l'application:</h2>
D'abord, il faut télécharger une copie du folder prototype2
<h3>Tester:</h3>
<ul>
    <li>
        Se rendre dans le folder <code>prototype2</code>
    </li>
    <li>
        Éxécuter la commande <code>mvn test</code>
    </li>
</ul>
<h3>Éxécuter:</h3>
<ul>
    <li>
        Se rendre dans le folder <code>prototype2</code>
    </li>
    <li>
        Éxécuter la commande <code>mvn package</code>
    </li>
    <li>
        Créer un folder <code>src</code> dans le folder <code>prototype2/target</code> et y déposer les fichiers de <code>prototype2/data</code>
    <li>
        S'assurer d'être dans le folder <code>prototype2/target</code> et éxécuter <code>java -cp prototype2-1.jar org.prototype.MaVille</code>
    </li>    
</ul>    