// Employee menu helper functions
function empUpload(){
    document.getElementById('uploadreceipt').style.display = 'block';
    document.getElementById('empinfo').style.display = 'none';
    document.getElementById('info').innerHTML = '';
}

function empUpdate(){
    document.getElementById('empinfo').style.display = 'block';
    document.getElementById('info').innerHTML = '';
    document.getElementById('newreimbursement').style.display = 'none';
    document.getElementById('uploadreceipt').style.display = 'none';
}

function empViewResolved(){
    document.getElementById('info').innerHTML = '';
    document.getElementById('newreimbursement').style.display = 'none';
    document.getElementById('uploadreceipt').style.display = 'none';
    postemp('/Project1/Servlet/getEmployeeResolvedReimbursements');
}

function empViewPending(){
    document.getElementById('info').innerHTML = '';
    document.getElementById('newreimbursement').style.display = 'none';
    document.getElementById('uploadreceipt').style.display = 'none';
    postemp('/Project1/Servlet/getEmployeeReimbursements');
}

function empNewReimbursement(){
    document.getElementById('newreimbursement').style.display = 'block';
    document.getElementById('empinfo').style.display = 'none';
    document.getElementById('info').innerHTML = '';
    document.getElementById('uploadreceipt').style.display = 'none';
}

// End employee helpers

function getEmployeeMenu(){ // Function so managers can view employee menu
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let name = xhttp.responseURL.split("?=")[2];
            name = `${name.split("%20")[0]} ${name.split("%20")[1]}`;
            document.getElementById("load").innerHTML = xhttp.responseText;
            document.getElementById("welcome").innerHTML = "<br>Welcome " + name;
        }
    };

    xhttp.open("post", "/Project1/Servlet/employeeMenu", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("username=" + user);
}

function CountDecimalDigits(number) {
    var char_array = number.toString().split(""); // split every single char
    var not_decimal = char_array.lastIndexOf(".");
    return (not_decimal < 0) ? 0 : char_array.length - not_decimal - 1;
}

function approve() { // add error to catch if id not in db
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = () => {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById("info").innerHTML = xhttp.responseText;
        }
    };
    let obj = {
        id: document.getElementById("name").value,
        username: user
    };
    xhttp.open("post", "/Project1/Servlet/approveReimbursement", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("data=" + JSON.stringify(obj));
}

function deny() { // add error to catch if id not in db
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = () => {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById("info").innerHTML = xhttp.responseText;
        }
    };
    let obj = {
        id: document.getElementById("name").value,
        username: user
    };
    xhttp.open("post", "/Project1/Servlet/denyReimbursement", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("data=" + JSON.stringify(obj));
}

function updateEmployee() {
    let obj = {
        olduser: document.getElementById("olduser").value,
        newuser: document.getElementById("newuser").value,
        oldpass: document.getElementById("oldpass").value,
        newpass: document.getElementById("newpass").value
    };

    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = () => {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            // success, refresh page
            let res = xhttp.responseText;
            if (res.slice(0, 3) === "Bad")
                document.getElementById("info").innerHTML = res;
            else if (res.slice(0, 2) === "An")
                docuement.getElementById("info").innerHTML = res;
            else {
                window.alert(res);
                window.location.reload();
            }
        }
    };

    xhttp.open("post", "/Project1/Servlet/updateEmployee", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("data=" + JSON.stringify(obj));
}

function postemp(uri) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = () => {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById("empinfo").style.display = "none";
            document.getElementById("info").innerHTML = xhttp.responseText;
        }
    };

    xhttp.open("post", uri, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("username=" + user);
}

function submitReimbursement() {
    let obj;   
    if (isNaN(document.getElementById("amount").value % 0.01)) {
        document.getElementById("info").innerHTML = "Enter a valid amount";
    } else if(document.getElementById("file").files[0] !== undefined){
        let file = document.getElementById("file").files[0];
        let extension = file.name.split('.').pop().toLowerCase();
        if(file.size / (1024 * 1024) > 10.00){ // max file size is actually ~64 MB
            alert("File size is too big.  Max size is 10 MB") // but who has a picture that big?
        } else if (extension === 'png'){
            let reader = new FileReader();
        
            reader.addEventListener("load", () => {
               let obj = {
                username: user,
                reason: document.getElementById("reason").value,
                amount: document.getElementById("amount").value,
                file: reader.result
               };
               sendReimbursementHelper(obj);
            }, false);
        
            reader.readAsDataURL(file);
        } else
            alert("Please choose a .png picture");  
    }else if (CountDecimalDigits(document.getElementById("amount").value) <= 2) {
        obj = {
            username: user,
            reason: document.getElementById("reason").value,
            amount: document.getElementById("amount").value,
        };
        sendReimbursementHelper(obj);
    } else {
        document.getElementById("info").innerHTML = "Enter a valid amount2";
    }
}

function sendReimbursementHelper(obj){ // has to be function since FileReader runs async....
    let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = () => {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                document.getElementById("info").innerHTML = xhttp.responseText;
                document.getElementById("reason").value = "";
                document.getElementById("amount").value = "";
                document.getElementById("file").type = "text";
                document.getElementById("file").type = "file";
                document.getElementById("newreimbursement").style.display = "none";
                document.getElementById("uploadreceipt").style.display = "none";
            }
        };

        xhttp.open("post", "/Project1/Servlet/submitReimbursement", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("data=" + JSON.stringify(obj));
}
// -----------------------------------------------------------------------------------------------------
function hide(uri, action) {
    document.getElementById("approve").style.display = "none";
    document.getElementById("deny").style.display = "none";

    if (action === 'post') {
        if (document.getElementById("name").value === "")
            document.getElementById("info").innerHTML = "Enter an employee username in the input box";
        else
            post(uri);
    } else
        get(uri);
}

function getPendingReimbursements() {
    document.getElementById("approve").style.display = "inline-block";
    document.getElementById("deny").style.display = "inline-block";
    document.getElementById("name").value = "";
    get('/Project1/Servlet/getAllPendingReimbursements');
}

function login(uri) { // login
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = () => {

        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let res = xhttp.responseText;
            if (res.slice(0, 3) === "Bad")
                document.getElementById("error").innerHTML = res;
            else {
                document.getElementById("logout").href = "/Project1/";
                document.getElementById("logout").innerHTML = "Logout";
                let name = xhttp.responseURL.split("?=")[2];
                name = `${name.split("%20")[0]} ${name.split("%20")[1]}`;
                document.getElementById("load").innerHTML = res;
                document.getElementById("welcome").innerHTML = "<br>Welcome " + name;
            }
        }
    };

    user = document.getElementById("name").value; // global to use later...
    let obj = {
        username: user,
        password: document.getElementById("password").value
    };
    xhttp.open("post", uri, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("data=" + JSON.stringify(obj));
}


function post(uri) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = () => {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById("info").innerHTML = xhttp.responseText;
        }
    };

    xhttp.open("post", uri, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("username=" + document.getElementById("name").value);
}


function get(uri) {
    let xhttp = new XMLHttpRequest();
    if (uri === "/Project1/Servlet/Login")
        uri += document.getElementById("name").value + document.getElementById("password").value;

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("info").innerHTML = this.responseText;
        }
    };
    xhttp.open("get", uri, true);
    xhttp.send();

}