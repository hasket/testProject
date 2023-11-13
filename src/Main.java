import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Organisation organisation = new Organisation();

        System.out.println("Список всех сотрудников:");
        // замените создание анонимного класса на лямбда-функцию
        EmployeeFilter organizationPrint = (Employee e) -> true;

        organisation.printEmployees(organizationPrint);

        EmployeeFilter organisationEmpl30 = (Employee e) -> e.age > 30;
        EmployeeFilter organisationEmpl50 = (Employee e) -> e.salary >= 50;
        EmployeeFilter organisationString = (Employee e) -> e.name.startsWith("А");


        System.out.println("Список сотрудников старше 30:");
        organisation.printEmployees(organisationEmpl30);

        System.out.println("Список сотрудников с зарплатой 50 и выше:");
        // ваш код здесь
        organisation.printEmployees(organisationEmpl50);

        System.out.println("Список сотрудников с именем начинающимся на \"А\":");
        // ваш код здесь
        organisation.printEmployees(organisationString);
    }
}

@FunctionalInterface
interface EmployeeFilter {
    boolean accept(Employee e);
}

class Employee {
    String name;
    int age;
    int salary;

    Employee(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + ", возраст " + age + ", зарплата " + salary;
    }
}

class Organisation {
    private ArrayList<Employee> employeeList;

    Organisation() {
        employeeList = new ArrayList<>();
        employeeList.add( new Employee("Алина", 20, 33));
        employeeList.add( new Employee("Иван", 41, 58));
        employeeList.add( new Employee("Ольга", 30, 41));
        employeeList.add( new Employee("Дмитрий", 23, 38));
        employeeList.add( new Employee("Анастасия", 38, 50));
        employeeList.add( new Employee("Максим", 31, 30));
        employeeList.add( new Employee("Антон", 23, 33));
    }

    void printEmployees(EmployeeFilter employeeFilter) {
        for (Employee e: employeeList) {
            if (employeeFilter.accept(e)) {
                System.out.println(e);
            }
        }
        System.out.println(); // печатаем пустую строку для улучшения читаемости
    }
}