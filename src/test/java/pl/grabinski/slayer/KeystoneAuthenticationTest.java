package pl.grabinski.slayer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openstack4j.api.OSClient;
import org.openstack4j.openstack.OSFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KeystoneAuthenticationTest {

    @Autowired
    private OpenStackCredentials openStackCredentials;

    @Test
    public void shouldAuthenticateInOpenStack() {
        OSClient.OSClientV2 os = OSFactory.builderV2()
                .endpoint(openStackCredentials.getAuthUrl())
                .credentials(openStackCredentials.getUsername(), openStackCredentials.getPassword())
                .tenantName(openStackCredentials.getTenantName())
                .authenticate();
        Assert.assertEquals("V2", os.getAccess().getVersion().name());
    }
}
