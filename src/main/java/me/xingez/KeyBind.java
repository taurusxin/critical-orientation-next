package me.xingez;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBind {
    private static final String categoryName = "Critical Orientation Next";
    KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                    "key.toggle.orientation",
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_BACKSLASH,
                    categoryName)
    );

    public KeyBind() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                assert client.player != null;
                double yaw = client.player.getHeadYaw();
                yaw = roundYaw(normalizeHeadYaw(yaw));
                client.player.refreshPositionAndAngles(client.player.getX(), client.player.getY(),
                        client.player.getZ(), (float) yaw, client.player.getPitch(0));
            }
        });
    }

    public static double normalizeHeadYaw(double yaw) {
        yaw = yaw % 360;
        if (yaw > 180 || yaw < -180) {
            double mod = yaw % 180;
            if (mod > 0) {
                yaw = -180 + mod;
            } else if (mod < 0) {
                yaw = 180 + mod;
            }
        }
        return yaw;
    }

    public static double roundYaw(double yaw) {
        if (yaw >= 0 && yaw < 22.5) {
            yaw = 0;
        }
        if (yaw >= 22.5 && yaw < 67.5) {
            yaw = 45;
        }
        if (yaw >= 67.5 && yaw < 112.5) {
            yaw = 90;
        }
        if (yaw >= 112.5 && yaw < 157.5) {
            yaw = 135;
        }
        if (yaw >= 157.5 && yaw <= 180) {
            yaw = 180;
        }

        if (yaw <= 0 && yaw > -22.5) {
            yaw = 0;
        }
        if (yaw <= -22.5 && yaw > -67.5) {
            yaw = -45;
        }
        if (yaw <= -67.5 && yaw > -112.5) {
            yaw = -90;
        }
        if (yaw <= -112.5 && yaw > -157.5) {
            yaw = -135;
        }
        if (yaw <= -157.5 && yaw >= -180) {
            yaw = 180;
        }

        return yaw;
    }
}
