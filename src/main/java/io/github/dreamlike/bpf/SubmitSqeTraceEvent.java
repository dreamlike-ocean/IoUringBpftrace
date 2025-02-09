package io.github.dreamlike.bpf;

public class SubmitSqeTraceEvent extends AbstractTraceEvent {
    private long userData;

    private int flags;

    private String ops;

    public long getUserData() {
        return userData;
    }

    public void setUserData(long userData) {
        this.userData = userData;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public String getOps() {
        return ops;
    }

    public void setOps(String ops) {
        this.ops = ops;
    }
}
