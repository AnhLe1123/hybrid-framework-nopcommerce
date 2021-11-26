package envConfig;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "classpath:${env}.properties" })
public interface Environment extends Config{
	@Key("app.userUrl")
	String userUrl();

	@Key("app.adminUrl")
	String adminUrl();
}
