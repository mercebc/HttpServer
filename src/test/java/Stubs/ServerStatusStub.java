package Stubs;

import Core.ServerStatus;

public class ServerStatusStub extends ServerStatus {

    private boolean status;
    private int counter = 0;

    public ServerStatusStub(boolean status) {
        super(status);
    }

    public boolean isOn() {
        if (counter == 0) {
            counter ++;
            status = true;
        } else {
            status = false;
        }
        return status;
    }
}
