package $

import org.openqa.selenium.By

{package}.pageobjects

import at.some.test.driverutil.WebDriverSession
import at.some.test.pageobjects.AbstractPage
import at.some.test.pageobjects.MainPage
import org.openqa.selenium.By

class WikipediaPage(session: WebDriverSession) : MainPage(session)  {

    fun isSearchbarPresent(): Boolean {
        return webDriver.findElements(By.id("searchInput")).size >= 1
    }

    fun submitSearch(): WikiPediaSearchresultPage {
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("cmdSearch"))).click()
        return WikiPediaSearchresultPage(session)
    }

    fun searchFor(searchstring: String?): WikipediaContentPage {
        getSearchbar().sendKeys(searchstring)
        submitSearch()
        return WikipediaContentPage(session)
    }


}
