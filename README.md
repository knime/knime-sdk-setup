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
This section is split into four parts ``General Setup``,  ``Develop Nodes``, ``Exploring KNIME Analytics Platform Source Code`` and `` Contribute to KNIME Analytics Platform Source Code``.  Please follow the instructions in ``General Setup`` first. Also, in our instructions we use the [Eclipse Git integration (EGit)](https://www.eclipse.org/egit/) to work with Git, however, you can use any other Git client as well.

### General Setup
#### 1. Install Java and Eclipse
* In case you haven't installed Java, please download and install [Java SE Development Kit 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and restart your computer.
* Download and install the latest version of [Eclipse for RCP and RAP Developers](https://www.eclipse.org/downloads/eclipse-packages/). Make sure you are using at least version 4.7.x.

#### 2. Install Git and Git LFS
Install Git with LFS support:

* Windows: Eclipse 4.7 already comes with a Git installation. In case you want to install Git manually, please follow https://git-scm.com/download/win. Git LFS should be part of the Git installation. If Git LFS is missing, please install it from https://git-lfs.github.com/.
* Linux: Git should be part of the standard repositories. If Git LFS is missing, get it from https://help.github.com/articles/installing-git-large-file-storage/#platform-linux.
* macOS: https://git-scm.com/download/mac and https://git-lfs.github.com/.

#### 3. Setup Eclipse
* Start Eclipse.
* Use the [Eclipse Git integration (EGit)](https://www.eclipse.org/egit/) (pre-installed with Eclipse 4.7.x) to clone this repository (_knime-sdk-setup_) into your Eclipse workspace. Go to ``File → Import → Git → Projects from Git File → Clone URI``. Enter ``https://github.com/knime/knime-sdk-setup`` as URI and proceed. Next, select the branches you want to clone. Select all branches starting with ``releases/`` and the `master` branch. Next, select the initial branch you want to work with (e.g. ``master``, see ``Development Notes`` above). Choose ``Import existing Eclipse projects``. In a last step, select all three projects (``org.apache.xmlbeans``, ``org.knime.sdk.setup`` and ``org.knime.example.node``) and press ``Finish``.
* In the imported ``org.knime.sdk.setup`` project, you find three target platform definition files (ending with ``.target``). A target definition defines the set of KNIME Extensions and Integrations which will be available when starting your KNIME Analytics Platform development version (see ``Launch KNIME Analytics Platform``). ``KNIME-AP.target`` comprises a minimal KNIME Analytics Platform installation, while ``KNIME-AP-complete.target`` contains all KNIME Extensions and Integrations. You can simply ignore ``KNIME-AP-complete-internal.target``.
* Double-click on the target platform definition you want to use for development. If in doubt, use ``KNIME-AP-complete.target``. _Note: Resolving the target platform the first time takes a while. You can monitor the progress at the bottom right corner of your Eclipse._
* Now click __Set as Active Target Platform__ (upper-right corner) and wait until Eclipse has resolved and activated the target platform. Important: Resolving the target platform definition may take a while.

#### 4. Adding Extensions to the Target Definition (Advanced)
In case you didn't select ``KNIME-AP-complete.target`` or you want to add third party extensions:

* _Adding Plug-ins From KNIME Analytics Platform Update Site:_ You can simply edit the existing update-site entries in the selected target definition and add more extensions as needed.
* _Third Party Update-Sites:_ If you need additional plug-ins from third party update-sites, you can edit the software sites in the target definitions. For example you can add the update-sites from [Community Contributions Website](https://www.knime.com/community).

#### 5. Launch KNIME Analytics Platform
* ``KNIME Analytics Platform`` launch configuration is now available to you in the debug and run configuration dialogs as an Eclipse application. The run configuration starts a new KNIME instance with all KNIME Analytics Platform Extensions in the target platform and your local workspace. This launched instance can be used for testing your custom functionality. All of the plug-ins available in your workspace take precedence over the plug-ins in the target platform. (Note: if the launch configuration is missing, ``Right-click`` on ``KNIME Analytics Platform.launch`` and select ``Run As → KNIME Analytics Platform``).
* The launch configuration uses 2GB of available RAM. If you want to use a different amount, change the value of the ``-Xmx2g`` VM argument in the _Arguments_ tab of the launch configuration.
* Starting KNIME Analytics Platform from Eclipse the first time may take some time (often > 5min), as Eclipse is resolving all bundle dependencies. All subsequent start-ups will be considerably faster.

### Develop Nodes
* The project ``org.knime.example.node`` you imported in the ``General Setup`` contains an example node implementation that you can use as the basis for your own node development.
* Alternatively, you can install the [KNIME New Node Wizard](https://www.knime.com/developer/documentation/wizard) into your Eclipse Installation from our [Update site (Category: KNIME Development)](http://update.knime.com/analytics-platform/3.6).

### Explore KNIME Analytics Platform Source Code
* If you want to work with the source code of KNIME Analytics Platform or a related extension, simply clone and import the repository of interest (e.g. https://github.com/knime/knime-core) and import the plug-ins as Java projects into your Eclipse workspace, similiar to ``General Setup - Eclipse Setup`` (``File → Import → Git → Projects from Git File → Clone URI``). 
* Projects imported into the workspace take precedence over plug-ins in the target platform.
* In case you experience compile errors such as ``The type org.dmg.pmml.* cannot be resolved`` or ``The import org.dmg.pmml.* cannot be resolved`` please close or remove the project ``org.knime.core.pmml``. This project contains auto-generated classes only and is already part of the target platform.

### Contribute to KNIME Analytics Platform Source Code
#### 1. Contributor License Agreement
* Please read and sign our [Contributor License Agreement](https://github.com/knime/knime-sdk-setup/blob/master/CONTRIBUTING.MD) such that we can accept your ``Pull Requests``.

#### 2. Configure API Baseline
Our API-Baseline ensures that we don't break existing KNIME API and stay backwards-compatible:
* Go to ``Window → Preferences → Plug-in Development → API Baseline and Add Baseline...``
* Select ``A target platform``
* Select ``KNIME Analytics Platform (3.x release)`` (which is in the ``org.knime.sdk.setup`` project)
* Click Refresh
* Give the baseline a meaningful name (e.g. ``KNIME Analytics Platform (3.x release)``) and click ``OK``

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
