package at.some.test.driverutil.strategy

import at.some.test.driverutil.factory.AppiumAndroidWebDriverFactory
import at.some.test.driverutil.factory.ChromeMobileEmulationWebDriverFactory
import at.some.test.driverutil.factory.ChromeWebDriverFactory
import at.some.test.driverutil.factory.ChromiumWebDriverFactory
import at.some.test.driverutil.DriverType
import at.some.test.driverutil.factory.EdgeWebDriverFactory
import at.some.test.driverutil.factory.FirefoxWebDriverFactory
import at.some.test.driverutil.factory.RemoteAndroidWebDriverFactory
import at.some.test.driverutil.factory.RemoteChromeMobileEmulationWebDriverFactory
import at.some.test.driverutil.factory.RemoteChromeMobileWebDriverFactory
import at.some.test.driverutil.factory.RemoteChromeWebDriverFactory
import at.some.test.driverutil.factory.RemoteFirefoxWebDriverFactory
import org.openqa.selenium.WebDriver
import java.time.Duration
import java.time.temporal.ChronoUnit

/**
 * Context class for the WebDriver strategy pattern.
 * This class is responsible for selecting and executing the appropriate WebDriverStrategy
 * based on the driver type specified in the system properties.
 */
class DriverContext {
    private var strategy: WebDriverStrategy? = null

    /**
     * Sets the WebDriverStrategy to be used.
     * @param strategy The WebDriverStrategy to use
     */
    fun setStrategy(strategy: WebDriverStrategy) {
        this.strategy = strategy
    }

    /**
     * Creates a WebDriver instance using the current strategy.
     * @return WebDriver instance
     * @throws IllegalStateException if no strategy has been set
     */
    private fun createWebDriver(): WebDriver {
        requireNotNull(strategy) { "No WebDriverStrategy has been set" }
        return strategy!!.createDriver()
        // Note: Timeouts are now configured in the WebDriverFactory
    }

    /**
     * Evaluates the necessary driver type at runtime and creates a WebDriver instance.
     * @return WebDriver instance
     */
    fun createWebDriverFromSystemProperties(): WebDriver {
        val browserName = System.getProperty("browser", DriverType.CHROME.toString()).uppercase()
        val driverType = DriverType.valueOf(browserName)

        when (driverType) {
            DriverType.CHROME -> {
                setStrategy(WebDriverFactoryAdapter(ChromeWebDriverFactory()))
            }
            DriverType.CHROMIUM -> {
                setStrategy(WebDriverFactoryAdapter(ChromiumWebDriverFactory()))
            }
            DriverType.FIREFOX -> {
                setStrategy(WebDriverFactoryAdapter(FirefoxWebDriverFactory()))
            }
            DriverType.EDGE -> {
                setStrategy(WebDriverFactoryAdapter(EdgeWebDriverFactory()))
            }
            DriverType.LOCAL_CHROME_MOBILE_EMULATION -> {
                setStrategy(WebDriverFactoryAdapter(ChromeMobileEmulationWebDriverFactory()))
            }
            DriverType.REMOTE_CHROME_MOBILE_EMULATION -> {
                setStrategy(WebDriverFactoryAdapter(RemoteChromeMobileEmulationWebDriverFactory()))
            }
            DriverType.REMOTE_CHROME -> {
                setStrategy(WebDriverFactoryAdapter(RemoteChromeWebDriverFactory()))
            }
            DriverType.REMOTE_FIREFOX -> {
                setStrategy(WebDriverFactoryAdapter(RemoteFirefoxWebDriverFactory()))
            }
            DriverType.REMOTE_CHROME_MOBILE -> {
                setStrategy(WebDriverFactoryAdapter(RemoteChromeMobileWebDriverFactory()))
            }
            DriverType.REMOTE_ANDROID -> {
                setStrategy(WebDriverFactoryAdapter(RemoteAndroidWebDriverFactory()))
            }
            DriverType.APPIUM_ANDROID_DEVICE -> {
                setStrategy(WebDriverFactoryAdapter(AppiumAndroidWebDriverFactory()))
            }
        }

        return createWebDriver()
    }

    /**
     * @deprecated Use createWebDriverFromSystemProperties() instead. The scenarioId parameter is not used.
     */
    @Deprecated("Use createWebDriverFromSystemProperties() instead. The scenarioId parameter is not used.", ReplaceWith("createWebDriverFromSystemProperties()"))
    fun createWebDriver(scenarioId: String): WebDriver {
        return createWebDriverFromSystemProperties()
    }

    /**
     * Gets the underlying WebDriverFactory instance from the current strategy.
     * This allows access to factory-specific features like cleanup() and configurable timeouts.
     * 
     * @return The WebDriverFactory instance, or null if no strategy has been set or the strategy is not a WebDriverFactoryAdapter
     */
    fun getFactory(): at.some.test.driverutil.factory.WebDriverFactory? {
        return (strategy as? WebDriverFactoryAdapter)?.getFactory()
    }
}
