/** Death4967 4:37 2025 May 24th */

package me.death.bootware;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.lwjgl.util.Display;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;

@Mod(modid = Client.MODID, name = Client.NAME, version = Client.VERSION)
public class Client
{
    public static final String MODID = "client";
    public static final String NAME = "Client";
    public static final String VERSION = "v0.0.1";
    public Socket socket;
    public Display displayer;
    private static Logger logger;
    public BufferedInputStream buffer;
    public Minecraft mc;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        try {
            socket = new Socket("0.0.0.1", 012344);
            buffer = new BufferedInputStream(socket.getInputStream());
            Object object1 = new Object();
            Object object2 = new Object();
            if (object1 instanceof BufferedInputStream) {
                object1 = buffer.read();
                object2 = object1.toString();
            } else {
                socket.close();
            }

            if (object2 instanceof String) {
                mc.player.sendChatMessage("xD pwned!" + object2.toString());
            } else {
                try {
                    object2.wait();
                } catch (InterruptedException e) {
                    e.getSuppressed();
                    throw new RuntimeException(e);
                }
            }

        } catch (IOException e) {
            e.getSuppressed();
            throw new RuntimeException(e);
        }
        displayer = new Display();
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("NIGGA BALLS >> {}", Blocks.DIRT.getRegistryName());
    }
}
