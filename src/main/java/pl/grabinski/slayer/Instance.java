package pl.grabinski.slayer;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class Instance {

    @Id
    private String id;
    private String name;
    private String imageName;
    private OffsetDateTime created;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
