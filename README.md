# Kevin Iris Fabien

## Usage
Simply clone the repository :
```
git clone https://github.com/simplon-CQP-Java/kif.git && cd kif
```

Create env.properties file in src/ressources/META-INF/ like this (replace value in {}):
```
mail.username={mailgun_username}
mail.password={mailgun_password}

admin.name={admin_name}
admin.password={admin_password}

mysql.url=jdbc:mysql://localhost:3306/{DB_NAME}?createDatabaseIfNotExist=true
mysql.username={mysql_username}
mysql.password={mysql_password}
```

When the application is launched you may log with {admin_name} and {admin_password}

### Hand launch
Maven clean
```
mvn clean
```

Launch with tomcat
```
mvn tomcat7:run
```

### Launch with make
Run the following command
```
make dev
```

App running by default to port 8080
So open your browser and go to ```http://localhost:8080/```
