
package com.journaldev.okhttp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Process {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("version")
    @Expose
    private Integer version;
    @SerializedName("resource")
    @Expose
    private String resource;
    @SerializedName("deploymentId")
    @Expose
    private String deploymentId;
    @SerializedName("diagram")
    @Expose
    private Object diagram;
    @SerializedName("suspended")
    @Expose
    private Boolean suspended;
    @SerializedName("tenantId")
    @Expose
    private Object tenantId;
    @SerializedName("versionTag")
    @Expose
    private Object versionTag;
    @SerializedName("historyTimeToLive")
    @Expose
    private Object historyTimeToLive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public void setDescription(Object description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public Object getDiagram() {
        return diagram;
    }

    public void setDiagram(Object diagram) {
        this.diagram = diagram;
    }

    public Boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }

    public Object getTenantId() {
        return tenantId;
    }

    public void setTenantId(Object tenantId) {
        this.tenantId = tenantId;
    }

    public Object getVersionTag() {
        return versionTag;
    }

    public void setVersionTag(Object versionTag) {
        this.versionTag = versionTag;
    }

    public Object getHistoryTimeToLive() {
        return historyTimeToLive;
    }

    public void setHistoryTimeToLive(Object historyTimeToLive) {
        this.historyTimeToLive = historyTimeToLive;
    }

}