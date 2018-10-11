describe("Tests for reports page", () => {

    it("Should go home", () => {
        element(by.css("a[href='#/vp/home']")).click();
        let welcome = element(by.css("#home > div:nth-child(1) > h1:nth-child(1)"));
        browser.wait(protractor.ExpectedConditions.visibilityOf(welcome), 5000);
        expect(browser.getCurrentUrl()).toEqual("https://dev-caliber.revature.tech/caliber/#/vp/home");
                
    });

    it("Should click navigate to reports page", () => {
        element(by.css("a[href='#/vp/reports']")).click();
        let cumulativeHeader = element(by.css(".top10 > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)"));
        browser.wait(protractor.ExpectedConditions.visibilityOf(cumulativeHeader), 5000);
        expect(browser.getCurrentUrl()).toEqual("https://dev-caliber.revature.tech/caliber/#/vp/reports");
    })

    it("Should display all years", () => {
        //Click year list dropdown
        element(by.className("dropdown-toggle ng-binding")).click();    //returns first element with this class, which just so happens to be the year list dropdown

        //Check that dropdown menu appears
        let yearDropdown = element(by.css("li.dropdown:nth-child(1) > ul:nth-child(2) > li:nth-child(2) > a:nth-child(1)"));    //By default, the dropdown starts at 2018
        browser.wait(protractor.ExpectedConditions.visibilityOf(yearDropdown), 5000);
        
        expect(yearDropdown.isDisplayed()).toBe(true);
    })
    
    //Next 3 tests use Ryan Lessley's batch on 11/12/17
    it("Should display QC data if a Trainer with QC data is selected", () => {
        //Select year 2017
        let year2017 = element(by.cssContainingText("body > div > ui-view > ui-view > div:nth-child(1) > div > div > ul > li.dropdown.open > ul > li:nth-child(3) > a","2017"));
        browser.wait(protractor.ExpectedConditions.visibilityOf(year2017), 5000);
        year2017.click();

        //Select Trainer with QC data
        let ryansBatch = element(by.cssContainingText(".ng-binding", "11/12/17"));
        let batchDropdown = element(by.css("li.dropdown:nth-child(2) > a:nth-child(1)"));
        batchDropdown.click();
        browser.wait(protractor.ExpectedConditions.visibilityOf(ryansBatch), 5000);
        ryansBatch.click();

        //Wait for Batch QC Report to appear (Looks for "Trainee" label)
        let qcReport = element(by.css(".table-bordered > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1)"));
        browser.wait(protractor.ExpectedConditions.visibilityOf(qcReport), 15000);   //Batch QC Report takes a while to appear

        //Assert that the Batch QC Report actually appears
        expect(qcReport.isDisplayed()).toBe(true);
    })

    it("Should display QC notes when you click a smiley face", () => {
        //Click smiley face
        let smiley = element(by.css(".table-bordered > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > div:nth-child(1) > a:nth-child(1)"));
        browser.wait(protractor.ExpectedConditions.visibilityOf(smiley), 5000);
        smiley.click();

        //Notes menu should appear
        let notes = element(by.binding("individualNote"));
        browser.wait(protractor.ExpectedConditions.visibilityOf(notes), 5000);

        expect(notes.isDisplayed()).toBe(true);
    })

    it("Should close Notes menu", () => {
        //Click "Close"
        element(by.buttonText("Close")).click();

        //See if Batch QC Report appears again
        let qcReport = element(by.css(".table-bordered > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1)"));
        browser.wait(protractor.ExpectedConditions.visibilityOf(qcReport), 15000);   //Batch QC Report takes a while to appear
        expect(element(by.css(".table-bordered > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1)")).isDisplayed()).toBe(true);
    })
});