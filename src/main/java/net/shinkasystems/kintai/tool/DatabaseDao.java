package net.shinkasystems.kintai.tool;

import net.shinkasystems.kintai.KintaiDB;

import org.seasar.doma.Dao;
import org.seasar.doma.Script;

@Dao(config = KintaiDB.class)
public interface DatabaseDao {

	@Script
	void user();
	
	@Script
	void notification();
}
