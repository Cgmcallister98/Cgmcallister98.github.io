/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var ctx = document.getElementById("myCanvas").getContext("2d");

var color = 'black';
var size = 25;
var bgColor = 'white';
var synth = window.speechSynthesis;
synth.lang = "en-US";


function starterArc(){
    ctx.beginPath();
    ctx.fillStyle = color;
    ctx.arc(400, 300, 25, 0, 2* Math.PI);
    ctx.fill();
    ctx.closePath();
    ctx.stroke();
}

var commands = {
    'color *word': function(word){
        alert('speech function');
        color = word;
        colorChange(word);
    },
    'background *word': function(word){
        alert('bg function');
        bgColor = word;
        backgroundChange(word);
    },
    'size *word': function(word){
        alert('size function');
        size = parseInt(word);
        sizeChange(size);
    },
    'help': function(){
        help();
    },
    'about': function(){
        about();
    }
};

annyang.addCommands(commands);

function startButton(){
  var x = document.getElementById("speakButton");
  if (x.value === "Speak") {
    starterArc();
    x.value = "Stop";
    annyang.start({continuous: false});
  } else {
    annyang.abort();  
    x.value = "Speak";
  }
  //annyang.start();
  
  
};

function help(){
   var msg = new SpeechSynthesisUtterance();
    msg.text = "Say color, followed by a color, to set the circle color. \n\
    Say background, followed by a color, to set the background color. \n\
    Say size, followed of a number from 1 to 300, to set the diameter of the circle. Say about, to hear about the program.";
    synth.speak(msg);
};

function about(){
  var msg = new SpeechSynthesisUtterance();
  msg.text =  "Created by Cassidy McAllister. This program is to show the usage of text to speech and speech recognition";
  synth.speak(msg);
};

function colorChange(word){
    ctx.beginPath();
    ctx.fillStyle = color;
    ctx.arc(400, 300, size, 0, 2* Math.PI);
    ctx.fill();
    ctx.closePath();
    ctx.stroke();
}

function backgroundChange(word){
    ctx.beginPath();
    ctx.fillStyle = bgColor;
    ctx.rect(0, 0, ctx.width, ctx.height);
    ctx.fill();
    ctx.closePath();
    ctx.stroke();
} 

function sizeChange(size){
   
    if(size < 1){
            var msg = new SpeechSynthesisUtterance();
            msg.text =  "Size too small, the minimize size is 1";
            synth.speak(msg);
    }
    if(size > 300){
            var msg = new SpeechSynthesisUtterance();
            msg.text =  "Size too big, the size limit is 300";
            synth.speak(msg);
    }
    else{
       ctx.fillStyle = color; 
       ctx.arc(400, 300, size, 0, 2* Math.PI);
       ctx.fill();
       ctx.stroke();
    }
}

