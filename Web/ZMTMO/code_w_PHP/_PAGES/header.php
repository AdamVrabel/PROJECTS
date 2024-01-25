<?php
    $menu = array(
        "SK" => array("Domov", "Technické špecifikácie", "Galéria", "Používateľské rozhranie", "Porovnanie", "Príslušenstvo", "Hodnotenie", "Kontakt"),
        "EN" => array("Home", "Technical specifications", "Gallery", "User interface", "Comparsion", "Accessories", "Rating", "Contact")
    );
?>
<!-- NAVIGACIA -->
<div class="container-fluid-nav">
        <div class="navigacia">
            <a href="../index.php<?php echo("?lang=".$jazyk);?>"><img src="../images/logo.svg" id="logo" alt="LOGO - ADAM BLOG"></a>

            
            <nav>
                <a href="../index.php<?php echo ('?lang='.$jazyk);?>"><?php echo $menu[$jazyk][0];?></a>
                <a href="2_technicke_specifikacie.php<?php echo ('?lang='.$jazyk);?>"><?php echo $menu[$jazyk][1];?></a>
                <a href="3_galeria.php<?php echo ('?lang='.$jazyk);?>"><?php echo $menu[$jazyk][2];?></a>
                <a href="4_rozhranie.php<?php echo ('?lang='.$jazyk);?>"><?php echo $menu[$jazyk][3];?></a>
                <a href="5_porovnanie.php<?php echo ('?lang='.$jazyk);?>"><?php echo $menu[$jazyk][4];?></a>
                <a href="6_prislusenstvo.php<?php echo ('?lang='.$jazyk);?>"><?php echo $menu[$jazyk][5];?></a>
                <a href="7_hodnotenie.php<?php echo ('?lang='.$jazyk);?>"><?php echo $menu[$jazyk][6];?></a>
                <a href="8_kontakt.php<?php echo ('?lang='.$jazyk);?>"><?php echo $menu[$jazyk][7];?></a>
            </nav>

            <div class="language">
                <a class="<?php if($jazyk == 'SK'){echo 'active';} ?>" href="<?php echo ($_SERVER['PHP_SELF']."?lang=SK");?>">SK</a>
                <a class="<?php if($jazyk == 'EN'){echo 'active';} ?>" href="<?php echo ($_SERVER['PHP_SELF']."?lang=EN");?>">EN</a>
            </div>

        </div>
    </div>