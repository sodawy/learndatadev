package soda.homework2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

public class RPCServer {
    public static void main(String[] args) {
        RPC.Builder builder = new RPC.Builder(new Configuration());
        builder.setBindAddress("127.0.0.1");
        builder.setPort(12345);
        builder.setProtocol(StudentInterface.class);
        builder.setInstance(new StudentRPCImpl());

        try {
            RPC.Server server = builder.build();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
