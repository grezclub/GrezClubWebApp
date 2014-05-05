package com.paloit.dao;

import com.paloit.entities.Educateur;
import com.paloit.entities.Roles;

public interface RoleService {
	
	public void enregistreRole (Educateur educateur);
	
	public void modifieRole (Educateur educateur);
	
	public Roles getById (int id);
	
	public void deleteRole (Roles role);

}
