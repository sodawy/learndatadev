package soda.homework2;

import org.apache.hadoop.ipc.VersionedProtocol;

public interface StudentInterface extends VersionedProtocol {
    long versionID = 1L;

    String findName(String studentName);

}
