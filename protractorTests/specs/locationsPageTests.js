
// //Add Location
// Given I am logged in to the Caliber website
// When I click on "Settings" 
//    And I click on the Locations tab
// Then the Locations page should appear on the browser.

describe("Should be on Trainers page", () => {

    it("Should check home url", () => {
        expect(browser.getCurrentUrl()).toEqual("https://dev-caliber.revature.tech/caliber/#/vp/home");
                
    });

    it("Should click Trainer link", () => {
       
        element(by.css("body > div > ui-view > nav > div > ul.nav.navbar-nav.navbar-right > li.dropdown > a")).click();
        element(by.css("a[href='#/vp/locations']")).click();
        expect(browser.getCurrentUrl()).toEqual("https://dev-caliber.revature.tech/caliber/#/vp/locations");

    })
});



// Given I am logged in to the Caliber website
//    And I am on the Locations Page
// When I click on “Create Location+”
// Then the “Add Location” menu should appear.

describe("Should be on Trainers page", () => {

    it("Should check home url", () => {
        expect(browser.getCurrentUrl()).toEqual("https://dev-caliber.revature.tech/caliber/#/vp/home");
                
    });

    it("Should click Trainer link", () => {
       
        element(by.css("body > div > ui-view > nav > div > ul.nav.navbar-nav.navbar-right > li.dropdown > a")).click();
        element(by.css("a[href='#/vp/locations']")).click();
        expect(browser.getCurrentUrl()).toEqual("https://dev-caliber.revature.tech/caliber/#/vp/locations");

    })
});

// Given I am logged in to the Caliber website
//    And I am in the “Add Location” menu
// When I properly fill in Company Name, Street Address, City, State, and Zipcode
//    And I click “Save”
// Then a new Location should appear on the list of Locations with the proper fields I filled in.

// Given I am logged in to the Caliber website
//    And I am in the “Add Location” menu
// When the “Company Name” field is left blank
//    And I click “Save”
// Then an error message should appear below the “Enter Company Name” textbox saying “Please fill out this field.”

// Given I am logged in to the Caliber website
//    And I am in the “Add Location” menu
// When the “Street Address” field is left blank
//    And I click “Save”
// Then an error message should appear below the “Enter Street Address” textbox saying “Please fill out this field.”

// Given I am logged in to the Caliber website
//    And I am in the “Add Location” menu
// When the “City” field is left blank
//    And I click “Save”
// Then an error message should appear below the “Enter City” textbox saying “Please fill out this field.”

// Given I am logged in to the Caliber website
//    And I am in the “Add Location” menu
// When the “State” field is left blank
//    And I click “Save”
// Then an error message should appear below the “Select State” dropdown saying “Please fill out this field.”

// Given I am logged in to the Caliber website
//    And I am in the “Add Location” menu
// When the “Zipcode” field is left blank
//    And I click “Save”
// Then an error message should appear below the “Enter Zipcode” textbox saying “Please fill out this field.”

// Given I am logged in to the Caliber website
//    And I am in the “Add Location” menu
// When the “Zipcode” field is filled in
//    But the input is not numeric
//    And I click “Save”
// Then an error message should appear below the “Enter Zipcode” textbox saying “Zipcode should be numbers only.”

// Given I am logged in to the Caliber website
//    And I am in the “Add Location” menu
// When the “Zipcode” field is filled in
//    And the input is numeric
//    But the input is not 5 or 9 digits long
//    And I click “Save”
// Then an error message should appear below the “Enter Zipcode” textbox saying “Zipcode must be 5 or 9 digits only.”

// //Edit Location
// Given I am logged in to the Caliber website
//    And I am on the Locations Page
// When I click on any pencil icon
// Then the “Edit Location” menu should appear.

// Given I am logged in to the Caliber website
//    And I am in the “Edit Location” menu
// When I properly fill in Company Name, Street Address, City, State, and Zipcode
//    And I click “Save”
// Then a new Location should appear on the list of Locations with the proper fields I filled in.

