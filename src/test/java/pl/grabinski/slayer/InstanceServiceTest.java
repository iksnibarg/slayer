package pl.grabinski.slayer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openstack4j.api.OSClient;
import org.openstack4j.api.compute.ComputeService;
import org.openstack4j.api.compute.ServerService;
import org.openstack4j.model.compute.Server;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InstanceServiceTest {

    @Mock
    private OpenStackClientFactory openStackClientFactory;
    @Mock
    private InstanceFactory instanceFactory;

    @Mock
    private OSClient.OSClientV2 osClientV2;
    @Mock
    private ComputeService computeService;
    @Mock
    private ServerService serverService;
    @Mock
    private Server server;
    @Mock
    private Instance instance;


    @Test
    public void findAll() throws Exception {
        when(openStackClientFactory.getOsClientV2()).thenReturn(osClientV2);
        when(osClientV2.compute()).thenReturn(computeService);
        when(computeService.servers()).thenReturn(serverService);
        doReturn(Collections.singletonList(server)).when(serverService).list();

        when(instanceFactory.fromServer(server)).thenReturn(instance);

        InstanceService instanceService = new InstanceService(openStackClientFactory, instanceFactory);
        assertEquals(Collections.singletonList(instance), instanceService.findAll());
    }

}