package sample.auth;


public enum ERRORSTATE {
    FORBIDDEN(403), NOT_FOUND(404), BAD_REQUEST(400);
    private int value;

    private ERRORSTATE(int value) {
        this.value = value;
    }

    public int getCode() {
        return value;
    }
};
