describe("Category Suite", () => {

    // returns callback with number of
    // the total categories in all tables
    function countCategories(callback){
        let amount = 0;
        element.all(by.tagName("tbody")).then((table) => {
        for(let i = 0; i < table.length; i++){ // for each table
                table[i].all(by.tagName("tr")).then((rows) => {
                    amount += rows.length; // add length
                })
            }
        }).then(() => {
            callback(amount);
        });
    };


    it("Should go to the categories page", () => {
        element(by.css("li[role='presentation']")).click();
        element(by.css("a[ui-sref='vp.category']")).click();
        expect(browser.getCurrentUrl()).toEqual("https://dev-caliber.revature.tech/caliber/#/vp/category");
    });

    it("Should add a category", () => {
        browser.wait(protractor.ExpectedConditions.elementToBeClickable(
            element(by.css("body > div > ui-view > ui-view > div:nth-child(1) > div > div > div > ul > li > a"))
        ), 5000);
        countCategories((oldAmount) => {
            element(by.css("body > div > ui-view > ui-view > div:nth-child(1) > div > div > div > ul > li > a")).click();
            // wait for text box to be clicked
            browser.wait(protractor.ExpectedConditions.elementToBeClickable(element(by.id("categoryName"))), 5000);
            element(by.id("categoryName")).sendKeys("A New Category");
            // submit
            element(by.css("#addCategoryModal > div > div > div.modal-body > div > div.modal-footer > input")).click();
            browser.wait(protractor.ExpectedConditions.elementToBeClickable(
                element(by.css("body > div > ui-view > ui-view > div:nth-child(1) > div > div > div > ul > li > a"))
            ), 5000);
            countCategories((newAmount) => {
                expect(newAmount).toEqual(oldAmount + 1); // check if amount of categories updated
            });                                          
        });
    });

    it("Should add an empty category", () => {
        browser.wait(protractor.ExpectedConditions.elementToBeClickable(
            element(by.css("body > div > ui-view > ui-view > div:nth-child(1) > div > div > div > ul > li > a"))
        ), 5000);
        countCategories((oldnum) => {
            element(by.css("body > div > ui-view > ui-view > div:nth-child(1) > div > div > div > ul > li > a")).click();
            // wait for text box to be clicked
            browser.wait(protractor.ExpectedConditions.elementToBeClickable(element(by.id("categoryName"))), 5000);
            element(by.id("categoryName")).sendKeys("");
            // submit
            element(by.css("#addCategoryModal > div > div > div.modal-body > div > div.modal-footer > input")).click();
            browser.wait(protractor.ExpectedConditions.elementToBeClickable(
                element(by.css("body > div > ui-view > ui-view > div:nth-child(1) > div > div > div > ul > li > a"))
            ), 5000);
            countCategories((newnum) => {
                expect(newnum).toEqual(oldnum + 1); // check if amount of categories updated
            });                                          
        });
    });

    it("Should click create a category and cancel", () => {
        countCategories((oldAmount) => {
            element(by.css("body > div > ui-view > ui-view > div:nth-child(1) > div > div > div > ul > li > a")).click();
            // wait for text box to be clicked
            browser.wait(protractor.ExpectedConditions.elementToBeClickable(element(by.id("categoryName"))), 5000);

            // click cancel
            element(by.css("#addCategoryModal > div > div > div.modal-body > div > div.modal-footer > button")).click();

            // check if categories didn't change
            countCategories((newAmount) => {
                expect(newAmount).toEqual(oldAmount);
            });
        });
    });

    it("Should rename the first category in the first table", () => {
        // wait for table
        browser.wait(protractor.ExpectedConditions.elementToBeClickable(element(by.css("#trainer-assess-table"))), 5000);
        
        element.all(by.tagName("tbody")).then((table) => {
            table[0].all(by.tagName("tr")).then((links) => {
                browser.wait(protractor.ExpectedConditions.elementToBeClickable(links[0]));
                links[0].click();
                // get text box
                let textBox = element(by.css("#editCategoryModal > div > div > div.modal-body > div > div.form-group.col-md-6.col-sm-6 > input"));

                // wait for text box to be clickable
                browser.wait(protractor.ExpectedConditions.elementToBeClickable(textBox), 5000);
                textBox.clear().then(() => { // clear then send keys
                    textBox.sendKeys("Category has been changed");

                // wait for submit
                browser.wait(protractor.ExpectedConditions.elementToBeClickable(
                    element(by.css("#editCategoryModal > div > div > div.modal-footer > input"))));

                // submit
                element(by.css("#editCategoryModal > div > div > div.modal-footer > input")).click();
                }); // end textBox.clear()
            }); // end then
        }); // end all
    });

    // this test takes a while
    it("Should change all the categories active status", () => {
        element.all(by.tagName("tbody")).then((table) => {
            for (let j = 0; j < table.length; j++) { // for every table
                table[j].all(by.tagName("tr")).then((links) => { // for every row
                    for (let i = 0; i < links.length; i++) {
                        // wait for div
                        browser.wait(protractor.ExpectedConditions.elementToBeClickable(element(by.css("#trainer-assess-table > div > div:nth-child(1)"))), 5000);
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