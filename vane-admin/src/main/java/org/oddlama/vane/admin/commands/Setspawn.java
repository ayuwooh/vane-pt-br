package org.oddlama.vane.admin.commands;

import org.bukkit.entity.Player;

import org.oddlama.vane.admin.Admin;
import org.oddlama.vane.util.LazyLocation;
import org.oddlama.vane.annotation.command.Name;
import org.oddlama.vane.core.command.Command;
import org.oddlama.vane.core.module.Context;

@Name("setspawn")
public class Setspawn extends Command<Admin> {
	public Setspawn(Context<Admin> context) {
		super(context);

		// Add help
		params().fixed("help").ignore_case().exec(this::print_help);
		// Command parameters
		params().exec_player(this::set_spawn);
	}

	private void set_spawn(Player player) {
		final var loc = player.getLocation();
		player.getWorld().setSpawnLocation(loc);

		// Save location in storage
		get_module().storage_spawn_location = new LazyLocation(loc.clone());
		save_persistent_storage();

		player.sendMessage("§aSpawn §7set!");
	}
}
