<?php
$jazyk= 'SK'; // 'SK' alebo 'EN'

//ZMENA JAZYKA - CEZ ODKAZ V MENU (GET METÓDOU)
if($_GET['lang']=='SK'){
    $jazyk = $_GET['lang'];
}
elseif($_GET['lang']=='EN'){
    $jazyk = $_GET['lang'];
}
$rating_page = array(
    "SK" => array(
        "title" => "HODNOTENIE",
        "nadpis" => "Hodnotenie",
        "plus" => "plusy",
        "minus" => "minusy",
        "plus_items" => array("spoľahlivosť, výkon", "operačný systém iOS, intuitívnosť, kvalita", "spoľahlivá a rýchla kompatibilita s APPLE zariadeniami a príslušenstvom", "Face ID (funguje rýchlo a spoľahlivo za svetla aj za tmy)", "Bezdrôtové nabíjanie, rýchle NVMe úložisko","Design, padne do ruky", "Bezdrôtové nabíjanie, rýchle NVMe úložisko", "Foťák, stabilizácia, lepšia výdrž batérie oproti starším modelom"),
        "minus_items" => array("vysoká cena", "v balení bez adaptéra (originál 20W adapter cca 25€)", "Cena, v balení bez adaptéra (iba kábel)"),

        "rating_form_nadpis" => "ohodnoťte tento telefón",
        "rating_form_meno" => "Vaše meno:",
        "rating_form_plusy" => "Plusy:",
        "rating_form_minusy" => "Mínusy:",
        "rating_form_btn" => "ODOSLAŤ"
    ),
    "EN" => array(
        "title" => "RATING",
        "nadpis" => "Rating",
        "plus" => "pros",
        "minus" => "CONS",
        "plus_items" => array("reliability, performance", "iOS operating system, intuitiveness, quality", "Reliable and fast compatibility with APPLE devices and accessories", "Face ID (works quickly and reliably in light and dark)", "Wireless charging, fast NVMe storage", "Design, fits in your hand", "Camera, stabilization, better battery life compared to older models"),
        "minus_items" => array("higher price", "in package without adapter (original 20W adapter approx. 25 €)", "\"economical\" packaging (without adapter and headphones)"),

        "rating_form_nadpis" => "rate this phone",
        "rating_form_meno" => "Your name:",
        "rating_form_plusy" => "Pros:",
        "rating_form_minusy" => "Cons:",
        "rating_form_btn" => "SUBMIT"
    )
);

?>

<!-- AK SA ODOSLE FORMULAR - PRIPISE UDAJE DO POĽA -->
<?php 
    if(isset($_POST['submit'])){

        //echo $_POST['meno'];
        //echo $_POST['plusy'];
        //echo $_POST['minusy'];
        if($_POST['plusy']!=''){
            array_push($rating_page[$jazyk]['plus_items'], $_POST['plusy']);
        }
        if($_POST['minusy']!=''){
            array_push($rating_page[$jazyk]['minus_items'], $_POST['minusy']);
        }

        //PRIDANIE HODNOTENIA DO SÚBORU
        $myfile_a = fopen("../EXPORT/7_hodnotenie_EXPORT.txt", "a") or die("Nedokážem otvoriť súbor!");

        fwrite($myfile_a, "\n\tAUTOR: \t".$_POST['meno']."\n");
        fwrite($myfile_a, "\tPLUS: \t".$_POST['plusy']."\n");
        fwrite($myfile_a, "\tMINUS: \t".$_POST['minusy']."\n\n");

        fclose($myfile_a);
    }
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

    <title><?php echo $rating_page[$jazyk]["title"];?> | iPhone 12</title>
</head>
<body>

    <?php include 'header.php';?>

    <!-- HODNOTENIE -->
    <div class="container-fluid">
        <section class="hodnotenie">
            <h2><?php echo $rating_page[$jazyk]["nadpis"];?></h2>

            <div class="content">
                <div class="item">

                    <h3><?php echo $rating_page[$jazyk]["plus"];?></h3>

                    <?php
                        foreach($rating_page[$jazyk]["plus_items"] as $plus_polozka){
                            echo '<div class="polozka">';
                            echo '<img class="ikona" src="../images/hodnotenie/plus.svg" alt="znak plus">';
                            echo '<p>'.$plus_polozka.'</p>';
                            echo '</div>';
                        }
                    ?>

                </div>
                
                <div class="item">

                    <h3><?php echo $rating_page[$jazyk]["minus"];?></h3>
                    
                    <?php
                        foreach($rating_page[$jazyk]["minus_items"] as $minus_polozka){
                            echo '<div class="polozka">';
                            echo '<img class="ikona" src="../images/hodnotenie/minus.svg" alt="znak minus">';
                            echo '<p>'.$minus_polozka.'</p>';
                            echo '</div>';
                        }
                    ?>
                    
                    
                </div>
            </div>


        </section>
    </div>
    <div class="container-fluid-color">
        <section class="hodnotenie-formular">
            <p class="nadpis"><?php echo $rating_page[$jazyk]["rating_form_nadpis"];?></p>
            
            <form action="<?php echo $_SERVER['PHP_SELF']; ?><?php echo ("?lang=".$jazyk);?>" method="post">

                <div class="input">
                    <label for="meno" class="input-label"><?php echo $rating_page[$jazyk]["rating_form_meno"];?></label>
                    <input type="text" id="meno" name="meno" class="input-field" value=""  required/>
                </div>

                <div class="input">
                    <label for="plusy" class="input-label"><?php echo $rating_page[$jazyk]["rating_form_plusy"];?></label>
                    <input type="text" id="plusy" name="plusy" class="input-field" value="" />
                </div>

                <div class="input">
                    <label for="minusy" class="input-label"><?php echo $rating_page[$jazyk]["rating_form_minusy"];?></label>
                    <input type="text" id="minusy" name="minusy" class="input-field" value="" />
                </div>

                <input class="submit" name="submit" type="submit" value="<?php echo $rating_page[$jazyk]["rating_form_btn"];?>">
                <!-- <div class="submit">
                    <a href="#"><?php echo $rating_page[$jazyk]["rating_form_btn"];?></a>
                </div> -->

            </form> 

            

        </section>
    </div>

    <?php $color = 'w';?>
    <?php include 'footer.php';?>


</body>
</html>