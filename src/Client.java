class Client {

    private final int id;

    public String name;

    private int zp;

    String dateBirthday;

    int transfer;


    public Client(int id) {
        this.id = id;
        this.name = Auxiliary.returnName(this.id);
        this.zp = Auxiliary.forZp(this.id);
        this.transfer = Auxiliary.forTrans(this.id);
        this.dateBirthday = Auxiliary.forDate(this.id);
    }
}
