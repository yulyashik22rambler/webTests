# UI test example

Tests for Web site google.com

## Quick Start

1. Install allure.
2. Clone this repository using `hg clone https://bitbucket.org/juju007/webtestsexamples` or download source code from [this page](https://bitbucket.org/juju007/webtestsexamples/downloads/).
3. Uncomment necessary propperties in file configuration.properties like type of system, type of browser and path to webdriver.
4. Download chrome drivers from [here](https://chromedriver.storage.googleapis.com/index.html?path=2.44/) or load  the latest versions and put in into folders webTestes/resource/windows/ or webTestes/resource/linux/.
5. Download gicko drivers from [here](https://github.com/mozilla/geckodriver/releases) to run firefox driver and put into folders webTestes/resource/windows/ or webTestes/resource/linux/. 
6. Default setting and driver are already predefined for windows and chrome browser. 
7. From project dir make  "mvn clean test && allure serve".
# webTests
