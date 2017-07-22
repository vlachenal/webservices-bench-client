#!/bin/bash

script_name=$0

printHelp() {
    cat <<EOF
usage: $script_name -p [PATH] [OPTIONS]
OPTIONS:
  -p, --path		PATH		the JAR path
  -n, --nb-calls	CALLS		the maximum number of simultaneous calls: it will execute from 1 to CALLS runs (default: 1)
  -z, --compression	COMPRESSION	HTTP compression (optional)
  -c, --comment		COMMENT		the test suite comment (optional)
EOF
}

# Parse command line arguments +
jar_path=""
nb_thread=1
compression=""
comment=""
while [[ $# -gt 0 ]]; do
    key="$1"
    case $key in
	-p|--path)
	    shift
	    jar_path="$1"
	    if [[ "${jar_path}" = "" ]]; then
		echo "No value for PATH"
		printHelp
		exit 1
	    fi
	    ;;
	-n|--nb-calls)
	    shift
	    ((nb_thread += $1))
	    if [ $nb_thread -eq 1 ]; then
		echo "Invalid number of thread format: $1"
		printHelp
		exit 1
	    fi
	    ;;
	-z|--compression)
	    shift
	    compression="$1"
	    if [[ "${compression}" = "" ]]; then
		echo "No value for COMPRESSION"
		printHelp
		exit 1
	    fi
	    ;;
	-c|--comment)
	    shift
	    comment="$1"
	    if [[ "${comment}" = "" ]]; then
		echo "No value for COMMENT"
		printHelp
		exit 1
	    fi
	    ;;
	-h|--help)
	    printHelp
	    exit 0
	    ;;
	*)
	    echo "Unknow option $key"
	    printHelp
	    exit 1
	    ;;
    esac
    shift
done

if [[ "${jar_path}" = "" ]]; then
    echo "No value for PATH"
    printHelp
    exit 1
fi
if [ $nb_thread -eq 1 ]; then
    nb_thread=2
fi
if [[ "${compression}" = "" ]]; then
    compression="none"
fi
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
	    echo "Run test suite for ${proto} with $i simultaneous calls"
	    $java_bin -jar $jar_path $proto $i $compression "${comment}"
	done
    done
done <<< "$protocols"

exit 0
