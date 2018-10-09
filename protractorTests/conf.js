exports.config = {
    seleniumAddress : "http://localhost:4444/wd/hub",
    specs: ['login.js','specs/locationsPageTests.js', 'specs/categoryPageTests.js']
};