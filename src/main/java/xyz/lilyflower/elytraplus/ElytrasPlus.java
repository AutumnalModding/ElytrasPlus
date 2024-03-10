package xyz.lilyflower.elytraplus;

import java.util.HashMap;
import java.util.Map;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class ElytrasPlus implements ModInitializer {

	public static final Map<String, Item> ELYTRAS_DYED = new HashMap<>();

	private void generateElytras() {
		for (DyeColor colour : DyeColor.values()) {
			ELYTRAS_DYED.put("elytra_" + colour.name().toLowerCase(), new EPCustomElytra());
		}
	}

	public static final Item ELYTRA_LESBIAN = new EPCustomElytra();
	public static final Item ELYTRA_NON_BINARY = new EPCustomElytra();
	public static final Item ELYTRA_PRIDE = new EPCustomElytra();
	public static final Item ELYTRA_TRANS = new EPCustomElytra();

	public static final TagKey<Item> ELYTRAS = TagKey.of(RegistryKeys.ITEM, new Identifier("elytras-plus", "elytra"));

	@Override
	public void onInitialize() {
		this.generateElytras();
		ELYTRAS_DYED.forEach((name, elytra) -> Registry.register(Registries.ITEM, new Identifier("elytras-plus", name), elytra));

		Registry.register(Registries.ITEM, new Identifier("elytras-plus", "elytra_lesbian"), ELYTRA_LESBIAN);
		Registry.register(Registries.ITEM, new Identifier("elytras-plus", "elytra_non_binary"), ELYTRA_NON_BINARY);
		Registry.register(Registries.ITEM, new Identifier("elytras-plus", "elytra_pride"), ELYTRA_PRIDE);
		Registry.register(Registries.ITEM, new Identifier("elytras-plus", "elytra_trans"), ELYTRA_TRANS);
	}
}