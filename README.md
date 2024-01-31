# ![KNIME® logo](https://www.knime.com/sites/default/files/knime_logo_github_40x40_4layers.png) KNIME® Analytics Platform - SDK Setup

KNIME Analytics Platform is the leading open solution for data-driven innovation, helping you discover the potential hidden in your data, mine for fresh insights, or predict new futures. Our enterprise-grade, open source platform is fast to deploy, easy to scale, and intuitive to learn.

![Image](https://www.knime.com/sites/default/files/inline-images/knime-analytics-platform.png)

The tools provided in this repository will help you set up your KNIME Analytics Platform SDK so that you can work with the source code of our extensions or develop your own for KNIME Analytics Platform. If you are not interested in development and prefer to simply download and try KNIME Analytics Platform right now, then click [here](http://knime.com/downloads).

This repository is maintained by the [KNIME DevOps Team](mailto:devops@knime.com).

## Introduction

KNIME Analytics Platform is built on Eclipse, employing its wealth of functionality in a variety of ways. A key concept behind Eclipse is its use of plug-ins, which can be added to an existing installation to provide additional functionality. Hence, an extension for KNIME Analytics Platform is essentially an [Eclipse plug-in](https://help.eclipse.org/2021-03/index.jsp?topic=/org.eclipse.pde.doc.user/concepts/plugin.htm&cp=4_1_3).

### Target Platform

One important part of your development setup is how dependencies are managed. In Eclipse, this is handled using a local [target platform](https://wiki.eclipse.org/PDE/Target_Definitions), which is populated with all the required dependencies. A target platform defines a set of plug-ins that can be used as dependencies by projects in your workspace. In this case, the specified dependencies may comprise of utility libraries, KNIME Analytics Platform core functionality, or KNIME Extensions. Subsequently, the KNIME Extensions specified in the target platform will be available when you start your KNIME Analytics Platform development version. Generally, any Eclipse plug-in can be added to the target platform.

In practice, the target platform is defined using target platform definition files (``.target`` ending), which can be loaded by Eclipse in order to populate the target platform. These files specify a list of dependencies and Eclipse configuration settings. This repository contains several such files (located in ``org.knime.sdk.setup``) that specify different sets of plug-ins for the target platform. You may use either of the following target platform definition files.

* ``KNIME-AP.target``: contains the minimal required plug-ins for a KNIME Analytics Platform installation
* ``KNIME-AP-complete.target``: contains all KNIME Extensions without Community Contributions

__Note:__ If you want to add or remove extensions from your used platform definition file (e.g. adding Community Contributions), please see the ``Adding Extensions to the Target Definition`` section below.

The target platform is different for every release of KNIME Analytics Platform. The different versions of the above mentioned ``.target`` files are contained on branches of this repository, hence you must select the correct branch of this repository in order to specify the version of KNIME Analytics Platform you want to use for your development work.


### KNIME Analytics Platform Versions, Release Branches, and Tags

Each release line of KNIME Analytics Platform corresponds to a branch named `releases/{YYYY-MM}` where the date is the
date of the first public release of that particular release line. 
For example, KNIME Analytics Platform 5.1.0 was released in July 2023, so its corresponding release line branch is `releases/2023-07`.
That branch will _also_ receive all bugfix commits slated for that particular release line after the initial `.0` release.
Note that, consequently, these branches might contain unreleased (bugfix) changes.

Each public release is assigned a tag consisting of the product
(`analytics-platform/`) and the version identifier `major.minor.bugfix`.
For example, repositories containing code which is released as part of KNIME Analytics Platform version 5.1.0
have the tag `analytics-platform/5.1.0`.

To summarize:

- If you want to develop an extension that is compatible with a specific release line, check out the corresponding
release branch (recommended).
- If you want to target the latest nightly build, choose the `master` branch. However, bear in mind that extensions
developed using a nightly build may not work in subsequent release builds due to changed and/or missing API.
- In case you want to view code as of a particular version, obtain it via the corresponding tag.

In addition, each KNIME Analytics Platform version is based on a particular version of the Eclipse Platform, which
is also noted in the table below.

| KNIME Analytics Platform Release Line | Branch              | Eclipse Version                                                                 |
|---------------------------------------| ------------------- | ------------------------------------------------------------------------------- |
| __Nightly (unreleased)__              | `master`            | [__2023-03__](https://www.eclipse.org/downloads/packages/release/2023-03/r/eclipse-ide-rcp-and-rap-developers)     |
| 5.2.x                                 | `releases/2023-12`  | [2023-03](https://www.eclipse.org/downloads/packages/release/2023-03/r/eclipse-ide-rcp-and-rap-developers)         |
| 5.1.x                                 | `releases/2023-07`  | [2023-03](https://www.eclipse.org/downloads/packages/release/2023-03/r/eclipse-ide-rcp-and-rap-developers)         |
| 4.7.x                                 | `releases/2022-12`  | [2022-06](https://www.eclipse.org/downloads/packages/release/2022-06/r/eclipse-ide-rcp-and-rap-developers)         |
| 4.6.x                                 | `releases/2022-06`  | [2021-03](https://www.eclipse.org/downloads/packages/release/2021-03/r/eclipse-ide-rcp-and-rap-developers)         |

## SDK Setup

This section provides step-by-step instructions on how to set up the KNIME Analytics Platform SDK. These instructions assume you are using the [Eclipse Git integration (EGit)](https://www.eclipse.org/egit/) as your Git client, which is already contained in the Eclipse installation. However you can use any other Git client as well.

__Note:__ In case you are on Apple Silicon (M1, M2, etc.) read the section "Apple Silicon Support" below.

#### 1. Install Java
* KNIME Analytics Platform uses Java 17 since version 4.6. In case you haven't installed the [OpenJDK 17](https://adoptium.net/), please download and install it, and then restart your computer.
  * for KNIME Analytics Platform 4.4 and 4.5, use [OpenJDK 11](https://adoptium.net/).
  * for KNIME Analytics Platform up to 4.3, use [OpenJDK 8](https://adoptium.net/).


#### 2. Install Eclipse
* Download and install the version of "Eclipse for RCP and RAP Developers" corresponding to the KNIME Analytics Platform
version you want to develop against, according to the table above.
Make sure you are using exactly the version noted, as there are compatibility issues with older/newer versions
(for example, JUnit 5 tests might not be properly recognized).

#### 3. Install Git and Git LFS
If you plan to use the [Eclipse Git integration (EGit)](https://www.eclipse.org/egit/), you may skip this step.

* __Git__: If you want to use Git manually (from the command line or using a [Git client](https://git-scm.com/downloads/guis/)), Git can be downloaded from [here](https://git-scm.com/downloads).
Note: For Linux, Git should already be part of most distributions and therefore does not need to be installed.

* __Git LFS__: Git LFS should already be part of most Git installations. In order to check, try running the following command: ``git lfs``. If the command cannot be found by the system, install Git LFS from [here](https://git-lfs.github.com/).

#### 4. Configure Eclipse
* Start Eclipse.
* Configure the default JRE used by Eclipse to be the one you installed earlier. See the [Eclipse Help](https://help.eclipse.org/2021-03/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Ftasks%2Ftask-add_new_jre.htm&cp%3D1_3_5_1) how to perform this task.
* Clone this repository (``knime-sdk-setup``) and import it into your Eclipse workspace. To do this using EGit, go to ``File → Import → Git → Projects from Git File → Clone URI``. Enter: [https://github.com/knime/knime-sdk-setup](https://github.com/knime/knime-sdk-setup) as URI and proceed. Now,  select the branches you want to clone. Select all branches starting with ``releases/`` and the ``master`` branch. Next, select the initial branch you want to work with (e.g. ``master``, see ``Target Platform`` section above). Finally, Choose ``Import existing Eclipse projects``, select all projects and click __Finish__.
* Double click the target platform definition file (``.target`` files In the imported ``org.knime.sdk.setup`` project) that you want to use for development (the difference between the files is explained in the ``Target Platform`` section above). If in doubt, use ``KNIME-AP-complete.target``.
NOTE: Resolving the target platform the first time takes a while as all dependencies need to be downloaded. You can monitor the progress at the bottom right corner of your Eclipse application. Wait until the target platform is resolved by Eclipse before continuing with the next step.
* Now click __Set as Active Target Platform__ (upper-right corner) and wait until Eclipse has resolved and activated the target platform.
NOTE: Setting the target platform definition may also take a while.

#### 5. Configure API Baseline
Set the API Baseline in Eclipse. The API Baseline ensures that no API breaks are introduced in new code (by showing an error message in the Eclipse editor when an API break is detected by Eclipse):

* Go to ``Window → Preferences → Plug-in Development → API Baseline and Add Baseline...``
* Select ``A target platform``
* Select ``KNIME Analytics Platform (5.x release)`` (which is in the ``org.knime.sdk.setup`` project)
* Click __Refresh__
* Give the baseline a meaningful name (e.g. ``KNIME Analytics Platform (5.x release)``) and click __OK__


#### Apple Silicon Support (M1, M2, etc.)

For Apple Silicon (M1, M2, etc. family of processors), determine whether you want to run native (aarch64) or under
Rosetta 2 (x64/x86\_64) and download the corresponding JDK build _and_ the matching Eclipse build.
Both have to be built for the same architecture, since otherwise some native libraries might not be properly resolved.

__Known Caveats:__

* If you depend on the Tableau extension, you must choose x86\_64, since Tableau does not supply aarch64
  libraries.
  In case you want to use aarch64 and don't need the Tableau extension, you have to remove the corresponding
  entries manually from the target platform definition files.
  The entries you need to remove begin with `org.knime.features.ext.tableau`.
* Some JDK version managers automatically install the build corresponding to the output of `arch`, which is `arm64`
  for native Apple Silicon.
  In order to change that, the current terminal must be run under the specific architecture you want to use.
  For example, to run Terminal.app under Rosetta (x86\_64 emulation), right click on the app bundle → "Get Info",
  and temporarily check "Open using Rosetta".
  The output of `arch` should now show `i386`.

## Launch KNIME Analytics Platform

This section describes how to launch KNIME Analytics Platform from Eclipse after you have set up the KNIME Analytics Platform SDK following the steps from the ``SDK Setup`` section.

Once the SDK Setup is complete, a ``KNIME Analytics Platform`` launch configuration is available in the debug and run configuration dialogs of the Eclipse application. The run configuration starts a new KNIME instance with all KNIME Analytics Platform Extensions in the target platform and your local workspace. This launched instance can be used for testing your custom functionality.

NOTE: If the launch configuration is missing, a preconfigured launch configuration (``.launch`` file) is located in the ``org.knime.sdk.setup`` project. Right click on the file ``KNIME Analytics Platform.launch`` and select ``Run As → KNIME Analytics Platform``.

## Adding Extensions to the Target Definition (Advanced)

This section describes how to add KNIME Extensions to your development setup by modifying the target platform definition file you chose in step 4 of the ``SDK Setup`` section. This is only necessary if you didn't select ``KNIME-AP-complete.target`` or if you want to add Community Contributions or third party extensions.

Do this by double clicking the ``.target`` file you want to modify (located in ``org.knime.sdk.setup``) which opens the Target Definition view of Eclipse. This view shows the name of the Target Definition at the top and list of locations below. The target definition files already contain the KNIME Analytics Platform Update Site entry (in the version of the branch you checked out via Git, see ``Target Platform`` section above), which contains a set of KNIME Analytics Platform plug-ins (depending on the target definition file you chose). These entries can be modified using the buttons on the right:

* __Adding KNIME Analytics Platform Plug-ins__: Select the KNIME Analytics Platform Update Site and click the __Edit__ button on the right. In the dialog window that opens, simply edit the existing entries and add/remove extensions as needed.
* __Adding Third Party Update-Sites__: For additional plug-ins from third party update sites, click the __Add__ button, select Software Site and provide the URL of the site you wish to add. E.g. you can add the update sites from the [Community Contributions Website](https://www.knime.com/community) (e.g. to add KNIME Image Processing plug-ins).

## Explore KNIME Analytics Platform Source Code (Advanced)

This section describes how to add KNIME Extensions to your development setup by importing KNIME Analytics Platform source code projects into your Eclipse workspace. This is necessary if you plan to modify KNIME Analytics Platform source code. If that is not the case and you want to add extensions, see the ``Adding Extensions to the Target Definition`` section.

All publicly available KNIME Analytics Platform source code can be found on the [KNIME GitHub page](https://github.com/knime). To import, simply clone the repository you want (e.g. [https://github.com/knime/knime-core](https://github.com/knime/knime-core) then go to ``File → Import → General → Existing Projects into Workspace``. From the dialog that opens, select the folder of the Git repository you just cloned. This displays a list of all projects that can be imported, in the middle of the dialog., Now select the projects you want to import (if in doubt choose __Select All__) and then click __Finish__. The projects will now appear in the Package Explorer of Eclipse.

IMPORTANT: All of the plug-ins available in your workspace take precedence over the plug-ins in the target platform. E.g. if you imported ``knime-core`` into your workspace and also added ``knime-core`` to the target platform definition, the version from the target platform is ignored. This is especially important to keep in mind if the source code of the plug-in in your workspace differs from the source code obtained via the target platform definition (i.e. the versions differ).

Note: You can safely ignore any warnings and errors related to `pom.xml` files (they only show up if you have installed an extension for Maven development in your Eclipse IDE). They are part of the KNIME internal build system.

## Contribute to KNIME Analytics Platform Source Code

#### 1. Contributor License Agreement
* Please read and sign our [Contributor License Agreement](https://github.com/knime/knime-sdk-setup/blob/master/CONTRIBUTING.MD) such that we can accept your ``Pull Requests``.

#### 2. Configure API Baseline
* Set the API Baseline in Eclipse as described in the ``SDK Setup`` section.

#### 3. Get KNIME source code
* Get the KNIME Analytics Platform source code you want work with as described in the ``Explore KNIME Analytics Platform Source Code`` section.

## Troubleshooting

* In case you experience compile errors such as ``The type org.dmg.pmml.* cannot be resolved`` or ``The import org.dmg.pmml.* cannot be resolved`` please close or remove the project ``org.knime.core.pmml``. This project contains auto-generated classes only and is already part of the target platform.

* Errors encountered while setting or modifying the target platform could result from old version numbers cached by Eclipse. This can be fixed by making sure that all plug-in versions specified in the target definition file are set to ``"0.0.0"``. To do this, double click on the ``.target`` file you want to use which opens the Target Definition view of Eclipse. At the bottom, click on the tab __Source__ which opens the raw file in the Eclipse editor. There, make sure all ``version=`` properties are set to ``"0.0.0"`` (including 	quotation marks).

* If you encounter errors when trying to launch KNIME Analytics Platform from within Eclipse, try:
    1. In Eclipse select `Run → Run Configurations...` ( or `Debug Configurations...`)
    2. Select the Run/Debug configuration you want to launch
    3. Select the `Configuration` tab
    4. Check the `Clear the configuration area before launching` checkbox

    If the problem is resolved, the checkbox can be unchecked upon the next launch

    Alternatively: shutdown Eclipse and start it with the `-clean` option.
## Links
* [Developer Guide](https://docs.knime.com/latest/development_contribute_extension)
* [Noding Guidelines](https://www.knime.com/sites/default/files/inline-images/noding_guidelines.pdf)
* [Pre-built Nightly Builds](https://www.knime.com/form/nightly-build)
* [Examples Repository](https://github.com/knime/knime-examples)

## KNIME Community Contributors
A great place to start your journey of developing and publishing an extension is our [Developer Guide](https://docs.knime.com/latest/development_contribute_extension). If you have questions about development, reach out to us in our [Forum](https://forum.knime.com/c/node-development/21). And if you have already developed an extension, consider [publishing it](https://docs.knime.com/latest/development_contribute_extension/#_publish_your_extension) to make it available to the KNIME Community and be part of our developer community! We are happy to support you.

