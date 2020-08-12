package net.minestom.server.world.biomes;

import net.minestom.server.utils.NamespaceID;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;
import org.jglrxavpok.hephaistos.nbt.NBTList;
import org.jglrxavpok.hephaistos.nbt.NBTTypes;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Allows servers to register custom dimensions. Also used during player joining to send the list of all existing dimensions.
 *
 * Contains {@link Biome#EXAMPLE} by default but can be removed.
 */
public class BiomeManager {

	private List<Biome> biomes = new LinkedList<>();

	public BiomeManager() {
		addBiome(Biome.EXAMPLE);
	}

	/**
	 * Add a new biome. This does NOT send the new list to players.
	 * @param biome
	 */
	public void addBiome(Biome biome) {
		biomes.add(biome);
	}

	/**
	 * Removes a biome. This does NOT send the new list to players.
	 * @param biome
	 * @return if the biome type was removed, false if it was not present before
	 */
	public boolean removeBiome(Biome biome) {
		return biomes.remove(biome);
	}

	/**
	 * Returns an immutable copy of the biomes already registered
	 * @return
	 */
	public List<Biome> unmodifiableList() {
		return Collections.unmodifiableList(biomes);
	}

	public Biome getById(int id) {
		Biome biome = null;
		for (final Biome biomeT : biomes) {
			if (biomeT.getId() == id) {
				biome = biomeT;
				break;
			}
		}
		return biome;
	}

	public Biome getByName(NamespaceID namespaceID) {
		Biome biome = null;
		for (final Biome biomeT : biomes) {
			if (biomeT.getName().equals(namespaceID)) {
				biome = biomeT;
				break;
			}
		}
		return biome;
	}

	public NBTCompound toNBT() {
		NBTCompound biomes = new NBTCompound();
		biomes.setString("type", "minecraft:worldgen/biome");
		NBTList<NBTCompound> biomesList = new NBTList<>(NBTTypes.TAG_Compound);
		for (Biome biome : this.biomes) {
			biomesList.add(biome.toNbt());
		}
		biomes.set("value", biomesList);
		return biomes;
	}
}