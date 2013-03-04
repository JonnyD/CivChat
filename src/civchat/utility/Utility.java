package civchat.utility;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class Utility {

	public static boolean canBeAntenna(Block block) {
		// Get the blocks material relative to the block placed by the player
		Material main = block.getType();
		Material up1 = block.getRelative(BlockFace.UP, 1).getType();
		Material up2 = block.getRelative(BlockFace.UP, 2).getType();
		Material down1 = block.getRelative(BlockFace.DOWN, 1).getType();
		Material down2 = block.getRelative(BlockFace.DOWN, 2).getType();

		// Concatenate the first letters of each of the materials
		String materialFirstLetters = first(up1.toString())
				+ first(up2.toString()) + first(main.toString())
				+ first(down1.toString()) + first(down2.toString());

		// Regex pattern
		String regex = "DIJ..|I.DJ.|..IDJ";

		// Check if the first letters of the materials match the regex pattern
		if (materialFirstLetters.matches(regex)) {
			return true;
		}
		
		return false;
	}
	
	private static String first(String text) {
		return text.substring(0, 1);
	}
}
