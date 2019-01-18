package cn.no7player.service;

import java.util.List;

import cn.no7player.model.SystemConfiguration;



/**
 * 
 * <br>
 * <b>功能：</b>SystemConfigurationService<br>
 */
public interface SystemConfigurationService{

	List<SystemConfiguration> listPageConfig(SystemConfiguration configuration);


}
