describe("Category Suite", () => {
    it("Should check home url", () => {
        expect(browser.getCurrentUrl()).toEqual("https://dev-caliber.revature.tech/caliber/#/vp/home");
    });
});