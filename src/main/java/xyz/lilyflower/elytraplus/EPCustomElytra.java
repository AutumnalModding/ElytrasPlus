package xyz.lilyflower.elytraplus;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.ElytraEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class EPCustomElytra extends ElytraItem implements FabricElytraItem {
    public EPCustomElytra() {
        super(new Item.Settings().rarity(Rarity.UNCOMMON).maxDamage(432));
    }

    @Environment(EnvType.CLIENT)
    public static class Renderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
        private final ElytraEntityModel<T> elytra;

        public Renderer(FeatureRendererContext<T, M> featureRendererContext, EntityModelLoader entityModelLoader) {
            super(featureRendererContext);
            this.elytra = new ElytraEntityModel<>(entityModelLoader.getModelPart(EntityModelLayers.ELYTRA));
        }

        @Override
        public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
            ItemStack stack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
            if (stack.isIn(ElytrasPlus.ELYTRAS) && !stack.isOf(Items.ELYTRA)) {
                Identifier texture;
                if (livingEntity instanceof AbstractClientPlayerEntity abstractClientPlayerEntity) {
                    if (abstractClientPlayerEntity.canRenderElytraTexture() && abstractClientPlayerEntity.getElytraTexture() != null) {
                        texture = abstractClientPlayerEntity.getElytraTexture();
                    } else if (abstractClientPlayerEntity.canRenderCapeTexture() && abstractClientPlayerEntity.getCapeTexture() != null && abstractClientPlayerEntity.isPartVisible(PlayerModelPart.CAPE)) {
                        texture = abstractClientPlayerEntity.getCapeTexture();
                    } else {
                        texture = new Identifier("elytras-plus", "textures/entity/elytra/" + stack.getItem().getTranslationKey().replace("item.elytras-plus.", "") + ".png");
                    }
                } else {
                    texture = new Identifier("elytras-plus", "textures/entity/elytra/" + stack.getItem().getTranslationKey().replace("item.elytras-plus.", "") + ".png");
                }

                if (stack.getName().toString().startsWith("Suspicious ")) {
                    texture = new Identifier("elytras-plus", "textures/entity/elytra/" + stack.getItem().getTranslationKey().replace("item.elytras-plus.", "") + "_suspicious.png");
                }

                matrixStack.push();
                matrixStack.translate(0.0D, 0.0D, 0.125D);
                getContextModel().copyStateTo(this.elytra);
                this.elytra.setAngles(livingEntity, f, g, j, k, l);
                VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider, this.elytra.getLayer(texture), false, stack.hasGlint());
                this.elytra.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
                matrixStack.pop();
            }
        }
    }
}
