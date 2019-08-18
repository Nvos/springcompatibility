package czort.view;

import java.time.LocalDate;

public class Model {
    public String name;
    public Integer value1;
    public Long value2;
    public LocalDate date;

    public void setName(String name) {
        this.name = name;
    }

    public void setValue1(Integer value1) {
        this.value1 = value1;
    }

    public void setValue2(Long value2) {
        this.value2 = value2;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public Integer getValue1() {
        return value1;
    }

    public Long getValue2() {
        return value2;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                ", value1=" + value1 +
                ", value2=" + value2 +
                ", date=" + date +
                '}';
    }
}
