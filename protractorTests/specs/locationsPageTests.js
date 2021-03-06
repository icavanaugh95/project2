


describe("Should be on Home page", () => {

    it("Should check home url", () => {
        expect(browser.getCurrentUrl()).toEqual("https://dev-caliber.revature.tech/caliber/#/vp/home");
                
    });

    it("Should click navagate to locations page", () => {
       
        element(by.css("body > div > ui-view > nav > div > ul.nav.navbar-nav.navbar-right > li.dropdown > a")).click();
        element(by.css("a[href='#/vp/locations']")).click();
        expect(browser.getCurrentUrl()).toEqual("https://dev-caliber.revature.tech/caliber/#/vp/locations");

    })

    it("Add location should appear", () => {

        element(by.css("body > div > ui-view > ui-view > div > div:nth-child(1) > div > div > ul > li > a")).click();

        let EC = protractor.ExpectedConditions;
        let el = element(by.css('#createLocationModal > div > div'));
        browser.wait(EC.visibilityOf(el), 5000);

        
        expect(el.isDisplayed()).toBe(true);

    })

    it("Close add location popup", () => {

        element(by.css("#createLocationModal > div > div > div.modal-header > button > span")).click();

        let EC = protractor.ExpectedConditions;
        let el = element(by.css('#createLocationModal > div > div'));
        browser.wait(EC.invisibilityOf(el), 5000);
       
        expect(el.isDisplayed()).toBe(false);
       
    })

    it("Add edit location should appear if a pencil icon is clicked", () => {
        
         pencil = element(by.className("table table-hover")).all(by.tagName('a')).get(0);
         pencil.click();

         let EC = protractor.ExpectedConditions;
         let el = element(by.css('#editLocationModal > div > div'));
         browser.wait(EC.visibilityOf(el), 5000);
         
         expect(el.isDisplayed()).toBe(true);
         

    })

    it("Add edit location should close if the x is clicked", () => {
        
        element(by.css("#editLocationModal > div > div > div.modal-header > button > span")).click();

        let EC = protractor.ExpectedConditions;
        let el = element(by.css('#editLocationModal > div > div'));
        browser.wait(EC.invisibilityOf(el), 5000);
        
        expect(el.isDisplayed()).toBe(false);
        

   })

//    it("Activate/Deactivate popup appears when clicking the Check/X", () => {
//     let el;
//     status = element(by.className("table table-hover")).all(by.tagName('a')).get(1);
//     icon = status.getAttribute("class");
//     status.click();

//     let EC = protractor.ExpectedConditions;
//     if(icon == "glyphicon glyphicon-remove"){

//         el = element(by.css('#addLocationModal > div > div > div.modal-body > h3'));
//     }
//     else{
//         el = element(by.css('#deleteLocationModal > div > div > div.modal-body > h3'))
//     }
    
//     browser.wait(EC.visibilityOf(el), 5000);
//     console.log("Made it through");
    
//     expect(el.isDisplayed()).toBe(true);
    

//     })

    // it("Activate/Deactivate popup should close if the x is clicked", () => {
    //     let el;
    //     let EC = protractor.ExpectedConditions;
    //     addMenu = element(by.css("#addLocationModal > div > div > div.modal-body > h3"));
    //     removeMenu = element(by.css("#deleteLocationModal > div > div > div.modal-body > h3"));
        
    //     addMenu.isDisplayed().then(function(displayed){
    //         if(displayed){
    //             console.log("Inside addmenu");
    //             element(by.css("#addLocationModal > div > div > div.modal-header > button")).click();
    //             console.log("After click");
    //             el = addMenu;
    //         }
    //         else{
    //             console.log("Inside Delemetsd")
    //             element(by.css("#deleteLocationModal > div > div > div.modal-header > button")).click();
    //             el = removeMenu;
    //         }
    //     });

        
    //     browser.wait(EC.invisibilityOf(el), 5000);
        
    //     expect(el.isDisplayed()).toBe(false);
        

    // })

    // it("Activate/Deactivate popup should close if close is clicked", () => {

    //     status = element(by.className("table table-hover")).all(by.tagName('a')).get(1);
    //     status.click();

    //     let EC = protractor.ExpectedConditions;
    //     let el = element(by.css('#addLocationModal > div > div > div.modal-body'));
    //     browser.wait(EC.visibilityOf(el), 5000);
            
    //     closeBox = element(by.css("#addLocationModal > div > div > div.modal-footer > button"));

    //     if(closeBox.isEnabled()){
    //         closeBox.click();
    //     }
    //     else{
    //         element(by.css("#deleteLocationModal > div > div > div.modal-footer > button")).click();
    //     }

    //     let EC2 = protractor.ExpectedConditions;
    //     let el2 = element(by.css('#addLocationModal > div > div > div.modal-body'));
    //     browser.wait(EC2.invisibilityOf(el2), 5000);
        
    //     expect(el2.isDisplayed()).toBe(false);
        

    // })


    // This works but the website has no way to delete locations so dont use for now
    // it("If I insert all required data to the Add location menu it should insert a new item into table", () => {

    //     element(by.css("body > div > ui-view > ui-view > div > div:nth-child(1) > div > div > ul > li > a")).click();

    //     let EC = protractor.ExpectedConditions;
    //     let el = element(by.css('#createLocationModal > div > div'));
    //     browser.wait(EC.visibilityOf(el), 5000);

    //     el.element(by.model("locationForm.company")).sendKeys("CDTest");
    //     el.element(by.model("locationForm.street")).sendKeys("CDTest Street");
    //     el.element(by.model("locationForm.city")).sendKeys("CDTest City");
    //     el.element(by.model("locationForm.state")).all(by.tagName("option")).get(36).click();
    //     el.element(by.model("locationForm.zipcode")).sendKeys("CDTest 123");

    //     element(by.css("#createLocationModal > div > div > div.modal-body > div.modal-footer > input")).click();

    //     browser.wait(EC.invisibilityOf(el), 5000);

    //     expect(element(by.cssContainingText('td', 'CDTest')).isPresent()).toBe(true);
            
        

    // })


    it("If I insert all required data to the Edit location menu it should edit the selected location", () => {

        element(by.css("body > div > ui-view > ui-view > div > div:nth-child(2) > div > div > table > tbody > tr:nth-child(11) > td:nth-child(4) > a > span")).click();

        let EC = protractor.ExpectedConditions;
        let el = element(by.css('#editLocationModal > div > div > div.modal-body'));
        browser.wait(EC.visibilityOf(el), 5000);

        el.element(by.model("locationForm.company")).clear().sendKeys("CDTest2");
        el.element(by.model("locationForm.street")).clear().sendKeys("CDTest Street");
        el.element(by.model("locationForm.city")).clear().sendKeys("CDTest City");
        el.element(by.model("locationForm.state")).all(by.tagName("option")).get(38).click();
        el.element(by.model("locationForm.zipcode")).clear().sendKeys("CDTest 123");

        element(by.css("#editLocationModal > div > div > div.modal-footer > input")).click();

        browser.wait(EC.invisibilityOf(el), 5000);

        expect(element(by.cssContainingText('td', 'CDTest2')).isPresent()).toBe(true);
            
        

     })


//      it("If i click the Reactivate/Deactivate icons on the right and confirm to change, the icon changes", () => {

//         icon = element(by.css("body > div > ui-view > ui-view > div > div:nth-child(2) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(5) > a > span"));
//         startClass = icon.getAttribute('class');
//         icon.click();

//         let EC = protractor.ExpectedConditions;
//         let el = element(by.css('#addLocationModal > div > div > div.modal-body > h3'));
//         browser.wait(EC.visibilityOf(el), 5000);

//         confirmBox = element(by.css("#addLocationModal > div > div > div.modal-footer > input"));

//         if(confirmBox.isEnabled()){
//             confirmBox.click();
//         }
//         else{
//             element(by.css("#deleteLocationModal > div > div > div.modal-footer > input")).click();
//         }

//         browser.wait(EC.invisibilityOf(el), 5000);

//         endClass = icon.getAttribute('class');

//         expect(startClass == endClass).toBe(true);
//      })




 });








// Given I am logged in to the Caliber website
//    And I am on the �Reactivate Location� menu
// When I click �Cancel�
// Then the �Reactivate Location� menu should close without any errors.
//    And the deactivated location�s record should still have a red x to the left of it.
//    And the rightmost icon should still be a green check mark.

// Given I am logged in to the Caliber website
//    And I am on the �Reactivate Location� menu
// When I click �Reactivate�
// Then the �Reactivate Location� menu should close without any errors.
//    And the previously deactivated location�s record should now have a green check mark to the left of it.
//    And the rightmost icon should now be a red x.

