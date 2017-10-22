package pl.grabinski.slayer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.image.Image;
import pl.grabinski.slayer.openstack.OpenStackComputeService;
import pl.grabinski.slayer.openstack.OpenStackImageService;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InstanceFactoryTest {

    private static final String ID = "321";
    private static final String NAME = "SomeName";
    private static final String IMAGE_ID = "777";
    private static final String IMAGE_NAME = "Cirros";
    private static final Date CREATED = new Date();
    private static final Server.Status STATUS = Server.Status.ACTIVE;
    private static final String FLAVOR_ID = "1";
    private static final String FLAVOR_NAME = "m1.tiny";
    private static final int FLAVOR_VCPUS = 2;
    private static final int FLAVOR_RAM = 512;
    private static final int FLAVOR_SWAP = 1024;
    private static final int FLAVOR_DISK = 4;

    @Mock
    private OpenStackComputeService openStackComputeService;
    @Mock
    private OpenStackImageService openStackImageService;
    @Mock
    private Server server;
    @Mock
    private Image image;
    @Mock
    private Flavor flavor;

    @Test
    public void shouldConvertServerToInstance() throws Exception {
        when(openStackComputeService.getFlavor(FLAVOR_ID)).thenReturn(flavor);
        when(openStackImageService.getImage(IMAGE_ID)).thenReturn(image);

        when(server.getId()).thenReturn(ID);
        when(server.getName()).thenReturn(NAME);
        when(server.getImageId()).thenReturn(IMAGE_ID);
        when(image.getName()).thenReturn(IMAGE_NAME);
        when(server.getCreated()).thenReturn(CREATED);
        when(server.getStatus()).thenReturn(STATUS);
        when(server.getFlavorId()).thenReturn(FLAVOR_ID);
        when(flavor.getId()).thenReturn(FLAVOR_ID);
        when(flavor.getName()).thenReturn(FLAVOR_NAME);
        when(flavor.getVcpus()).thenReturn(FLAVOR_VCPUS);
        when(flavor.getRam()).thenReturn(FLAVOR_RAM);
        when(flavor.getSwap()).thenReturn(FLAVOR_SWAP);
        when(flavor.getDisk()).thenReturn(FLAVOR_DISK);

        Instance instance = new InstanceFactory(openStackComputeService, openStackImageService).fromServer(server);

        assertEquals(ID, instance.getId());
        assertEquals(NAME, instance.getName());
        assertEquals(IMAGE_NAME, instance.getImageName());
        assertEquals(OffsetDateTime.ofInstant(CREATED.toInstant(), ZoneOffset.UTC), instance.getCreated());
        assertEquals(STATUS.name(), instance.getStatus());
        assertEquals(FLAVOR_ID, instance.getFlavor().getId());
        assertEquals(FLAVOR_NAME, instance.getFlavor().getName());
        assertEquals(FLAVOR_VCPUS, instance.getFlavor().getVcpus());
        assertEquals(FLAVOR_RAM, instance.getFlavor().getRam());
        assertEquals(FLAVOR_SWAP, instance.getFlavor().getSwap());
        assertEquals(FLAVOR_DISK, instance.getFlavor().getDisk());
    }

}