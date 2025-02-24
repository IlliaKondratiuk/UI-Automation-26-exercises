//"Video Tutorials" is not included as it redirects to youtube.com

package config;

public enum PageNames {
    MAIN("https://automationexercise.com/"),
    PRODUCTS("https://automationexercise.com/products"),
    CART("https://automationexercise.com/view_cart"),
    SIGNUPLOGIN("https://automationexercise.com/login"),
    TESTCASES("https://automationexercise.com/test_cases"),
    APITESTING("https://automationexercise.com/api_list"),
    CONTACTUS("https://automationexercise.com/contact_us");

    final String url;

    PageNames(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
