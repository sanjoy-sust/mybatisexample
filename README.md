# mybatisexample
Spring mybatis sample project with complete java config

#Steps to run this project : 

1. Install mysql database
2. Create database mybatis
3. Run sql scripts from db folder (https://github.com/sanjoy-sust/mybatisexample/tree/master/src/main/resources/db)
4. Run mvn clean package, Brfore that configure your maven settings.
5. Copy mybatis-example-1.0-SNAPSHOT.war file from target folder to your tomcat webapps folder
6. Goto bin folder of your tomcat and run ./startup.sh for linux and ./startup.bat for windows
7. goto web browser and hit http://localhost:8080/mybatis-example-1.0-SNAPSHOT/employee
8. You will see employee list
