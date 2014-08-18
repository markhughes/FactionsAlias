package com.markehme.factionsalias.support;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.massivecraft.factions.cmd.FCommand;
import com.massivecraft.factions.cmd.req.ReqFactionsEnabled;
import com.massivecraft.factions.cmd.req.ReqHasFaction;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;
import com.massivecraft.massivecore.util.Txt;

/**
 * Command Skeleton for Factions 2.x 
 * 
 * @author MarkehMe<mark@markeh.me>
 *
 */
public class Factions2XCommandSkeleton extends FCommand {
	public String exec = "";
	public String permissionRequired = null;
	public String permissionDeniedMsg = "";
	
	public Factions2XCommandSkeleton(
			List<String> aliases,
			boolean requiresFactionsEnabled,
			boolean requiresIsPlayer,
			boolean requiresInFaction,
			String permission,
			String permissionDeniedMessage,
			String desc,
			String executingCommand
	) {
		
		// Move through all the aliases and add them for this command
		for(Object alias : aliases) {
			this.aliases.add((String) alias);
		}		
		
		if(requiresFactionsEnabled) {
			this.addRequirements(ReqFactionsEnabled.get());
		}
		
		if(requiresIsPlayer) {
			this.addRequirements(ReqIsPlayer.get());
		}
		
		if(requiresInFaction) {
			this.addRequirements(ReqHasFaction.get());
		}
		
		this.permissionRequired = permission;
		this.permissionDeniedMsg = permission;
		
		this.setHelp(desc);
		this.setDesc(desc);
		
		exec = executingCommand;
		
		this.errorOnToManyArgs = false;
	}
	
	@Override
	public void perform() {
		if(this.permissionRequired != null) {
			if(me instanceof Player) {
				if(!me.hasPermission(permissionRequired)) {
					msg(permissionDeniedMsg);
					return;
				}
			}
		}
				
		Bukkit.getServer().dispatchCommand(sender, exec + " " + Txt.implode(args, " ").replaceAll("(&([a-f0-9]))", "& $2"));

	}

}
