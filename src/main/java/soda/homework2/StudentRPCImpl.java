package soda.homework2;

import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;

public class StudentRPCImpl implements StudentInterface {
    @Override
    public String findName(String studentName) {
        if (studentName == null || "".equals(studentName)) {
            return null;
        }
        if ("20211234567890".equals(studentName)) {
            return "Soda";
        } else {
            return null;
        }
    }

    @Override
    public long getProtocolVersion(String s, long l) {
        return StudentInterface.versionID;
    }

    @Override
    public ProtocolSignature getProtocolSignature(String s, long l, int i) {
        return null;
    }
}
