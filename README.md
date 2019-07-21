# ph-as4

A library to send and receive AS4 messages. 
Licensed under the Apache 2 License!

It consists of the following sub-projects:
  * **ph-as4-lib** - basic data structures for AS4 handling
  * **ph-as4-esens** - AS4 profile for eSENS as well as the PMode and the respective validation
  * **ph-as4-servlet** - AS4 servlet for integration into existing (since 0.6.0)
  * **ph-as4-server-webapp** - Standalone AS4 server for **demo** purposes (since 0.7.0)
  * **ph-as4-server-webapp-test** - Standalone AS4 server for internal **testing** purposes only (since 0.7.0)

## News and noteworthy

* v0.9.0 - work in progress
    * Updated to WSS4J 2.2.3
    * Updated to ph-oton 8.2.0
    * Updated to peppol-commons 7.0.0
    * The submodule `ph-as4-esens` was renamed to `ph-as4-profile-cef`
    * The AS4 message handler now have a chance to access the received HTTP headers
* v0.8.2 - 2019-02-27
    * Adoptions for integration into TOOP
* v0.8.1 - 2018-11-26
    * The web application now uses LOG4J 2.x
    * Requires at least ph-commons 9.2.0
    * Added `@type`-fix from https://issues.oasis-open.org/projects/EBXMLMSG/issues/EBXMLMSG-2
* v0.8.0 - 2018-06-21
    * Updated to ph-commons 9.1.2
    * Updated to BouncyCastle 1.59
    * Updated to WSS4J 2.2.2
    * Successfully send test messages to AS4.NET and Holodeck 3.x
* v0.7.0 - 2017-07-24
    * Added HTTP retry for client
    * Added server duplicate message detection for incoming messages
    * `MessageInfo/Timestamp` uses UTC - thanks Sander
    * Added two-way handling
    * Fixed bug that Receipt is not signed (if desired)
    * Removed `PModeConfig` in favor of redundant `PMode` objects
    * Removed partner handling - not needed anymore 
    * To be on the safe side, delete all previously created `as4-*.xml` files as there were incompatible changes.
    * Added a second webapp - one for demo, one for testing
* v0.6.0 - 2017-01-26
    * Extracted subproject `ph-as4-servlet` with only the AS4Servlet
    * Unified the namespaces across the sub-projects
    * Requires ph-web 8.7.2 or higher
    * Renamed `ph-as4-server` to `ph-as4-server-webapp-demo`
* v0.5.0 - 2017-01-18
    * Initial release
    * Has everything needs for sending and receiving using the eSENS P-Mode profiles
    * Basic compatibility with Holodeck 2.1.2 is provided
    * Supports signed messages
    * Supports encrypted messages
    * Supports compressed messages
    * Targets to be easily integrateable into existing solutions
    * Requires Java 8 for building and execution
    
## Configuration

The configuration of ph-as4 is based on 2 different files:
  * `crypto.properties` - the WSS4J configuration file - https://ws.apache.org/wss4j/config.html
  * `as4.properties` - ph-as4-server specific configuration file
  
### crypto.properties

Use the following file as a template and fill in your key structure:

```ini
org.apache.wss4j.crypto.provider=org.apache.wss4j.common.crypto.Merlin
org.apache.wss4j.crypto.merlin.keystore.file=keys/dummy-pw-test.jks
org.apache.wss4j.crypto.merlin.keystore.password=test
org.apache.wss4j.crypto.merlin.keystore.type=jks
org.apache.wss4j.crypto.merlin.keystore.alias=ph-as4
org.apache.wss4j.crypto.merlin.keystore.private.password=test
```

The file is a classpath relative path like `keys/dummy-pw-test.jks`. 

PEPPOL users: the key store must contain the AccessPoint private key and the truststore must contain the PEPPOL truststore.

### as4.properties

This AS4 server specific file contains the following properties:

```ini
#server.profile=
server.debug=false
server.production=false
server.nostartupinfo=true
server.datapath=/var/www/as4/data
#server.incoming.duplicatedisposal.minutes=10
#server.address=
```

The file is searched in the locations specified as follows:
* A path denoted by the environment variable `AS4_SERVER_CONFIG`
* A path denoted by the system property `as4.server.configfile`
* A file named `private-as4.properties` within your classpath
* A file named `as4.properties` within your classpath

The properties have the following meaning
* **`server.profile`**: a specific AS4 profile ID that can be used to validate incoming messages. Only needed in specific circumstances. Not present by default.
* **`server.debug`**: enable or disable the global debugging mode in the system. It is recommended to have this always set to `false` except you are developing with the components. Valid values are `true` and `false`.
* **`server.production`**: enable or disable the global production mode in the system. It is recommended to have this set to `true` when running an instance in a production like environment to improve performance and limit internal checks. Valid values are `true` and `false`.
* **`server.nostartupinfo`**: disable the logging of certain internals upon server startup when set to `true`. Valid values are `true` and `false`.
* **`server.datapath`**: the writable directory where the server stores data. It is recommended to be an absolute path (starting with `/`). The default value is the relative directory `conf`.
* **`server.incoming.duplicatedisposal.minutes`**: the number of minutes a message is kept for duplication check. After that time, the same message can be retrieved again. Valid values are integer numbers &ge; 0. The default value is `10`. 
* **`server.address`**: the public URL of this AS4 server to send responses to. This value is optional.

## Building from source

After initial checkout, it is necessary to run `mvn process-sources` once on the `as4-lib` subproject. Additionally the folder `target/generated-sources/xjc` must be added to the source build path. When building only on the commandline, this is done automatically.

## Known limitations

Per now the following known limitations exist:
  * Multi-hop does not work (and is imho not relevant for a usage in PEPPOL)

## Differences to Holodeck

  * This is a library and not a product
  * ph-as4 is licensed under the business friendly Apache 2 license and not under GPL/LGPL
  * This library only takes care about the effective receiving of documents, but does not provide a storage for them. You need to implement your own incoming document handler!
  * ph-as4 does not use an existing WS-Stack like Axis or Apache CXF but instead operates directly on a Servlet layer for retrieval and using Apache HttpClient for sending

## How to help

Any voluntary help on this project is welcome.
If you want to write documentation or test the solution - I'm glad for every help.
Just write me an email - see pom.xml for my email address - or tweet me @philiphelger

---

My personal [Coding Styleguide](https://github.com/phax/meta/blob/master/CodingStyleguide.md) |
On Twitter: <a href="https://twitter.com/philiphelger">@philiphelger</a>
