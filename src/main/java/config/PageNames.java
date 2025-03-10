//"Video Tutorials" is not included as it redirects to youtube.com

package config;

public enum PageNames {
    MAIN("https://automationexercise.com/"),
    PRODUCTS("https://automationexercise.com/products"),
    CART("https://automationexercise.com/view_cart"),
    LOGIN("https://automationexercise.com/login"),
    TEST_CASES("https://automationexercise.com/test_cases"),
    API_TESTING("https://automationexercise.com/api_list"),
    CONTACT_US("https://automationexercise.com/contact_us");

    final String url;

    PageNames(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
