package com.exopteron.exomagicmod;

import com.exopteron.exomagicmod.block.BlockSetup;
import com.exopteron.exomagicmod.item.ItemSetup;
import com.exopteron.exomagicmod.network.NetworkSetup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

public class ExoMagicMod implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);
    @Override
    public void onInitialize() {
        LOGGER.info("Sup!");
        BlockSetup.setup();
        ItemSetup.setup();
        NetworkSetup.setup();
    }
    
}
