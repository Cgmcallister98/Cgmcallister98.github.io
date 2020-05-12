/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var bird = new Audio('Bird.wav');
var falcon = new Audio('falcon.wav');
var kookaburra = new Audio('kookaburra.wav');
var owl = new Audio('owl.wav');
var parrot = new Audio('parrot.wav');
var duck = new Audio("duck.wav");

function BirdCall(){
  bird.play();  
}

function FalconCall(){
    falcon.play();
}

function KookaburraCall(){
    kookaburra.play();
}

function OwlCall(){
    owl.play();
}

function ParrotCall(){
   parrot.play(); 
}

function DuckCall(){
    duck.play();
}