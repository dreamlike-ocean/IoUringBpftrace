package io.github.dreamlike;

import io.github.dreamlike.bpf.AbstractTraceEvent;
import io.github.dreamlike.bpf.Trace;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Current pid:" + ProcessHandle.current().pid());
        Trace.startTrace(Main::recordEvent);
    }

    private static void recordEvent(AbstractTraceEvent event){
        System.out.println(event.getNsecs() + ":" + event.getEvent() + ":" + event.getClass());
    }
}