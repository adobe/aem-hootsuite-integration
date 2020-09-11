# AEM - Hootsuite Integration

## Introduction

Hootsuite is a Social Media Marketing and Management Platform. This project aims to build an integration layer between AEM and Hootsuite leveraging Hootsuite REST APIs to enable publishing experience fragments and assets via AEM.

## Usage

### Prerequisites/ Initial Setup

Setup is divided into following section:

* [Hootsuite integration setup](./documentation/HOOTSUITE_SETUP.md)

* Adobe Experience Manager Setup.
  * [Cloudservice Setup](./documentation/AEM_CLOUDSERVICES_SETUP.md)
  * [Experience Fragment Setup](./documentation/AEM_XF_SETUP.md)
  * [Assets Setup](./documentation/AEM_ASSETS_SETUP.md)
  
  
## Modules

The main parts of the template are:

* core: Java bundle containing all core functionality like OSGi services and component-related Java code.
* ui.apps: contains the /apps (and /etc) parts of the project, ie JS&CSS clientlibs, components, templates, runmode specific configs
* ui.content: contains sample content using the components from the ui.apps

## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

If you have a running AEM instance you can build and package the whole project and deploy into AEM with

    mvn clean install -PautoInstallPackage

Or to deploy it to a publish instance, run

    mvn clean install -PautoInstallPackagePublish

Or alternatively

    mvn clean install -PautoInstallPackage -Daem.port=4503

Or to deploy only the bundle to the author, run

    mvn clean install -PautoInstallBundle

## Maven settings

The project comes with the auto-public repository configured. To setup the repository in your Maven settings, refer to:

    http://helpx.adobe.com/experience-manager/kb/SetUpTheAdobeMavenRepository.html

### Contributing

Contributions are welcomed! Read the [Contributing Guide](./.github/CONTRIBUTING.md) for more information.

### Licensing

This project is licensed under the Apache V2 License. See [LICENSE](LICENSE) for more information.
