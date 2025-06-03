package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 * The GraphicsConstants class contains values useful throughout the creation
 * of graphics during the program.
 * 
 * @author Daniel Santillan
 * @version 1.0
 */
public class GraphicsConstants {

	/**
	 * Default constructor
	 */
	public GraphicsConstants() {
		
	}

	
	/** Color for the header of the panels */
	public static final Color COLOR_BG_HEADER = new Color(3, 112, 180);
	/** Color for the background of the panels */
	public static final Color COLOR_BG_MAIN = new Color(216, 224, 232);

	/** Font for the buttons */
	public static final Font FONT_BUTTON = new Font("Roboto", Font.PLAIN, 30);
	/** Font for small-sized */
	public static final Font FONT_ROBOTO_B30 = new Font("Roboto", Font.BOLD, 30);
	/** Font for medium-sized text */
	public static final Font FONT_ROBOTO_B40 = new Font("Roboto", Font.BOLD, 40);
	/** Font for large-sized text */
	public static final Font FONT_ROBOTO_B50 = new Font("Roboto", Font.BOLD, 50);

	/** Dimension for text fields */
	public static final Dimension DIMENSION_TEXTFIELD_DEFAULT = new Dimension(800, 75);


	/**
	 * This is the toString method for this class.
	 */
	@Override
	public String toString() {
		return "GraphicsConstants []";
	}
}
