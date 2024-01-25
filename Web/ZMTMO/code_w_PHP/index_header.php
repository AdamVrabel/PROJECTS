 <!-- NAVIGACIA -->
 <div class="container-fluid-nav">
        <div class="navigacia">
            <a href="<?php echo ($_SERVER['PHP_SELF']."?lang=".$jazyk);?>"><img src="images/logo.svg" id="logo" alt="LOGO - ADAM BLOG"></a>

            <nav>
                <a href="index.php<?php echo ("?lang=".$jazyk);?>"><?php echo $index_menu[$jazyk][0];?></a>
                <a href="_PAGES/2_technicke_specifikacie.php<?php echo ('?lang='.$jazyk);?>"><?php echo $index_menu[$jazyk][1];?></a>
                <a href="_PAGES/3_galeria.php<?php echo ('?lang='.$jazyk);?>"><?php echo $index_menu[$jazyk][2];?></a>
                <a href="_PAGES/4_rozhranie.php<?php echo ('?lang='.$jazyk);?>"><?php echo $index_menu[$jazyk][3];?></a>
                <a href="_PAGES/5_porovnanie.php<?php echo ('?lang='.$jazyk);?>"><?php echo $index_menu[$jazyk][4];?></a>
                <a href="_PAGES/6_prislusenstvo.php<?php echo ('?lang='.$jazyk);?>"><?php echo $index_menu[$jazyk][5];?></a>
                <a href="_PAGES/7_hodnotenie.php<?php echo ('?lang='.$jazyk);?>"><?php echo $index_menu[$jazyk][6];?></a>
                <a href="_PAGES/8_kontakt.php<?php echo ('?lang='.$jazyk);?>"><?php echo $index_menu[$jazyk][7];?></a>
            </nav>

            <div class="language">
                <a class="<?php if($jazyk == 'SK'){echo 'active';} ?>" href="<?php echo ($_SERVER['PHP_SELF']."?lang=SK");?>">SK</a>
                <a class="<?php if($jazyk == 'EN'){echo 'active';} ?>" href="<?php echo ($_SERVER['PHP_SELF']."?lang=EN");?>">EN</a>
            </div>
            
        </div>
    </div>