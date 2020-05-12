/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var ctx = document.getElementById("myCanvas").getContext("2d");

var color;
var size;
var bgColor;
var int;
var recognition = new webkitSpeechRecognition();
recognition.continious = true;
recognition.interimResults = true;
recognition.lang = "en-US";

ctx.beginPath();
ctx.fillStyle = 'black';
ctx.arc(400, 300, 25, 0, 2* Math.PI);
ctx.fill();
ctx.stroke();

var commands = {
    'color *': function(word){
        if(word === 'red' || word === 'blue')//add all colors
            color = word;
    },
    'background *': function(word){
        if(word === 'red' || word === 'blue')//add all colors
            bgColor = word;
    },
    'size *': function(word){
        size = parseInt(word);
        if(size < 1)
            //too small error
        if(size > 300)
            ;//too big error
    },
    'help': function(){
        alert('help');
    },
    'about': function(){
        
    }
};

annyang.addCommands(commands);

function startButton(){
  document.getElementById('speakButton').value = "Stop"; //change back as well
  annyang.start();
};

recognition.onresult = function (event) {
    for (var i = event.resultIndex; i < event.results.length; ++i) {
        if (event.results[i].isFinal) {
            //if word equals color/background/size then do size otherwise error msg
        }
    }
};

recognition.onerror = function(event){

};

recognition.onend = function(){ 
  
  };