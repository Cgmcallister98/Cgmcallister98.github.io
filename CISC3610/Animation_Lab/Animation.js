"use strict";

var Scene = {
    canvas : undefined,
    canvasContext : undefined,
    sprite: undefined
};
var running = false;


Scene.start = function () {
	
	// Get the canvas and it's context.
    Scene.canvas = document.getElementById("myCanvas");
    Scene.canvasContext = Scene.canvas.getContext("2d");
	
	// Seup the Numbers to be displayed.
    Scene.sprite = Number;
	
	// Attach the image to be used for the sprite.
    Scene.sprite.img = new Image();
    Scene.sprite.img.src = Scene.sprite.src;
    
    //Establish the offset   
    Scene.sprite.offset=Scene.sprite.frames[Scene.sprite.frame].frame.w/2;
    
    
// Wait till the Numbers are loaded before starting the animation.
    Scene.sprite.img.onload = function() {		   
     if(!running){
		Scene.mainLoop();
		running = true; 
	};
    };
  
};

Scene.clearCanvas = function () {
    
    Scene.canvasContext.fillStyle = "red";
    Scene.canvasContext.fillRect(0, 0, Scene.canvas.width, Scene.canvas.height);
};

Scene.mainLoop = function() {
    Scene.clearCanvas();
    Scene.update();
    Scene.draw();
    
        
       
	
    // Animate at 500ms
    window.setTimeout(Scene.mainLoop, 1000 / 2);
};

Scene.update = function () {
	// Set the canvas width to be that of the display Window. Which helps if you resize the window.
  	//Scene.canvas.width = window.innerWidth;
        Scene.canvasContext.fillStyle = "red";
        Scene.canvasContext.fillRect(0, 0, Scene.canvas.width, Scene.canvas.height);
	
	
	
};

Scene.draw = function () {
    
    
    if(Scene.sprite.frame < Scene.sprite.frames.length)
        Scene.canvasContext.drawImage(Scene.sprite.img,Scene.sprite.frames[Scene.sprite.frame].frame.x,Scene.sprite.frames[Scene.sprite.frame].frame.y,Scene.sprite.frames[Scene.sprite.frame].frame.w,Scene.sprite.frames[Scene.sprite.frame].frame.h,Scene.sprite.offset,0,Scene.sprite.frames[Scene.sprite.frame].frame.w,Scene.sprite.frames[Scene.sprite.frame].frame.h);
        // Advance to the next frame.
	
    if(Scene.sprite.frame == Scene.sprite.frames.length)
        Scene.ten();
    //alert(Scene.sprite.frame);    
    Scene.sprite.frame++;    
};

Scene.ten = function(){
          
          Scene.sprite.frame=0;
          Scene.canvasContext.drawImage(Scene.sprite.img,Scene.sprite.frames[Scene.sprite.frame].frame.x,Scene.sprite.frames[Scene.sprite.frame].frame.y,Scene.sprite.frames[Scene.sprite.frame].frame.w,Scene.sprite.frames[Scene.sprite.frame].frame.h,Scene.sprite.offset+40,0,Scene.sprite.frames[Scene.sprite.frame].frame.w,Scene.sprite.frames[Scene.sprite.frame].frame.h);
          Scene.sprite.frame=1;
          Scene.canvasContext.drawImage(Scene.sprite.img,Scene.sprite.frames[Scene.sprite.frame].frame.x,Scene.sprite.frames[Scene.sprite.frame].frame.y,Scene.sprite.frames[Scene.sprite.frame].frame.w,Scene.sprite.frames[Scene.sprite.frame].frame.h,Scene.sprite.offset-40,0,Scene.sprite.frames[Scene.sprite.frame].frame.w,Scene.sprite.frames[Scene.sprite.frame].frame.h);  
          Scene.sprite.frame=0;
          running = false;            
          Scene.stop();
		 
};

