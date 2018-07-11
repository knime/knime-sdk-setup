# ![Image](https://www.knime.com/files/knime_logo_github_40x40_4layers.png) KNIME® Analytics Platform - SDK Setup

KNIME Analytics Platform is the leading open solution for data-driven innovation, helping you discover the potential hidden in your data, mine for fresh insights, or predict new futures. Our enterprise-grade, open source platform is fast to deploy, easy to scale, and intuitive to learn.

![Image](https://www-cdn.knime.com/sites/default/files/inline-images/knime-analytics-platform.png)

The tools provided in this repository will help you set up your KNIME Analytics Platform SDK in order to work with the source code of our plug-ins or develop new extensions for KNIME Analytics Platform. If you are not interested in development and prefer to simply download and try KNIME Analytics Platform right now, then click [here](http://knime.com/downloads).

_Please note: We are currently in the process of publishing more of our open-source projects on Bitbucket and GitHub. Stay tuned!_

## Development Notes
KNIME Analytics Platform is built on Eclipse, employing its wealth of functionality in a variety of ways. A key concept behind Eclipse is its use of plug-ins, which can be added to an existing installation to provide additional functionality. In order to develop custom functionality to extend KNIME Analytics Platform, you first have to populate your local [target platform](https://wiki.eclipse.org/PDE/Target_Definitions) with all the required dependencies. A target platform defines a set of plug-ins that can be used as dependencies by projects in your workspace. 

The target platform is different for every release, so you must select the correct branch of ``knime-sdk-setup``. For example for developing
nodes that are compatible with the 3.6 release, checkout the branch ``releases/2018-07`` and select the file ``KNIME-AP.target``. If you want to develop against the latest nightly build, checkout the master branch and use ``KNIME-AP.target`` from there. However, bear in mind that extensions developed against nightly may not work in
releases installations due to changed and/or missing API.

## Getting Started
### General Setup for Node Development
#### 1. Setup Eclipse
* Download and install the latest version of [Eclipse for RCP and RAP Developers](https://www.eclipse.org/downloads/eclipse-packages/). Make sure you
  are using at least version 4.7.x.
* Clone this (_knime-sdk-setup_) repository to your computer.
* Import ``org.knime.sdk.setup`` ``(File → Import → General → Existing Projects)`` from this repository into your workspace.
* In this project, you will find two target platform definition files. One with a minimal installation (``KNIME-AP.target``) and one containing all extensions (``KNIME-AP-complete.target``).
* Double-click the target platform definition of you want to use for development. If in doubt, use ``KNIME-AP-complete.target``. 
* Now click __Set as Target Platform__ (upper-right corner) and wait until Eclipse has resolved and activated the target platform. Important: Resolving the target platform definition may take a while.

*Adding Extensions to the Target Definition:*
* _Adding Plug-ins From KNIME Analytics Platform Update Site:_ You can simply edit the existing update-site entry in the selected target definition and add more extensions as needed.
* _Third Party Update-Sites:_ If you need additional plug-ins from third party update-sites, you can edit the software sites in the target definitions. For example you can add the update-sites from [Community Contributions Website](https://www.knime.com/community).

#### 2. Example Code
* Import ``org.knime.example.node`` ``(File → Import → General → Existing Projects)`` from this repository into your workspace. This project contains and example node implementation that you can use as the basis for your own node development.

#### 3. Launch KNIME
* The ``KNIME Analytics Platform`` launch configuration is now available to you in the debug and run configuration dialogs as an Eclipse application. The run configuration starts a new KNIME instance with all KNIME Analytics Platform Extensions in the target platform and your local workspace. This launched instance can be used for debugging and testing your custom functionality. All of the plug-ins available in your workspace take precedence over the plug-ins in the target platform. (Note: if the launch configuration is missing, ``Right-click`` on ``KNIME Analytics Platform.launch`` and select ``Run As → KNIME Analytics Platform``).
* The launch configuration uses 2GB of available RAM. If you want to use a different amount, change the value of the ``-Xmx2g`` VM argument in the _Arguments_ tab of the launch configuration.

### Contributing to KNIME Analytics Platform code
#### 1. General Setup
* Follow the steps in _General Setup for Node Development_

#### 2. Install Git and Git LFS
Install The Git command line client and Git LFS support:

* Linux: Git should be part of the standard repositories, Git LFS might miss, get it from https://help.github.com/articles/installing-git-large-file-storage/#platform-linux
* Windows: https://git-scm.com/download/win. Git LFS should be part of the Git installation. If Git LFS is missing, please install it from https://git-lfs.github.com/
* MacOS X: https://git-scm.com/download/mac and https://git-lfs.github.com/

#### 3. Configure API Baseline
* Go to ``Window → Preferences → Plug-in Development → API Baseline and Add Baseline...``
* Select ``A target platform``
* Select ``KNIME Analytics Platform (3.x release)`` (which is in the ``org.knime.sdk.setup`` project)
* Click Refresh
* Give the baseline a meaningful name (e.g. ``KNIME Analytics Platform (3.x release)``) and click ``OK``

#### 4. Work with source code of KNIME Analytics Platform
* Some projects auto-generate code using XML Beans. They require you to have the project _org.apache.xmlbeans_ from this repository in your workspace. Import ``org.apache.xmlbeans`` ``(File → Import → General → Existing Projects)`` from this repository into your workspace.
* If you want to work with the source code of KNIME Analytics Platform or a related extension, simply clone the repository of interest (e.g. from [Bitbucket](http://bitbucket.com/knime)) and import the plug-ins as Java projects into your Eclipse workspace (``File → Import → General → Existing Projects``). 
* Projects imported into the workspace take precedence over plug-ins in the target platform.
* Use ``git lfs clone`` in favor of ``git clone`` to clone our repositories for faster cloning.
* In case you experience compile errors such as ``The type org.dmg.pmml.* cannot be resolved`` or ``The import org.dmg.pmml.* cannot be resolved`` please close or remove the project ``org.knime.core.pmml``. This project contains auto-generated classes only and is already part of the target platform.

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
