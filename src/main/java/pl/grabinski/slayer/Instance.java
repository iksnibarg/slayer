package pl.grabinski.slayer;

import java.time.OffsetDateTime;

public class Instance {
    private String id;
    private String name;
    private OffsetDateTime created;
    private String status;

    public Instance(String id, String name, OffsetDateTime created, String status) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
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
