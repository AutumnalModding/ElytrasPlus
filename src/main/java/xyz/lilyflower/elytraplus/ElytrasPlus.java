package xyz.lilyflower.elytraplus;

import java.util.HashMap;
import java.util.Map;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.ArmorStandEntityModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElytrasPlus implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("elytras-plus");

	private final Map<String, Item> ELYTRAS_DYED = new HashMap<>();

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
		this.ELYTRAS_DYED.forEach((name, elytra) -> {
			Registry.register(Registries.ITEM, new Identifier("elytras-plus", name), elytra);
		});

		Registry.register(Registries.ITEM, new Identifier("elytras-plus", "elytra_lesbian"), ELYTRA_LESBIAN);
		Registry.register(Registries.ITEM, new Identifier("elytras-plus", "elytra_non_binary"), ELYTRA_NON_BINARY);
		Registry.register(Registries.ITEM, new Identifier("elytras-plus", "elytra_pride"), ELYTRA_PRIDE);
		Registry.register(Registries.ITEM, new Identifier("elytras-plus", "elytra_trans"), ELYTRA_TRANS);
	}
}