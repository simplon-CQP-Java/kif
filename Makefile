BRANCH=$(shell git rev-parse --abbrev-ref HEAD)

mergeOnMaster:
	git checkout master
	git pull
	git merge $(BRANCH)
	git push
	git checkout $(BRANCH)

dev:
	mvn clean
	mvn tomcat7:run
