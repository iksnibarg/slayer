package pl.grabinski.slayer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openstack4j.model.compute.Image;
import org.openstack4j.model.compute.Server;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InstanceFactoryTest {

    private static final String ID = "321";
    private static final String NAME = "SomeName";
    private static final String IMAGE_NAME = "Cirros";
    private static final Date CREATED = new Date();
    private static final Server.Status STATUS = Server.Status.ACTIVE;

    @Mock
    private Server server;
    @Mock
    private Image image;

    @Test
    public void shouldConvertServerToInstance() throws Exception {
        when(server.getId()).thenReturn(ID);
        when(server.getName()).thenReturn(NAME);
        when(server.getImage()).thenReturn(image);
        when(image.getName()).thenReturn(IMAGE_NAME);
        when(server.getCreated()).thenReturn(CREATED);
        when(server.getStatus()).thenReturn(STATUS);

        Instance instance = new InstanceFactory().fromServer(server);

        assertEquals(ID, instance.getId());
        assertEquals(NAME, instance.getName());
        assertEquals(IMAGE_NAME, instance.getImageName());
        assertEquals(OffsetDateTime.ofInstant(CREATED.toInstant(), ZoneOffset.UTC), instance.getCreated());
        assertEquals(STATUS.name(), instance.getStatus());
    }

}