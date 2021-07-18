package soda.homework.runs;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TrafficBean implements Writable {
    private long up;
    private long down;
    private long total;
    transient private String phoneNo;

    public TrafficBean() {
    }

    public TrafficBean(String line) {
        PlainRecord plainRecord = new PlainRecord(line);
        this.up = plainRecord.getUpTraffic();
        this.down = plainRecord.getDownTraffic();
        this.total = this.up + this.down;
        this.phoneNo = plainRecord.getPhoneNo();
    }

    public TrafficBean(long up, long down, long total) {
        this.up = up;
        this.down = down;
        this.total = total;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(up);
        dataOutput.writeLong(down);
        dataOutput.writeLong(total);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.setUp(dataInput.readLong());
        this.setDown(dataInput.readLong());
        this.setTotal(dataInput.readLong());
    }

    @Override
    public String toString() {
        return up + "\t" + down + "\t" + total;
    }

    public long getUp() {
        return up;
    }

    public void setUp(long up) {
        this.up = up;
    }

    public long getDown() {
        return down;
    }

    public void setDown(long down) {
        this.down = down;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    private class PlainRecord {
        private String ts;
        private String phoneNo;
        private String baseMAC;
        private String ip;
        private String hostname;
        private String webType;
        private int dataPacket;
        private int revPacket;
        private long upTraffic;
        private long downTraffic;
        private int respCode;

        public PlainRecord(String line) {
            String[] tokens = line.split("\\t");
            ts = tokens[0];
            phoneNo = tokens[1].trim();
            baseMAC = tokens[2];
            ip = tokens[3];
            hostname = tokens[4];
            webType = tokens[5];
            dataPacket = tokens[6].equals("") ? 0 : Integer.parseInt(tokens[6]);
            revPacket = Integer.parseInt(tokens[7]);
            upTraffic = Long.valueOf(tokens[8]);
            downTraffic = Long.valueOf(tokens[9]);
            respCode = Integer.valueOf(tokens[10]);
        }


        public String getTs() {
            return ts;
        }

        public void setTs(String ts) {
            this.ts = ts;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getBaseMAC() {
            return baseMAC;
        }

        public void setBaseMAC(String baseMAC) {
            this.baseMAC = baseMAC;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        public int getDataPacket() {
            return dataPacket;
        }

        public void setDataPacket(int dataPacket) {
            this.dataPacket = dataPacket;
        }

        public int getRevPacket() {
            return revPacket;
        }

        public void setRevPacket(int revPacket) {
            this.revPacket = revPacket;
        }

        public long getUpTraffic() {
            return upTraffic;
        }

        public void setUpTraffic(long upTraffic) {
            this.upTraffic = upTraffic;
        }

        public long getDownTraffic() {
            return downTraffic;
        }

        public void setDownTraffic(long downTraffic) {
            this.downTraffic = downTraffic;
        }

        public int getRespCode() {
            return respCode;
        }

        public void setRespCode(int respCode) {
            this.respCode = respCode;
        }

        public String getWebType() {
            return webType;
        }

        public void setWebType(String webType) {
            this.webType = webType;
        }
    }

}


