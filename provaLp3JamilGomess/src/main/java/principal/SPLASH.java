package principal;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class SPLASH {
	  private final int LARGURA_IMG = 420;
	    private final int ALTURA_IMG = 250;
	    private final int TEMPO_DE_SPLASH = 6000;
	    private final String CAMINHO_GIF = "/imagens/loading.gif";
	    
	    public SPLASH(){
	        
	        JWindow janelaSplash = new JWindow();
	        
	        janelaSplash.getContentPane().add(
	                new JLabel(
	                        "",
	                        new ImageIcon(getClass().getResource(CAMINHO_GIF)),
	                        SwingConstants.CENTER
	                )
	        );
	              
	        Toolkit toolkit = Toolkit.getDefaultToolkit();
	        Dimension dimension = toolkit.getScreenSize();
	        
	        janelaSplash.setBounds(
	                (dimension.width - LARGURA_IMG) / 2,
	                (dimension.height - ALTURA_IMG)/ 2,
	                LARGURA_IMG,
	                ALTURA_IMG
	        );
	        
	        janelaSplash.setVisible(true);
	        
	        try{
	            Thread.sleep(TEMPO_DE_SPLASH);
	        }catch(InterruptedException e){}
	        
	        
	        
	        new LoginForm();
	        janelaSplash.dispose();
	       
	        
	    }


}
