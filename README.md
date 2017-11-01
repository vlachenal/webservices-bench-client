# webservices-bench-client
 Project to test and compare different webservices - the client side
 
 Special thanks for [JSON generator](https://www.json-generator.com/) for generating test data (see generate.json file for generate new data).

## Synopsis
This is the client side of the [webservices-bench project](https://github.com/vlachenal/webservices-bench)

I tried to make calls in the same way for the different APIs (REST and Thrift for now). If you have optimization, please contact me or make a pull request.

## Usage
To compile project:
 * Run 'bootRepackage' Gradle task
 * To launch one run:
     * Go to build/libs directory
     * Run java -jar webservices-bench-client-\<version\>.jar \<protocol\> \<number of threads\>
 * To launch test suites:
     * java executable will be found according to:
         * JAVA_HOME
         * JDK_HOME
         * java from PATH
     * Run launch.sh -p \<JAR path\> \<options\>
         * -h or --help to print help and exit
         * -n or --nb-calls \<calls\> to define maximum number of simultaneous calls (default to 1). It will execute from 1 to \<calls\> test suite
         * -z or --compression to set HTTP compression
         * -c or --comment to set test suites comment

## TODO
By priority order:
 - ~~Add script to run every tests in one command~~
 - ~~Manage test suite comment~~
 - ~~Manage HTTP compression~~
 - Find a way (if it is possible) to specify HTTP client pool underlying Spring-Boot RESTTemplate
