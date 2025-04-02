package at.some.test.stepdefinitions

import at.some.test.pageobjects.WikipediaStartPage
import at.some.test.pageobjects.PageUrls

class NavigationSteps(testDataContainer: TestDataContainer) : AbstractStepDefs(testDataContainer) {

    init {
        Given("the start page is loaded") {
            getWebDriverSession().gotoUrl(PageUrls.HOME, WikipediaStartPage::class, testDataContainer)
        }

        When("{string} opens software testing") { userid: String ->
            getWebDriverSession(userid).gotoUrl(PageUrls.SOFTWARETEST, WikipediaStartPage::class, testDataContainer)
        }

    }

}