package io.github.dreamlike.bpf;

public class PollArmTraceEvent extends AbstractTraceEvent {
    private String ops;
    private int mask;
    private int events;

    public String getOps() {
        return ops;
    }

    public void setOps(String ops) {
        this.ops = ops;
    }

    public int getMask() {
        return mask;
    }

    public void setMask(int mask) {
        this.mask = mask;
    }

    public int getEvents() {
        return events;
    }

    public void setEvents(int events) {
        this.events = events;
    }
}
