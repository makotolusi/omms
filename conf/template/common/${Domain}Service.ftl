package ${basePackage}.service;

import m.w.core.service.WxIdService;

import ${basePackage}.domain.${domainName};

import org.nutz.ioc.loader.annotation.IocBean;

/**
 * ${chineseName}
 *
 */
@IocBean(fields="dao")
public class ${domainName}Service extends WxIdService<${domainName}>{

}
