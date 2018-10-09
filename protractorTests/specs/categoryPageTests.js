describe("Category Suite", () => {
    it("Should be logged in", () => {
        browser.wait(protractor.ExpectedConditions.elementToBeClickable(element(
            by.css("body > div > ui-view > nav > div > ul.nav.navbar-nav.navbar-right > li:nth-child(1) > a"))),10000);
        expect(browser.getCurrentUrl()).toEqual("https://dev-caliber.revature.tech/caliber/#/vp/home");
    });
    it("Should go to the categories page", () => {
        element(by.css("li[role='presentation']")).click();
        element(by.css("a[ui-sref='vp.category']")).click();
        expect(browser.getCurrentUrl()).toEqual("https://dev-caliber.revature.tech/caliber/#/vp/category");
    });
    it("Should add a category", () => {
        element(by.css("body > div > ui-view > ui-view > div:nth-child(1) > div > div > div > ul > li > a")).click();
        browser.wait(protractor.ExpectedConditions.elementToBeClickable(element(by.id("categoryName"))), 5000);
        element(by.id("categoryName")).sendKeys("Node.js");
        element(by.css("#addCategoryModal > div > div > div.modal-body > div > div.modal-footer > input")).click();
    });
    it("Should change all the categories active status", () => {
        element.all(by.tagName("tbody")).then((table) => {
            for(let j = 0; j < table.length; j++){ // for every table
            table[j].all(by.tagName("tr")).then((links) => { // for every row
                for(let i = 0; i < links.length; i ++){
                    // wait for div
                    browser.wait(protractor.ExpectedConditions.elementToBeClickable(element(by.css("#trainer-assess-table > div > div:nth-child(1)")), 5000))
                    // wait for link to be clickable
                    browser.wait(protractor.ExpectedConditions.elementToBeClickable(links[i]));
                    links[i].click();
                    browser.wait(protractor.ExpectedConditions.elementToBeClickable(
                        element(by.css("#editCategoryModal > div > div > div.modal-body > div > div.form-group.col-sm-1.col-md-1 > input"))), 5000);
                    element(by.css("#editCategoryModal > div > div > div.modal-body > div > div.form-group.col-sm-1.col-md-1 > input")).click();
                    element(by.css("#editCategoryModal > div > div > div.modal-footer > input")).click();
                } // end row for loop
            }); // end then
            } // end table for loop
        });
    }); // end it
});

