package soda.homework2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCClient {
    public static void main(String[] args) throws IOException {
        String hostname = "127.0.0.1";
        int port = 12345;
        StudentInterface clientProxy = RPC.getProxy(StudentInterface.class,
                1L,
                new InetSocketAddress(hostname, port),
                new Configuration());

        System.out.println(clientProxy.findName("haha"));
        System.out.println(clientProxy.findName("20211234567890"));

    }
}
