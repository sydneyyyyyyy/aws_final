package dbsecure;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@Configuration // 내가 설정 클래스입니다
@EnableEncryptableProperties // 현재 정보를 application.properties 파일에서 사용 가능
public class DBConfig {

	@Bean("jasyptEncryptor") // => 메서드 리턴 객체 자동으로 주입
	public StringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(System.getenv("DB_PASSWORD"));
		
		config.setAlgorithm("PBEWithMD5AndDES"); // 알고리즘
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        
        System.out.println("===DBConfig 출력===");
        System.out.println(System.getenv("DB_PASSWORD"));
        System.out.println(encryptor.decrypt("ymbzfCVQFazKftxdX/FR0iqrlpmFnKXPgzmXIRmJxEY="));
        System.out.println(encryptor.decrypt("KW/UXJSHnkdvDMfBVaybhnjGNfZbgdaYjpFV567yMQ/Xscw+bMWEFIvTA/9PhLhvFpc1Mf+O5tMqIbVE9Eao+dZF/t6aJib8vmt3s2vouikHeRO+iJVa1HNgxPE0egwdegavCGO9MRU="));
        System.out.println(encryptor.decrypt("J3qO2+L4Tre3ynPcoRK5yBpURjiZ95hk"));
        System.out.println(encryptor.decrypt("H3WqOc5x3b58L4vDnk3TGINOueYF4x2X"));
        
        return encryptor;
	}
	
}