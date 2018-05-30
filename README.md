This is a very fast Geolocation and User agent analysis API. For Geolocation database, the freely available [DB-IP](https://db-ip.com/db/) dataset is used.
For parsing User agent strings, [browscap](http://browscap.org/ua-lookup) csv file is used, and the parsing is done via [blueconic's](https://github.com/blueconic/browscap-java) fantastic library for Browscap.

# Start
Follow these simple instructions :

1. Clone/download the repo
2. Download the latest version of DB-IP dataset available [here](https://db-ip.com/db/download/city)
3. Rename the file to dbip.csv.gz and place it in ```/src/main/resources```  folder
4. If you have a Java IDE installed, then just go to ```/src/main/resources/io/github/ankushs92/GeoUaParserApiApplication``` and just run it.

# Oh, and before you begin
The entirety of DB-IP and Browscap files are loaded into the JVM when the app starts. A minimum of 3 gb is required to run this 

# Endpoints

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

  User Agent :  ```Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36```,
  
  URL : ```http://localhost:8080/uaAnalysis?ua=Mozilla%2F5.0%20%28Macintosh%3B%20Intel%20Mac%20OS%20X%2010_12_6%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Chrome%2F66.0.3359.181%20Safari%2F537.36```

  
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
  
  
  # Performance
  
 The API's are pretty damn fast, if I say so myself. The load tests were done on a 4 core, 16 gb RAM Macbook.
  Here are some statistics. 
 
 For the ```/geo``` endpoint :
 
 ```
10 threads and 10 connections
Thread Stats   Avg      Stdev     Max   +/- Stdev
Latency   841.08us    2.51ms 160.60ms   99.67%
Req/Sec     1.32k   319.64     2.67k    78.53%
785692 requests in 1.00m, 154.35MB read
Requests/sec:  13086.42
Transfer/sec:      2.57MB

```


For the ```/uaAnalysis``` endpoint :

```
10 threads and 10 connections
Thread Stats   Avg      Stdev     Max   +/- Stdev
Latency     8.38ms    2.57ms  37.08ms   88.21%
Req/Sec   119.97     27.54   180.00     79.65%
71770 requests in 1.00m, 103.42MB read
Requests/sec:   1194.89
Transfer/sec:      1.72MB

```