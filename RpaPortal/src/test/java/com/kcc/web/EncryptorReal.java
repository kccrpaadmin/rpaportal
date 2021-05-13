package com.kcc.web;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptorReal {
private static final Logger logger = LoggerFactory.getLogger(EncryptorReal.class);
	
	public static void main(String[] args) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		EnvironmentPBEConfig config = new EnvironmentPBEConfig();
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setPassword("kccenc");
		encryptor.setConfig(config);
		
		// 암호화
        String driverDB1 = encryptor.encrypt("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String urlDB1 = encryptor.encrypt("jdbc:sqlserver://70.71.14.122:1433;DatabaseName=RPADB;loginTimeout=90;queryTimeout=-1");
        String usernameDB1 = encryptor.encrypt("kccenc");
        String passwordDB1 = encryptor.encrypt("const!2pri");
        
        // 복호화
        String decDriverDB1 = encryptor.decrypt(driverDB1);
        String decUrlDB1 = encryptor.decrypt(urlDB1);
        String decUsernameDB1 = encryptor.decrypt(usernameDB1);
        String decPasswordDB1 = encryptor.decrypt(passwordDB1);
        
        logger.info(driverDB1);
        logger.info(urlDB1);
        logger.info(usernameDB1);
        logger.info(passwordDB1);
        
        logger.info(decDriverDB1);
        logger.info(decUrlDB1);
        logger.info(decUsernameDB1);
        logger.info(decPasswordDB1);
	}
}
