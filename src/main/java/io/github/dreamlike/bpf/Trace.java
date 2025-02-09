package io.github.dreamlike.bpf;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

public class Trace {

    private final static boolean isReq;

    private final static List<String> btFiles = List.of(
            "traceBT/trace_async_work.bt",
            "traceBT/trace_complete_cqe.bt",
            "traceBT/trace_cqe_overflow.bt",
            "traceBT/trace_poll_arm.bt",
            "traceBT/trace_submit_sqe.bt"
    );

    private static final ObjectMapper JSON = new ObjectMapper();

    static {
        try {
            Process exec = Runtime.getRuntime().exec("sudo bpftrace -l kprobe:*io_uring_submit_req");
            String result = new String(exec.getInputStream().readAllBytes());
            isReq = !result.isBlank();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSON.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static void startTrace(Consumer<AbstractTraceEvent> event) throws IOException {
        File file = mergeBT();
        Process exec = Runtime.getRuntime().exec("sudo bpftrace " + file.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(exec.getInputStream()))) {
            String line;
            //skip "Attaching 5 probes"
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
               event.accept(JSON.readValue(line, AbstractTraceEvent.class));
            }
        }
    }

    private static File mergeBT() throws IOException {
        var aClass = Trace.class.getClassLoader();
        StringJoiner joiner = new StringJoiner("\n");
        for (String btFile : btFiles) {
            try (InputStream resourceAsStream = aClass.getResourceAsStream(btFile)) {
                String string = new String(resourceAsStream.readAllBytes());
                if (isReq && btFile.contains("trace_submit_sqe")) {
                    string = string.replace("io_uring_submit_sqe", "io_uring_submit_req");
                }
                joiner.add(string);
            }
        }

        File file = File.createTempFile("trace", ".bt");
        Files.write(file.toPath(), joiner.toString().getBytes());
        return file;
    }


}
