const PropertiesReader = require('properties-reader');
const properties = PropertiesReader('../src/main/resources/login.properties');
const username = properties.get("username");
const password = properties.get("password");
const url = properties.get("url");

describe("Login", () => {
    it("Should login successfully", () => {
        browser.driver.get(url);
        browser.driver.findElement(by.name("username")).sendKeys(username);
        browser.driver.findElement(by.name("pw")).sendKeys(password);
        browser.driver.findElement(by.className("btn btn-primary col-md-2 col-md-offset-10")).click();
        expect(browser.driver.getCurrentUrl()).toEqual("https://dev-caliber.revature.tech/caliber/#/routing");
    });
});