var config = {
        type: Phaser.AUTO,
        width: 600,
        height: 600,
        scene: {
            preload: preload,
            create: create,
            update: update
        }
    };

    var game = new Phaser.Game(config);

    function preload () {
        this.load.image('background', 'Assets/background.png');
        this.load.image('eyes1', 'Assets/sprites/eyes1.png');
        this.load.image('eyes2', 'Assets/sprites/eyes2.png');
        this.load.image('eyes3', 'Assets/sprites/eyes3.png');
        this.load.image('eyes4', 'Assets/sprites/eyes4.png');
        this.load.image('glassesEyes', 'Assets/sprites/GlassesEyes.png');
        this.load.image('lear', 'Assets/sprites/Lear.png');
        this.load.image('rear', 'Assets/sprites/Rear.png');
        this.load.image('mouth1', 'Assets/sprites/mouth1.png');
        this.load.image('mouth2', 'Assets/sprites/mouth2.png');
        this.load.image('mouth3', 'Assets/sprites/mouth3.png');
        this.load.image('nose', 'Assets/sprites/nose.png');
        this.load.image('nose2', 'Assets/sprites/nose2.png');
        this.load.image('potatobody', 'Assets/sprites/potatoBody3.png');        
    }

    function create () {
       this.add.image( 300, 300, 'background');
       var potatobody = this.add.image( 400, 300, 'potatobody');
       var eyes1 = this.add.image( 50, 450, 'eyes1');
       var eyes2 = this.add.image( 50, 150, 'eyes2');
       var eyes3 = this.add.image( 50, 250, 'eyes3');
       var eyes4 = this.add.image( 50, 350, 'eyes4');
       var glassesEyes = this.add.image( 50, 550, 'glassesEyes');
       var lear = this.add.image( 50, 50, 'lear');
       var rear = this.add.image( 150, 50, 'rear');
       var mouth1 = this.add.image( 150, 450, 'mouth1');
       var mouth2 = this.add.image( 150, 550, 'mouth2');
       var mouth3 = this.add.image( 150, 350, 'mouth3');
       var nose = this.add.image( 150, 150, 'nose');
       var nose2 = this.add.image( 150, 250, 'nose2');
      
       eyes1.setInteractive();
       eyes2.setInteractive();
       eyes3.setInteractive();
       eyes4.setInteractive();
       glassesEyes.setInteractive();
       lear.setInteractive();
       rear.setInteractive();
       mouth1.setInteractive();
       mouth2.setInteractive();
       mouth3.setInteractive();
       nose.setInteractive();
       nose2.setInteractive();
       potatobody.setInteractive();

       this.input.setDraggable(eyes1);
       this.input.setDraggable(eyes2);
       this.input.setDraggable(eyes3);
       this.input.setDraggable(eyes4);
       this.input.setDraggable(glassesEyes);
       this.input.setDraggable(lear);
       this.input.setDraggable(rear);
       this.input.setDraggable(mouth1);
       this.input.setDraggable(mouth2);
       this.input.setDraggable(mouth3);
       this.input.setDraggable(nose);
       this.input.setDraggable(nose2);

    this.input.on('drag', function (pointer, gameObject, dragX, dragY) {

        gameObject.x = dragX;
        gameObject.y = dragY;

    });
    }

    function update ()
    {
    }


