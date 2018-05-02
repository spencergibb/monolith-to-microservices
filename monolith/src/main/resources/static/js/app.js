function get(theUrl, callback)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
            callback(xmlHttp.responseText);
    }
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}

function nameAndFortune() {
    var name = document.getElementById("name").value;
    get("/service/hello?name="+name, function (response) {
        document.getElementById("hello").innerText = response;
    })
    get("/service/randomfortune", function (response) {
        document.getElementById("fortune").innerText = response;
    })
}

function clearNameAndFortune() {
    document.getElementById("name").value = "";
    document.getElementById("hello").innerText = "";
    document.getElementById("fortune").innerText = "";
}