<?php
$jazyk= 'SK'; // 'SK' alebo 'EN'

//ZMENA JAZYKA - CEZ ODKAZ V MENU (GET METÓDOU)
if($_GET['lang']=='SK'){
    $jazyk = $_GET['lang'];
}
elseif($_GET['lang']=='EN'){
    $jazyk = $_GET['lang'];
}



$gallery_page = array(
    "SK" => array(
        "title" => "GALÉRIA",

        "first_nadpis" => "Kamera",
        "first_text" => "Táto sústava dvoch kamier je jednoducho zážitok. Širokouhlé fotografie bez efektu tzv. \"Rybieho oka\". Tento telefón nám ponúkne 2x optický zoom, doplní ho 5-násobné digitálne priblíženie. Zaujať môže aj vylepšený režim portrétu. Rozmazanie pozadia a ovládanie hĺbky rozmazania ako skúsený fotograf. Už žiadne rozmazané fotky, pomôže tomu pokročilá stabilizácia obrazu. Pokročilý nočný režim zachytí detail aj zdanlivo tmavého prostredia.",

        "second_nadpis" => "Video",
        "second_text" => "Tento telefón nám ponúka možnosť nahrávania veľmi kvalitných videonahrávok. S rozlíšením 4K pri 60FPS jednoznačne dokáže zaujať. Už žiadne roztrasené videá. Tento telefón Vám dokáže ako doba pokročila. Bez žiadnych prídavných zariadení ako je napríklad stabilizátor, dokáže nahrávať pozoruhodné videá aj za behu. <br>Možnosť nahrávania spomalených videí pri 240FPS a stále dostačujúcej FULL HD kvalite (1080p) si pozriete spomalene každý moment."
    ),
    "EN" => array(
        "title" => "GALLERY",

        "first_nadpis" => "Camera",
        "first_text" => "This system of two cameras is simply an experience. Widescreen photos without \"Fisheye\" effect. This phone will offer us 2x optical zoom, complemented by a 5x digital zoom. You may also be interested in the improved portrait mode. Background blur and depth control control like an experienced photographer. No more blurred photos, advanced image stabilization will help. Advanced night mode captures detail even in seemingly dark environments.",

        "second_nadpis" => "Video",
        "second_text" => "This phone offers us the opportunity to record high quality videos. With a resolution of 4K at 60FPS, it can clearly impress. No more shaky videos. This phone can tell you how time has come. Without any additional devices such as a stabilizer, it can record remarkable videos on the fly. <br>You can see the possibility of recording slow-motion videos at 240FPS and still sufficient FULL HD quality (1080p) in slow motion every moment."
    )
);
?>

<!DOCTYPE html>
<html lang="<?php echo (strtolower($jazyk));?>">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="icon" href="../images/favicon.png" sizes="16x16" type="image/png">

    <!-- GOOGLE FONTS - OPEN SANS -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&display=swap" rel="stylesheet">

    <!-- MOJE CSS -->
    <link rel="stylesheet" href="../styles/style.css">

    <title><?php echo $gallery_page[$jazyk]["title"];?> | iPhone 12</title>
