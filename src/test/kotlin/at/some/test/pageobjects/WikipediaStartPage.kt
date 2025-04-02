package at.some.test.pageobjects;

import at.some.test.driverutil.WebDriverSession
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions

class WikipediaStartPage(session: WebDriverSession) : MainPage(session) {

    fun isSearchbarPresent(): Boolean {
        return webDriver.findElements(By.id("searchInput")).size >= 1
    }

    fun submitSearch(): WikipediaContentPage {
        wdwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))).click()
        return WikipediaContentPage(session)
    }

    fun searchFor(searchstring: String?): WikipediaContentPage {
        getSearchbar().sendKeys(searchstring)
        submitSearch()
        return WikipediaContentPage(session)
    }

    fun getSearchbar(): WebElement {
        return wdwait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput")))
    }
}
