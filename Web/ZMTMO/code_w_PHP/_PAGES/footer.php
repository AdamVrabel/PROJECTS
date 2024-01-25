<?php 
$footer = array(
    "SK" => array(
        "footer_text" => "Táto stránka opisuje telefón iPhone 12, ktorý vlastním. Týmto projektom chcem ukázať môj telefón aj iným ľudom."
    ),
    "EN" => array(
        "footer_text" => "This page describes the iPhone 12 I own. With this project, I want to show my phone to other people."
    )
);
?>
<!-- FOOTER -->
<div class="container-fluid-color <?php if($color == 'w'){echo 'white';}?>">

<footer>
    <div class="container">
        <div class="f-item">
            <p><?php echo $footer[$jazyk]["footer_text"];?></p>
            <div class="odkazy">
                <ul>
                    <li><a href="https://facebook.com/"><img src="../images/i_fb.svg" alt="FB"></a></li>
                    <li><a href="https://www.instagram.com/"><img src="../images/i_insta.svg" alt="IG"></a></li>
                    <li><a href="https://mail.google.com/"><img src="../images/i_mail.svg" alt="MAIL"></a></li>
                </ul>
            </div>
        </div>
        <div class="f-nav">
            <div class="mini-nav">
                <ul>
                    <li><a href="../index.php<?php echo ('?lang='.$jazyk);?>"><?php echo $menu[$jazyk][0];?></a></li>
                    <li><a href="2_technicke_specifikacie.php<?php echo ('?lang='.$jazyk);?>"><?php echo $menu[$jazyk][1];?></a></li>
                    <li><a href="3_galeria.php<?php echo ('?lang='.$jazyk);?>"><?php echo $menu[$jazyk][2];?></a></li>
                    <li><a href="4_rozhranie.php<?php echo ('?lang='.$jazyk);?>"><?php echo $menu[$jazyk][3];?></a></li>
                    <li><a href="5_porovnanie.php<?php echo ('?lang='.$jazyk);?>"><?php echo $menu[$jazyk][4];?></a></li>
                    <li><a href="6_prislusenstvo.php<?php echo ('?lang='.$jazyk);?>"><?php echo $menu[$jazyk][5];?></a></li>
                    <li><a href="7_hodnotenie.php<?php echo ('?lang='.$jazyk);?>"><?php echo $menu[$jazyk][6];?></a></li>
                    <li><a href="8_kontakt.php<?php echo ('?lang='.$jazyk);?>"><?php echo $menu[$jazyk][7];?></a></li>
                </ul>
            </div>
        </div>

    </div>

    <p class="author">&copy Adam Vrabeľ</p>
</footer>