package org.aritra.restful.Messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.aritra.restful.Messenger.database.DatabaseClass;
import org.aritra.restful.Messenger.model.Message;
import org.aritra.restful.Messenger.model.Profile;

public class ProfileService {
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	public ProfileService()
	{
		profiles.put("aritra", new Profile(1L,"aritra","Aritra","De"));
	}
	public List<Profile> getAllProfiles()
	{
		return new ArrayList<Profile>(profiles.values());
	}
	public Profile getProfile(String profileName)
	{
		return profiles.get(profileName);
	}
	public Profile addProfile(Profile profile)
	{
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName() ,profile);
		return profile;
	}
	public Profile updateProfile(Profile profile)
	{
		if(profile.getProfileName().isEmpty())
			return null;
		else
		{
			profiles.put(profile.getProfileName(),profile);
		}
		return profile;
	}
	public Profile removeProfile(String profileName )
	{
		return profiles.remove(profileName);
	}

}
