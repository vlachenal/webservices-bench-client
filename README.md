# webservices-bench-client
 Project to test and compare different webservices - the client side

## Synposis
This is the client side of the [webservices-bench project](https://github.com/vlachenal/webservices-bench)

I tried to make calls in the same way for the different APIs (REST and Thrift for now). If you have optimization, please contact me or make a pull request.

## Usage
To compile project:
 * Run 'jar' Gradle task
 * Run 'bootRepackage' Gradle task
 * To launch one run:
     * Go to build/libs directory
     * Run java -jar webservices-bench-client-\<version\>.jar \<protocol\> \<number of threads\>
 * To launch test suites:
     * java executable will be found according to:
         * JAVA_HOME
         * JDK_HOME
         * java from PATH
     * Run launch.sh \<JAR path\> \<number of threads\>

## TODO
By priority order:
 - ~~Add script to run every tests in one command~~
 - Manage test suite comment
 - Manage HTTP compression
 - Find a way (if it is possible) to specify HTTP client pool underlying Spring-Boot RESTTemplate
