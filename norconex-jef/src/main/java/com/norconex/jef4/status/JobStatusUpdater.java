package com.norconex.jef4.status;

import java.util.List;

import com.norconex.commons.lang.map.IMapChangeListener;
import com.norconex.commons.lang.map.MapChangeEvent;
import com.norconex.commons.lang.map.Properties;

public abstract class JobStatusUpdater {

    private final MutableJobStatus status;
    
    public JobStatusUpdater(final MutableJobStatus status) {
        this.status = status;
        status.getProperties().addMapChangeListener(
                new IMapChangeListener<String, List<String>>() {
            @Override
            public void mapChanged(MapChangeEvent<String, List<String>> event) {
                statusUpdated(status);
            }
        });
    }

    public String getJobId() {
        return status.getJobId();
    }
    
    public Properties getProperties() {
        return status.getProperties();
    }
    public double getProgress() {
        return status.getProgress();
    }
    public void setProgress(double progress) {
        status.setProgress(progress);
        statusUpdated(status);
    }
    public void incrementProgress(double increment) {
        status.setProgress(status.getProgress() + increment);
        statusUpdated(status);
    }
    public long getDuration() {
        return status.getDuration().getDuration();
    }
    public void setNote(String note) {
        status.setNote(note);
        statusUpdated(status);
    }
    
    protected abstract void statusUpdated(MutableJobStatus status);
}
