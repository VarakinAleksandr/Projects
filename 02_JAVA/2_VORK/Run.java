import java.util.*;

public class Run {

    List<Form> forms = new ArrayList<>();
    public Run() {
        for (int i = 0; i < 10; i++) {
            String name = "name" + i;
            String code = "code" + i;


            forms.add(new Form(code,name));

        }
        forms.remove(4);
        for (int i = 0; i < forms.size(); i++) {
            System.out.println(forms.get(i).getInfo());
        }

    }


    public List<Form> getForms() {
        return forms;
    }
}
