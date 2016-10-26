#!/bin/sh

if [ x${DYNAMO_ROOT} = x ]; then
    echo "Your DYNAMO_ROOT directory isn't set. Exiting"
    exit 1
fi

CRS_HOME=${DYNAMO_ROOT}/CommerceReferenceStore

function mycp()
{
    if [[ -z "$1" || -z "$2" ]]; then
        echo "Usage: mycp SOURCE... DIRECTORY"
        return 1;
    fi
    mkdir -p "$2" 
    cp -r "$1" "$2" 
}

# copy files from CRS to Maven build area
mycp ${CRS_HOME}/Store/Endeca/liveconfig Store/Endeca/Endeca/src/main
mycp ${CRS_HOME}/Store/Endeca/META-INF Store/Endeca/Endeca/src/main/module

mycp ${CRS_HOME}/Store/Endeca/Assembler/configlayers Store/Endeca/Assembler/src/main
mycp ${CRS_HOME}/Store/Endeca/Assembler/liveconfig Store/Endeca/Assembler/src/main
mycp ${CRS_HOME}/Store/Endeca/Assembler/META-INF Store/Endeca/Assembler/src/main/module
mycp ${CRS_HOME}/Store/Endeca/Assembler/src/config Store/Endeca/Assembler/src/main
mycp ${CRS_HOME}/Store/Endeca/Assembler/src/Java/* Store/Endeca/Assembler/src/main/java

mycp ${CRS_HOME}/Store/Endeca/Base/configlayers Store/Endeca/Base/src/main
mycp ${CRS_HOME}/Store/Endeca/Base/liveconfig Store/Endeca/Base/src/main
mycp ${CRS_HOME}/Store/Endeca/Base/META-INF Store/Endeca/Base/src/main/module
mycp ${CRS_HOME}/Store/Endeca/Base/src/config Store/Endeca/Base/src/main

mycp ${CRS_HOME}/Store/Endeca/Index/liveconfig Store/Endeca/Index/Index/src/main
mycp ${CRS_HOME}/Store/Endeca/Index/META-INF Store/Endeca/Index/Index/src/main/module
mycp ${CRS_HOME}/Store/Endeca/Index/src/config Store/Endeca/Index/Index/src/main
mycp ${CRS_HOME}/Store/Endeca/Index/src/Java/* Store/Endeca/Index/Index/src/main/java

mycp ${CRS_HOME}/Store/Endeca/Index/Versioned/liveconfig Store/Endeca/Index/Versioned/src/main
mycp ${CRS_HOME}/Store/Endeca/Index/Versioned/META-INF Store/Endeca/Index/Versioned/src/main/module
mycp ${CRS_HOME}/Store/Endeca/Index/Versioned/src/config Store/Endeca/Index/Versioned/src/main

mycp ${CRS_HOME}/Store/Endeca/International/liveconfig Store/Endeca/International/International/src/main
mycp ${CRS_HOME}/Store/Endeca/International/META-INF Store/Endeca/International/International/src/main/module

mycp ${CRS_HOME}/Store/Endeca/International/Assembler/liveconfig Store/Endeca/International/Assembler/src/main
mycp ${CRS_HOME}/Store/Endeca/International/Assembler/META-INF Store/Endeca/International/Assembler/src/main/module
mycp ${CRS_HOME}/Store/Endeca/International/Assembler/src/config Store/Endeca/International/Assembler/src/main

mycp ${CRS_HOME}/Store/Endeca/International/Base/liveconfig Store/Endeca/International/Base/src/main
mycp ${CRS_HOME}/Store/Endeca/International/Base/META-INF Store/Endeca/International/Base/src/main/module
mycp ${CRS_HOME}/Store/Endeca/International/Base/src/config Store/Endeca/International/Base/src/main

mycp ${CRS_HOME}/Store/Endeca/International/Index/liveconfig Store/Endeca/International/Index/src/main
mycp ${CRS_HOME}/Store/Endeca/International/Index/META-INF Store/Endeca/International/Index/src/main/module
mycp ${CRS_HOME}/Store/Endeca/International/Index/src/config Store/Endeca/International/Index/src/main
mycp ${CRS_HOME}/Store/Endeca/International/Index/src/Java/* Store/Endeca/International/Index/src/main/java

mycp ${CRS_HOME}/Store/Endeca/International/Reader/liveconfig Store/Endeca/International/Reader/src/main
mycp ${CRS_HOME}/Store/Endeca/International/Reader/META-INF Store/Endeca/International/Reader/src/main/module
mycp ${CRS_HOME}/Store/Endeca/International/Reader/src/config Store/Endeca/International/Reader/src/main
mycp ${CRS_HOME}/Store/Endeca/International/Reader/src/Java/* Store/Endeca/International/Reader/src/main/java

mycp ${CRS_HOME}/Store/Endeca/Reader/liveconfig Store/Endeca/Reader/src/main
mycp ${CRS_HOME}/Store/Endeca/Reader/META-INF Store/Endeca/Reader/src/main/module
mycp ${CRS_HOME}/Store/Endeca/Reader/src/config Store/Endeca/Reader/src/main
mycp ${CRS_HOME}/Store/Endeca/Reader/src/Java/* Store/Endeca/Reader/Index/src/main/java

mycp ${CRS_HOME}/Store/EStore/configlayers Store/EStore/EStore/src/main
mycp ${CRS_HOME}/Store/EStore/liveconfig Store/EStore/EStore/src/main
mycp ${CRS_HOME}/Store/EStore/META-INF Store/EStore/EStore/src/main/module
mycp ${CRS_HOME}/Store/EStore/src/config Store/EStore/EStore/src/main
mycp ${CRS_HOME}/Store/EStore/src/Java/* Store/EStore/EStore/src/main/java
mycp ${CRS_HOME}/Store/EStore/admin Store/EStore/EStore/src/main
mycp ${CRS_HOME}/Store/EStore/keystore Store/EStore/EStore/src/main

mycp ${CRS_HOME}/Store/EStore/International/liveconfig Store/EStore/International/International/src/main
mycp ${CRS_HOME}/Store/EStore/International/META-INF Store/EStore/International/International/src/main/module
mycp ${CRS_HOME}/Store/EStore/International/src/config Store/EStore/International/International/src/main
mycp ${CRS_HOME}/Store/EStore/International/src/Java/* Store/EStore/International/International/src/main/java

mycp ${CRS_HOME}/Store/EStore/International/Versioned/configlayers Store/EStore/International/Versioned/src/main
mycp ${CRS_HOME}/Store/EStore/International/Versioned/liveconfig Store/EStore/International/Versioned/src/main
mycp ${CRS_HOME}/Store/EStore/International/Versioned/META-INF Store/EStore/International/Versioned/src/main/module
mycp ${CRS_HOME}/Store/EStore/International/Versioned/src/config Store/EStore/International/Versioned/src/main
mycp ${CRS_HOME}/Store/EStore/International/Versioned/src/Java/* Store/EStore/International/Versioned/src/main/java

mycp ${CRS_HOME}/Store/EStore/Versioned/configlayers Store/EStore/Versioned/Versioned/src/main
mycp ${CRS_HOME}/Store/EStore/Versioned/liveconfig Store/EStore/Versioned/Versioned/src/main
mycp ${CRS_HOME}/Store/EStore/Versioned/META-INF Store/EStore/Versioned/Versioned/src/main/module
mycp ${CRS_HOME}/Store/EStore/Versioned/src/config Store/EStore/Versioned/Versioned/src/main
mycp ${CRS_HOME}/Store/EStore/Versioned/src/Java/* Store/EStore/Versioned/Versioned/src/main/java

mycp ${CRS_HOME}/Store/EStore/Versioned/j2ee-apps Store/EStore/Versioned/J2EE-APP/src/main

mycp ${CRS_HOME}/Store/Fluoroscope/liveconfig Store/Fluoroscope/Fluoroscope/src/main
mycp ${CRS_HOME}/Store/Fluoroscope/META-INF Store/Fluoroscope/Fluoroscope/src/main/module
mycp ${CRS_HOME}/Store/Fluoroscope/src/config Store/Fluoroscope/Fluoroscope/src/main
mycp ${CRS_HOME}/Store/Fluoroscope/src/Java/* Store/Fluoroscope/Fluoroscope/src/main/java

mycp ${CRS_HOME}/Store/Fluoroscope/j2ee-apps Store/Fluoroscope/J2EE-APP/src/main

mycp ${CRS_HOME}/Store/Fulfillment/liveconfig Store/Fulfillment/src/main
mycp ${CRS_HOME}/Store/Fulfillment/META-INF Store/Fulfillment/src/main/module
mycp ${CRS_HOME}/Store/Fulfillment/src/config Store/Fulfillment/src/main
mycp ${CRS_HOME}/Store/Fulfillment/src/Java/* Store/Fulfillment/src/main/java

mycp ${CRS_HOME}/Store/KnowledgeBase/liveconfig Store/KnowledgeBase/KnowledgeBase/src/main
mycp ${CRS_HOME}/Store/KnowledgeBase/META-INF Store/KnowledgeBase/KnowledgeBase/src/main/module
mycp ${CRS_HOME}/Store/KnowledgeBase/src/config Store/KnowledgeBase/KnowledgeBase/src/main
mycp ${CRS_HOME}/Store/KnowledgeBase/src/Java/* Store/KnowledgeBase/KnowledgeBase/src/main/java

mycp ${CRS_HOME}/Store/KnowledgeBase/International/liveconfig Store/KnowledgeBase/International/src/main
mycp ${CRS_HOME}/Store/KnowledgeBase/International/META-INF Store/KnowledgeBase/International/src/main/module
mycp ${CRS_HOME}/Store/KnowledgeBase/International/src/config Store/KnowledgeBase/International/src/main

mycp ${CRS_HOME}/Store/Recommendations/liveconfig Store/Recommendations/Recommendations/src/main
mycp ${CRS_HOME}/Store/Recommendations/META-INF Store/Recommendations/Recommendations/src/main/module
mycp ${CRS_HOME}/Store/Recommendations/src/config Store/Recommendations/Recommendations/src/main
mycp ${CRS_HOME}/Store/Recommendations/src/Java/* Store/Recommendations/Recommendations/src/main/java

mycp ${CRS_HOME}/Store/Recommendations/International/liveconfig Store/Recommendations/International/src/main
mycp ${CRS_HOME}/Store/Recommendations/International/META-INF Store/Recommendations/International/src/main/module
mycp ${CRS_HOME}/Store/Recommendations/International/src/config Store/Recommendations/International/src/main

mycp ${CRS_HOME}/Store/Storefront/liveconfig Store/Storefront/Storefront/src/main
mycp ${CRS_HOME}/Store/Storefront/META-INF Store/Storefront/Storefront/src/main/module
mycp ${CRS_HOME}/Store/Storefront/src/Java/* Store/Storefront/Storefront/src/main/java
mycp ${CRS_HOME}/Store/Storefront/j2ee-apps Store/Storefront/Storefront/src/main

mycp ${CRS_HOME}/Store/Storefront/NoPublishing/liveconfig Store/Storefront/NoPublishing/NoPublishing/src/main
mycp ${CRS_HOME}/Store/Storefront/NoPublishing/META-INF Store/Storefront/NoPublishing/NoPublishing/src/main/module
mycp ${CRS_HOME}/Store/Storefront/NoPublishing/src/config Store/Storefront/NoPublishing/NoPublishing/src/main

mycp ${CRS_HOME}/Store/Storefront/NoPublishing/International/liveconfig Store/Storefront/NoPublishing/International/src/main
mycp ${CRS_HOME}/Store/Storefront/NoPublishing/International/META-INF Store/Storefront/NoPublishing/International/src/main/module
mycp ${CRS_HOME}/Store/Storefront/NoPublishing/International/src/config Store/Storefront/NoPublishing/International/src/main


# Change module names from Store.EStore to MavenStore.EStore

function changeModuleName()
{
    if [ -z "$1" ]; then
        echo "Usage: changeModuleName SOURCE"
        return 1;
    fi
    sed -i '/^ATG-Required/s/Store\./MavenStore\./g' ${1}
    sed -i '$ a ATG-Build: ${build.number}' ${1}
}


changeModuleName Store/Endeca/Assembler/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/Endeca/Base/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/Endeca/Index/Index/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/Endeca/International/Assembler/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/Endeca/International/Base/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/Endeca/International/Index/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/EStore/Versioned/Versioned/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/EStore/International/Versioned/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/EStore/International/International/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/Fulfillment/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/KnowledgeBase/KnowledgeBase/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/KnowledgeBase/International/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/Storefront/Storefront/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/Endeca/Index/Versioned/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/Endeca/International/Reader/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/Endeca/International/International/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/Endeca/Endeca/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/EStore/Versioned/Versioned/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/Recommendations/International/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/Storefront/NoPublishing/International/src/main/module/META-INF/MANIFEST.MF
changeModuleName Store/Storefront/NoPublishing/NoPublishing/src/main/module/META-INF/MANIFEST.MF

sed -i 's/Store.EStore/MavenStore.EStore/' Store/EStore/EStore/src/main/config/atg/store/security/crypto/SecretKeyStoreManager.properties
sed -i 's/Store.EStore/MavenStore.EStore/' Store/EStore/EStore/src/main/config/atg/modules/Store.properties

sed -i '$ a Build-Date: ${timestamp}' Store/EStore/EStore/src/main/module/META-INF/MANIFEST.MF

