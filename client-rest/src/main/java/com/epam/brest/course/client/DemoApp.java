package com.epam.brest.course.client;

import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.Scanner;

/**
 * REST client console application demo.
 */
public class DemoApp {

    /**
     * DepartmentService object for rest client.
     */
    private DepartmentService departmentService;

    /**
     * Scanner for rest client.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructor with a parameter.
     * @param deptService department service.
     */
    public DemoApp(DepartmentService deptService) {
        this.departmentService = deptService;
    }

    /**
     * Main method of application.
     * @param args arguments.
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-context.xml");
        DepartmentService departmentService =
                context.getBean(DepartmentService.class);
        DemoApp demoApp = new DemoApp(departmentService);
        demoApp.menu();
    }

    /**
     * Menu for rest client.
     */
    private void menu() {
        System.out.println("=====================================");
        System.out.println("|        MENU SELECTION DEMO         |");
        System.out.println("=====================================");
        System.out.println("|Options:                            |");
        System.out.println("|    1.Get all departments           |");
        System.out.println("|    2.Get departments by id         |");
        System.out.println("|    3.Add departments               |");
        System.out.println("|    4.Exit                          |");
        System.out.println("=====================================");

        int swValue = 0;
        while (swValue != 4) {
            System.out.print("Select option: ");
            if(scanner.hasNextInt()) {
                swValue = scanner.nextInt();
                try {
                    checkValue(swValue);
                } catch (ServerDataAccessException sda) {
                    System.out.println("RESPONSE: "
                            + sda.getMessage());
                }
            } else {
                System.out.println("Bad value: " + scanner.next());
            }
        }
    }

    /**
     * Checking switch value.
     * @param item chosen item.
     */
    private void checkValue(int item) {
        switch (item) {
            case 1:
                getAllDepartments();
                break;
            case 2:
                getDepartmentById();
                break;
            case 3:
                addDepartment();
                break;
            case 4:
                System.out.println("Good bye!");
                break;
            default:
                System.out.println("Invalid selection.");
        }
    }

    /**
     * Getting all departments.
     */
    private void getAllDepartments() {
        Collection<DepartmentDto> departments =
                departmentService.getDepartmentsDto();
        System.out.println("departments: " + departments);
    }

    /**
     * Getting department by its id.
     */
    private void getDepartmentById() {
        System.out.println("Enter department id: ");
        int id;
        if(scanner.hasNextInt() && (id = scanner.nextInt()) > 0) {
            Department department = departmentService.getDepartmentById(id);
            System.out.println("department: " + department);
        } else {
            System.out.println("Bad value: " + scanner.next());
        }
    }

    /**
     * Adding department.
     */
    private void addDepartment() {
        System.out.println("Enter department name: ");
        String name = scanner.next();

        System.out.println("Enter department description: ");
        String description = scanner.next();

        Department department = new Department(name, description);
        department = departmentService.addDepartment(department);
        System.out.println("added department: " + department);
    }
}
