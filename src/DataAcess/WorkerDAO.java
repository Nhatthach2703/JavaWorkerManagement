/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAcess;

import Common.Library;
import Common.Validation;
import Model.History;
import Model.Worker;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 *
 * @author Xuan Vinh
 */
public class WorkerDAO {
    private static WorkerDAO instance = null;
    Library l;
    Validation valid;

    public WorkerDAO() {
        l = new Library();
        valid = new Validation();
    }

    public static WorkerDAO Instance() {
        if (instance == null) {
            synchronized (WorkerDAO.class) {
                if (instance == null) {
                    instance = new WorkerDAO();
                }
            }
        }
        return instance;
    }

    public void addWorker(ArrayList<Worker> workerList) {
        while (true) {
            String id = l.inputString("Enter code: ");
            while (valid.checkIdExist(workerList, id)) {
                System.err.println("Code already existed!!!");
                id = l.inputString("Enter code: ");
            }
            String name = l.inputString("Enter name: ");
            int age = l.getInt("Enter age", 18, 50);
            int salary = inputSalary();
            String workLocation = l.inputString("Enter work location: ");
            if (!valid.checkWorkerExist(workerList, name, age, salary, workLocation)) {
                System.err.println("Duplicate!!!");
            } else {
                workerList.add(new Worker(id, name, age, salary, workLocation));
                System.err.println("Add success!!!");
                return;
            }
        }
    }

    
    public void changeSalary(ArrayList<Worker> workerList, ArrayList<History> historyList, String status) {
        if (workerList.isEmpty()) {
            System.err.println("List empty!!!");
            return;
        }
        String id = l.inputString("Enter code: ");
        Worker worker = getWorkerByCode(workerList, id);
        if (worker == null) {
            System.err.println("The worker is not exist!!!");
        } else {
            int salaryCurrent = worker.getSalary();
            int salaryUpdate = inputSalary();
            if (status.equalsIgnoreCase("DOWN")) {
                while (true) {
                    if (salaryUpdate >= salaryCurrent) {
                        System.err.println("Must be smaller than current salary!!!");
                        salaryUpdate = inputSalary();
                    } else {
                        salaryCurrent -= salaryUpdate;
                        break;
                    }
                }
            } else {
                salaryCurrent += salaryUpdate;
            }
            historyList.add(new History(status, getCurrentDate(), worker.getId(), worker.getName(), worker.getAge(), salaryCurrent, worker.getWorkLocation()));
            worker.setSalary(salaryCurrent);
            System.err.println("Update success!!!");
        }
    }

    public int inputSalary() {
        int salary = l.getIntNoLimit("Enter salary");
        while (salary <= 0) {
            System.out.println("Salary must be greater than 0!!!");
            salary = l.getIntNoLimit("Enter salary");
        }
        return salary;
    }

    public void printListHistory(ArrayList<History> historyList) {
        if (historyList.isEmpty()) {
            System.err.println("List empty!!!");
            return;
        }
        System.out.printf("%-10s%-12s%-10s%-10s%-10s%-20s\n", "Code", "Name", "Age", "Salary", "Status", "Date");
        Collections.sort(historyList);
        for (History history : historyList) {
            printHistory(history);
        }
    }

    public Worker getWorkerByCode(ArrayList<Worker> workerList, String id) {
        for (Worker worker : workerList) {
            if (id.equalsIgnoreCase(worker.getId())) {
                return worker;
            }
        }
        return null;
    }

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    public void printHistory(History history) {
        System.out.printf("%-10s%-12s%-10d%-10d%-10s%-20s\n", history.getId(), history.getName(), history.getAge(), history.getSalary(), history.getStatus(), history.getDate());
    }
}
