package Core;

public class ServerStatus {
    private boolean status;

    public ServerStatus(boolean status){
        this.status = status;
    }

    public boolean isOn() {
        return status;
    }

}
