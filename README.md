# MARKET 4.0 Consumer Application 
The Consumer Application is part of the Marker 4.0 Consumer Connector. Main features:
* DAPS interaction for getting token
* consuming data (FIWARE Device Data Model) from the ActiveMQ queue
* messages compliant to the IDS Information Model
* IDS Clearing House interaction (WIP) 
* IDS Broker interaction (TODO)

# How to install and run #
1. Get app from the GitHub:
	https://github.com/Engineering-Research-and-Development/market4.0-application_consumer
2. Unpuck and copy in the .m2\repository the file de.7z from the folder with the name: Maven - RESOURCE
3. Import the app as Maven project in the Eclipse
4. Maven update
5. Change in the all app the IP: 192.168.56.102 with the you IP of the Virtual Box Ubuntu.
6. Set the proxy username and password
7. Set the Tomcat server v8.5 and add on it the base-consumer
8. Setup for the Tomcat server v.8.5: 
Port:
	Tomcat admin port: 8006
	HTTP/1.1: 8081
	AJP/1.3: 8010
9. Run the Tomcat with the market4.0-application_consumer
