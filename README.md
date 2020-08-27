# AEM - Hootsuite Integration

## Introduction

Hootsuite is a Social Media Marketing and Management Platform. This project aims to build an integration layer between AEM and Hootsuite leveraging Hootsuite REST APIs to enable publishing experience fragments and assets via AEM.

## Usage

### Prerequisites/ Initial Setup

* Hootsuite integration setup
   + Login to [Hootsuite Developer Portal](https://hootsuite.com/developers/my-apps).
   + Create a Developer App. Once the App is created contact the Hootsuite support for activation of REST API support. Also, Callback URL for the integration app is configured by the Hootsuite support team. This Integration expects the callback URL in this format - **https://{{AEM_AUTHOR_HOSTNAME}}/bin/services/authorize**


* AEM Setup
  + Install the [Latest Integration Package](https://github.com/adobe/aem-hootsuite-integration/releases/download/1.0.0/hootsuite-integration.all-1.0.0.zip)
  + Configure the Hootsuite cloud service
    + Navigate to **Tools -> Cloud Services -> Hootsuite**
    + Create a new Cloud Service configuration. Enter the details as:
      + **Title**: Title of your configuration e.g. Hootsuite Integration
      + **OAuth Endpoint Host**: Configure the OAuth Endpoint - https://platform.hootsuite.com/oauth2/token
      + **OAuth Scope**: Enter the OAuth Scope - offline
      + **OAuth Grant Type** : Enter the grant type - authorization_code
      + **Redirect URL**: Enter the Redirect callback URL - Same as configured in Hootsuite Integration App.
      + **Client ID**: Your Hootsuite OAuth App Client ID.
      + **Client Secret**: Your Hootsuite OAuth App Client Secret.
    + Click on "Connect" button - This would estable connectivity between AEM and Hootsuite using the aforesaid integration credentials.
    + Finally, Save and close the configuration.
    + Subsequently, this cloud configuration may be applied on Experience Fragments or Assets
    + Configure Supported Components/Resource Types: Users may configure the AEM Components supported for Hootsuite content authoring by configuring the [OSGI Configuration](http://localhost:4502/system/console/configMgr/com.adobe.core.hootsuite.integration.internal.services.MessageServiceImpl) in the format: <RESOURCE_TYPE>|<CONTENT_TYPE_IMAGE_TEXT>|<PROPERTY_NAME> e.g. hootsuite/components/content/image|asset|fileReference, hootsuite/components/content/text|text|text
  
This integration supports publishing Experience Fragments and Assets to Hootsuite. As an Example, following Experience Fragment template(s), which supports Hootsuite Integration, has been provided for user reference
+ /conf/Hootsuite/settings/wcm/templates/hootsuite-xf-template

### Publishing the content

Content authored as Experience Fragments or Assets in Adobe Experience Manager can be published using the following workflows:

* **Experience Fragment Publish Workflow**: /conf/global/settings/workflow/models/hootsuite-asset-publish-workflow
* **Asset Publish Workflow**: /conf/global/settings/workflow/models/hootsuite-xf-publish-workflow

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
