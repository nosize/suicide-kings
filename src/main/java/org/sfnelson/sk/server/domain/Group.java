package org.sfnelson.sk.server.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

@Entity(name="groups")
@NamedQueries({
    @NamedQuery(name="allGroups", query="select g from org.sfnelson.sk.server.domain.Group g"),
    @NamedQuery(name="countGroups", query="select count(g) from org.sfnelson.sk.server.domain.Group g")
})
public class Group {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;

    @Basic(fetch=FetchType.EAGER)
    @Embedded
    private Server server;

    private String name;

    @Basic(fetch=FetchType.EAGER)
    private Set<String> owners;

    private Set<Long> characterIds;

    public Group() {
        this.characterIds = new HashSet<Long>();
        this.owners = new HashSet<String>();
    }

    public Group(Server server, String name) {
        this();
        this.server = server;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getCharacterIds() {
        return characterIds;
    }

    public void addCharacter(Long characterId) {
        this.characterIds.add(characterId);
    }

    public Set<String> getOwners() {
        return owners;
    }

    public void addOwner(String owner) {
        this.owners.add(owner);
    }

    public void removeOwner(String owner) {
        this.owners.remove(owner);
    }

    @Override
    public String toString() {
        return id + ": " + name + " (" + server + ")";
    }
}