# WEare social network

**Project Description**

The task is to develop a SOCIAL NETWORK web application. The SOCIAL NETWORK application enables users to: connect with people; create, comment and like posts; get a feed of the newest/most relevant posts of your connections. This is a social network for exchanging of services allowing the users to set their profession/skills and make posts with the services they offer (e.g. online lawyer consultations).

## Project Structure
The project is structured as follows:
```bash
📦 RESTAssured
├─ .allure
├─ allure-results
├─ src
│  ├─ main
│  │  ├─ java
│  │  │  ├─ api
│  │  │  │  ├─ models
│  │  │  │  │  ├─ Comment
│  │  │  │  │  ├─ Connection
│  │  │  │  │  ├─ Post
│  │  │  │  │  ├─ Skill
│  │  │  │  │  ├─ User
│  │  │  │  │  └─ UserC
│  │  │  │  ├─ services
│  │  │  │  │  ├─ CommentService
│  │  │  │  │  ├─ ConnectionService
│  │  │  │  │  ├─ PostService
│  │  │  │  │  ├─ SkillService
│  │  │  │  │  ├─ UserService
│  │  │  │  │  └─ UserServiceC
│  │  │  │  └─ utils
│  │  │  │     ├─ AssertHelper
│  │  │  │     ├─ Constants
│  │  │  │     ├─ Endpoints
│  │  │  │     ├─ Helper
│  │  │  │     └─ JSONRequests
│  │  │  └─ test
│  │  │     ├─ java
│  │  │     │  ├─ base
│  │  │     │  │  └─ BaseTestSetup
│  │  │     │  └─ tests
│  │  │     │     ├─ CommentTest
│  │  │     │     ├─ ConnectionTests
│  │  │     │     ├─ PostTest
│  │  │     │     ├─ SkillTests
│  │  │     │     └─ UserTests
│  │  └─ resources
│  │     └─ main-suite.xml
└─ pom.xml

📦 Selenium WebDriver
├─ src
│  ├─ main
│  │  ├─ java
│  │  │  └─ testframework
│  │  │     ├─ pages
│  │  │     │  ├─ BasePage
│  │  │     │  ├─ CustomWebDriverManager
│  │  │     │  ├─ Driver
│  │  │     │  ├─ PropertiesManager
│  │  │     │  ├─ RandomGenerator
│  │  │     │  ├─ UserActionNonImplemented
│  │  │     │  ├─ UserActions
│  │  │     │  └─ Utils
│  │  │     └─ weare
│  │  │        ├─ AdminPage
│  │  │        ├─ BasePage
│  │  │        ├─ CommentPage
│  │  │        ├─ HomePage
│  │  │        ├─ LoginPage
│  │  │        ├─ PersonalProfilePage
│  │  │        ├─ PostPage
│  │  │        ├─ RegistrationPage
│  │  └─ resources
│  │     ├─ images
│  │     │  ├─ bug-photo.png
│  │     │  ├─ bug-photo-2.jpg
│  │     │  └─ panoramic.jpg
│  │     ├─ log4j.properties
│  │     ├─ log4j.xml
│  │     └─ log4j2.xml
│  └─ test
│     ├─ java
│     │  └─ weare
│     │     ├─ AdminTests
│     │     ├─ BaseTest
│     │     ├─ CommentTests
│     │     ├─ HomePageTests
│     │     ├─ LoginTest
│     │     ├─ PersonalProfileTests
│     │     ├─ PostTests
│     │     └─ RegistrationTest
│     └─ resources
│        ├─ mappings
│        │  └─ ui_map.properties
│        ├─ config.properties
│        └─ output.log
├─ pom.xml
├─ .gitignore
└─ README.md


```

## Installation Steps

In order to use the framework:

1. [Fork](https://github.com/Final-Project-Telerik-Academy/WEare.git) the repository.
2. Clone, i.e, download your copy of the repository to your local machine using
```
git clone https://github.com/[your_username]/WEare.git
```
3. Import the project in [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).



## Languages and Frameworks

The project uses the following:

- *[Java 11](https://openjdk.java.net/projects/jdk/11/)* as the programming language.
- *[Maven](https://maven.apache.org)* as project management and comprehension tool.
- *[Selenium WebDriver](https://www.selenium.dev/)* as the web browser automation framework using the Java binding.
- *[jUnit](https://junit.org/junit5/)* as the testing framework.
- *[Lombok](https://projectlombok.org/)* to generate getters.
- *[Allure Reports](https://allurereport.org)* as the test reporting strategy for REST Assured.

- *[IntelliJ IDEA](https://www.jetbrains.com/idea/)* as the IDE.


## Requirements


## Reporting
### Newman
A *[Newman](https://www.npmjs.com/package/newman-reporter-htmlextra)* HTML reporter that has been extended to include the separation of the iteration runs so these are no longer aggregated together and also some additional handlebars helpers to enable users to create better custom templates.
#### Install
The reporter works as a plugin with Newman so ensure that you have already installed that package globally, using *npm install -g newman.*
To globally install the htmlextra package:
```
npm install -g newman-rep
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

### Allure Reports
Allure Report is a flexible, lightweight multi-language test report tool that not only shows a very concise representation of what has been tested in a neat web report form, but also allows for a deeper test debugging by providing logs, steps, fixtures, attachments, timings, and more. It's designed to create clear and comprehensive test reports that are easy to read and understand.

For the *installation* of Allure Reports, follow these detailed instructions:

https://www.swtestacademy.com/allure-report-junit/