// Given I am logged in to the Caliber website
//    And I am in the “Edit Location” menu
// When the “Company Name” field is left blank
//    And I click “Save”
// Then an error message should appear below the “Enter Company Name” textbox saying “Please fill out this field.”

// Given I am logged in to the Caliber website
//    And I am in the “Edit Location” menu
// When the “Street Address” field is left blank
//    And I click “Save”
// Then an error message should appear below the “Enter Street Address” textbox saying “Please fill out this field.”

// Given I am logged in to the Caliber website
//    And I am in the “Edit Location” menu
// When the “City” field is left blank
//    And I click “Save”
// Then an error message should appear below the “Enter City” textbox saying “Please fill out this field.”

// Given I am logged in to the Caliber website
//    And I am in the “Edit Location” menu
// When the “State” field is left blank
//    And I click “Save”
// Then an error message should appear below the “Select State” dropdown saying “Please fill out this field.”

// Given I am logged in to the Caliber website
//    And I am in the “Edit Location” menu
// When the “Zipcode” field is left blank
//    And I click “Save”
// Then an error message should appear below the “Enter Zipcode” textbox saying “Please fill out this field.”

// Given I am logged in to the Caliber website
//    And I am in the “Edit Location” menu
// When the “Zipcode” field is filled in
//    But the input is not numeric
//    And I click “Save”
// Then an error message should appear below the “Enter Zipcode” textbox saying “Zipcode should be numbers only.”

// Given I am logged in to the Caliber website
//    And I am in the “Edit Location” menu
// When the “Zipcode” field is filled in
//    And the input is numeric
//    But the input is not 5 or 9 digits long
//    And I click “Save”
// Then an error message should appear below the “Enter Zipcode” textbox saying “Zipcode must be 5 or 9 digits only.”

// //Reactivate Location
// Given I am logged in to the Caliber website
// When I click on any green check mark
// Then the “Reactivate Location” menu should appear.

// Given I am logged in to the Caliber website
//    When I click on "Settings" 
//    And I click on the Locations tab
//    And I click on the red x icon next to <name>
// Then the message on the “Remove Location” page should say “Are you sure you want to re-activate <name>?”

// Given I am logged in to the Caliber website
//    And I am on the “Reactivate Location” menu
// When I click “Cancel”
// Then the “Reactivate Location” menu should close without any errors.
//    And the deactivated location’s record should still have a red x to the left of it.
//    And the rightmost icon should still be a green check mark.

// Given I am logged in to the Caliber website
//    And I am on the “Reactivate Location” menu
// When I click “Reactivate”
// Then the “Reactivate Location” menu should close without any errors.
//    And the previously deactivated location’s record should now have a green check mark to the left of it.
//    And the rightmost icon should now be a red x.

// //Remove Location
// Given I am logged in to the Caliber website
// When I click on "Settings" 
//    And I click on the Locations tab
//    And I click on any rightmost red x icon
// Then the “Remove Location” warning page should appear.

// Given I am logged in to the Caliber website
//    When I click on "Settings" 
//    And I click on the Locations tab
//    And I click on the rightmost red x icon of <name>’s record
// Then the message on the “Remove Location” page should say “Are you sure you want to delete <name>?”

// Given I am logged in to the Caliber website
//    And I am on the “Remove Location” menu
// When I click “Cancel”
// Then the “Remove Location” menu should close without any errors.
//    And the deactivated location’s record should still have a green check mark to the left of it.
//    And the rightmost icon should still be a red x.

// Given I am logged in to the Caliber website
//    And I am on the “Remove Location” menu
// When I click “Reactivate”
// Then the “Remove Location” menu should close without any errors.
//    And the previously deactivated location’s record should now have a red x to the left of it.
//    And the rightmost icon should now be a green check mark.
