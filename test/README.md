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

0171667180
