/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    var config = {
        type: Phaser.AUTO,
        width: 800,
        height: 600,
        scene: {
            preload: preload,    
            create: create,
            update: update
        },
        physics: { 
            default: 'arcade',
            arcade: {
                gravity: { y:300 },
                debug: false
            }
        }
    };
    
    var player;
    var coin;
    var enemy;
    var platforms;
    var score;
    var gameOver = false;
    var scoreText;
    var lives;
    var livesText;
    var arrow;
    var scoreSound;
    var hurtSound;
    var Nkey;
    var Hkey;
    var gameOverText;
    var startText;
    var objects = this.scene;
    
    
    var game = new Phaser.Game(config);
   
   function preload() {
        this.load.image('background', 'Assets/Sprites/Background.png');
        this.load.image('ground', 'Assets/Sprites/Ground.png');
        this.load.image('plat', 'Assets/Sprites/Platform.png');
        this.load.image('enemy', 'Assets/Sprites/Enemy.png');
        this.load.image('points', 'Assets/Sprites/Points.png');
        this.load.image('player', 'Assets/Sprites/Player.png');
        this.load.audio('scoreSnd', 'Assets/Sounds/coinSound.wav');
        this.load.audio('hurt', 'Assets/Sounds/hurt.wav');
    };

    function create (){
        gameOver = false;
        
        //top of ground is 430
        this.add.image(400, 300, 'background');
        
        arrow = this.input.keyboard.createCursorKeys();
        Nkey = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.KeyN);
        Hkey = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.KeyH);
             
        
        let style = { 
            font: '20px Arial', 
            fill: '#fff'
        };
        
        let ngStyle = { 
            font: '20px Arial', 
            fill: 'red'
        };
        
        score = 0;
        scoreText = this.add.text(20, 20, 'Score: ' + score, style);
        scoreSound = this.sound.add('scoreSnd');
        lives = 3;
        livesText = this.add.text(710, 20, 'Lives: ' + lives, style);
        hurtSound = this.sound.add('hurt');
        
        startText = this.add.text(280, 200, '     COIN COLLECTOR \n \tby Cassidy McAllister \n \nCollect as many coins as possible \nbefore running out of lives! \n\n N - New Game  H - Help\n\n  Press Enter to start');
        gameOverText = this.add.text(280, 200, 'GAME OVER \n\nPress N to Start a New Game', style);
        gameOverText.setVisible(false);
        
        helpText = this.add.text(140, 200, 'Objective: Collect as many coins as possible before running out of lives \n\
                                            \nControls: Use the Arrow keys to move in the respective direction\n\n                  Press any key to close menu', style);
        helpText.setVisible(false);
        
        newGameText = this.add.text(280, 200, '     NEW GAME \n\n Are You Sure?  Y/N', ngStyle);
        newGameText.setVisible(false);
        
        
        platforms = this.physics.add.staticGroup();
        
        platforms.create(400, 485, 'ground');
        
        platforms.create(90, 315, 'plat');
        platforms.create(400, 150, 'plat');
        platforms.create(710, 315, 'plat');
        
        player = this.physics.add.sprite(400, 400, 'player');
        player.setBounce(0.2); //CHECK THIS
        player.setCollideWorldBounds(true);
        
        
        coin = this.physics.add.sprite(400, 100, 'points');
        coin.allowGravity = false;
        
        enemy = this.physics.add.sprite(150, 150, 'enemy');
        enemy.setVelocity(Phaser.Math.Between(-200, 200), 20);
        enemy.setBounce(1);
        
        
        enemy.setCollideWorldBounds(true);
        
        enemy.allowGravity = false;
            
        this.physics.add.collider(player, platforms);
        this.physics.add.collider(coin, platforms);
        this.physics.add.collider(enemy, platforms);
        
        this.physics.add.overlap(player, coin, collectCoin, null, this);
        
        this.physics.add.collider(player, enemy, hitEnemy, null, this);  
        
        var objects = this.scene;
        
        objects.pause();
        
        document.addEventListener('keypress', function(e){
            if(e.code === 'KeyN'){
                gameOverText.setVisible(false);
                newGame();
            }
            else if(e.code === 'KeyH') //72
                help();
            else if(e.code === 'Enter'){
                objects.resume();
                startText.setVisible(false);
                
            }                
        }); 
        
        function newGame(){
            newGameText.setVisible(true);
            gameOverText.setVisible(false);
            objects.pause();
        
            document.addEventListener('keypress', function(e){
                if(e.code === 'KeyY'){ //1 = yes
                    objects.restart();//no work
                }
                else if(e.code === 'KeyN'){ //2 = no
                    newGameText.setVisible(false);
                    objects.resume();
                }                   
            });
        };
        
    function help(){
            helpText.setVisible(true);
            startText.setVisible(false);
            
            objects.pause();
        
            document.addEventListener('keypress', function(){
                helpText.setVisible(false);
                objects.resume();
            });
        };
    };

    function update (){
         
        if(gameOver){
            endGame();
        }
        
        if (arrow.left.isDown){
            player.setVelocityX(-160);
        }
        else if (arrow.right.isDown)
        {
            player.setVelocityX(160);
        }
        else {
            player.setVelocityX(0);
        }

        if (arrow.up.isDown && player.body.touching.down) {
            player.setVelocityY(-330);
        }
        
        if (Nkey.isDown){
            alert(N);
            newGame();
        }
        if (Hkey.isDown){
            alert('H has been pressed2');
            help();
        } 
    
        
    };
    
    function collectCoin(player, coin) { 
        scoreSound.play();
       
        coin.x = Phaser.Math.Between(20, 780);
        coin.y = Phaser.Math.Between(20, 430);
        
        score += 10;
        scoreText.setText('Score: ' + score);
    }
    
    
   function hitEnemy(player, enemy) {
        hurtSound.play();
       
       
       
        enemy.x = Phaser.Math.Between(50, 750);
        enemy.y = Phaser.Math.Between(50, 300);
        
        player.x = 400;
        player.y= 390;
        
        lives -= 1;
        if(lives == 0)
           gameOver = true;
        livesText.setText('Lives: ' + lives);
    }
    
    function endGame(){
        player.disableBody(true, true);
        enemy.disableBody(true, true);
        coin.disableBody(true, true);
        
        gameOverText.setVisible(true);   
    }
    
    
    
    
    
    
   