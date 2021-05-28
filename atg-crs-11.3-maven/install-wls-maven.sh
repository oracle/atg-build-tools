#!/bin/sh

if [ x${MW_HOME} = x ]; then
    echo "Your MW_HOME directory isn't set. Exiting"
    exit 1
fi

# This is the maven weblogic plugin. The install will vary depending on your version of WLS
# This is for Weblogic 12.1. Refer to the documentation for your specific version for instructions.
cd ${MW_HOME}/wlserver/server/lib
mvn install
mvn install:install-file -Dfile=wls-maven-plugin.jar -DpomFile=pom.xml

