# ![Image](https://www.knime.com/files/knime_logo_github_40x40_4layers.png) KNIME® Analytics Platform - SDK Setup

KNIME Analytics Platform is the leading open solution for data-driven innovation, helping you discover the potential hidden in your data, mine for fresh insights, or predict new futures. Our enterprise-grade, open source platform is fast to deploy, easy to scale, and intuitive to learn.

![Image](https://www-cdn.knime.com/sites/default/files/inline-images/knime-analytics-platform.png)

The tools provided in this repository will help you set up your KNIME Analytics Platform SDK in order to work with the source code of our plug-ins or develop new extensions for KNIME Analytics Platform. If you are not interested in development and prefer to simply download and try KNIME Analytics Platform right now, then click [here](http://knime.com/downloads).

_Please note: We are currently in the process of publishing more of our open-source projects on Bitbucket and GitHub. Stay tuned!_

## Development Notes
KNIME Analytics Platform is built on Eclipse, employing its wealth of functionality in a variety of ways. A key concept behind Eclipse is its use of plug-ins, which can be added to an existing installation to provide additional functionality. In order to develop custom functionality to extend KNIME Analytics Platform, you first have to populate your local [target platform](https://wiki.eclipse.org/PDE/Target_Definitions) with all the required dependencies. A target platform defines a set of plug-ins that can be used as dependencies by projects in your workspace. A pre-configured target platform definition that 
contains a minimum set of plug-ins can be found in the file ``KNIME.target`` in the project ``org.knime.sdk.setup``.

The target platform is different for every release, therefore you must check out the correct branch of ``org.knime.sdk.setup``. E.g. for developing
nodes that are compatible with the 3.2 release, check out branch ``releases/3.2``and use its ``KNIME.target`` file. If you want to develop against the current master code, check out the ``master`` branch of this repository. However, bear in mind that extensions developed against master may not work in
release installations due to changed and/or missing API.

Some projects auto-generate code using XML Beans. Therefore you must have the project _org.apache.xmlbeans_ from this repository in your workspace.

### Git LFS
We use [Git LFS](https://git-lfs.github.com/), therefore you must have the corresponding program installed and enabled on your system in order to properly
clone our repositories.

### Adding Extensions to the Target Definition
* _Adding Plug-ins From KNIME Analytics Platform Update Site:_ You can simply edit the existing update-site entry in the selected target definition and add more extensions as needed.
* _Third Party Update-Sites:_ If you need additional plug-ins from third party update-sites, you can edit the software sites in the target definitions. For example you can add the update-sites from [Community Contributions Website](https://tech.knime.org/community).

### Getting Started
* Download and install the latest version of [Eclipse](https://www.eclipse.org/downloads/eclipse-packages/). Make sure you
  are using a version newer than 4.6.2 because that version contains a bug in the Java compiler, which makes it impossible to compile some KNIME classes.
  The current versions of eclipse (4.7.x) do not suffer from this bug and are therefore recommended.
* Install the __Eclipse Plug-in Development Environment__ plug-in from  __The Eclipse Project Updates__ update-site. If you downloaded the `Eclipse IDE for Eclipse Committers` this step is not necessary, as it comes with this plug-in preinstalled.
* Clone this repository
* Import **all** projects ``(File -> Import -> General -> Existing Projects)`` from this repository into your workspace
* Double-click the target platform definition file ``KNIME.target``, which you will find in the ``org.knime.sdk.setup`` project.
* Now wait until Eclipse has resolved the target platform and then click  __Set as Target Platform__ (upper-right corner)
* The ``KNIME Analytics Platform`` launch configuration is now available to you in the debug and run configuration dialogs as Eclipse application. The run configuration starts a new KNIME instance with all KNIME Analytics Platform Extensions in the target platform and your local workspace. This launched instance can be used for debugging and testing your custom functionality. All of the plug-ins available in your workspace take precedence over the plug-ins in the target platform.
* The launch configuration uses 2GB of available RAM. If you want to use a different amount, change the value of the ``-Xmx2g`` VM argument in the _Arguments_ tab of the launch configuration.

## Work with Source of KNIME Analytics Platform Extensions
While the target platform definitions enable you to install all KNIME Analytics Platform Extensions directly into your Eclipse, you may want to inspect the code of certain plug-ins in more detail. Simply clone the extension of interest from one of our git repositories and import the plug-ins as Java projects into your Eclipse workspace (``File -> Import -> General -> Existing Projects``). Note that, as already mentioned, projects in the workspace take precedence over plug-ins in the target platform.

### Notes
* Eclipse might show an error "No API Baseline set". You can safely set the error level at ``Window -> Preferences -> Plug-in Development -> API Baselines`` to ignore.
* Use ``git lfs clone`` in favor of ``git clone`` to clone our repositories for faster cloning.
* In case you experience compile errors such as ``The type org.dmg.pmml.* cannot be resolved`` or ``The import org.dmg.pmml.* cannot be resolved`` please close or remove the project ``org.knime.core.pmml``. This project contains auto-generated classes only and is already part of the target platform.

## Nightly Builds
Nightly Builds are available [here]( https://www.knime.com/form/nightly-build); the relevant forum section can be found [here](https://www.knime.com/forum/nightly-build).


## Links
* [JavaDoc](https://tech.knime.org/javadoc-api)
* [Developer Guide](https://tech.knime.org/developer-guide)
* [Noding Guidelines](https://tech.knime.org/files/development/noding_guidelines.pdf)
* [Developer FAQ](https://tech.knime.org/developer/faq)
* [Example Plug-in](https://tech.knime.org/developer/example/node-model)

## KNIME Community Contributors
### Forum
Do you have questions regarding the development of KNIME Analytics Platform? Reach out to us in our [Forum](https://tech.knime.org/forum).

### Be Part of the Community
If you have developed an extension of general interest and you want to make it available to the KNIME Community, we are happy to support you! Contact us via our [Community Contributions Website](https://tech.knime.org/community). 

### Pull-Requests
Currently we can't accept external pull requests for various reasons. However, we plan to allow external developers to contribute directly to KNIME Analytics Platform in the future.