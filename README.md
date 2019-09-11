# MARKET 4.0 Consumer Application 
The Consumer Application is part of the Marker 4.0 Consumer Connector. Main features:
* DAPS interaction for getting token
* consuming data (FIWARE Device Data Model) from the ActiveMQ queue
* messages compliant to the IDS Information Model
* IDS Clearing House interaction (WIP) 
* IDS Broker interaction (TODO)

# How to install cer #
1. For the URL: https://daps.aisec.fraunhofer.de/token
     Extract the certification information from HTTPS site (i.e., using Chrome) --> aices.cer
2. Save .cer in the folder on the Windows (exampe: PATH WHERE IS LOCATED CERTIFICATION)
3. Import above .cer in the Java JDK:
     1. open PowerShell
     2. go to the JDK path, in the folder jre\bin (example: C:\Program Files\Java\jdk1.8.0_162\jre\bin), and execute the next command: 
.\keytool.exe -import -noprompt -trustcacerts -alias aisecDaps -file 'PATH WHERE IS LOCATED CERTIFICATION\aisec.cer' -keystore ..\lib\security\cacerts
4. Default password to add certificate is: changeit

# How to install and run #
1. Get app from the GitHub:
	https://github.com/Engineering-Research-and-Development/market4.0-application_consumer
2. Unpuck and copy in the .m2\repository the file de.7z from the folder with the name: Maven - RESOURCE <em> (only if you have problems to download data from the Fraunhofer repositories, if you are able to download the remote dependencies please skip this step) </em>
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
