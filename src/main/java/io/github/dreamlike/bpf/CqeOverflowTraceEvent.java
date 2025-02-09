package io.github.dreamlike.bpf;

public class CqeOverflowTraceEvent extends AbstractTraceEvent {
    private long userData;

    private int res;

    public long getUserData() {
        return userData;
    }

    public void setUserData(long userData) {
        this.userData = userData;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
