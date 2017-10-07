package pl.grabinski.slayer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.dns.v2.Status;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InstanceFactoryTest {

    public static final String ID = "321";
    public static final String NAME = "SomeName";
    public static final Date CREATED = new Date();
    public static final Server.Status STATUS = Server.Status.ACTIVE;

    @Mock
    private Server server;

    @Test
    public void shouldConvertServerToInstance() throws Exception {
        when(server.getId()).thenReturn(ID);
        when(server.getName()).thenReturn(NAME);
        when(server.getCreated()).thenReturn(CREATED);
        when(server.getStatus()).thenReturn(STATUS);

        Instance instance = new InstanceFactory().fromServer(server);

        assertEquals(ID, instance.getId());
        assertEquals(NAME, instance.getName());
        assertEquals(OffsetDateTime.ofInstant(CREATED.toInstant(), ZoneOffset.UTC), instance.getCreated());
        assertEquals(STATUS.name(), instance.getStatus());
    }

}