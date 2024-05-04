package com.lingxian.test.items;


import com.lingxian.test.IModel;
import com.lingxian.test.Test;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IModel {
    public ItemBase(String name, int stackSize, CreativeTabs tab) {
        setUnlocalizedName(Test.MODID + "." + name);
        setRegistryName(Test.MODID, name);
        setCreativeTab(tab);
        setMaxStackSize(stackSize);
    }

    @Override
    public void RegisterModel() {
        Test.proxy.registerItemRenderer(this, 0, "inventory");
    }
}