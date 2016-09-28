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
### SBT 
Add it in your build.sbt at the end of resolvers:
```java
resolvers += "jitpack" at "https://jitpack.io"
```
Add the dependency:
```java
libraryDependencies += "com.github.bosnian" % "Epictic-Droid" % "master"	
```

### Leiningen
Add it in your project.clj at the end of repositories:
```clojure
:repositories [["jitpack" "https://jitpack.io"]]
```
Add the dependency:
```clojure
:dependencies [[com.github.bosnian/Epictic-Droid "master"]]	
```

## Step 2: Initialize Epictic environment with EpicticConfiguration
```java
EpicticConfiguration configuration = new EpicticConfiguration("YOUR_API", "YOUR_URL");
Epictic epic = new  Epictic(getApplicationContext(),configuration);

```

## Step 3: Track your events!
```java

epic.track("Edit Profile");
```

# Documentation
## Initialization

Use shared instance.
```java
EpicticConfiguration configuration = new EpicticConfiguration("YOUR_API", "YOUR_URL");
Epictic.initShared(getApplicationContext(),configuration);
Epictic.shared.track("Edit Profile");
```
Create one instance, or multiple ones with different environments.
```java
EpicticConfiguration configuration = new EpicticConfiguration("YOUR_API", "YOUR_URL");

Epictic.initShared(getApplicationContext(),configuration);
Epictic.shared.track("Edit Profile");

Epictic epictic = new Epictic(getApplicationContext(),configuration);
epictic.track("Troubleshooting");
```

## Tracking

Track event without properties.
```java
epictic.track("Login");
```
Track event with properties.
```java
Hashtable<String,Object> properties = new Hashtable<>();
properties.put("EmailEntered",true);
properties.put("PasswordEntered",false);
properties.put("Success",false);

epictic.track("Login",properties);
```

### Register properties 
Register properties once and they will be sent with every request. If you register properties multiple times, properties will be merged in favor of newly added.
```java
PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

Hashtable<String,Object> properties = new Hashtable<>();
properties.put("AppVersionName",pInfo.versionName);
properties.put("AppVersionCode",pInfo.versionCode);

epictic.register(properties);
```
## Copyright & License
 
Project released under the [MIT license](LICENSE.md)
