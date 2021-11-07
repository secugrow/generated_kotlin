package io.secugrow.step_definitions

import io.secugrow.pageobjects.WikipediaPage
import io.secugrow.pageobjects.PageUrls


class NavigationSteps(testDataContainer: TestDataContainer) : AbstractStepDefs(testDataContainer) {

    init {
        Given("the start page is loaded") {
            getWebDriverSession().gotoUrl(PageUrls.HOME, WikipediaPage::class, testDataContainer)
        }

        When("{string} opens software testing") { userid: String ->
            getWebDriverSession(userid).gotoUrl(PageUrls.SOFTWARETEST, WikipediaPage::class, testDataContainer)
        }

    }

}
