# Maven plugin to build ear files with ATG runAssembler.


### System requirements:
* Oracle ATG Commerce 9.4, 10.x, or 11.x
* Java jdk1.6 or jdk 1.7
* Maven 2.x or 3.x. Plugin had been tested with 2.2.1, 3.2.5 and 3.3.3

The version of maven used by the plugin pom itself is 2.2.1, and should not be changed for compatibility.

Note that not all arguments available in this plugin are applicable to all versions of ATG. 
Make sure you read the Platform Programming Guide, and look at the runAssembler options applicable to the version 
of Commerce you are using.


## Installation instructions

1. From the directory this README is in, run "mvn install"
This will install the plugin to your local maven repository.
If you have a remote, shared maven repository, install the plugin through your normal process.

2. Add the plugin to your project pom.
The maven goal for this plugin in runAssembler.
You will find working examples to build ear files in the src/sample-projects tree.

To execute the sample projects, cd to one of the project folders (i.e. src/sample-projects/linux/crsBCCExample)
These examples are tied to the maven package phase. You can run them with "mvn install" as an example.
Or, if you wish to call the plugin directly, execute:
mvn com.oracle.atg.ateam:atg-runAssembler-plugin:runAssembler

There are samples for windows and linux, with the only difference being the path the ear file is built to.
For linux, it builds to /tmp
For windows, it builds to c:\temp
You can change these to match your system if needed.


Plugin usage: add to your pom as follows. Look at the sample projects for examples with options filled in.

				<plugin>
					<groupId>com.oracle.atg.ateam</groupId>
					<artifactId>atg-runAssembler-plugin</artifactId>
					<version>1.0</version>
                    <configuration>
                    	Add config options here					
                    </configuration>
				</plugin>
												
				
## Plugin options:

	<destinationEar>STRING</destinationEar>        	Required. This is the path and name of the ear to be generated            
	<moduleNames>STRING</moduleNames>				Required. Modules passed to runAssembler (-m flag)
	<addEarFile>STRING</addEarFile>					Optional. Adds -add-ear-file with value you enter here
	<classesOnly>BOOLEAN</classesOnly>				Optional. Adds -classesonly flag
	<collapseClassPath>BOOLEAN</collapseClassPath>	Optional. Adds -collapse-class-path flag
	<contextRootsFile>STRING</contextRootsFile>		Optional. Adds -context-roots-file and value you enter here
	<displayName>STRING</displayName>				Optional. Adds -displayname flag and value you enter here
	<distributable>BOOLEAN</distributable>			Optional. Adds -distributable flag
	<jarDirs>BOOLEAN</jarDirs>						Optional. Adds -jardirs flag
	<jboss>BOOLEAN</jboss>							Optional. Adds -jboss flag (ATG 11 only)
	<layer>STRING STRING</layer>					Optional. Adds -layer flag and values you enter. Value are space separated.
	<liveconfig>BOOLEAN</liveconfig>				Optional. Adds -liveconfig flag
	<noFix>BOOLEAN</noFix>							Optional. Adds -nofix flag
	<overwriteEar>BOOLEAN</overwriteEar>			Optional. Adds -overwrite flag
	<pack>BOOLEAN</pack>							Optional. Adds -pack flag
	<prependJars>STRING,STRING</prependJars>		Optional. Adds -prependJars flag and values you enter. Value are comma separated.
	<runInPlace>BOOLEAN</runInPlace>				Optional. Adds -run-in-place flag
	<serverName>STRING</serverName>					Optional. Adds -server flag and value you enter.
	<standalone>BOOLEAN</standalone>				Optional. Adds -standalone flag


### Special options:

	<dynamoHome>STRING</dynamoHome>				Optional. Sets the path to DYNAMO_HOME. By default, this uses your DYNAMO_HOME environment variable
	<assemblerPath>STRING</assemblerPath>		Optional. Sets the path to runAssembler relative to DYNAMO_HOME. This is set to bin/runAssembler by default
	*See the note below on assemblerPath.

	<passThroughCommand>STRING</passThroughCommand> 
	passThroughCommand is a special case allowing you to directly pass your own build flags to runAssembler.
	Using this option causes all other config options to be ignored, except dynamoHome and assemblerPath.


## Notes:
The plugin is designed to work on linux and windows, and detects which operating system it is being run on.
When Windows is detected, the .bat extension is automatically appended to the runAssembler command.
So on windows, the default value of bin/runAssembler will be changed to bin/runAssembler.bat at runtime.
The only value you should have to change for the same pom setup to work between operating systems is the path to build the ear to.

## Contributing

atg-maven-plugin is an open source project. See
[CONTRIBUTING](https://github.com/oracle/atg-maven-plugin/tree/master/CONTRIBUTING.md)
for details.



