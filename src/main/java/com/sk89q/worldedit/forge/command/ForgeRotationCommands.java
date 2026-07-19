package com.sk89q.worldedit.forge.command;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.forge.ForgeWorldEdit;
import com.sk89q.worldedit.forge.compat.ModRotationBlockTransformHook;
import com.sk89q.worldedit.forge.compat.rotation.RotationMappings;

/**
 * Forge-specific commands.
 */
public class ForgeRotationCommands {

    private final WorldEdit we;

    public ForgeRotationCommands(WorldEdit we) {
        this.we = we;
    }

    /** Reload rotation mappings from disk. */
    @Command(aliases = { "reloadmappings" }, usage = "", desc = "Reload rotation mappings", min = 0, max = 0)
    @CommandPermissions("worldedit.reload")
    public void reloadMappings(Actor actor, CommandContext args) throws WorldEditException {
        RotationMappings.init(ForgeWorldEdit.inst.getWorkingDir());
        ModRotationBlockTransformHook hook = ForgeWorldEdit.inst.getModRotationHook();
        if (hook != null) {
            hook.clearCache();
        }
        actor.print("Rotation mappings reloaded!");
    }
}
