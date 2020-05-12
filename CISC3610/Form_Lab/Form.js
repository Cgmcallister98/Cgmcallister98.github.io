/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var oldSave = {
    oldHead: undefined,
    oldBody: undefined
};

function AutoSave(){
    //Store old save 
    if(localStorage.getItem("head")){
    oldSave.oldHead = localStorage.getItem("head");
    oldSave.oldBody = localStorage.getItem("body");
    }
    // Store new save
     localStorage.setItem("head", document.getElementById("head").value);
     localStorage.setItem("body", document.getElementById("body").value);
};

function Startup(){
     if (typeof(Storage) !== "undefined") {
        //retrieve old local storage 
        document.getElementById("head").value = localStorage.getItem("head");
        document.getElementById("body").value = localStorage.getItem("body");
    } else { 
        document.getElementById("head").innerHTML = "Sorry, your browser does not support Web Storage";
        document.getElementById("body").innerHTML = "Sorry, your browser does not support Web Storage";
    }
};

function UndoForm(){
    //Display Old Save
    document.getElementById("head").value = oldSave.oldHead;
    document.getElementById("body").value = oldSave.oldBody;
    //Store Local Stroage into Old Save
    oldSave.oldHead = localStorage.getItem("head");
    oldSave.oldBody = localStorage.getItem("body");
    //Store Form data into Local Storage
    localStorage.setItem("head", document.getElementById("head").value);
    localStorage.setItem("body", document.getElementById("body").value);
};

function ClearForm () {
   //Clear data by setting it to blank
   document.getElementById("head").value = "";
   document.getElementById("body").value = "";
}