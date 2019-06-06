function authenticate(){
    console.log("Authentication");
}

function redirect(destination){
    window.location(destination);
}

window.onload=function(){
    console.log("javascript caricato correttamente");

    let loginForm = document.getElementById("loginForm");

    loginForm.addEventListener(onsubmit, authenticate);
}