Ci dessous le code de l'article Implémentation d'une architecture deux tiers en Java EE

Compilation avec la commande Maven suivante (70 Mo d'espace requis)

mvn clean install


Exécution avec la commande Maven

mvn exec:java

ou directement en Java :

java -jar ccm-kx-server-jar-with-dependencies.jar


Pour arrêter le serveur, il faut appuyer sur Entrée dans la ligne de commande.

Exemples d'utilisation avec un navigateur web :

Liste des utilisateurs (renvoie la liste des login)
http://localhost:8080/user/list

Modification du mot de passe de l'administrateur (renvoie null)
http://localhost:8080/user/updatePassword?userLogin=admin&userPassword=admin&newPassword=ADMIN

Création d'un nouvel utilisateur par l'administrateur (renvoie null)
http://localhost:8080/user/create?userLogin=admin&userPassword=ADMIN&newLogin=toto&newPassword=123456

Pour manipuler directement la base de données vous pouvez lancer la console H2 comme ceci :

java -cp ccm-kx-server-jar-with-dependencies.jar org.h2.tools.Console


Avec les même paramètres que ceux décrits dans le fichier persistence.xml :
- Pilote → org.h2.Driver
- URL → jdbc:h2:file:C:\h2\ccm.kx.server.h2
- Utilisateur → admin
- Mot de passe → admin

Erreur fréquente :
Lors de l'utilisation du programme, vous pourriez avoir cette erreur au démarrage :

java.lang.ExceptionInInitializerError
Caused by: org.hibernate.service.spi.ServiceException: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment]
Caused by: org.hibernate.HibernateException: Access to DialectResolutionInfo cannot be null when 'hibernate.dialect' not set


Ce message apparaît lorsque le fichier de la base de donnée est déjà utilisé, soit par une autre instance de serveur, soit par la console H2.
