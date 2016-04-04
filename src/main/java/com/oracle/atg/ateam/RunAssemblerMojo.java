/**
 * Copyright (c) 2015, Oracle Corporation
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided
 * that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *     * Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 *     * Neither the name of Oracle nor the names of its contributors may be used to endorse or
 *       promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL ORACLE BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.oracle.atg.ateam;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;

/**
 * @author Michael Shanley
 * @version 1.0
 *
 * @goal runAssembler
 */
public class RunAssemblerMojo extends AbstractMojo {

	/**
	 * Path to DYNAMO_HOME
	 *
	 * @parameter default-value="${env.DYNAMO_HOME}"
	 * @required
	 */
	private String dynamoHome;

	/**
	 * Path to runAssembler
	 *
	 * @parameter default-value="bin/runAssembler"
	 * @required
	 */
	private String assemblerPath;

	/**
	 * The path and name of the EAR file to generate.
	 *
	 * @parameter
	 */
	private String destinationEar;

	/**
	 * What modules to include in the EAR
	 *
	 * @parameter
	 * @required
	 */
	private String moduleNames;

	/**
	 * -add-ear-file option
	 *
	 * @parameter
	 */
	private String addEarFile;

	/**
	 * -classesonly option
	 *
	 * @parameter
	 */
	private boolean classesOnly;

	/**
	 * -collapse-class-path option
	 *
	 * @parameter
	 */
	private boolean collapseClassPath;

	/**
	 * -context-roots-file option
	 *
	 * @parameter
	 */
	private String contextRootsFile;

	/**
	 * -displayname option
	 *
	 * @parameter
	 */
	private String displayName;

	/**
	 * adds distributable tag to web.xml for JBoss
	 *
	 * @parameter
	 */
	private boolean distributable;

	/**
	 * -jardirs option
	 *
	 * @parameter
	 */
	private boolean jarDirs;

	/**
	 * For ATG 11 and JBoss. Does not exist in ATG 10.x
	 *
	 * @parameter
	 */
	private boolean jboss;

	/**
	 * Enabled one or more config layers. -layer option -layer must precede
	 * –standalone and –m
	 *
	 * @parameter
	 */
	private String layer;

	/**
	 * Enable the liveconfig layer –liveconfig must follow the runAssembler
	 * command
	 *
	 * @parameter default-value="false"
	 */
	private boolean liveconfig;

	/**
	 * -nofix option
	 *
	 * @parameter
	 */
	private boolean noFix;

	/**
	 * Whether to overwrite the ear
	 *
	 * @parameter
	 */
	private boolean overwriteEar;

	/**
	 * Should the ear be packed
	 *
	 * @parameter
	 */
	private boolean pack;

	/**
	 * PrependJars - archive that needs to be prepended to the ear.
	 *
	 * @parameter
	 */
	private String prependJars;

	/**
	 * add run-in-place option - for JBoss only
	 *
	 * @parameter
	 */
	private boolean runInPlace;

	/**
	 * ATG serverName layer
	 *
	 * @parameter
	 */
	private String serverName;

	/**
	 * add -standalone option. must precede –m
	 *
	 * @parameter
	 */
	private boolean standalone;

	/**
	 * Allow command override
	 *
	 * @parameter
	 */
	private String passThroughCommand;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

		if (getLog().isDebugEnabled()) {
			dumpParams();
		}

		if (StringUtils.isBlank(dynamoHome)) {
			throw new MojoExecutionException(
					"dynamoHome cannot be empty. Set your env.DYNAMO_HOME, or explicitly set dynamoHome in your pom");
		}

		// if passThrough is not set, you must have a destination ear
		if (StringUtils.isBlank(destinationEar)
				&& StringUtils.isBlank(passThroughCommand)) {
			throw new MojoExecutionException("destinationEar cannot be empty");
		}

