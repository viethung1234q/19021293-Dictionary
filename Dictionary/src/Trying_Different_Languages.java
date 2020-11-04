import java.io.IOException;

import com.darkprograms.speech.synthesiser.SynthesiserV2;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 * This is where all begins .
 * 
 * @author GOXR3PLUS
 *
 */
public class Trying_Different_Languages {
	
	// Create a Synthesizer instance
	SynthesiserV2 synthesizer = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
	
	/**
	 * Constructor
	 */
	public Trying_Different_Languages(String text) {
		
		// Let's speak in English
		speak(text);
		
	}
	
	/**
	 * Calls the MaryTTS to say the given text
	 * 
	 * @param text
	 */
	public void speak(String text) {
		
		// Create a new Thread because JLayer is running on the current Thread 
		// and will make the application to lag
		Thread thread = new Thread(() -> {
			try {
				//Create a JLayer instance
				AdvancedPlayer player = new AdvancedPlayer(synthesizer.getMP3Data(text));
				player.play();
				
			} catch (IOException | JavaLayerException e) {
				e.printStackTrace();
			}
		});
		
		// We don't want the application to terminate before this Thread terminates
		thread.setDaemon(false);
		
		// Start the Thread
		thread.start();
		
	}
}
