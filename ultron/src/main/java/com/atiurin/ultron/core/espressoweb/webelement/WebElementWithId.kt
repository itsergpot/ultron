package com.atiurin.ultron.core.espressoweb.webelement

import android.view.View
import androidx.test.espresso.web.matcher.DomMatchers.elementById
import androidx.test.espresso.web.model.ElementReference
import androidx.test.espresso.web.model.WindowReference
import androidx.test.espresso.web.webdriver.Locator
import com.atiurin.ultron.core.config.UltronConfig
import com.atiurin.ultron.core.espressoweb.operation.WebInteractionOperation
import com.atiurin.ultron.core.espressoweb.operation.WebOperationResult
import com.atiurin.ultron.custom.espresso.matcher.ElementWithAttributeMatcher.Companion.withAttribute
import org.hamcrest.Matcher
import org.w3c.dom.Document

class WebElementWithId(
    override val value: String,
    override val webViewMatcher: Matcher<View> = UltronConfig.Espresso.webViewMatcher,
    override val elementReference: ElementReference? = null,
    override val windowReference: WindowReference? = null
) : WebElement(Locator.ID, value, webViewMatcher, elementReference, windowReference) {
    fun hasAttribute(
        attributeName: String,
        attributeValueMatcher: Matcher<String>,
        timeoutMs: Long = UltronConfig.Espresso.ACTION_TIMEOUT,
        resultHandler: (WebOperationResult<WebInteractionOperation<Document>>) -> Unit =
            UltronConfig.Espresso.WebInteractionOperationConfig.resultHandler as (WebOperationResult<WebInteractionOperation<Document>>) -> Unit
    ) = apply {
        this.hasElementAttribute(
            attributeName, attributeValueMatcher, timeoutMs, resultHandler, elementById(
                value, withAttribute(
                    attributeName,
                    attributeValueMatcher
                )
            )
        )
    }
}