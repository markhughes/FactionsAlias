package com.markehme.factionsalias.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.massivecraft.factions.Factions;
import com.massivecraft.massivecore.cmd.MassiveCommand;

/**
 * Factions 2.x Support
 *     
 * @author MarkehMe<mark@markeh.me>
 *
 */
public class Factions2X implements SupportBase {
	List<Factions2XCommandSkeleton> commands = new ArrayList<Factions2XCommandSkeleton>();
	
	public Factions2X(HashMap<String, String> hashMap) {
		// Currently no settings are used. 
	}

	public void add(List<String> aliases,
			Boolean requiresFactionsEnabled,
			Boolean requiresIsPlayer,
			Boolean requiresInFaction,
			Boolean requiresIsLeader,
			String permission,
			String permissionDeniedMessage,
			String desc,
			String executingCommand) {
		
		Factions2XCommandSkeleton command = new Factions2XCommandSkeleton(
			aliases,
			requiresFactionsEnabled,
			requiresIsPlayer,
			requiresInFaction,
			requiresIsLeader,
			permission,
			permissionDeniedMessage,
			desc,
			executingCommand
		);
		
		commands.add(command);
		
		Factions.get().getOuterCmdFactions().addChild(
			(MassiveCommand) command
		);			
	}

	@Override
	public void unregister() {
		for (int i=0; i < commands.size(); i++) {
			Factions.get().getOuterCmdFactions().removeChild(this.commands.get(i));
		}
	}
	
	@Override
	public void finishCall() {
		// Nothing to do 
	}
}
