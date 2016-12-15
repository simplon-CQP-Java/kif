BRANCH=$(shell git rev-parse --abbrev-ref HEAD)

mergeOnMaster:
	git checkout master
	git pull
	git merge $(BRANCH)
	git push
	git checkout $(BRANCH)

rebaseOnMaster:
	git checkout master
	git pull
	git rebase $(BRANCH)
	git push
	git checkout $(BRANCH)

dev:
	mvn clean
	mvn -Dmaven.tomcat.port=8000 -DMYSQL_USER=$(MYSQL_USER) -DMYSQL_PASSWORD=$(MYSQL_PASSWORD) -DMAILGUN_PASSWORD=$(MAILGUN_PASSWORD) -DADMIN_NAME=$(ADMIN_NAME) -DADMIN_PASSWORD=$(ADMIN_PASSWORD) tomcat7:run

war:
	mvn -DMYSQL_USER=$(MYSQL_USER) -DMYSQL_PASSWORD=$(MYSQL_PASSWORD) -DMAILGUN_PASSWORD=$(MAILGUN_PASSWORD) -DADMIN_NAME=$(ADMIN_NAME) -DADMIN_PASSWORD=$(ADMIN_PASSWORD) clean install
