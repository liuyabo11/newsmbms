package cn.pb.smbms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.pb.smbms.dao.ProviderMapper;
import cn.pb.smbms.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {
	@Resource
	private ProviderMapper providerMapper;
}
