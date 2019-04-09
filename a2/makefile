PATHBoot = usr.Bootstrap
PATHPlanner = Planner
PATHConnectionDemo = "DBaseConnector.ConnectionDemo"
PATHPrepStudentScript = database.PrepStudentScript
PATHtest = database.testDB


all: univC connector dataC runConnectionDemo runPlanner
clean: cleanUsr cleanUniv cleanDatabase

univC:
	javac -cp .:mysql-connector-java-8.0.13.jar univ/*.java

connector:
	javac -cp .:mysql-connector-java-8.0.13.jar DBaseConnector/*.java

dataC:
	javac -cp .:mysql-connector-java-8.0.13.jar *.java

runConnectionDemo:
	java -cp .:mysql-connector-java-8.0.13.jar $(PATHConnectionDemo)

runPlanner:
	java -cp .:mysql-connector-java-8.0.13.jar $(PATHPlanner)

cleanUsr:
	rm -rf ./DBaseConnector/*.class

cleanUniv:
	rm -rf ./univ/*.class

cleanDatabase:
	rm -rf ./*.class
