#!/bin/bash

script_name=$0

printHelp() {
    cat <<EOF
usage: $script_name [jar_path] [nb_thread] [compression] [comment]
  jar_path	    the JAR path
  nb_thread	    the maximum number of simultaneous calls: it will execute from 1 to <nb_thread> runs
  compression       HTTP compression (optional ; for null use null or none)
  comment           the test suite comment (optional)
EOF
}

# Parse command line arguments +
jar_path=""
nb_thread=1
if [ $# -lt 2 ]; then
    echo "Invalid number of parameters"
    printHelp
    exit 1
fi
jar_path=$1
((nb_thread += $2))
if [ $nb_thread -eq 1 ]; then
    echo "Invalid number of thread format: $2"
    printHelp
    exit 1
fi
compression="$3"
comment="\"$4\""
# Parse command line arguments -

# Retrieve Java executable +
java_bin=""
if [[ "${JAVA_HOME}" != "" ]]; then
    java_bin="${JAVA_HOME}/bin/java"
elif [[ "${JDK_HOME}" != "" ]]; then
    java_bin="${JDK_HOME}/bin/java"
else
    which java 1>/dev/null 2>&1
    if [ $? -eq 0 ]; then
	java_bin=`which java`
    fi
fi

if [[ "${java_bin}" = "" ]]; then
    echo "No java executable has been found"
    exit 1
fi
# Retrieve Java executable -

protocols="rest|thrift"
while IFS='|' read -ra PROTOS; do
    for proto in "${PROTOS[@]}"; do
	for((i=1;i<$nb_thread;i++)); do
	    #test_run="${java_bin} -jar ${jar_path} ${proto} $i ${compression} ${comment}"
	    test_run="${java_bin} -jar ${jar_path} ${proto} $i"
	    echo "${test_run}"
	    $test_run $3 "$4"
	done
    done
done <<< "$protocols"

exit 0
