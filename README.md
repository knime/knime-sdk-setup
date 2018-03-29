# ![Image](https://www.knime.com/files/knime_logo_github_40x40_4layers.png) KNIME® Analytics Platform - SDK Setup

KNIME Analytics Platform is the leading open solution for data-driven innovation, helping you discover the potential hidden in your data, mine for fresh insights, or predict new futures. Our enterprise-grade, open source platform is fast to deploy, easy to scale, and intuitive to learn.

![Image](https://www-cdn.knime.com/sites/default/files/inline-images/knime-analytics-platform.png)

The tools provided in this repository will help you set up your KNIME Analytics Platform SDK in order to work with the source code of our plug-ins or develop new extensions for KNIME Analytics Platform. If you are not interested in development and prefer to simply download and try KNIME Analytics Platform right now, then click [here](http://knime.com/downloads).

_Please note: We are currently in the process of publishing more of our open-source projects on Bitbucket and GitHub. Stay tuned!_

## Development Notes
KNIME Analytics Platform is built on Eclipse, employing its wealth of functionality in a variety of ways. A key concept behind Eclipse is its use of plug-ins, which can be added to an existing installation to provide additional functionality. In order to develop custom functionality to extend KNIME Analytics Platform, you first have to populate your local [target platform](https://wiki.eclipse.org/PDE/Target_Definitions) with all the required dependencies. A target platform defines a set of plug-ins that can be used as dependencies by projects in your workspace. A pre-configured target platform definition for each recent release that contains a minimum set of plug-ins, can be found in the ``KNIME-AP-X.Y.target`` files in the project ``org.knime.sdk.setup``.

The target platform is different for every release, so you must select  the correct branch of ``org.knime.sdk.setup``. E.g. for developing
nodes that are compatible with the 3.5 release, use the `KNIME-AP-3.5.target` file. If you want to develop against the latest nightly build, use the ``KNIME-AP-nightly.target`` file. However, bear in mind that extensions developed against nightly may not work in
release installations due to changed and/or missing API.

Some projects auto-generate code using XML Beans. They require you to have the project _org.apache.xmlbeans_ from this repository in your workspace.

## Getting Started
#### 1. Install Git and Git LFS
Install The Git command line client and Git LFS support:
* Linux: Git should be part of the standard repositories, Git LFS might miss, get it from [here](https://help.github.com/articles/installing-git-large-file-storage/)
* Windows: https://git-scm.com/download/win and https://git-lfs.github.com/ (use the recommended settings during installation)
* MacOS X: https://git-scm.com/download/mac and https://git-lfs.github.com/ (use the recommended settings during installation)

#### 2. Setup Eclipse
* Download and install the latest version of [Eclipse](https://www.eclipse.org/downloads/eclipse-packages/). Make sure you
  are using a version newer than 4.6.2 because that version contains a bug in the Java compiler, which makes it impossible to compile some KNIME classes.
  The current versions of eclipse (4.7.x) do not suffer from this bug and are therefore recommended.
* Install the __Eclipse Plug-in Development Environment__ plug-in from  __The Eclipse Project Updates__ update-site, you can find it in the __Eclipse Plugin Development Tools__ folder. If you downloaded the `Eclipse IDE for Eclipse Committers` this step is not necessary, as it comes with this plug-in preinstalled.
* Clone this repository to your computer.
* Import **all** projects ``(File -> Import -> General -> Existing Projects)`` from this repository into your workspace.
* In the ``org.knime.sdk.setup`` project, you will find several target platform definition files, that define different releases of the KNIME Analytics Platform.
* Double-click the target platform definition file that matches ``KNIME-AP-X.Y.target``, where X.Y is the release (or nighly) you want to develop against.
* Now click __Set as Target Platform__ (upper-right corner) and wait until Eclipse has resolved and activated the target platform.

#### 3. Configure API Baseline
* Go to Preferences → Plug-in Development → API Baseline and Add Baseline...
* Select "A target platform"
* Select "Release-20XX-YY.target" (which is in the org.knime.sdk.setup project)
* Click Refresh
* Give the baseline a meaningful name (e.g. "Release-2017-12") and click OK

Note: If you are not planning to change the KNIME Analytics Platform API, you do not need to configure it and can instead safely set the error level at ``Window -> Preferences -> Plug-in Development -> API Baselines`` to _Ignore_.

#### 4. Launch KNIME
* The ``KNIME Analytics Platform`` launch configuration is now available to you in the debug and run configuration dialogs as an Eclipse application. The run configuration starts a new KNIME instance with all KNIME Analytics Platform Extensions in the target platform and your local workspace. This launched instance can be used for debugging and testing your custom functionality. All of the plug-ins available in your workspace take precedence over the plug-ins in the target platform.
* The launch configuration uses 2GB of available RAM. If you want to use a different amount, change the value of the ``-Xmx2g`` VM argument in the _Arguments_ tab of the launch configuration.

## Work with source code of KNIME Analytics Platform
* If you want to work with the source code of KNIME Analytics Platform or a related extension, simply clone the repository of interest (e.g. from [Bitbucket](http://bitbucket.com/knime)) and import the plug-ins as Java projects into your Eclipse workspace (``File -> Import -> General -> Existing Projects``). 
* Projects imported into the workspace take precedence over plug-ins in the target platform.
* Use ``git lfs clone`` in favor of ``git clone`` to clone our repositories for faster cloning.
* In case you experience compile errors such as ``The type org.dmg.pmml.* cannot be resolved`` or ``The import org.dmg.pmml.* cannot be resolved`` please close or remove the project ``org.knime.core.pmml``. This project contains auto-generated classes only and is already part of the target platform.

## Adding Extensions to the Target Definition
* _Adding Plug-ins From KNIME Analytics Platform Update Site:_ You can simply edit the existing update-site entry in the selected target definition and add more extensions as needed.
* _Third Party Update-Sites:_ If you need additional plug-ins from third party update-sites, you can edit the software sites in the target definitions. For example you can add the update-sites from [Community Contributions Website](https://www.knime.com/community).

## Links
* [JavaDoc](https://www.knime.com/javadoc-api)
* [Developer Guide](https://www.knime.com/developer-guide)
* [Noding Guidelines](https://tech.knime.org/files/development/noding_guidelines.pdf)
* [Developer FAQ](https://www.knime.com/developer/faq)
* [Example Plug-in](https://www.knime.com/developer/example/node-model)
* [Pre-built Nightly Builds](https://www.knime.com/form/nightly-build)

## KNIME Community Contributors
### Forum
Do you have questions regarding the development of KNIME Analytics Platform? Reach out to us in our [Forum](https://forum.knime.com/c/knime-development).

### Be Part of the Community
If you have developed an extension of general interest and you want to make it available to the KNIME Community, we are happy to support you! Contact us via our [Community Contributions Website](https://www.knime.com/community). 

### Pull-Requests
Currently we can't accept external pull requests for various reasons. However, we plan to allow external developers to contribute directly to KNIME Analytics Platform in the future.