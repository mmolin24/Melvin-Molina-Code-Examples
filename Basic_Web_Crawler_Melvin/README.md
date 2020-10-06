# Basic_Web_Crawler_Melvin
  This is a Web Crawler created in Java using Maven. This project is meant to be launched from the command line.

## Installation
  to install use the following 
  ```Bash 
  git clone https://github.com/mmolin24/Basic_Web_Crawler_Melvin.git
  
  ````
  
  ## Compile
  be sure Maven is installed, if not installed visit https://maven.apache.org/install.html.
  change directory to the appropriate directory named Basic_Web_Crawler_Melvin
  
  ```Bash
  mvn clean
  ```
  ```Bash
  mvn -q clean compile exec:java -Dexec.mainClass="src.main.SpiderCrawler" -Dexec.args="WEBSITE"
  ```
  
  Within the "WEBSITE" place whatever link you'd like to begin your crawl from.
  
  ## Default
  By default the max number of fetches is 3. This can be changed by changing the "final int MAX" number to whatever you'd like.
  
 
