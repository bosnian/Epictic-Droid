# Epictic Droid - Epic Analytics
[![](https://jitpack.io/v/bosnian/Epictic-Droid.svg)](https://jitpack.io/#bosnian/Epictic-Droid)
# Installation
## Step 1: Include library

### Gradle
Add it in your root build.gradle at the end of repositories:

```java
allprojects {
    repositories {
        ...
		maven { url "https://jitpack.io" }
    }
}
```
Add the dependency:

```java
dependencies {
	  compile 'com.github.bosnian:Epictic-Droid:master'
}
```

### Maven

Add the JitPack repository to your build file:
```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>		    
        <url>https://jitpack.io</url>
	</repository>
</repositories>
```
Add the dependency:
```xml
<dependency>
	    <groupId>com.github.bosnian</groupId>
	    <artifactId>Epictic-Droid</artifactId>
	    <version>master</version>
</dependency>
```

## Step 2: Initialize Epictic environment with *key* and *url*
```javascript
var tracker = Epic.init("YOUR_URL", "API_KEY");
```

## Step 3: Track your events!
```javascript
tracker.track("Edit Profile", null);
```

# Documentation
## Initialization

Use shared instance.
```javascript
Epic.initShared("YOUR_URL", "API_KEY");
Epic.track("Edit Profile", null);
```
Create one instance, or multiple ones with different environments.
```javascript
var tracker = Epic.init("YOUR_URL", "API_KEY");
var tracker2 = Epic.init("YOUR_URL2", "API_KEY2");
tracker.track("Edit Profile", null);
tracker2.track("Troubleshooting", null);
```

## Tracking

Track event without properties.
```javascript
tracker.track("Login",null);
```
Track event with properties.
```javascript
var properties = {
  EmailEntered: true,
  PasswordEntered: false,
  Success: false 
}
tracker.track("Login",properties);
```

### Register properties 
Register properties once and they will be sent with every request. If you register properties multiple times, properties will be merged in favor of newly added.
```javascript
var properties = {
  Platform: navigator.platform,
  Browser: navigator.appName,
  BrowserVersion: navigator.appVersion
}
tracker.register(properties);
```
## Copyright & License
 
Project released under the [MIT license](LICENSE.md)