		// build command and pass it to runAssembler
		executeAssembler(buildCommand());

	}

	/**
	 * Build the command for runAssembler
	 *
	 * @return command to send to runAssembler
	 */
	protected String buildCommand() {
		final StringBuilder assemblerCommand = new StringBuilder();

		// if passthrough is set, skip all this
		if (StringUtils.isBlank(passThroughCommand)) {
			if (liveconfig) {
				assemblerCommand.append(" -liveconfig");
			}
			if (StringUtils.isNotBlank(addEarFile)) {
				assemblerCommand.append(" -add-ear-file ");
				assemblerCommand.append(addEarFile);
			}
			if (classesOnly) {
				assemblerCommand.append(" -classesonly");
			}
			if (collapseClassPath) {
				assemblerCommand.append(" -collapse-class-path");
			}
			if (StringUtils.isNotBlank(contextRootsFile)) {
				assemblerCommand.append(" -context-roots-file ");
				assemblerCommand.append(contextRootsFile);
			}
			if (StringUtils.isNotBlank(displayName)) {
				assemblerCommand.append(" -displayname ");
				assemblerCommand.append(displayName);
			}
			if (distributable) {
				assemblerCommand.append(" -distributable");
			}
			if (jarDirs) {
				assemblerCommand.append(" -jardirs");
			}
			if (jboss) {
				assemblerCommand.append(" -jboss");
			}
			if (noFix) {
				assemblerCommand.append(" -nofix");
			}
			if (standalone) {
				assemblerCommand.append(" -standalone");
			}
			if (overwriteEar) {
				assemblerCommand.append(" -overwrite");
			}
			if (pack) {
				assemblerCommand.append(" -pack");
			}
			if (StringUtils.isNotBlank(prependJars)) {
				assemblerCommand.append(" -prependJars ");
				assemblerCommand.append(prependJars);
			}
			if (runInPlace) {
				assemblerCommand.append(" -run-in-place");
			}
			if (StringUtils.isNotBlank(serverName)) {
				assemblerCommand.append(" -server ");
				assemblerCommand.append(serverName);
			}
			assemblerCommand.append(" ");
			assemblerCommand.append(destinationEar);

			if (StringUtils.isNotBlank(layer)) {
				assemblerCommand.append(" -layer ");
				assemblerCommand.append(layer);
			}
			if (StringUtils.isNotBlank(moduleNames)) {
				assemblerCommand.append(" -m ");
				assemblerCommand.append(moduleNames);
			}
		} else {
			// allow override of all commands. Just pass through the exact
			// string
			// used in the pom
			assemblerCommand.append(passThroughCommand);
		}

		return assemblerCommand.toString();
	}

	/**
	 * Call runAssembler with command
	 *
	 * @param command
	 * @throws MojoExecutionException
	 * @throws MojoFailureException
	 */
	private void executeAssembler(final String command)
			throws MojoExecutionException, MojoFailureException {

		final File assemblerCommand = new File(dynamoHome, assemblerPath
				+ (SystemUtils.IS_OS_UNIX ? "" : ".bat"));

		if (!assemblerCommand.isFile()) {
			throw new MojoExecutionException(assemblerCommand.getAbsolutePath()
					+ " is not a regular file");
		}
		if (!assemblerCommand.canExecute()) {
			throw new MojoExecutionException(assemblerCommand.getAbsolutePath()
					+ " is not executable");
		}

		String execCommand = command;

		getLog().info("*******************************************");
		getLog().info("Calling runAssembler");
		getLog().info(" ");
		getLog().info("Command: " + assemblerCommand + " " + execCommand);
		getLog().info(" ");
		getLog().info("*******************************************");

		try {

			// spit out lines from stdout and stderr as soon as they come in
			final StreamConsumer stdout = new StreamConsumer() {
				@Override
				public void consumeLine(final String line) {
					getLog().info(line);
				}
			};

			final StreamConsumer stderr = new StreamConsumer() {
				@Override
				public void consumeLine(final String line) {
					getLog().error(line);
				}
			};

			final Commandline commandLine = new Commandline();

			// if the OS is windows, we need to call the batch file through
			// cmd.exe
			if (SystemUtils.IS_OS_UNIX) {
				commandLine.setExecutable(assemblerCommand.getAbsolutePath());
			} else {
				commandLine.setExecutable("cmd.exe");
				commandLine.createArg().setValue("/c");
				execCommand = assemblerCommand.getAbsolutePath() + " "
						+ execCommand;
			}

			commandLine.createArg().setValue(execCommand);

			final int returncode = CommandLineUtils.executeCommandLine(
					commandLine, stdout, stderr);

			final String returnMsg = "Return code: " + returncode;
			if (returncode != 0) {
				throw new MojoFailureException(returnMsg);
			}
		} catch (final CommandLineException e) {
			throw new MojoFailureException(
					"CommandLineException executing runAssembler ", e);
		}

	}

	private void dumpParams() {
		getLog().debug("dynamoHome " + dynamoHome);
		getLog().debug("assemblerPath " + assemblerPath);
		getLog().debug("destinationEar " + destinationEar);
		getLog().debug("moduleNames " + moduleNames);
		getLog().debug("addEarFile " + addEarFile);
		getLog().debug("classesOnly " + classesOnly);
		getLog().debug("collapseClassPath " + collapseClassPath);
		getLog().debug("contextRootsFile " + contextRootsFile);
		getLog().debug("displayName " + displayName);
		getLog().debug("distributable " + distributable);
		getLog().debug("jarDirs " + jarDirs);
		getLog().debug("jboss " + jboss);
		getLog().debug("layer " + layer);
		getLog().debug("liveconfig " + liveconfig);
		getLog().debug("noFix " + noFix);
		getLog().debug("overwriteEar " + overwriteEar);
		getLog().debug("pack " + pack);
		getLog().debug("prependJars " + prependJars);
		getLog().debug("runInPlace " + runInPlace);
		getLog().debug("serverName " + serverName);
		getLog().debug("standalone " + standalone);
	}

	/**
	 * @return dynamoHome
	 */
	public String getDynamoHome() {
		return dynamoHome;
	}

	/**
	 *
	 * @param dynamoHome
	 */
	public void setDynamoHome(final String dynamoHome) {
		this.dynamoHome = dynamoHome;
	}

	/**
	 * @return assemblerPath
	 */
	public String getAssemblerPath() {
		return assemblerPath;
	}

	/**
	 * @param assemblerPath
	 */
	public void setAssemblerPath(final String assemblerPath) {
		this.assemblerPath = assemblerPath;
	}

	/**
	 * @return destinationEar
	 */
	public String getDestinationEar() {
		return destinationEar;
	}

	/**
	 * @param destinationEar
	 */
	public void setDestinationEar(final String destinationEar) {
		this.destinationEar = destinationEar;
	}

	/**
	 * @return moduleNames
	 */
	public String getModuleNames() {
		return moduleNames;
	}

	/**
	 * @param moduleNames
	 */
	public void setModuleNames(final String moduleNames) {
		this.moduleNames = moduleNames;
	}

	/**
	 * @return addEarFile
	 */
	public String getAddEarFile() {
		return addEarFile;
	}

	/**
	 * @param addEarFile
	 */
	public void setAddEarFile(final String addEarFile) {
		this.addEarFile = addEarFile;
	}

	/**
	 * @return classesOnly
	 */
	public boolean isClassesOnly() {
		return classesOnly;
	}

	/**
	 * @param classesOnly
	 */
	public void setClassesOnly(final boolean classesOnly) {
		this.classesOnly = classesOnly;
	}

	/**
	 * @return collapseClassPath
	 */
	public boolean isCollapseClassPath() {
		return collapseClassPath;
	}

	/**
	 * @param collapseClassPath
	 */
	public void setCollapseClassPath(final boolean collapseClassPath) {
		this.collapseClassPath = collapseClassPath;
	}

	/**
	 * @return contextRootsFile
	 */
	public String getContextRootsFile() {
		return contextRootsFile;
	}

	/**
	 * @param contextRootsFile
	 */
	public void setContextRootsFile(final String contextRootsFile) {
		this.contextRootsFile = contextRootsFile;
	}

	/**
	 * @return displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName
	 */
	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return distributable
	 */
	public boolean isDistributable() {
		return distributable;
	}

	/**
	 * @param distributable
	 */
	public void setDistributable(final boolean distributable) {
		this.distributable = distributable;
	}

	/**
	 * @return jarDirs
	 */
	public boolean isJarDirs() {
		return jarDirs;
	}

	/**
	 * @param jarDirs
	 */
	public void setJarDirs(final boolean jarDirs) {
		this.jarDirs = jarDirs;
	}

	/**
	 * @return jboss
	 */
	public boolean isJboss() {
		return jboss;
	}

	/**
	 * @param jboss
	 */
	public void setJboss(final boolean jboss) {
		this.jboss = jboss;
	}

	/**
	 * @return layer
	 */
	public String getLayer() {
		return layer;
	}

	/**
	 * @param layer
	 */
	public void setLayer(final String layer) {
		this.layer = layer;
	}

	/**
	 * @return liveconfig
	 */
	public boolean isLiveconfig() {
		return liveconfig;
	}

	/**
	 * @param liveconfig
	 */
	public void setLiveconfig(final boolean liveconfig) {
		this.liveconfig = liveconfig;
	}

	/**
	 * @return noFix
	 */
	public boolean isNoFix() {
		return noFix;
	}

	/**
	 * @param noFix
	 */
	public void setNoFix(final boolean noFix) {
		this.noFix = noFix;
	}

	/**
	 * @return overwriteEar
	 */
	public boolean isOverwriteEar() {
		return overwriteEar;
	}

	/**
	 * @param overwriteEar
	 */
	public void setOverwriteEar(final boolean overwriteEar) {
		this.overwriteEar = overwriteEar;
	}

	/**
	 * @return pack
	 */
	public boolean isPack() {
		return pack;
	}

	/**
	 * @param pack
	 */
	public void setPack(final boolean pack) {
		this.pack = pack;
	}

	/**
	 * @return prependJars
	 */
	public String getPrependJars() {
		return prependJars;
	}

	/**
	 * @param prependJars
	 */
	public void setPrependJars(final String prependJars) {
		this.prependJars = prependJars;
	}

	/**
	 * @return runInPlace
	 */
	public boolean isRunInPlace() {
		return runInPlace;
	}

	/**
	 * @param runInPlace
	 */
	public void setRunInPlace(final boolean runInPlace) {
		this.runInPlace = runInPlace;
	}

	/**
	 * @return serverName
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * @param serverName
	 */
	public void setServerName(final String serverName) {
		this.serverName = serverName;
	}

	/**
	 * @return standalone
	 */
	public boolean isStandalone() {
		return standalone;
	}

	/**
	 * @param standalone
	 */
	public void setStandalone(final boolean standalone) {
		this.standalone = standalone;
	}

	/**
	 * @return passThroughCommand
	 */
	public String getPassThroughCommand() {
		return passThroughCommand;
	}

	/**
	 * @param passThroughCommand
	 */
	public void setPassThroughCommand(final String passThroughCommand) {
		this.passThroughCommand = passThroughCommand;
	}

}
