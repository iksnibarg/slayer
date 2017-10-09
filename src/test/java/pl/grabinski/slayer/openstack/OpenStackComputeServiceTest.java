package pl.grabinski.slayer.openstack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openstack4j.api.OSClient;
import org.openstack4j.api.compute.ComputeService;
import org.openstack4j.api.compute.ServerService;
import org.openstack4j.model.compute.Server;
import pl.grabinski.slayer.openstack.OpenStackClientFactory;
import pl.grabinski.slayer.openstack.OpenStackComputeService;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OpenStackComputeServiceTest {

    @Mock
    private OpenStackClientFactory openStackClientFactory;

    @Mock
    private OSClient.OSClientV2 osClientV2;
    @Mock
    private ComputeService computeService;
    @Mock
    private ServerService serverService;
    @Mock
    private Server server;


    @Test
    public void shouldRetrieveListOfServers() throws Exception {
        when(openStackClientFactory.getOsClientV2()).thenReturn(osClientV2);
        when(osClientV2.compute()).thenReturn(computeService);
        when(computeService.servers()).thenReturn(serverService);
        doReturn(Collections.singletonList(server)).when(serverService).list();

        List<Server> servers = new OpenStackComputeService(openStackClientFactory).getServers();

        assertEquals(Collections.singletonList(server), servers);
    }

}