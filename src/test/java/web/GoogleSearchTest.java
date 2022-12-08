package web;

import org.assertj.core.api.Assertions;
import org.springframework.context.annotation.Description;
import org.testng.annotations.Test;
import web.basic.BaseTest;

public class GoogleSearchTest extends BaseTest {

    private static final String PAGE_URL = "https://www.google.co.il/";
    private static final String SEARCH_INPUT = "Java";
    private static final String SEARCH_NEGATIVE_INPUT = "Interview";
    private static int SEARCH_RESULTS_COUNT;
    private GoogleDefaultPage googleDefaultPage;
    private GoogleSearchResultPage googleSearchResultPage;

    @Description("Search 'Java' in Google search")
    @Test
    public void searchTest() {
        getGooglePage();
        searchInput();
        printSearchResult();
        verifyFirstResult();
        verifyLastResult();
    }

    @Description("Navigate https://www.google.co.il/ Page")
    public void getGooglePage() {
        webDriver.get(PAGE_URL);
    }

    @Description("Type “Java” into search field And Press on “Google Search” button")
    private void searchInput() {
        googleDefaultPage = new GoogleDefaultPage();
        googleDefaultPage.setSearchInput(SEARCH_INPUT);
        googleSearchResultPage = googleDefaultPage.clickOnSearchButton();
    }

    @Description("Print only search result links")
    private void printSearchResult() {
        SEARCH_RESULTS_COUNT = googleSearchResultPage.getSearchResultsCount();
        googleSearchResultPage.printSearchResults();
    }

    @Description("Verify first search result contains the word “Java”")
    private void verifyFirstResult() {
        Assertions.assertThat(googleSearchResultPage.validateResultExist(SEARCH_INPUT, 1))
                .withFailMessage("Word %s is not in the %s result ", SEARCH_INPUT, 1)
                .isTrue();
    }

    @Description("Verify last search result doesn’t contain the word “Interview”")
    private void verifyLastResult() {
        Assertions.assertThat(googleSearchResultPage.validateResultExist(SEARCH_NEGATIVE_INPUT,
                SEARCH_RESULTS_COUNT))
                .withFailMessage("Word %s is not in the %s result ", SEARCH_NEGATIVE_INPUT,
                        SEARCH_RESULTS_COUNT)
                .isFalse();
    }

}
