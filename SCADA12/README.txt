Ce programme vise à tester l'excellente librairie Javamod.
C'est un jeu d'écluse pour 4 participants. Chaque participant est responsable d'une barriere.
Lorsqu'il effectue un changement, il informe les autres par l'envoie de trames Modbus. 
Ce programme peut être tester en local, c'est à dire lancer 4 applications sur un meme ordinateur.

1)Crée un projet Ecluse dans éclipse et mettez le contenu de ce dossier dans le dossier Ecluse nouvellement créée dans votre workspace
2)Ajouter en librairie externe le comm.jar de ce dossier, effacer l'ancien
Maintenant le code devrait compiler

3)Pour faire les tests en local (c'est à dire sur le même ordinateur), il vous faut :
Lancer 4 fois le programme, on vous demandera le numéro de barierre, indiquez successivement 3, 2, 1 et 0

Maintenant indiquez true à chaque fois aux questions posées en console, et notez le résultat sur les 4 écrans. Les quatres écran devraient être similaires.

4)Pour faire les tests sur 4 ordis différents. Il suffit que chaque participant suive les questions sur console. Dans ce cas, chaque participant lance une seule application bien sûr.

Problèmes connus en non local :
_Du à certains firewalls une exception time out peut apparaître lors de l'établissement d'une connection TCP
_ Suivant votre OS votre slave écoutera sur votre adresse IP ou sur l'adresse localHost 127.0.0.1
Dans ce dernier cas, il vous faut vous rendre dans las classe ModbusTCPListener du package Modbus.net et décommenter la ligne 62, 63, et compléter les informations manquantes.



Equipe CyberSCADA


