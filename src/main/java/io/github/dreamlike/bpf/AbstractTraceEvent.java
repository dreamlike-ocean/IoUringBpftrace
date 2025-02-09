package io.github.dreamlike.bpf;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "event",
        visible = true,
        defaultImpl = KnowTraceEvent.class
)
@JsonSubTypes(
        value = {
                @JsonSubTypes.Type(value = SubmitSqeTraceEvent.class, name = "SUBMIT_SQE"),
                @JsonSubTypes.Type(value = CompleteCqeTraceEvent.class, name = "COMPLETE_CQE"),
                @JsonSubTypes.Type(value = CqeOverflowTraceEvent.class, name = "CQE_OVERFLOW"),
                @JsonSubTypes.Type(value = PollArmTraceEvent.class, name = "POLL_ARM"),
                @JsonSubTypes.Type(value = AsyncWorkTraceEvent.class, name = "ASYNC_WORK")
        }
)
public abstract class AbstractTraceEvent {

    private TraceEventType event;

    private long nsecs;


    public TraceEventType getEvent() {
        return event;
    }

    public void setEvent(TraceEventType event) {
        this.event = event;
    }

    public long getNsecs() {
        return nsecs;
    }

    public void setNsecs(long nsecs) {
        this.nsecs = nsecs;
    }
}
