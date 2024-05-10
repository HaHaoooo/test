package com.lingxian.test;

import com.lingxian.test.entity.CoinEntity;
import com.lingxian.test.items.Coin;
import com.lingxian.test.items.JuQingCompositeObjects;
import com.lingxian.test.items.ThrowCoin;
import com.lingxian.test.sound.EnumSounds;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = Test.MODID)
public class Registry {
    // 物品实例
    public static ArrayList<Item> ITEMS = new ArrayList<>();
    public static Coin coin = new Coin("coin", 1, CreativeTabs.MISC);
    public static ThrowCoin throwCoin = new ThrowCoin("throw_coin", 16, CreativeTabs.MISC);

    // 注册物品
    @SubscribeEvent
    public static void registryItems(RegistryEvent.Register<Item> register) {
        ITEMS.addAll(JuQingCompositeObjects.getJuQing());
        ITEMS.add(coin);
        ITEMS.add(throwCoin);
        for (Item item : ITEMS) {
            register.getRegistry().register(item);
        }
    }

    // 注册模型
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ITEMS) {
            if (item instanceof IModel) {
                ((IModel) item).RegisterModel();
            }
        }
    }

    // 注册实体
    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().register(EntityEntryBuilder.create()
            .entity(CoinEntity.class)
            .id(new ResourceLocation(Test.MODID, "coin_entity"), 0)
            .name("test.coin_entity")
            .tracker(64, 10, true)
            .build());
    }

    // 注册声音
    @SubscribeEvent
    public static void onSoundEvenrRegistration(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().register(EnumSounds.COIN_SHOOT.getSoundEvent());
        event.getRegistry().register(EnumSounds.RAINBOW.getSoundEvent());
    }
}
