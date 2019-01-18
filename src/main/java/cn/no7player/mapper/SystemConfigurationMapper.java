package cn.no7player.mapper;


import java.util.List;

import cn.no7player.model.SystemConfiguration;


/**
 * 
 * <br>
 * <b>功能：</b>SystemConfigurationDao<br>
 */
public interface SystemConfigurationMapper {

	List<SystemConfiguration> listPageConfig(SystemConfiguration configuration);


	
	
}
