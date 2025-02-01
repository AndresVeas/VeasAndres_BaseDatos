/*---------------------------------------------------\
|  Copyright (©) 2K25 EPN-FIS. All rights reserved.  |
|  andres.veas@epn.edu.ec  PROPRIETARY/CONFIDENTIAL. |
|  Use is subject to license terms.     andres.veas  |
\---------------------------------------------------*/


import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterContrastIJTheme;
import UserInterface.Form.MainForm;
import UserInterface.Form.SplashScreenForm;

public class App {
    public static void main(String[] args) throws Exception {

         FlatLightLaf.setup();
        FlatLightLaf.supportsNativeWindowDecorations();
        try {
            UIManager.setLookAndFeel(new FlatMaterialLighterContrastIJTheme());
        } catch (Exception e) {
            e.printStackTrace();
        }
       //SplashScreenForm.show();
       MainForm frmMain = new MainForm("PoliBiblio -------GRUPO 6");
       frmMain.setVisible(true); 
    } 

}