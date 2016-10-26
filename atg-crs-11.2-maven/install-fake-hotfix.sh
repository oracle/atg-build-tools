#!/bin/sh

ATG_VERSION=11.2

# add hotfix to our maven repository. Keep the version number the same as the version of the ATG installation
# Different versions of a hotfix can exist for different versions of ATG
mvn install:install-file \
  -DgroupId=atg \
  -DartifactId=p1234-fakehotfix \
  -Dpackaging=jar \
  -Dversion=${ATG_VERSION} \
  -Dfile=p1234-fakehotfix.jar

