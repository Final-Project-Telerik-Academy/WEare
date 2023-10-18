# WEare Social Network

## Introduction

This README serves as a guide for conducting testing procedures related to the WeAreSocialApp. Within this document, you will discover comprehensive information on how to execute various testing approaches, including manual testing, API testing, and UI testing. Our central objective is to provide precise and actionable guidance to uphold the quality, performance, and reliability standards of the WeAreSocialApp

**Project Description** 

The task is to test a WEAre Social Network web application which is a social media platform designed to connect individuals based on their skills, services, and shared interests. The Social Network application enables users to connect with people, create posts and comment and like posts, to get a feed of the newest/most relevant posts of your connections. This is a social network for exchanging of services allowing the users to set their profession/skills and make posts with the services they offer (e.g. online lawyer consultations). Testing will verify that the application's features and functionalities are working as intended. Our focus is on validating the correctness of the application's behavior and ensuring that it aligns with the specified requirements.

## Table of Content

1. [Manual Testing](#manual-testing)
2. [API Testing with Postman](#api-testing-with-postman)
3. [API Testing with Rest Assured](#api-testing-with-rest-assured)
4. [UI Testing with Selenium WebDriver](#ui-testing-with-selenium)
5. [Reporting](#reporting)
6. [Contributors](#contributors)
7. [Documentation](#documentation)

## Manual Testing

In our quality assurance process, we employ the use of Xray within Jira to guide and document our manual testing procedures. While our high-level tests provide a comprehensive overview of the Weare application's core functionality and user interactions, we also maintain a detailed set of test cases. These detailed test cases offer a thorough examination of the application's features and functionalities. They are designed to ensure that every element works precisely as intended. This level of detail allows us to detect and rectify any anomalies, ensuring a seamless and reliable experience for our users.

## API Testing with Postman

The API testing with Postman has allowed us to thoroughly assess and validate the effectiveness of the Weare application's APIs. With this testing approach, we have examined various API endpoints to ensure they perform precisely as intended, handling requests and responses accurately.

In this process, we have used the developers' API documentation, which can be accessed through the provided link - *[API Documentation](http://localhost:8081/swagger-ui.html)*.

## API Testing with Rest Assured

With a programmatic and code-driven approach using Java and JUnit as a library, we have systematically verified the correctness of API behaviors, ensuring they align with the specified requirements.

The same API documentation was used for the testing purpose.

## UI Testing with Selenium

Our UI testing strategy utilizes Selenium WebDriver in combination with Maven and JUnit, following a page-oriented model. This approach allows us to comprehensively validate the user interface and user experience of the Weare application.

With Selenium WebDriver, we automate the interaction with the application's web pages, simulating user behavior to verify the correctness of page elements, user flows, and interactions. Maven assists in building and managing the testing environment, while JUnit serves as a reliable testing framework to structure and execute our UI tests.

By using the page-oriented model, we organize our UI tests based on individual pages or components of the application, enabling a systematic and maintainable testing process.



## Project Structure
The project is structured as follows:
```bash
.
├── RESTAssured
│   ├── .allure
│   ├── allure-results
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   ├── api
│   │   │   │   │   ├── models
│   │   │   │   │   │   ├── BaseModel.java
│   │   │   │   │   │   ├── Comment.java
│   │   │   │   │   │   ├── Connection.java
│   │   │   │   │   │   ├── Post.java
│   │   │   │   │   │   ├── Skill.java
│   │   │   │   │   │   └── User.java
│   │   │   │   │   ├── services
│   │   │   │   │   │   ├── CommentService.java
│   │   │   │   │   │   ├── ConnectionService.java
│   │   │   │   │   │   ├── PostService.java
│   │   │   │   │   │   ├── SkillService.java
│   │   │   │   │   │   └── UserService.java
│   │   │   │   │   └── utils
│   │   │   │   │       ├── AssertHelper.java
│   │   │   │   │       ├── Constants.java
│   │   │   │   │       ├── Endpoints.java
│   │   │   │   │       ├── Helper.java
│   │   │   │   │       └── JSONRequests.java
│   │   │   └── test
│   │   │       ├── java
│   │   │       │   ├── base
│   │   │       │   │   └── BaseTestSetup.java
│   │   │       │   └── tests
│   │   │       │       ├── CommentTest.java
│   │   │       │       ├── ConnectionTests.java
│   │   │       │       ├── PostTest.java
│   │   │       │       ├── SkillTests.java
│   │   │       │       └── UserTests.java
│   │   └── resources
│   │       └── main-suite.xml
│   ├── target
│   │   └── jmeter.log
│   └── pom.xml

├── Selenium WebDriver
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   ├── testframework
│   │   │   │   │   ├── pages
│   │   │   │   │   │   ├── BasePage.java
│   │   │   │   │   │   ├── CustomWebDriverManager.java
│   │   │   │   │   │   ├── Driver.java
│   │   │   │   │   │   ├── PropertiesManager.java
│   │   │   │   │   │   ├── RandomGenerator.java
│   │   │   │   │   │   ├── UserActionNonImplemented.java
│   │   │   │   │   │   └── UserActions.java
│   │   │   │   │   ├── Utils.java
│   │   │   │   │   └── weare
│   │   │   │   │       ├── AdminPage.java
│   │   │   │   │       ├── BasePage.java
│   │   │   │   │       ├── CommentPage.java
│   │   │   │   │       ├── HomePage.java
│   │   │   │   │       ├── LoginPage.java
│   │   │   │   │       ├── PersonalProfilePage.java
│   │   │   │   │       ├── PostPage.java
│   │   │   │   │       └── RegistrationPage.java
│   │   │   └── resources
│   │   │       ├── images
│   │   │       │   ├── bug-photo.png
│   │   │       │   ├── bug-photo-2.jpg
│   │   │       │   └── panoramic.jpg
│   │   │       ├── log4j.properties
│   │   │       ├── log4j.xml
│   │   │       └── log4j2.xml
│   │   └── test
│   │       ├── java
│   │       │   └── weare
│   │       │       ├── AdminTests.java
│   │       │       ├── BaseTest.java
│   │       │       ├── CommentTests.java
│   │       │       ├── HomePageTests.java
│   │       │       ├── LoginTest.java
│   │       │       ├── PersonalProfileTests.java
│   │       │       ├── PostTests.java
│   │       │       └── RegistrationTest.java
│   │       └── resources
│   │           ├── mappings
│   │           │   └── ui_map.properties
│   │           └── config.properties
│   └── pom.xml
├── .gitignore
└── README.md

```
## Prerequisites
- Java SDK 11
- jUnit 5
- Windows, macOS, or Linux operating system

## Installation Steps

In order to use the framework:

1. Install Git:

If you haven't installed Git on your system, you can download and install it from here.

Open a terminal or command prompt.
1. Navigate to the directory where you want to clone the repository.
2. Clone the Repository., i.e, download your copy of the repository to your local machine using
   - Open a terminal or command prompt.
   - Navigate to the directory where you want to clone the repository.
   - Run the following command:
```
git clone https://github.com/Final-Project-Telerik-Academy/WEare.git
```
3. Explore the project structure and files using your preferred code editor or IDE.
4. Start and access the application  from WeareApplication.class with your IDE, it will typically be accessible via a web browser. The default address is usually http://localhost:8081/, but this might vary based on the project configuration.
## Languages and Frameworks

The project uses the following:

- *[Java 11](https://openjdk.java.net/projects/jdk/11/)* as the programming language.
- *[Maven](https://maven.apache.org)* as project management and comprehension tool.
- *[Selenium WebDriver](https://www.selenium.dev/)* as the web browser automation framework using the Java binding.
- *[jUnit](https://junit.org/junit5/)* as the testing framework.
- *[Lombok](https://projectlombok.org/)* to generate getters.
- *[Allure Reports](https://allurereport.org)* as the test reporting strategy for REST Assured and Selenium.

- *[IntelliJ IDEA](https://www.jetbrains.com/idea/)* as the IDE.

<div align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="40" alt="java logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/intellij/intellij-original.svg" height="40" alt="intellij logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jira/jira-original.svg" height="40" alt="jira logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/github/github-original.svg" height="40" alt="github logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" height="40" alt="mysql logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/selenium/selenium-original.svg" height="40" alt="selenium logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/chrome/chrome-original.svg" height="40" alt="chrome logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/windows8/windows8-original.svg" height="40" alt="windows8 logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/linux/linux-original.svg" height="40" alt="linux logo"  />
</div>

## Requirements


## Reporting

Throughout our testing procedures, we generate comprehensive reports that include the results of manual testing, API testing, and UI testing.
These reports provide a clear and concise summary of the test outcomes, highlighting any issues or defects discovered during the testing process.

### Newman
A *[Newman](https://www.npmjs.com/package/newman-reporter-htmlextra)* HTML reporter that has been extended to include the separation of the iteration runs so these are no longer aggregated together and also some additional handlebars helpers to enable users to create better custom templates.
#### Install
The reporter works as a plugin with Newman so ensure that you have already installed that package globally, using *npm install -g newman.*
To globally install the htmlextra package:
```
npm install -g newman-rep
```
To globally install the htmlextra package:
```
npm install -g newman-reporter-htmlextra
```

#### Usage
1. Run the Postman collection specified in collection.json.
2. Use the environment variables from environment.json.
3. Generate a report using the htmlextra reporter, which provides an enhanced HTML report of the run.
```
newman run collection.json -e environment.json -r htmlextra
```

The command runs the Postman collection from collection.json 10 times using variables from environment.json. It produces an enhanced HTML report with a progress bar. Each request has a 3-second timeout, and scripts have a 1-second timeout. If exceeded, they're terminated as failures.
```
newman run collection.json -e environment.json -r htmlextra --reporter-htmlextra-displayProgressBar --iteration-count 10 --timeout-request 3000 --timeout-script 1000
```
You can execute Newman directly from these two script files in Postman folder in the project:

_For Linux:_
1. Navigate to the "Postman" directory within the project.
2. Open a terminal in this directory.
3. Write in terminal one of these commands: 

```
./run.sh

```
or
```
./run10.sh
```
- **run.sh** - This script will execute the basic command.

- **run10.sh** - This script will execute 10 times the command with additional parameters.

4. Тhe test report will appear in a folder named 'Newman' in the same directory.

_For Windows:_

Double-click on the desired batch file to run it. The pause command at the end of each batch file will keep the command prompt window open so you can view the results.

Alternatively, you can also run the batch files from the Command Prompt. Navigate to the directory containing the batch files and type the name of the batch file you want to run, followed by pressing Enter.


```
run.bat
```
or
```
run10.bat
```
### Allure Reports
Allure Report is a flexible, lightweight multi-language test report tool that not only shows a very concise representation of what has been tested in a neat web report form, but also allows for a deeper test debugging by providing logs, steps, fixtures, attachments, timings, and more. It's designed to create clear and comprehensive test reports that are easy to read and understand.

For the *installation* of Allure Reports, follow these detailed instructions:

https://www.swtestacademy.com/allure-report-junit/

## Contributors

- Rositsa Markova: [GitHub profile](https://github.com/rosicatm)
- Kristina Duhneva: [GitHub profile]()
- Filip Gargov: [GitHub profile]()

## Documentation

Here are the links to our documentation resources:

- [Test Plan](https://docs.google.com/document/d/1TiwLMgM4jrnX9gJTI9wzVqLraTC7h764croxKT1-zdM/edit)
- [Test Case Template](https://docs.google.com/document/d/1sGhZSPXvgvnOjZosZUeBZyZnDNZTzJjKANeSIV3z-dc/edit)
- [BUG Template](https://docs.google.com/document/d/1GpPPaK76lmC1_k1vc4E53eV829WNz1Ib69nuoVRwCrA/edit)
- [High-Level Test Cases](https://docs.google.com/document/d/1QNIOPp_iGTjVdGASaATBMkjdmaiSwjubrC1VHQrnPOM/edit)
- [Exploratory Testing Diagram]()

<p align="left">
✨ Test Plane-<br>
📚 Test Case Template-<br>
🎯 Bug Template<br>
🎲 High-Level Test Cases</p>

###