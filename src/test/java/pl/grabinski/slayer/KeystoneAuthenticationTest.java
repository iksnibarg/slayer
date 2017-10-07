package pl.grabinski.slayer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openstack4j.api.OSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KeystoneAuthenticationTest {

    @Autowired
    private OpenStackClientFactory openStackClientFactory;

    @Test
    public void shouldAuthenticateInOpenStack() {
        OSClient.OSClientV2 os = openStackClientFactory.getOsClientV2();
        assertEquals("V2", os.getAccess().getVersion().name());
    }
}
