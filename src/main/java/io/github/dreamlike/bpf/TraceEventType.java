package io.github.dreamlike.bpf;

public enum TraceEventType {
    ASYNC_WORK,
    COMPLETE_CQE,
    CQE_OVERFLOW,
    POLL_ARM,
    SUBMIT_SQE
}