</head>
<body>

    <?php include 'header.php';?>

    <!-- SEKCIA KAMERA (PODSTRÁNKA GALERIA)  -->
    <div class="container-fluid">
        <section class="gallery-first">
            <h2><?php echo $gallery_page[$jazyk]["first_nadpis"];?></h2>
            <p><?php echo $gallery_page[$jazyk]["first_text"];?></p>

            <div class="gallery gallery_one">
                <div class="item">
                    <div class="inline-item">
                        <a href="../images/gallery/1.jpg"><img src="../images/gallery/BIG/1.jpg" alt="photo by iPhone 12"></a>
                        <a href="../images/gallery/2.jpg"><img src="../images/gallery/BIG/2.jpg" alt="photo by iPhone 12"></a>
                    </div>
                </div>
                <div class="item">
                    <a href="../images/gallery/3.jpg"><img src="../images/gallery/BIG/3.jpg" alt="photo by iPhone 12"></a>
                </div>
                <div class="item">
                    <div class="inline-item">
                        <a href="../images/gallery/4.jpg"><img src="../images/gallery/BIG/4.jpg" alt="photo by iPhone 12"></a>
                        <a href="../images/gallery/4.jpg"><img src="../images/gallery/BIG/4.jpg" alt="photo by iPhone 12"></a>
                    </div>
                </div>
        
            </div>
            <div class="gallery visible">
                <div class="item">
                    <a href="../images/gallery/5.jpg"><img src="../images/gallery/BIG/5.jpg" alt="photo by iPhone 12"></a>
                </div>
                <div class="item">
                    <a href="../images/gallery/6.jpg"><img src="../images/gallery/BIG/6.jpg" alt="photo by iPhone 12"></a>
                </div>
                <div class="item">
                    <a href="../images/gallery/7.jpg"><img src="../images/gallery/BIG/7.jpg" alt="photo by iPhone 12"></a>
                </div>
                <div class="item">
                    <a href="../images/gallery/8.jpg"><img src="../images/gallery/BIG/8.jpg" alt="photo by iPhone 12"></a>
                </div>
            </div>
            <!-- HIDDEN -->
            <div class="gallery gallery_two hidden">
                <div>
                    <div class="item">
                        <a href="../images/gallery/5.jpg"><img src="../images/gallery/BIG/5.jpg" alt="photo by iPhone 12"></a>
                    </div>
                    <div class="item">
                        <a href="../images/gallery/6.jpg"><img src="../images/gallery/BIG/6.jpg" alt="photo by iPhone 12"></a>
                    </div>
                </div>
                <div>
                    <div class="item">
                        <a href="../images/gallery/7.jpg"><img src="../images/gallery/BIG/7.jpg" alt="photo by iPhone 12"></a>
                    </div>
                    <div class="item">
                        <a href="../images/gallery/8.jpg"><img src="../images/gallery/BIG/8.jpg" alt="photo by iPhone 12"></a>
                    </div>
                </div>
            </div>
            <!-- HIDDEN -->
            <div class="gallery">
                <div class="item">
                    <a href="../images/gallery/PANORAMA.jpg"><img src="../images/gallery/BIG/PANORAMA1.jpg" alt="photo by iPhone 12"></a>
                </div>
            </div>


        </section>
    </div>

    <!-- SEKCIA VIDEO (PODSTRÁNKA GALERIA)  -->
    <div class="container-fluid">
        <section class="gallery-second">
            <h2><?php echo $gallery_page[$jazyk]["first_nadpis"];?></h2>
            <p><?php echo $gallery_page[$jazyk]["first_text"];?></p>


            <div class="videa visible">
                <div class="video-item">
                    <video muted controls>
                        <source src="../images/gallery/videa/1.mp4" type="video/mp4">
                    </video>
                </div>
                <div class="video-item">
                    <video muted controls>
                        <source src="../images/gallery/videa/2.mp4" type="video/mp4">
                    </video>
                </div>
                <div class="video-item">
                    <video muted controls>
                        <source src="../images/gallery/videa/3.mp4" type="video/mp4">
                    </video>
                </div>
                <div class="video-item">
                    <video muted controls>
                        <source src="../images/gallery/videa/4.mp4" type="video/mp4">
                    </video>
                </div>
            </div>
            <!-- HIDDEN -->
            <div class="videa hidden">
                <div>
                    <div class="video-item">
                        <video muted controls>
                            <source src="../images/gallery/videa/1.mp4" type="video/mp4">
                        </video>
                    </div>
                    <div class="video-item">
                        <video muted controls>
                            <source src="../images/gallery/videa/2.mp4" type="video/mp4">
                        </video>
                    </div>
                </div>
                <div class="help">
                    <div class="video-item">
                        <video muted controls>
                            <source src="../images/gallery/videa/3.mp4" type="video/mp4">
                        </video>
                    </div>
                    <div class="video-item">
                        <video muted controls>
                            <source src="../images/gallery/videa/4.mp4" type="video/mp4">
                        </video>
                    </div>
                </div>
            </div>
            <!-- HIDDEN -->
        </section>
    </div>
    

    <?php include 'footer.php';?>



</body>
</html>