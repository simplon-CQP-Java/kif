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
```

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
