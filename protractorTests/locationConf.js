exports.config = {
    seleniumAddress : "http://localhost:4444/wd/hub",
    specs: ['login.js','specs/locationsPageTests.js'],

    capabilities: {
        browserName: 'chrome'
     },
  
     jasmineNodeOpts: {
       showColors: true,
       defaultTimeoutInterval: 360000
     },
  
     allScriptsTimeout: 360000, 
};