package at.some.test.pageobjects

import at.some.test.driverutil.WebDriverSession
import at.some.test.pageobjects.AbstractPage
import at.some.test.pageobjects.MainPage
import org.openqa.selenium.By

class WikipediaPage(session: WebDriverSession) : MainPage(session)  {

    fun isSearchbarPresent(): Boolean {
        return webDriver.findElements(By.id("searchInput")).size >= 1
    }


}
