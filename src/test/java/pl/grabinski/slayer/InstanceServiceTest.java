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
    private InstanceRepository instanceRepository;

    @Mock
    private Instance instance;


    @Test
    public void findAll() throws Exception {
        when(instanceRepository.findAll()).thenReturn(Collections.singletonList(instance));
        InstanceService instanceService = new InstanceService(instanceRepository);
        assertEquals(Collections.singletonList(instance), instanceService.findAll());
    }

}