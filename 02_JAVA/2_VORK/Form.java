public class Form {
    protected static int count;
    protected int id;
    protected String code_in;
    protected String name;
    static {
        count = 0;
    }


     public Form(String code_in, String name) {
         this.id = setId();
        this.code_in = code_in;
        this.name = name;
    }


//    public Form(){
//        this.code_in = "";
//        this.name = "";
//    }
    private int setId(){
        return id = ++count;
    }

    public int getId() {
        return id;
    }

    public String getCode_in() {
        return code_in;
    }
    public String getName() {
        return name;
    }
    public void setCode_in(String code_in) {
        this.code_in = code_in;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getInfo(){
        return String.format("id: %s, Name: %s, Code: %s",this.getId(), this.name, this.code_in);
    }

}
