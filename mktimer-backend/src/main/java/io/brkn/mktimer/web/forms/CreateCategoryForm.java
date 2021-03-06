package io.brkn.mktimer.web.forms;

public class CreateCategoryForm {
    private String name;

    public CreateCategoryForm() {
    }

    public CreateCategoryForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CreateCategoryForm{" +
                "name='" + name + '\'' +
                '}';
    }
}
