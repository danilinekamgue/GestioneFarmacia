1- create war file : mvn clean install

2- copiare e incollare il farmacia.war dentro la cartella webapps dove è installato tomcat sul tuo pc.

3- rivviare o lanciare tomcat

4- dal browser digitare: http://localhost:8080/farmacia

5- Se la prima volta per creare il database :
fare la get: http://localhost:8080/farmacia/init-db

login: 

admin user: admin@admin.it password: admin

client user: s@s.s password: s@s.s

------------ ICI C EST A SUPPRIMER APRES

1 - Install Apach Tomcat on your pc
https://tomcat.apache.org/download-90.cgi
2 - add pluging smart tomcat
3 - configure appache tomcat (edit conf -> tomcat->)
4 - 

5 inser the filter to check the security
- create class that implement the filter
- go into web.xml to register the filter and add rule url

DATABASE

1-) docker pull mysql:8.2
2-) docker run --name db-mysql -e MYSQL_ROOT_PASSWORD=password -p 3307:3306 MYSQL_DATABASE=farmacia -d mysql:8.2
-----------------------------------





