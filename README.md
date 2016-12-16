# Kevin Iris Fabien

## Usage
Simply clone the repository :
```
git clone https://github.com/simplon-CQP-Java/kif.git && cd kif
```

Create environments variables before starting application from command line like this (replace value in {}):
```
export MAILGUN_PASSWORD={mailgun_password}
export ADMIN_NAME={admin_name}
export ADMIN_PASSWORD={admin_password}
export MYSQL_USER={mysql_username}
export MYSQL_PASSWORD={mysql_password}
```

When the application is launched you may log with {admin_name} and {admin_password}

### Hand launch
Maven clean
```
mvn clean
```

Launch with tomcat
```
mvn -DMYSQL_USER=$(MYSQL_USER) -DMYSQL_PASSWORD=$(MYSQL_PASSWORD) -DMAILGUN_PASSWORD=$(MAILGUN_PASSWORD) -DADMIN_NAME=$(ADMIN_NAME) -DADMIN_PASSWORD=$(ADMIN_PASSWORD) tomcat7:run
```

### Launch with make
Run the following command
```
make dev
```

App running by default to port 8080
So open your browser and go to ```http://localhost:8080/```
