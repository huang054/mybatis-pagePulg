package cn.no7player.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.no7player.mapper.SystemConfigurationMapper;
import cn.no7player.model.SystemConfiguration;



/**
 * 
 * <br>
 * <b>功能：</b>SystemConfigurationService<br>
 */
@Service("systemConfigurationService")
public class SystemConfigurationServiceImpl  implements SystemConfigurationService {

	@Autowired
	private SystemConfigurationMapper dao;



	public List<SystemConfiguration> listPageConfig(SystemConfiguration configuration) {
		return dao.listPageConfig(configuration);
	}

	

}
