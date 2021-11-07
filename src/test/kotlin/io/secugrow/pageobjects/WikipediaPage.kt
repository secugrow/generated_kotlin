package io.secugrow.pageobjects

import io.secugrow.driverutil.WebDriverSession
import io.secugrow.pageobjects.AbstractPage
import io.secugrow.pageobjects.MainPage
import org.openqa.selenium.By

class WikipediaPage(session: WebDriverSession) : MainPage(session)  {

    fun isSearchbarPresent(): Boolean {
        return webDriver.findElements(By.id("searchInput")).size >= 1
    }


}
