This is a very fast Geolocation and User agent analysis API. For Geolocation database, the freely available [DB-IP](https://db-ip.com/db/) dataset is used.
For parsing User agent strings, [browscap](http://browscap.org/ua-lookup) csv file is used, and the parsing is done via [blueconic's](https://github.com/blueconic/browscap-java) fantastic library for Browscap.

#Start
Follow these simple instructions :

1. Clone/download the repo
2. Download the latest version of DB-IP dataset available [here](https://db-ip.com/db/download/city)
3. Rename the file to dbip.csv.gz and place it in ```/src/main/resources```  folder
4. If you have a Java IDE installed, then just go to ```/src/main/resources/io/github/ankushs92/GeoUaParserApiApplication``` and just run it.



#Endpoints

The application will run on port 8080.

#### 1. /geo :
   
  For example, if the ip address is ```103.16.178.144```, hit the url ```http://localhost:8080/geo?ip=103.16.178.144```.
  The response would be :
  
  ```json
    {
      "city" : "New Delhi",
      "country" : "India",
      "province" : "Delhi",
      "countryCode" : "IN"
    }
  
  ```
  
#### 2. /uaAnalysis :
  For example, if the user agent is : ```Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36```,
  hit the url ```http://localhost:8080/uaAnalysis?ua=Mozilla%2F5.0%20%28Macintosh%3B%20Intel%20Mac%20OS%20X%2010_12_6%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Chrome%2F66.0.3359.181%20Safari%2F537.36```

  
  Response :
  ```json
    {
      "parent" : "Chrome Generic",
      "comment" : "Chrome Generic",
      "browser" : "Chrome",
      "browserBits" : "32",
      "browserType" : "Browser",
      "browserMaker" : "Google Inc",
      "browserModus" : "Unknown",
      "browserVersion" : "0.0",
      "browserMajorVersion" : "0",
      "browserMinorVersion" : "0",
      "platform" : "macOS",
      "platformVersion" : "10.12",
      "platformDesc" : "macOS",
      "platformBits" : "32",
      "platformMaker" : "Apple Inc",
      "isAlpha" : "false",
      "isBeta" : "false",
      "cssVersion" : "3",
      "aolVersion" : "0",
      "deviceName" : "Macintosh",
      "deviceMaker" : "Apple Inc",
      "deviceType" : "Desktop",
      "devicePointingMethod" : "mouse",
      "deviceCodeName" : "Macintosh",
      "deviceBrandName" : "Apple",
      "renderingEngineName" : "Blink",
      "renderingEngineVersion" : "Unknown",
      "renderingEngineDesc" : "a WebKit Fork by Google",
      "renderingEngineMaker" : "Google Inc",
      "isWin16" : false,
      "isWin32" : false,
      "isWin64" : false,
      "isIframes" : true,
      "isFrames" : true,
      "isTables" : true,
      "isCookies" : true,
      "isBackgroundSounds" : false,
      "isJavascript" : true,
      "isVbScript" : false,
      "isJavaApplets" : false,
      "isActiveXControls" : false,
      "isMobileDevice" : false,
      "isTablet" : false,
      "isSyndicationReader" : false,
      "isCrawler" : false,
      "isFake" : false,
      "isAnonymized" : false,
      "isModified" : false,
      "isMasterParent" : false,
      "isLiteMode" : false
    }
 
  ```